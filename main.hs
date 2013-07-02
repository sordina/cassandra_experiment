{-# LANGUAGE OverloadedStrings #-}
{-# LANGUAGE NoMonomorphismRestriction #-}

import Web.Scotty
import Text.Groom
import Data.Text.Lazy
import Data.Map (Map)
import Control.Monad.IO.Class
import Network.Wai.Middleware.RequestLogger

import qualified Database.Cassandra.Basic      as C
import qualified Data.ByteString.Lazy.Internal as BSLI

type App   = C.CPool -> ScottyM ()
type Route = C.CPool -> ActionM ()

main :: IO ()
main = localConnection "fookeyspace" >>= scotty 3030 . webStack

-- Web Stuff

webStack :: App
webStack pool = middleware logStdoutDev >> routes pool

routes :: App
routes pool = do
  get  "/:key" (key    pool)
  get  "/"     (list   pool)
  post "/"     (insert pool)

key :: Route
key pool = do
  p   <- read `fmap` param "key"
  foo <- liftQuery pool $ C.get "foo" (k p) resultSet C.ONE
  text $ pack $ groom foo
  where
    k p       = C.packKey $ C.TInt32 p
    resultSet = C.All

list :: Route
list pool = do
  foo <- liftQuery pool (everything "foo")
  text $ pack $ groom foo

insert :: Route
insert pool = do
  k <- read `fmap` param "key"
  v <- param "value"

  let
    ckey = C.packKey $ C.TInt32 k
    cval = BSLI.packChars v
    ccol = [C.packCol (C.TAscii "fooname", cval)]

  liftQuery pool $ C.insert "foo" ckey C.ONE ccol
  redirect "/"


-- DB Stuff

localConnection :: C.KeySpace -> IO C.CPool
localConnection keyspace = C.createCassandraPool [("127.0.0.1", 9160)] 2 2 300 keyspace

liftQuery :: MonadIO m => C.CPool -> C.Cas a -> m a
liftQuery pool = liftIO . C.runCas pool

everything :: C.MonadCassandra m => C.ColumnFamily -> m (Map BSLI.ByteString C.Row)
everything columnFamily = C.getMulti columnFamily range resultSet C.ONE
  where
    resultSet = C.All
    range     = C.KeyRange C.InclusiveRange minKey maxKey maxBound
    minKey    = C.packKey (C.TInt32 minBound)
    maxKey    = C.packKey (C.TInt32 minBound)

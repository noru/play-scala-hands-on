/*
* Sample of using Redis
*
* Wrap up a singleton as a common.cache api, to compare with the Play common.cache api
*
* TODO, remove this after compare
*/

package common.cache

import com.redis._
import com.redis.serialization.Parse

object RedisCache {

    val redisClient = new RedisClient("localhost", 6379) // todo, load it from config

    def apply(key: String, value: Any):Unit = {
        redisClient.set(key, value)
    }
    def apply[T](key:String)(implicit parse: Parse[T]):Option[T] = {
        redisClient.get[T](key)
    }

}

/*
* Sample of using Redis
*
* Wrap up a singleton as a cache api, to compare with the Play cache api
*/

package cache

import com.redis._
import com.redis.serialization.Parse

object RedisCache {

    val redisClient = new RedisClient("localhost", 6379) // todo, load it from config

    def apply(key: String, value: Any):Unit = {
        redisClient.set(key, value);
    }
    def apply[T](key:String)(implicit parse: Parse[T]):Option[T] = {
        redisClient.get[T](key)
    }

}

package cache

import com.redis._

object RedisCache {

    val redisClient = new RedisClient("localhost", 6379) // todo, load it from config

    def apply(key: String, value: Any):Unit = {
        redisClient.set(key, value);
    }
    def apply(key:String):Any = {
        redisClient.get(key)
    }

}

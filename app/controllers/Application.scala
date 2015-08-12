package controllers

import base.BaseController
import cache.RedisCache
import dao.CandidatesDAO
import play.api.mvc._
import javax.inject.Inject
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.cache.Cache
import play.api.Play.current
import scala.concurrent.duration._

class Application @Inject()(CandidatesDao : CandidatesDAO) extends BaseController{

    def index = Action {
        Ok(RedisCache[String]("key").getOrElse("Nothing"))
    }

    def getCache(key: String) = Action {
        Ok(key + " => " + Cache.getAs[String](key).getOrElse("Nothing"))
    }

    def setCache(value : String) = Action {
        Cache.set("key", value, 1.minutes)
        Ok
    }

    def mysql = Action.async {
        CandidatesDao.all().map{case candidates => Ok(candidates.map(_.value).toList.mkString(", "))}
    }

}

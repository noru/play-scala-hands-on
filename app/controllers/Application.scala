package controllers

import common.auth.Account
import play.api.mvc._
import play.api.cache.Cache
import play.api.Play.current
import scala.concurrent.ExecutionContext.Implicits.global
import base.BaseController
import dao.CandidatesDAO
import javax.inject.Inject
import scala.concurrent.Future
import scala.concurrent.duration._

class Application @Inject()(CandidatesDao : CandidatesDAO) extends BaseController {

    def index = StackAction(AuthorityKey -> AuthCheck) { implicit request =>
        Ok("index")
    }

    def getCache(key: String) = Action {
        Ok(key + " => " + Cache.getAs[String](key).getOrElse("Nothing"))
    }

    def setCache(value : String = "key") = Action {
        Cache.set("key", value, 1.minutes)
        Ok
    }

    def mysql = Action.async {
        CandidatesDao.all().map{case candidates => Ok(candidates.map(_.value).toList.mkString(", "))}
    }

    def test = Action {
        Ok(play.api.Play.configuration.getIntList("test.c").get.get(1).toString)
    }



    // TODO Stub!
    // replace it with real authority check
    // and move it to BaseController
    private def AuthCheck(account : Account): Future[Boolean] = {
        Future {true}
    }

}

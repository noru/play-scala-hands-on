package controllers

import base.BaseController
import dao.CandidatesDAO
import play.api.mvc._
import javax.inject.Inject
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class Application @Inject()(CandidatesDao : CandidatesDAO) extends BaseController{
    import cache.RedisCache
    def index = Action {
        Ok(RedisCache("key").toString)
    }

    def mysql = Action.async {
        CandidatesDao.all().map{case candidates => Ok(candidates.map(_.value).toList.mkString(", "))}
    }

}

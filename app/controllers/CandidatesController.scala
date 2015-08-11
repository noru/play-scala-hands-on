package controllers

import javax.inject.Inject

import base.BaseController
import dao.CandidatesDAO
import model.Candidate
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc._

class CandidatesController @Inject()(CandidatesDao : CandidatesDAO) extends BaseController{
    def all = Action.async {
        CandidatesDao.all().map{case candidates => Ok(candidates.map(_.value).toList.mkString(", "))}
    }


    def getById(id:Int) = Action.async {
        CandidatesDao.getById(id).map {
            case candidate :Some[Candidate] => Ok(candidate.get.value)
            case _ => NotFound("Not Found")
        }
    }

    def insert(name:String) = Action.async {

        val candidate = Candidate(None, name)
        CandidatesDao.insert(candidate).map(_ => Ok)
    }

}

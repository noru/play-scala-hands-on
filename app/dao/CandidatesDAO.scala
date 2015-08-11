package dao

import javax.inject.Inject

import model.Candidate
import play.api.db.slick.{HasDatabaseConfigProvider, DatabaseConfigProvider}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.JdbcProfile

import scala.concurrent.Future


class CandidatesDAO @Inject()(protected val dbConfigProvider : DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile]{
    import driver.api._
    
    private val candidates = TableQuery[CandidatesTable]

    def all() = db.run(candidates.result)

    def insert(candidate: Candidate) = db.run(candidates += candidate).map{ _ => ()}

    def getById(id: Int) = db.run(candidates.filter(_.id === id).result.headOption)

}

package dao

import slick.lifted.{Tag}
import model.Candidate
import slick.driver.MySQLDriver.api._


class CandidatesTable(tag: Tag) extends Table[Candidate](tag, "Candidates"){

    def id: Rep[Option[Int]] = column[Int]("ID", O.PrimaryKey)
    def value: Rep[String] = column[String]("VALUE")

    override def * = (id, value) <> (Candidate.tupled, Candidate.unapply _)
}

package common.auth

import scala.concurrent.{ExecutionContext, Future}

object Account {
    def findById (id: String)(implicit ctx : ExecutionContext): Future[Option[Account]] = {
        // TODO: STUB!
        // Replace this by retrieving a real Account
        Future[Option[Account]]{ Option{ Account("anyId") }}
    }
}

case class Account (id: String)
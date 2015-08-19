package common.auth

import controllers.routes
import jp.t2v.lab.play2.auth.AuthConfig
import play.api.Logger
import play.api.mvc.{Result, RequestHeader}
import play.api.mvc.Results

import scala.concurrent.{Future, ExecutionContext}
import scala.reflect._

trait AuthConfigImpl extends AuthConfig with Results{

    type Id = String
    type Authority = User => Future[Boolean]
    type User = Account
    def idTag: ClassTag[Id] = classTag[Id]

    def resolveUser(id: Id)(implicit context: ExecutionContext): Future[Option[Account]] = Account.findById(id)

    def sessionTimeoutInSeconds: Int = 15 * 60

    def authorize(account: Account, role: Authority)(implicit context: ExecutionContext): Future[Boolean] = {
        // TODO: stub! implement real authorization
        Future{true}
    }
    def loginSucceeded(request: RequestHeader)(implicit context: ExecutionContext): Future[Result] = {

        // redirect back to original uri after login
        val uri = request.session.get("access_uri")
        Future.successful(uri match {
            case None => Ok
            case u : Some[String] => Redirect(u.get).withSession(request.session - "access_uri")
        })
    }


    def authenticationFailed(request: RequestHeader)(implicit context: ExecutionContext): Future[Result] = {
        // first time access the resources that requires authorization
        Future.successful(Redirect(routes.Authenticate.login).withSession("access_uri" -> request.uri))
    }

    def authorizationFailed(request: RequestHeader,
                            account: Account,
                            role: Option[Authority])(implicit context: ExecutionContext): Future[Result] =
        throw new Exception("Stub!")

    def logoutSucceeded(request: RequestHeader)(implicit context: ExecutionContext): Future[Result] = throw new Exception("Stub!")

}

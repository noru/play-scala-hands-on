package base;

import common.auth.AuthConfigImpl
import jp.t2v.lab.play2.auth.AuthElement
import play.api.mvc._

class BaseController extends Controller with AuthElement with AuthConfigImpl{


}

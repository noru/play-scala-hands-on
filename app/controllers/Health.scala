package controllers

import play.api.mvc._

/*
* TODO: Stub for Health page
*/
class Health extends Controller{
    def index = Action {
        Ok("seems ok")
    }
    def detail =Action {
        Ok("Indeed I'm very ok")
    }

}

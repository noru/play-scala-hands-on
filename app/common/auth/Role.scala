package common.auth

//TODO, Stub!
abstract class Role (id : String) {

}

case class Administrator(id: String) extends Role(id : String)

case class Normal(id: String) extends Role(id : String)


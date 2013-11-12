import akka.io._
import akka.util.ByteString

sealed trait PostCommand

object Mail extends PostCommand {

  val command = ByteString("MAILIO")

}

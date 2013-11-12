package com.ackma.postoffice

import com.ackma.Stop
import akka.actor._
import akka.io.{ IO, Tcp }
import akka.util.ByteString
import java.net.InetSocketAddress

class PostOffice extends Actor with akka.actor.ActorLogging{

  import Tcp._
  import context.system

  IO(Tcp) ! Bind(self, new InetSocketAddress("localhost", 8089))

  def receive = {
    
    case b @ Bound(localAddress) =>
      log.info("Binding to port")

    case CommandFailed(_: Bind) => 
      log.error("Could not bind to port")
      context stop self

    case c @ Connected(remote,local) =>
      val handler = context.actorOf(Props[PostClerk])
      val connection = sender
      connection ! Register(handler)

    case Stop =>
      context.parent ! Stop
  }
}

class PostClerk extends Actor with akka.actor.ActorLogging{
  import Tcp._
  def receive = {
    case Received(data) =>
      sender ! Write(data)
    case PeerClosed     => 
      context.parent ! Stop
      log.info("peer closed")
  }
}


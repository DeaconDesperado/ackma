package com.ackma

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.Props

import com.ackma.postmaster._
import com.ackma.postoffice._
import com.ackma.message._

sealed trait ServiceCmds
case object Stop extends ServiceCmds

class Service extends Actor {
  
  override def preStart(){
    //val postman = context.actorOf(Props[Postman], "postman");
    //postman ! Deliver(Message("bobblocker@worldnet.att.net","christina@snet.com"))
    val postoffice = context.actorOf(Props[PostOffice],"postoffice") 

  }

  def receive = {
    case Delivering(actorRef:ActorRef,msg:Message) => 
      self ! Stop
    case Stop => context.stop(self)
  } 

}

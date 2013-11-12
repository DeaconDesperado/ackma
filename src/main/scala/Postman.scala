package com.ackma.postmaster 
import akka.actor.{ Actor, ActorRef, FSM }
import akka.actor.Props

import com.ackma.message._

sealed trait State
case object Idle extends State
case object Active extends State

sealed trait MessageActions 
case class Deliver(message: Message) extends MessageActions 
case class Delivering(postman:ActorRef,msg:Message) extends MessageActions 

class Postman extends Actor with FSM[State,InboxData] with akka.actor.ActorLogging{
    
  startWith(Idle, Empty)

  when(Idle) {
    case Event(Deliver(msg), Empty) =>
      log.info(msg.toString)
      goto(Active) replying Delivering(self,msg)
  }

  when(Active) {
    case Event(e,s)=>
      goto(Idle) using Empty
  }

  initialize()

}

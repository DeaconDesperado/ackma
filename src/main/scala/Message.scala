package com.ackma.message

sealed trait InboxData

case class Message(
  from:String, 
  to:String) extends InboxData

case object Empty extends InboxData


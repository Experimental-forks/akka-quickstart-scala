package com.example

import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}
import akka.actor.typed.{ActorSystem, Behavior}


object Main {
  def apply(): Behavior[String] =
    Behaviors.setup(context => new Main(context))
}

class Main(context: ActorContext[String]) extends AbstractBehavior[String](context) {
  override def onMessage(msg: String): Behavior[String] =
    msg match {
      case "start" =>
        val first = context.spawn(StartStopActor1(), "first")
        first ! "stop"
        this
    }
}

object StartStopApp extends App {
  val testSystem = ActorSystem(Main(), "testSystem")
  testSystem ! "start"
}
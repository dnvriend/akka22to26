package com.github.dnvriend

import java.util.UUID

import akka.actor.{ActorLogging, ActorSystem, Props}
import akka.persistence.{PersistentActor, RecoveryCompleted}

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.util.Try

object Util {
  def getProperty(key: String, default: String): String = Try(System.getProperty(key)).toOption.flatMap(Option(_)).getOrElse(default)
}

class Persister(val persistenceId: String, max: Int = Util.getProperty("max", "10").toInt)(implicit ec: ExecutionContext) extends PersistentActor with ActorLogging {
  context.system.scheduler.schedule(0.seconds, 300.millis, self, "FOO")

  var counter = 0

  override val receiveRecover: Receive = {
    case RecoveryCompleted => log.debug("Recovery completed: lastSeqNr: {}", lastSequenceNr)
    case msg: String =>
      log.debug("Recovering with: {}", msg)
      counter += 1
    case unknown => log.debug("Unknown msg: {}", unknown)
  }

  override val receiveCommand: Receive = {
    case _ if counter < max =>
      persist(UUID.randomUUID().toString) { _ => counter += 1; () }
    case _ =>
      log.debug("Stopping...")
      context.stop(self)
      context.system.terminate()
  }

  override def postStop(): Unit = {
    log.debug("Stopped!")
  }
}

object Main extends App {
  implicit val system = ActorSystem()
  implicit val ec = system.dispatcher
  system.actorOf(Props(new Persister("abcdefg")))

  println("This is v2.6.3")
}

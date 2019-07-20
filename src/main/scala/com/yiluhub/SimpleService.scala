package com.yiluhub

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.Await
import scala.util.{Failure, Success}

object SimpleService extends LazyLogging {

  logger.debug("Microservice started.")
  val serviceName = "SimpleService"
  val registryUrl = "http://localhost:8000"

  def register(): Unit = {
    //call registry and tell it who you are
    // http post call to register with serviceName and registryUrl
  }

  def run(): Unit = {
    val host = "0.0.0.0"
    val port = 9000

    implicit val system: ActorSystem = ActorSystem(name = "todoapi")
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    import akka.http.scaladsl.server.Directives._
    import system.dispatcher

    def route = path("service") {
      get {
        complete("Hello, World from micro service")
      }
    }

    val binding = Http().bindAndHandle(route, host, port)
    binding.onComplete {
      case Success(_) => println("Success!")
      case Failure(error) => println(s"Failed: ${error.getMessage}")
    }

    import scala.concurrent.duration._
    Await.result(binding, 3.seconds)
  }
}

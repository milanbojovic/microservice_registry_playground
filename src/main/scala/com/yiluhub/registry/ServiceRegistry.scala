package com.yiluhub.registry

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.Await
import scala.util.{Failure, Success}

object ServiceRegistry extends LazyLogging {

  logger.debug("Registry started.")

  // Please note that this is the simplest possible solution,
  // which means that one micro service serves for one business solution
  // the improvement would be to add List of urls for a needed service e.g. Map[String, List[String]]
  // in this way multiple services could be registered for one business need eg. calculation, creation ...
  var registryMap :Map[String, String] = Map();

  //Function for registering a new Micro service
  def register(serviceName : String, serviceUrl: String): Unit = {
    registryMap += serviceName -> serviceUrl
  }

  //Function for unregistering a Micro service
  def unregister(serviceName : String): Unit = {
    registryMap -= serviceName
  }

  def run(): Unit = {
    val host = "0.0.0.0"
    val port = 8000

    implicit val system: ActorSystem = ActorSystem(name = "todoapi")
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    import akka.http.scaladsl.server.Directives._
    import system.dispatcher

    def route = path("service") {
      get {
        complete("Hello, World from service registry")
      }
    }

    //HTTP HANDLERS NOT IMPLEMENTED,
    // JUST A SKELETHONS FOR REGISTERING AND UNREGISTERING WITH A SERVICE REGISTRY
/*    val routeRegister = path("register") {
      get {
        parameters('serviceName.as[String], 'serviceUrl.as[String]) { (serviceName, serviceUrl) =>
          complete ( "Hello, World from register micro service")
            //call function
            //register("ddd", "")
        }
      }
    }*/
 
  /*  val routeUnRegister = path("unregister") {
      get {
        parameters('serviceName.as[String]) { (serviceName, serviceUrl) =>
          complete (
            complete ( "Hello, World from unregister micro service")
            //call unregister function
            //unregister("ddd", "")
          )
        }
      }
    }
*/
    val binding = Http().bindAndHandle(route, host, port)
    binding.onComplete {
      case Success(_) => println("Success!")
      case Failure(error) => println(s"Failed: ${error.getMessage}")
    }

    import scala.concurrent.duration._
    Await.result(binding, 3.seconds)
  }
}

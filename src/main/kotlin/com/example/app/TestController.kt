package com.example.app

import com.google.api.gax.rpc.StatusCode
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing

class TestController {

  fun initRoute(application: Application) {
    application.routing {
      route("/test") {
        get("/warn") {
          val id = call.request.queryParameters["id"]
          val statusCode = call.request.queryParameters["status"]?.toInt() ?: HttpStatusCode.OK.value
          logger().warn("Warning log message for $id")
          call.respond(HttpStatusCode.fromValue(statusCode))
        }
        get("/error") {
          val id = call.request.queryParameters["id"]
          val statusCode = call.request.queryParameters["status"]?.toInt() ?: HttpStatusCode.OK.value
          logger().error("Error log message for $id")
          call.respond(HttpStatusCode.fromValue(statusCode))
        }
      }
    }
  }
}
package com.example.app

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TestController {

  fun initRoute(application: Application) {
    application.routing {
      route("/test") {
        get("/warn") {
          val id = call.request.queryParameters["id"]
          logRequest(id, true)
          val statusCode = call.request.queryParameters["status"]?.toInt() ?: HttpStatusCode.OK.value
          call.respond(HttpStatusCode.fromValue(statusCode))
        }
        get("/error") {
          val id = call.request.queryParameters["id"]
          logRequest(id, false)
          val statusCode = call.request.queryParameters["status"]?.toInt() ?: HttpStatusCode.OK.value
          call.respond(HttpStatusCode.fromValue(statusCode))
        }
      }
    }
  }

  private suspend fun logRequest(id: String?, warn: Boolean) {
    withContext(Dispatchers.IO) {
      val msg = "Error log message for $id"
      if (warn) {
        logger().warn(msg)
      } else
        logger().error(msg)
    }
  }
}
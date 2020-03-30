package com.example.app

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun Application.main() {
// This adds Date and Server headers to each response, and allows custom additional headers
  install(DefaultHeaders)
  // This uses use the logger to log every call (request/response)
  install(CallLogging)

  TestController().initRoute(this)
}

inline fun <reified T : Any> T.logger(): Logger = LoggerFactory.getLogger(T::class.java)
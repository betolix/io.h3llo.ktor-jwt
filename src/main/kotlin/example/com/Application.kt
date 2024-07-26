package example.com


import example.com.plugins.configureSecurity
import example.com.plugins.configureSerialization
import example.com.repository.UserRepository
import example.com.routing.configureRouting
import example.com.service.JwtService
import example.com.service.UserService
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val userRepository = UserRepository()
    val userService = UserService(userRepository)
    val jwtService = JwtService(this, userService)

    configureSerialization()
    configureSecurity(jwtService)
    configureRouting(userService, jwtService)


}

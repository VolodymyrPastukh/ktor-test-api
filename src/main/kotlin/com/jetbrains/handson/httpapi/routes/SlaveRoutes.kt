package com.jetbrains.handson.httpapi.routes

import com.jetbrains.handson.httpapi.DependencyProvider
import com.jetbrains.handson.httpapi.exceptions.IllegalEnteredDataException
import com.jetbrains.handson.httpapi.factories.LiteratureFactory
import com.jetbrains.handson.httpapi.factories.WorkerFactory
import com.jetbrains.handson.httpapi.models.Literature
import com.jetbrains.handson.httpapi.models.Worker
import com.jetbrains.handson.httpapi.repository.SlaveRepositoryImpl
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.mustache.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*


fun Application.registerSlaveRoutes(){
    routing {
        getSlaves()
        putSlave()
        putForm()
    }
}

private fun Route.getSlaves(){
    get("/{slaves}"){
        val query = call.parameters["slaves"] ?:
            return@get call.respondText("Empty table", status = HttpStatusCode.BadRequest)
            toContent(query)
    }

    static("static"){
        resources("css")
    }
    static("pictures"){
        resources("files")
    }
}

private fun Route.putSlave(){
    post("/{slaves}"){
        val query = call.parameters["slaves"]
        val value = when (query){
            "workers" -> call.receive<Worker>()
            "literatures" -> call.receive<Literature>()
            else -> throw IllegalEnteredDataException()
        }
        val isAdded = repo.putContent(query, value)
        if(isAdded) call.respondText("Correctly added $query", status = HttpStatusCode.Accepted)
    }
}

private fun Route.putForm(){
    post("/addSlave"){
        val query = "workers"
        val params = call.receiveParameters()
        val value = Worker(null,
            params["name"].toString(),
            params["surname"].toString()
        ).also { it.email = params["email"].toString() }
        val isAdded = repo.putContent(query, value)

        if(isAdded) toContent(query)
    }
}

suspend fun PipelineContext<Unit, ApplicationCall>.toContent(query: String): ApplicationCall {
    try {
        val content = repo.getContent(query)
        if (content.isNotEmpty()) call.respond(MustacheContent("${query}.mustache", mapOf("elements" to content)))
        else call.respond(MustacheContent("error.mustache", null))
    }catch (e: IllegalEnteredDataException){
         call.respond(MustacheContent("error.mustache", null))
    }
    return context
}

val repo = DependencyProvider.provideRepository()
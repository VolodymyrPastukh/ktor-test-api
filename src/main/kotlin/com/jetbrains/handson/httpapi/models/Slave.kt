package com.jetbrains.handson.httpapi.models

import com.jetbrains.handson.httpapi.exceptions.IllegalEnteredDataException
import com.jetbrains.handson.httpapi.utils.log
import kotlinx.serialization.*

@Serializable
sealed class Slave{
    enum class Type{
        HUMAN,
        RESOURCE,
        SOMETHING
    }
    abstract val type: Type
}

@Serializable
data class Worker(val id: Int? = null, val name: String, val surname: String): Slave(){
    var email: String = "Has no email"
        set(value) {
            if(!value.matches(Regex("^(.+)@(.+)\$"))) throw IllegalEnteredDataException()
            .also { log(value) }
            field = value.also { log(value) }
    }
    override val type: Type = Type.HUMAN
}

@Serializable
data class Literature(val title: String, val author: String, val year: Int): Slave(){
    override val type: Type = Type.RESOURCE
}



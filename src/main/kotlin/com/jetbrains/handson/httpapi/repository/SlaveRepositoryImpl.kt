package com.jetbrains.handson.httpapi.repository

import com.jetbrains.handson.httpapi.exceptions.IllegalEnteredDataException
import com.jetbrains.handson.httpapi.factories.LiteratureFactory
import com.jetbrains.handson.httpapi.factories.WorkerFactory
import com.jetbrains.handson.httpapi.models.Literature
import com.jetbrains.handson.httpapi.models.Slave
import com.jetbrains.handson.httpapi.models.Worker

class SlaveRepositoryImpl(
    private val workerFactory: WorkerFactory,
    private val literatureFactory: LiteratureFactory
): Repository<Slave> {

    override fun getContent(query: String?): Collection<Slave> {
        return when(query){
            WORKER -> workerFactory.content
            LITERATURE -> literatureFactory.content
            else -> throw IllegalEnteredDataException()
        }
    }

    override fun putContent(query: String?, value: Slave): Boolean {
        return when(query){
            WORKER -> workerFactory.content.add(value as Worker)
            LITERATURE -> literatureFactory.content.add(value as Literature)
            else -> throw IllegalEnteredDataException()
        }
    }

    companion object{
        const val WORKER = "workers"
        const val LITERATURE = "literatures"
    }
}


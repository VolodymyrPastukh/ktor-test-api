package com.jetbrains.handson.httpapi.factories

import com.jetbrains.handson.httpapi.db.DatabaseFactory
import com.jetbrains.handson.httpapi.models.Worker

class WorkerFactory : SlaveFactory<Worker> {
    override val content: MutableSet<Worker> = DatabaseFactory.getWorkers().toMutableSet()
}
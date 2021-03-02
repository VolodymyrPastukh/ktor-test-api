package com.jetbrains.handson.httpapi

import com.jetbrains.handson.httpapi.factories.LiteratureFactory
import com.jetbrains.handson.httpapi.factories.WorkerFactory
import com.jetbrains.handson.httpapi.repository.SlaveRepositoryImpl
import com.sun.org.omg.CORBA.Repository

object DependencyProvider {
    private val workerFactory = WorkerFactory()
    private val literatureFactory = LiteratureFactory()

    fun provideRepository():SlaveRepositoryImpl = SlaveRepositoryImpl(workerFactory, literatureFactory)
}
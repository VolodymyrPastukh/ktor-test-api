package com.jetbrains.handson.httpapi.repository

import com.jetbrains.handson.httpapi.models.Slave

interface Repository<T: Slave>{
    fun getContent(query: String?): Collection<Slave>
    fun putContent(query: String?, value: Slave): Boolean
}
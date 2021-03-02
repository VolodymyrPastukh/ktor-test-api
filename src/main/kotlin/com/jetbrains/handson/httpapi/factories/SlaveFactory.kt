package com.jetbrains.handson.httpapi.factories

import com.jetbrains.handson.httpapi.models.Slave

interface SlaveFactory<T: Slave> {
    val content: MutableSet<T>
}
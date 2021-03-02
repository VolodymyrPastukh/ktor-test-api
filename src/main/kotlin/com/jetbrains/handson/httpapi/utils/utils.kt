package com.jetbrains.handson.httpapi.utils

import com.jetbrains.handson.httpapi.models.Slave
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.io.File
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


fun log(s: Any?){
    val log: String = "${s ?: "Lol"} ${System.lineSeparator()}"
    File("log.txt").appendText(s.toString())
}


//@ExperimentalSerializationApi
//@Serializer(forClass = Date::class)
//object DataSerializer: KSerializer<Date> {
//    private val df: DateFormat = SimpleDateFormat("dd/MM/yyyy")
//    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)
//
//    override fun serialize(encoder: Encoder, value: Date) {
//        val result = df.format(value)
//        encoder.encodeString(result)
//    }
//
//    override fun deserialize(decoder: Decoder): Date {
//        val result = decoder.decodeString()
//        return SimpleDateFormat("dd/MM/yyyy").parse(result)
//    }
//}

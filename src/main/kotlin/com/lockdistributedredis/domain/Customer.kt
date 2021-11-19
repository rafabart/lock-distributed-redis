package com.lockdistributedredis.domain

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.util.*

@RedisHash("customer")
data class Customer(

    @Id
    val uuid: UUID? = UUID.randomUUID(),

    val name: String,

    var active: Boolean? = false
){

    override fun toString(): String {
        return "Customer(uuid=$uuid, name='$name', active=$active)"
    }
}

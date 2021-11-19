package com.lockdistributedredis

import com.lockdistributedredis.domain.Customer
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.util.*

//@Repository
class CustomerRepository(

//    @Qualifier("CustomerCache")
//    private val redisTemplate: RedisTemplate<UUID, Customer>

) {


//    fun create(idKey: UUID, customer: Customer) {
//        redisTemplate.opsForValue().set(idKey, customer)
//    }
//
//
//    fun get(idKey: UUID): Customer? {
//        return redisTemplate.opsForValue().get(idKey)
//    }
}
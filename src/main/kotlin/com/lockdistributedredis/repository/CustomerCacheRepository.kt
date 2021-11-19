package com.lockdistributedredis.repository

import com.lockdistributedredis.domain.Customer
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.repository.CrudRepository
import java.util.*

@EnableRedisRepositories
interface CustomerCacheRepository : CrudRepository<Customer, UUID> {
}
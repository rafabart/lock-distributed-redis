package com.lockdistributedredis

import com.lockdistributedredis.domain.Customer
import com.lockdistributedredis.job.SimpleJob
import com.lockdistributedredis.repository.CustomerCacheRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component

@Component
class RunnerInit(

//    Popula o redis caso esteja com a quantidade no cache diferente do valor da constante 'size'

    private val customerCacheRepository: CustomerCacheRepository,
    private val redisTemplate: StringRedisTemplate,

    ) : CommandLineRunner {


    override fun run(vararg args: String?) {

        val size = 3

        if (customerCacheRepository.findAll().toList().size != size) {

            redisTemplate.delete(SimpleJob.JOB_KEY)
            customerCacheRepository.deleteAll()

            for (i in 1..size) {

                customerCacheRepository.save(
                    Customer(
                        name = "Customer - $i"
                    )
                )

            }
        }
    }
}
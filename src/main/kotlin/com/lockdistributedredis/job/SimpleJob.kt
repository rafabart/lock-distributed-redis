package com.lockdistributedredis.job

import com.lockdistributedredis.repository.CustomerCacheRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.TimeUnit

@Component
class SimpleJob(

    private val redisTemplate: StringRedisTemplate,
    private val customerCacheRepository: CustomerCacheRepository

) {

    companion object {
        const val JOB_KEY = "application:job:processingSomething"
    }

    @Value("\${spring.application.name}")
    private val APP_NAME: String? = null


    //Roda a cada 10s
    @Scheduled(cron = "*/10 * * * * *")
    fun processingSomething() {

        val appName = APP_NAME + " : " + UUID.randomUUID().toString()

//        Verifica se tem a chave já no redis, se não tiver já cria e salva uma com o nome da api que esta executando
//        o Scheduled. Se já tiver, quer dizer que alguma api já iniciou o Scheduled.
        val canRun = redisTemplate.boundValueOps(JOB_KEY)
            .setIfAbsent(appName, 1L, TimeUnit.MINUTES)

        if (canRun != null && canRun) {

            println("Lock acquired. Running something in $appName")

            val customerList = customerCacheRepository.findAll().toList()
            customerList.forEach {
                println(it.toString())
            }

            Thread.sleep(5000)

            customerList.forEach {
                it.active = !it.active!!
                customerCacheRepository.save(it)
            }

            redisTemplate.delete(JOB_KEY)

        } else {
            println("Lock unavailable! Job is running in other instance")

            customerCacheRepository.findAll().forEach {
                println(it.toString())
            }
        }
    }
}
package com.lockdistributedredis

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class LockDistributedRedisApplication

fun main(args: Array<String>) {
	runApplication<LockDistributedRedisApplication>(*args)
}

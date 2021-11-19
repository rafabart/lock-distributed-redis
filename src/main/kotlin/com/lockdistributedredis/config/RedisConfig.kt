package com.lockdistributedredis.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.lockdistributedredis.domain.Customer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.util.*

//@Configuration
class RedisConfig {


//    Não foi necessário usar o redisTemplate já que estou usando a implementação CrudRepository com
//    a entidade com notação do redis @RedisHash
//    @Bean("CustomerCache")
    fun redisTemplateCustomer(
        connectionFactory: RedisConnectionFactory,
        objectMapper: ObjectMapper
    ): RedisTemplate<UUID, Customer> {

        val template = RedisTemplate<UUID, Customer>()

        val jackson2JsonRedisSerializer = Jackson2JsonRedisSerializer(Customer::class.java)
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper)

        template.setConnectionFactory(connectionFactory)
        template.keySerializer = StringRedisSerializer()
        template.valueSerializer = jackson2JsonRedisSerializer
        template.hashKeySerializer = StringRedisSerializer()
        template.hashValueSerializer = jackson2JsonRedisSerializer

        return template
    }
}
package me.bazzi.shardingsphereh2

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
class Config {

    @Bean
    fun datasourceBeans(
            entityManagerFactory: List<EntityManagerFactory>,
            datasources: List<DataSource>
    ): List<String> {
        println("datasources")
        return emptyList()
    }



}
package me.bazzi.shardingsphereh2

import org.apache.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import java.util.Map
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
class Config {

    @Bean
    fun datasourceBeans(

            datasources: List<DataSource>
    ): List<String> {
        println("datasources")
        return emptyList()
    }

    @Bean
    @Primary
    fun entityManagerFactory(
            entityManagerFactory: EntityManagerFactory,
            shardingDataSource: ShardingDataSource
    ): LocalContainerEntityManagerFactoryBean? {
        val localContainerEntityManagerFactoryBean = LocalContainerEntityManagerFactoryBean()
        return localContainerEntityManagerFactoryBean
    }



}
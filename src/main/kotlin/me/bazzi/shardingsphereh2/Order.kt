package me.bazzi.shardingsphereh2

import ch.qos.logback.classic.db.names.TableName
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "t_order")
class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var name: String? = null
    var type: String? = null
    var gmtCreate: LocalDateTime? = null
}
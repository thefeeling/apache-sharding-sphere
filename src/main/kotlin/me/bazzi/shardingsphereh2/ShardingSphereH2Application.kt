package me.bazzi.shardingsphereh2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import java.time.LocalDateTime

@SpringBootApplication
@EntityScan(basePackages = ["me.bazzi.shardingsphereh2"])
class ShardingSphereH2Application: CommandLineRunner {
	@Autowired
	private lateinit var orderRepository: OrderRepository

	override fun run(vararg args: String?) {
		for (x in 0..100) {
			orderRepository.save(
					Order().apply {
						name = "name"
						type = "type"
						gmtCreate = LocalDateTime.now()
					}
			)
		}
		println("ddd")
	}

}

fun main(args: Array<String>) {
	runApplication<ShardingSphereH2Application>(*args)
}

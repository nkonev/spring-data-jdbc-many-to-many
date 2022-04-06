package com.example.web.jdbc.web.jdbc

import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component

@SpringBootApplication
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}

@Component
class AppRunner(
        private val orderRepository: OrderRepository,
) : ApplicationRunner {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun run(args: ApplicationArguments) {

        // https://spring.io/blog/2018/09/24/spring-data-jdbc-references-and-aggregates
//        val orders = orderRepository.findAll()
//        orders.forEach { logger.info("Found order {}", it) }

        val order = PurchaseOrder(0, "Kutaisi")
        order.addItem(4, "Captain Future Comet Lego set")
        order.addItem(2, "Cute blue angler fish plush toy")
        val savedOrder = orderRepository.save(order)

        val orders = orderRepository.findAll()
        orders.forEach { logger.info("Found order {}", it) }

        logger.info("=== deleting order with its items ===")
        orderRepository.deleteByShippingAddress("Kutaisi")

        val afterDeleteOrders = orderRepository.findAll()
        afterDeleteOrders.forEach { logger.info("Found order after delete {}", it) }

    }
}
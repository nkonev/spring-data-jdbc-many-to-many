package com.example.web.jdbc.web.jdbc

import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@SpringBootApplication
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}

@Component
class AppRunner(
        private val subjectRepository: SubjectRepository,
        private val branchRepository: BranchRepository,
        private val personRepository: PersonRepository,
        private val orderRepository: OrderRepository,
) : ApplicationRunner {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun run(args: ApplicationArguments) {
        branchRepository.deleteAll()
        subjectRepository.deleteAll()

        val subj1: Subject = subjectRepository.save(Subject(0, "Software Engineering", "Apply key aspects of software engineering processes for the development of a complex software system"))
        val subj2: Subject = subjectRepository.save(Subject(0, "Distributed System", "Explore recent advances in distributed computing systems"))
        val subj3: Subject = subjectRepository.save(Subject(0, "Business Analysis and Optimization", "understand the Internal and external factors that impact the business strategy"))

        val branch1: Branch = Branch(0, "Computer Science and Engineering", "CSE", "CSE department offers courses under ambitious curricula in computer science and computer engineering..")
        branch1.addSubject(subj1)
        branch1.addSubject(subj2)
        branch1.branchData = BranchData("Classic office", 100, "A pretty good office")
        val createdBranch1: Branch = branchRepository.save(branch1)
        logger.info("Created first branch {}", createdBranch1)

        val branch2: Branch = Branch(0, "Information Technology", "IT", "IT is the business side of computers - usually dealing with databases, business, and accounting")
        branch2.addSubject(subj1)
        branch2.addSubject(subj3)
        val createdBranch2: Branch = branchRepository.save(branch2)
        logger.info("Created second branch {}", createdBranch2)

        val findById = branchRepository.findById(createdBranch1.branchId)
        logger.info("Found --- first branch {}", findById)

        logger.info("Deleting first branch {}", createdBranch1)
        branchRepository.delete(createdBranch1)
        logger.info("Searching for first branch {}", branchRepository.existsById(createdBranch1.branchId))

        logger.info("Deleting second branch {}", createdBranch2)
        branchRepository.delete(createdBranch2)
        logger.info("Searching for second branch {}", branchRepository.existsById(createdBranch2.branchId))

        logger.info("Checking if branches still presents")
        val allBranches = branchRepository.findAll()
        allBranches.forEach { logger.info("Found branch {}", it) }

        logger.info("Checking if subjects still presents")
        val allSubjects = subjectRepository.findAll()
        allSubjects.forEach { logger.info("Found subject {}", it) }


        // https://docs.spring.io/spring-data/jdbc/docs/current/reference/html/#jdbc.query-methods
        val foundPersons = personRepository.findByLastName("Doe", PageRequest.of(1, 10))
        foundPersons.forEach { logger.info("Found Person {}", it) }

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
        orderRepository.delete(savedOrder)

        val afterDeleteOrders = orderRepository.findAll()
        afterDeleteOrders.forEach { logger.info("Found order after delete {}", it) }

    }
}
package com.example.web.jdbc.web.jdbc

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface SubjectRepository : CrudRepository<Subject, Int>

@Repository
interface BranchRepository : CrudRepository<Branch, Int>

@Repository
interface PersonRepository : CrudRepository<Person, Long> {
    fun findByLastName(lastname: String, pageable: Pageable) : Page<Person>
}

interface OrderRepository : CrudRepository<PurchaseOrder, Long> {
    @Query("select count(*) from order_item")
    fun countItems(): Int
}
package com.example.web.jdbc.web.jdbc

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface SubjectRepository : CrudRepository<Subject, Long>

@Repository
interface BranchRepository : CrudRepository<Branch, Long>

@Repository
interface PersonRepository : CrudRepository<Person, Long> {
    fun findByLastName(lastname: String, pageable: Pageable) : Page<Person>
}
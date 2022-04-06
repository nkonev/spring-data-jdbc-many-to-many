package com.example.web.jdbc.web.jdbc

import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.repository.CrudRepository

interface OrderRepository : CrudRepository<PurchaseOrder, Long> {

    @Modifying
    fun deleteByShippingAddress(shippingAddress: String)
}
package com.example.web.jdbc.web.jdbc

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.MappedCollection

data class OrderItem (
    @Id
    @Column("order_item_id")
    val id: Long,
    var quantity: Int = 0,
    var product: String
)

data class PurchaseOrder (
    @Id
    val id: Long,
    var shippingAddress: String,
    @MappedCollection(idColumn = "purchase_order_id")
    val items: MutableSet<OrderItem> = HashSet()
) {
    fun addItem(quantity: Int, product: String) {
        items.add(OrderItem(0, quantity, product))
    }
}
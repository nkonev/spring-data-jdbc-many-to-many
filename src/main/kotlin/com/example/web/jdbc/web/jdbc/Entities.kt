package com.example.web.jdbc.web.jdbc

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table


data class Subject(@Id val subjectId: Int, var subjectDesc: String, var subjectName: String)

@Table("branch_subject")
data class SubjectRef(val subjectId: Int)

data class Branch(
    @Id val branchId: Int,
    var branchName: String,
    @Column("branch_short_name") var branchShortName: String,
    var description: String? = null,
    @MappedCollection(idColumn = "branch_id")
    private val subjects: MutableSet<SubjectRef> = HashSet(),
    var branchData: BranchData? = null
) {

    fun addSubject(subject: Subject) {
        subjects.add(SubjectRef(subject.subjectId))
    }
}

data class BranchData(val buildingType: String?, var rating: Int, var comment: String?)

data class Person (val id: Long, var firstName: String, var lastName: String)

data class OrderItem (
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
        items.add(OrderItem(quantity, product))
    }
}
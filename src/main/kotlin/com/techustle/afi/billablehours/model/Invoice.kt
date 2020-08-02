package com.techustle.afi.billablehours.model

import javax.persistence.*

@Entity
class Invoice(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        var company: String,

        @OneToMany
        var invoiceDataList: MutableList<Any>) {


}
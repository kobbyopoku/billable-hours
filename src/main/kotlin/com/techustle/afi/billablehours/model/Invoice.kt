package com.techustle.afi.billablehours.model

import com.techustle.afi.billablehours.data.InvoiceData
import javax.persistence.*

@Entity
class Invoice(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        var company: String,

        @OneToMany
        var invoiceDataList: MutableList<InvoiceData>) {


}
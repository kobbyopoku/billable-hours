package com.techustle.afi.billablehours.model

import com.techustle.afi.billablehours.controller.InvoiceStatus
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.*

@Entity
class Invoice(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long?,
        var company: String,
        var invoiceDate: LocalDate,
        var invoiceStatus: InvoiceStatus,
        var itemsCount : Int,

        @OneToMany(fetch = FetchType.LAZY)
        var invoiceDataList: MutableList<InvoiceData>) {


}
package com.techustle.afi.billablehours.data

import com.techustle.afi.billablehours.model.Employee
import com.techustle.afi.billablehours.model.Invoice
import javax.persistence.*

@Entity
data class InvoiceData(
        @Id
        var id: Long?,

        @OneToOne
        var employee: Employee?,
        var numberOfHours: Int?,
        var unitPrice: Double?,
        var cost: Double?,

        @OneToOne
        var invoice: Invoice?) {
}
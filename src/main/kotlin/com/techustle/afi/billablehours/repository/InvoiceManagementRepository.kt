package com.techustle.afi.billablehours.repository

import com.techustle.afi.billablehours.data.InvoiceData
import com.techustle.afi.billablehours.model.Invoice
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface InvoiceManagementRepository: CrudRepository<Invoice, Long> {
}

@Repository
interface InvoiceDataManagementRepository: CrudRepository<InvoiceData, Long>{

}
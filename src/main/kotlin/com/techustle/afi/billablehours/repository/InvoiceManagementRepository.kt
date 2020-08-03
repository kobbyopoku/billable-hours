package com.techustle.afi.billablehours.repository

import com.techustle.afi.billablehours.model.InvoiceData
import com.techustle.afi.billablehours.model.Invoice
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface InvoiceManagementRepository: CrudRepository<Invoice, Long> {
    fun findByCompany(company: String): MutableList<Invoice>
    override fun findAll() :MutableList<Invoice>
}

@Repository
interface InvoiceDataManagementRepository: CrudRepository<InvoiceData, Long>{

}
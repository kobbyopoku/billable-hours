package com.techustle.afi.billablehours.service

import com.techustle.afi.billablehours.data.InvoiceData
import com.techustle.afi.billablehours.model.Invoice
import com.techustle.afi.billablehours.repository.InvoiceDataManagementRepository
import com.techustle.afi.billablehours.repository.InvoiceManagementRepository
import org.springframework.stereotype.Service

@Service
class InvoiceManagementService(val invoiceManagementRepository: InvoiceManagementRepository, val invoiceDataManagementRepository: InvoiceDataManagementRepository) {


    fun saveInvoice(invoice: Invoice): Invoice {
        return invoiceManagementRepository.save(invoice)
    }

    fun saveInvoiceData(invoiceData: InvoiceData){
        invoiceDataManagementRepository.save(invoiceData)
    }
}


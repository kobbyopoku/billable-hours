package com.techustle.afi.billablehours.controller

import com.techustle.afi.billablehours.data.InvoiceData
import com.techustle.afi.billablehours.data.InvoiceResponseObject
import com.techustle.afi.billablehours.model.EmployeeJobs
import com.techustle.afi.billablehours.model.Invoice
import com.techustle.afi.billablehours.service.InvoiceManagementService
import com.techustle.afi.billablehours.service.JobManagementService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@Api("This controller handles management and operations of invoices")
@RestController
@RequestMapping("api/v1")
class InvoiceController (val jobManagementService: JobManagementService, val invoiceManagementService: InvoiceManagementService){


    @ApiOperation(httpMethod = "GET", value = "This endpoint is used to generate invoices for a comapny")
    @GetMapping("/invoice/company/{company}")
    fun generateInvoice( @PathVariable(name = "company") company:String):InvoiceResponseObject? {
        val requestId: String  = UUID.randomUUID().toString()

        var invoice: Invoice = Invoice(Long.MIN_VALUE, "", mutableListOf());
        val companyJobs = jobManagementService.getAllCompanyJobs(company)

        invoice.company = company;

        var invoiceData: InvoiceData? = InvoiceData(
                Long.MIN_VALUE,
                null,
                null,
                null,
                null,
                null
        );

        companyJobs.forEach { job: EmployeeJobs ->
            run {
                invoiceData?.unitPrice = job.employee?.rate!!;
                invoiceData?.numberOfHours = job.endTime.hour - job.startTime.hour
                invoiceData?.cost = invoiceData?.unitPrice?.times(invoiceData.numberOfHours!!)!!
                invoiceData.employee = job.employee!!
//                invoiceData.invoice = invoice

                invoiceManagementService.saveInvoiceData(invoiceData)
                invoice.invoiceDataList.add(invoiceData)
            }
        }

        invoiceManagementService.saveInvoice(invoice)


        val responseObject: InvoiceResponseObject = InvoiceResponseObject(requestId, "success", 200, invoice)
        println(responseObject.requestId)
        return responseObject
    }
}
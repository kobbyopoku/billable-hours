package com.techustle.afi.billablehours.controller

import com.techustle.afi.billablehours.data.InvoiceData
import com.techustle.afi.billablehours.data.InvoiceResponseObject
import com.techustle.afi.billablehours.model.EmployeeJobs
import com.techustle.afi.billablehours.model.Invoice
import com.techustle.afi.billablehours.service.InvoiceManagementService
import com.techustle.afi.billablehours.service.JobManagementService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import java.time.LocalTime
import java.util.*

@CrossOrigin(origins = ["*"])
@Api("This controller handles management and operations of invoices")
@RestController
@RequestMapping("api/v1")
class InvoiceController (val jobManagementService: JobManagementService, val invoiceManagementService: InvoiceManagementService){


    @ApiOperation(httpMethod = "GET", value = "This endpoint is used to generate invoices for a comapny")
    @GetMapping("/invoice/company/{company}")
    fun generateInvoice( @PathVariable(name = "company") company:String):InvoiceResponseObject? {
        val requestId: String  = UUID.randomUUID().toString()

        var message: String
        var status: Int
        var invoice: Invoice = Invoice(null, "", mutableListOf());
        val companyJobs = jobManagementService.getAllCompanyJobs(company)

        if(companyJobs.isNotEmpty()) {
            invoice.company = company;
            invoice = invoiceManagementService.saveInvoice(invoice)


            var invoiceData: InvoiceData? = InvoiceData(
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );


            companyJobs.forEach { job: EmployeeJobs ->
                run {
                    invoiceData?.id = job.id
                    invoiceData?.unitPrice = job.employee?.rate!!;
                    invoiceData?.numberOfHours = LocalTime.parse(job.endTime ).hour - LocalTime.parse(job.startTime).hour
                    invoiceData?.cost = invoiceData?.unitPrice?.times(invoiceData.numberOfHours!!)!!
                    invoiceData.employee = job.employee!!

                    invoiceManagementService.saveInvoiceData(invoiceData)
                    invoice.invoiceDataList.add(invoiceData)
//                    jobManagementService.deleteJobById(job.id)
                }
            }

            invoiceManagementService.saveInvoice(invoice)

            message="SUCCESS"
            status = 200

        }else {
            message="DATA_NOT_FOUND"
            status = 404
        }
        val responseObject: InvoiceResponseObject = InvoiceResponseObject(requestId, message, status, invoice)

        return responseObject
    }
}
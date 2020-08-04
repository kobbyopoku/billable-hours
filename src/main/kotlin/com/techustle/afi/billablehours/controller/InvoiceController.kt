package com.techustle.afi.billablehours.controller

import com.techustle.afi.billablehours.model.InvoiceData
import com.techustle.afi.billablehours.data.InvoiceResponseObject
import com.techustle.afi.billablehours.data.InvoicesResponse
import com.techustle.afi.billablehours.model.EmployeeJobs
import com.techustle.afi.billablehours.model.Invoice
import com.techustle.afi.billablehours.service.InvoiceManagementService
import com.techustle.afi.billablehours.service.JobManagementService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@CrossOrigin(origins = ["*"])
@Api("This controller handles management and operations of invoices")
@RestController
@RequestMapping("api/v1")
class InvoiceController (val jobManagementService: JobManagementService, val invoiceManagementService: InvoiceManagementService){

    @ApiOperation(httpMethod = "GET", value = "This endpoint is used to generate invoices for a company")
    @GetMapping("/invoice/generate/{company}")
    fun generateInvoice(
            @PathVariable(name = "company") company:String,
            httpServletResponse: HttpServletResponse,
            httpServletRequest: HttpServletRequest):InvoiceResponseObject? {
        val requestId: String  = UUID.randomUUID().toString()

        val message: String
        var amount: Double? = 0.0
        var invoice: Invoice = Invoice(0, "",  LocalDate.now(), InvoiceStatus.PENDING, 0.0, 0,mutableListOf())
        val companyJobs = jobManagementService.getAllCompanyJobs(company)


        invoice.company = company
        if(companyJobs.isNotEmpty()) {
            invoice = invoiceManagementService.saveInvoice(invoice)

            val invoiceDataList = mutableListOf<InvoiceData>()

            val invoiceData: InvoiceData? = InvoiceData(
                    null,
                    null,
                    null,
                    null,
                    null
            )


            companyJobs.forEach { job: EmployeeJobs ->
                run {
                invoiceData?.id = job.id
                invoiceData?.unitPrice = job.employee?.rate!!
                    invoiceData?.numberOfHours = LocalTime.parse(job.endTime ).hour - LocalTime.parse(job.startTime).hour
                invoiceData?.cost = invoiceData?.unitPrice?.times(invoiceData.numberOfHours!!)!!
                invoiceData.employee = job.employee!!
                    val cost = invoiceData.cost
                    amount = amount?.plus(cost!!)

                val newInvoiceData: InvoiceData = invoiceManagementService.saveInvoiceData(invoiceData)
                invoiceDataList.add(newInvoiceData)
                    jobManagementService.deleteJob(job)
                }
            }

            invoice.invoiceStatus = InvoiceStatus.PENDING
            invoice.totalAmount = amount!!
            invoice.itemsCount = invoiceDataList.size
            invoice.invoiceDataList = invoiceDataList
            invoiceManagementService.saveInvoice(invoice)
            message="Success"
        }else {
            message="No data found for $company"
            invoice.invoiceStatus = InvoiceStatus.NONE
        }
        val status = httpServletResponse.status
        return InvoiceResponseObject(requestId, message, status, invoice)
    }


    @ApiOperation(httpMethod = "GET", value = "This endpoint is used to get invoices for a company")
    @GetMapping("/invoice/company/{company}")
    fun getCompanyInvoice(
            @PathVariable(name = "company") company: String,
            httpServletRequest: HttpServletRequest,
            httpServletResponse: HttpServletResponse):InvoicesResponse{

        val requestId: String  = UUID.randomUUID().toString()
        val companyInvoiceList: MutableList<Invoice> = invoiceManagementService.getCompanyInvoices(company)
        val count = companyInvoiceList.size
        val message = if(companyInvoiceList.isNotEmpty()){"Success"}else{"No data found for $company"}
        val status = httpServletResponse.status
        return InvoicesResponse(requestId,message,status, count, companyInvoiceList)
    }


    @ApiOperation(httpMethod = "GET", value = "This endpoint is used list all invoices")
    @GetMapping("/invoices")
    fun getInvoice(
            httpServletRequest: HttpServletRequest,
            httpServletResponse: HttpServletResponse):InvoicesResponse{

        val requestId: String  = UUID.randomUUID().toString()

        val companyInvoiceList: MutableList<Invoice> = invoiceManagementService.getAllInvoices()
        val count = companyInvoiceList.size
        val message = if( companyInvoiceList.isNotEmpty() ){ "Success" }else{ "No data found"}
        val status:Int = httpServletResponse.status
        return InvoicesResponse(requestId,message,status, count, companyInvoiceList)
    }

}




enum class InvoiceStatus{
    NONE, PENDING, SENT, PAID
}
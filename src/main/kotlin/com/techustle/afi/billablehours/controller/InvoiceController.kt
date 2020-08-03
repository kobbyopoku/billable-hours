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
    fun generateInvoice( @PathVariable(name = "company") company:String, httpServletRequest: HttpServletResponse, httpServletResponse: HttpServletRequest):InvoiceResponseObject? {
        val requestId: String  = UUID.randomUUID().toString()

        var message: String
        var status: Int
        var amount: Double? = 0.0
        var invoice: Invoice = Invoice(0, "",  LocalDate.now(), InvoiceStatus.PENDING, 0.0, 0,mutableListOf());
        val companyJobs = jobManagementService.getAllCompanyJobs(company)


        invoice.company = company;
        if(companyJobs.isNotEmpty()) {
            invoice = invoiceManagementService.saveInvoice(invoice)

            val invoiceDataList = mutableListOf<InvoiceData>()

            var invoiceData: InvoiceData? = InvoiceData(
                    null,
                    null,
                    null,
                    null,
                    null
            );


            companyJobs.forEach { job: EmployeeJobs ->
                run {
                    println(":: Now on job ${job.id}")

                invoiceData?.id = job.id
                invoiceData?.unitPrice = job.employee?.rate!!;
                invoiceData?.numberOfHours = LocalTime.parse(job.endTime ).hour - LocalTime.parse(job.startTime).hour
                invoiceData?.cost = invoiceData?.unitPrice?.times(invoiceData.numberOfHours!!)!!
                invoiceData.employee = job.employee!!

                    var cost = invoiceData?.cost

                    amount = amount?.plus(cost!!)

                val newInvoiceData: InvoiceData = invoiceManagementService.saveInvoiceData(invoiceData)
                invoiceDataList.add(newInvoiceData)
                    jobManagementService.deleteJob(job)
                }
            }


            invoice.totalAmount = amount!!
            invoice.itemsCount = invoiceDataList.size
            invoice.invoiceDataList = invoiceDataList
            invoiceManagementService.saveInvoice(invoice)

            message="Success"
            status = 200

        }else {
            message="No data found for $company"
            invoice.invoiceStatus = InvoiceStatus.NONE
            status = 404
        }

        return InvoiceResponseObject(requestId, message, status, invoice)
    }


    @ApiOperation(httpMethod = "GET", value = "This endpoint is used to get invoices for a company")
    @GetMapping("/invoice/company/{company}")
    fun getCompanyInvoice( @PathVariable(name = "company") company: String):InvoicesResponse{

        val requestId: String  = UUID.randomUUID().toString()
        var message:String
        var status:Int
        var count : Int

        val companyInvoiceList: MutableList<Invoice> = invoiceManagementService.getCompanyInvoices(company)
        count = companyInvoiceList.size
        if(companyInvoiceList.isNotEmpty()){

            message="Success"
            status = 200
        }else{

            message="No data found for $company"
            status = 404
        }
        return InvoicesResponse(requestId,message,status, count, companyInvoiceList)
    }


    @ApiOperation(httpMethod = "GET", value = "This endpoint is used list all invoices")
    @GetMapping("/invoices")
    fun getInvoicea():InvoicesResponse{

        val requestId: String  = UUID.randomUUID().toString()
        var message:String
        var status:Int
        var count : Int

        val companyInvoiceList: MutableList<Invoice> = invoiceManagementService.getAllInvoices()
        count = companyInvoiceList.size
        if(companyInvoiceList.isNotEmpty()){

            message="Success"
            status = 200
        }else{

            message="No data found"
            status = 404
        }
        return InvoicesResponse(requestId,message,status, count, companyInvoiceList)
    }

}




enum class InvoiceStatus{
    NONE, PENDING, SENT, PAID
}
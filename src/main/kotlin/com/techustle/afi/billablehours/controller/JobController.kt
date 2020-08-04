package com.techustle.afi.billablehours.controller

import com.techustle.afi.billablehours.data.JobResponse
import com.techustle.afi.billablehours.data.Jobs
import com.techustle.afi.billablehours.model.Employee
import com.techustle.afi.billablehours.model.EmployeeJobs
import com.techustle.afi.billablehours.service.EmployeeManagementService
import com.techustle.afi.billablehours.service.JobManagementService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Slf4j
@CrossOrigin(origins = ["*"])
@Api("This controller handles management and operations of projects")
@RestController
@RequestMapping("/api/v1")
class JobController(val jobManagementService: JobManagementService, val employeeManagementService: EmployeeManagementService) {


    @ApiOperation(httpMethod = "POST", value = "This endpoint is used to record new project by an employee")
    @PostMapping("/job/employee/{id}")
    fun recordJob(@RequestBody jobs: Jobs, @PathVariable(name ="id")employeeId: Long, httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse): JobResponse{
        val employee: Employee? = employeeManagementService.findEmployee(employeeId)
        val employeeJobs = EmployeeJobs(
                id=jobs.id,
                project = jobs.project,
                startTime = jobs.startTime,
                endTime = jobs.endTime,
                employee = employee,
                date = jobs.date,
                status = true,
                invoiceStatus = false)

        httpServletRequest.requestedSessionId

        val requestId = UUID.randomUUID().toString()
        val employeeJobsData = jobManagementService.addNewJob(employeeJobs)
        val message = if(employeeJobsData != null){"success"}else{"No data found"}
        val status : Int = httpServletResponse.status
        return JobResponse(requestId, message, status, employee as Employee, mutableListOf(employeeJobsData))
    }


    @ApiOperation(httpMethod = "GET", value = "This endpoint is used to get jobs of an employee by id")
    @GetMapping("/job/employee/{id}")
    fun getEmployeeJobs(@PathVariable(name = "id") employeeId: Long, httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse):JobResponse{

        val requestId = UUID.randomUUID().toString()
        val employee = employeeManagementService.findEmployee(employeeId)
        val employeeJobList  = jobManagementService.getEmployeeJobs(employee as Employee)
        val message = if(employeeJobList.isNotEmpty()){"success"}else{"No data found"}
        val status : Int = httpServletResponse.status
        return JobResponse(requestId, message, status, employee, employeeJobList)
    }

    @ApiOperation(httpMethod = "GET", value = "This endpoint is used to get all jobs")
    @GetMapping("/jobs")
    fun getAllJobs( httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse): JobResponse {
        val requestId = UUID.randomUUID().toString()
        val employeeJobList =jobManagementService.getAllEmployeesJobs()
        val message = if(employeeJobList.isNotEmpty()){"success"}else{"No data found"}
        val status : Int = httpServletResponse.status
        return JobResponse(requestId, message, status, null, employeeJobList)
    }

    @ApiOperation(httpMethod = "GET", value = "This endpoint is used to get all jobs done for a specific company")
    @GetMapping("/jobs/company/{company}")
    fun getAllCompanyJobs(@PathVariable(name = "company") company: String, httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse): MutableIterable<EmployeeJobs> {
        return jobManagementService.getAllCompanyJobs(company)
    }

    @ApiOperation(httpMethod = "DELETE", value = "This endpoint is used to delete job by id")
    @DeleteMapping("/jobs/{id}")
    fun getAllCompanyJobs(@PathVariable(name = "id") id: Long, httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse): JobResponse{
        val requestId = UUID.randomUUID().toString()
        val status = httpServletResponse.status
        jobManagementService.deleteJobById(id)
        return JobResponse(requestId, "success", status, null, mutableListOf())
    }

}
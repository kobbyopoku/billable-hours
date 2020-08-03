package com.techustle.afi.billablehours.controller

import com.techustle.afi.billablehours.data.Jobs
import com.techustle.afi.billablehours.model.Employee
import com.techustle.afi.billablehours.model.EmployeeJobs
import com.techustle.afi.billablehours.service.EmployeeManagementService
import com.techustle.afi.billablehours.service.JobManagementService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.*

@Slf4j
@Api("This controller handles management and operations of projects")
@RestController
@RequestMapping("/api/v1")
class JobController(val jobManagementService: JobManagementService, val employeeManagementService: EmployeeManagementService) {


    @ApiOperation(httpMethod = "POST", value = "This endpoint is used to record new project by an employee")
    @PostMapping("/job/employee/{id}")
    fun recordJob(@RequestBody jobs: Jobs, @PathVariable(name ="id")employeeId: Long): EmployeeJobs{
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
        return  jobManagementService.addNewJob(employeeJobs)
    }


    @ApiOperation(httpMethod = "GET", value = "This endpoint is used to get jobs of an employee by id")
    @GetMapping("/job/employee/{id}")
    fun getEmployeeJobs(@PathVariable(name = "id") employeeId: Long):MutableIterator<EmployeeJobs>{
        val employee = employeeManagementService.findEmployee(employeeId)
        return jobManagementService.getEmployeeJobs(employee as Employee)
    }

    @ApiOperation(httpMethod = "GET", value = "This endpoint is used to get all jobs")
    @GetMapping("/jobs")
    fun getAllJobs(): MutableIterable<EmployeeJobs> {
        return jobManagementService.getAllEmployeesJobs()
    }

    @ApiOperation(httpMethod = "GET", value = "This endpoint is used to get all jobs done for a specific company")
    @GetMapping("/jobs/company/{company}")
    fun getAllCompanyJobs(@PathVariable(name = "company") company: String): MutableIterable<EmployeeJobs> {
        return jobManagementService.getAllCompanyJobs(company)
    }

    @ApiOperation(httpMethod = "DELETE", value = "This endpoint is used to delete job by id")
    @GetMapping("/jobs/company/{id}")
    fun getAllCompanyJobs(@PathVariable(name = "id") id: Long) {
        jobManagementService.deleteJobById(id)
    }

}
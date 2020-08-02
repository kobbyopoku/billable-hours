package com.techustle.afi.billablehours.controller

import com.techustle.afi.billablehours.data.Jobs
import com.techustle.afi.billablehours.model.Employee
import com.techustle.afi.billablehours.model.EmployeeJobs
import com.techustle.afi.billablehours.service.EmployeeManagementService
import com.techustle.afi.billablehours.service.JobManagementService
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.*

@Slf4j
@RestController
@RequestMapping("/api/v1")
class JobController(val jobManagementService: JobManagementService, val employeeManagementService: EmployeeManagementService) {

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


    @GetMapping("/job/employee/{id}")
    fun getEmployeeJobs(@PathVariable(name = "id") employeeId: Long):MutableIterator<EmployeeJobs>{
        val employee = employeeManagementService.findEmployee(employeeId)
        return jobManagementService.getEmployeeJobs(employee as Employee)
    }

    @GetMapping("/jobs")
    fun getAllJobs(): MutableIterable<EmployeeJobs> {
        return jobManagementService.getAllEmployeesJobs()
    }

    @GetMapping("/jobs/company/{company}")
    fun getAllCompanyJobs(@PathVariable(name = "company") company: String): MutableIterable<EmployeeJobs> {
        return jobManagementService.getAllCompanyJobs(company)
    }

}
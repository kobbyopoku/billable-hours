package com.techustle.afi.billablehours.service

import com.techustle.afi.billablehours.model.Employee
import com.techustle.afi.billablehours.model.EmployeeJobs
import com.techustle.afi.billablehours.repository.JobManagementRepository
import org.springframework.stereotype.Service

@Service
class JobManagementService(val jobManagementRepository: JobManagementRepository) {

    fun addNewJob(employeeJobs: EmployeeJobs):EmployeeJobs{
        return jobManagementRepository.save(employeeJobs)
    }

    fun getEmployeeJobs(employee: Employee):MutableIterator<EmployeeJobs>{
        return jobManagementRepository.findAllByEmployee(employee)
    }

    fun getAllEmployeesJobs(): MutableIterable<EmployeeJobs> {
        return jobManagementRepository.findAll()
    }

    fun getAllCompanyJobs(company: String): MutableIterable<EmployeeJobs> {
        return jobManagementRepository.findAllByProject(company)
    }

}
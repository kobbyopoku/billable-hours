package com.techustle.afi.billablehours.service

import com.techustle.afi.billablehours.model.EmployeeJobs
import com.techustle.afi.billablehours.repository.JobManagementRepository
import org.springframework.stereotype.Service

@Service
class JobManagementService(val jobManagementRepository: JobManagementRepository) {

    fun addNewJob(employeeJobs: EmployeeJobs):EmployeeJobs{
        return jobManagementRepository.save(employeeJobs)
    }

    fun getEmployeeJobs(employeeId:Long):MutableList<EmployeeJobs>{
        return jobManagementRepository.findAllById(employeeId)
    }

    fun getAllEmployeesJobs(): MutableIterable<EmployeeJobs> {
        return jobManagementRepository.findAll()
    }

}
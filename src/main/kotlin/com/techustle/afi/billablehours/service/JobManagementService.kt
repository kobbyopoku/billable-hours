package com.techustle.afi.billablehours.service

import com.techustle.afi.billablehours.model.Employee
import com.techustle.afi.billablehours.model.EmployeeJobs
import com.techustle.afi.billablehours.repository.JobManagementRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class JobManagementService(val jobManagementRepository: JobManagementRepository) {

    fun addNewJob(employeeJobs: EmployeeJobs):EmployeeJobs{
        return jobManagementRepository.save(employeeJobs)
    }

    fun getEmployeeJobs(employee: Employee):MutableList<EmployeeJobs?>{
        return jobManagementRepository.findAllByEmployee(employee)
    }

    fun getAllEmployeesJobs(): MutableList<EmployeeJobs?> {
        return jobManagementRepository.findAll()
    }

    fun getAllCompanyJobs(company: String): MutableList<EmployeeJobs> {
        return jobManagementRepository.findAllByProject(company)
    }

    fun deleteJobById(id: Long) {
        jobManagementRepository.deleteById(id)
    }

    fun deleteJob(job: EmployeeJobs) {
        jobManagementRepository.delete(job)
    }

    fun getJobById(jobId: Long): EmployeeJobs? {
        return jobManagementRepository.findByIdOrNull(jobId)
    }

    fun getJobByIdAndEmployee( id: Long, employee: Employee): EmployeeJobs{
        return jobManagementRepository.findByIdAndEmployee(id, employee)
    }

}
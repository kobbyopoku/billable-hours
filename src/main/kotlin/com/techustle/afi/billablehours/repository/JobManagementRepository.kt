package com.techustle.afi.billablehours.repository

import com.techustle.afi.billablehours.model.Employee
import com.techustle.afi.billablehours.model.EmployeeJobs
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface JobManagementRepository: CrudRepository<EmployeeJobs, Long> {
    fun findAllById(employeeId: Long):MutableIterator<EmployeeJobs>
    fun findAllByEmployee(employeeId: Employee): MutableList<EmployeeJobs?>
    fun findAllByProject(company: String): MutableList<EmployeeJobs>
    override fun findAll(): MutableList<EmployeeJobs?>
    fun findByIdAndEmployee(id: Long, employee: Employee): EmployeeJobs

}

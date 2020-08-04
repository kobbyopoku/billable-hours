package com.techustle.afi.billablehours.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import com.techustle.afi.billablehours.model.Employee
import com.techustle.afi.billablehours.model.EmployeeJobs
import com.techustle.afi.billablehours.model.Invoice
import java.util.*

@Repository
interface EmployeeManagementRepository: CrudRepository<Employee, Long> {
    public override fun findById(id:Long): Optional<Employee>
    public override fun findAll(): MutableList<Employee?>
    fun findByEmail(email: String): Employee?
    fun findByEmailAndPassword(email: String, password: String): Employee?
}


@Repository
interface JobManagementRepository: CrudRepository<EmployeeJobs, Long>{
    fun findAllById(employeeId: Long):MutableIterator<EmployeeJobs>
    fun findAllByEmployee(employeeId: Employee): MutableList<EmployeeJobs>
    fun findAllByProject(company: String): MutableList<EmployeeJobs>
    override fun findAll(): MutableList<EmployeeJobs>

}


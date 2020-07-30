package com.techustle.afi.billablehours.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import com.techustle.afi.billablehours.model.Employee
import java.util.*

@Repository
interface EmployeeRepository: CrudRepository<Employee, Long> {
    public override fun findById(id:Long): Optional<Employee>
    public override fun findAll(): MutableList<Employee>
}
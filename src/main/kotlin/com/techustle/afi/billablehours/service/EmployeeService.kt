package com.techustle.afi.billablehours.service

import com.techustle.afi.billablehours.model.Employee
import com.techustle.afi.billablehours.repository.EmployeeRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class EmployeeService(val employeeRepository: EmployeeRepository) {
    fun findEmployee(id: Long): Optional<Employee> {
        return employeeRepository.findById(id)
    }

    fun createEmployee(employee: Employee): Employee {
        return employeeRepository.save(employee)
    }

    fun getEmployees(): MutableList<Employee> {
        return employeeRepository.findAll()
    }
}



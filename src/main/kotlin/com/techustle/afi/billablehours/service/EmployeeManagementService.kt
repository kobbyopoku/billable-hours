package com.techustle.afi.billablehours.service

import com.techustle.afi.billablehours.model.Employee
import com.techustle.afi.billablehours.repository.EmployeeManagementRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class EmployeeManagementService(val employeeManagementRepository: EmployeeManagementRepository) {
    fun findEmployee(id: Long): Employee? {
        return employeeManagementRepository.findByIdOrNull(id)
    }

    fun createEmployee(employee: Employee): Employee {
        return employeeManagementRepository.save(employee)
    }

    fun getEmployees(): MutableIterable<Employee> {
        return employeeManagementRepository.findAll()
    }
}



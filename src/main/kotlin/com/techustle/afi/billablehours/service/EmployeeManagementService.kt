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

    fun getEmployees(): MutableList<Employee?> {
        return employeeManagementRepository.findAll()
    }

    fun login(email: String, password: String): Employee? {
        return employeeManagementRepository.findByEmailAndPassword(email, password)
    }

    fun findEmployeeByEmail(email: String): Employee? {
        return employeeManagementRepository.findByEmail(email)
    }
}



package com.techustle.afi.billablehours.controller

import com.techustle.afi.billablehours.model.Employee
import com.techustle.afi.billablehours.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(name = "/api/v1")
class EmployeeController @Autowired constructor(val employeeService: EmployeeService)

    private val employeeService: EmployeeService
        get() {
            TODO()
        }

    @GetMapping(name = "/employee/{id}")
    fun getEmployee(@PathVariable(name ="id") id:Long): Optional<Employee> {
        return employeeService.findEmployee(id)
    }

    @PostMapping(name = "/employee")
    fun addNewEmployee(@RequestBody employee:Employee): Employee{
        val employees: MutableList<Employee> = employeeService.getEmployees()
        return employeeService.createEmployee(employee)
    }
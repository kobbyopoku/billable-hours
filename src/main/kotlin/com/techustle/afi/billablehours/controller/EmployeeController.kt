package com.techustle.afi.billablehours.controller

import com.techustle.afi.billablehours.model.Employee
import com.techustle.afi.billablehours.service.EmployeeService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(name = "/api/v1")
class EmployeeController (val employeeService: EmployeeService) {


    @GetMapping(name = "/employee/{id}")
    fun getEmployee(@PathVariable(name ="id") id:Long): Employee? {
        return employeeService.findEmployee(id)
    }

    @PostMapping(name = "/employee")
    fun addNewEmployee(@RequestBody employee:Employee): Employee{
        val employees: MutableList<Employee> = employeeService.getEmployees()
        return employeeService.createEmployee(employee)
    }

    @PutMapping(name = "/employee/{id}/{rate}")
    fun changeEmployeeRate(@PathVariable(name="id")id: Long, @PathVariable(name="rate") rate: String): Employee{
        val employees: MutableList<Employee> = employeeService.getEmployees()
        val employee: Employee?  = employeeService.findEmployee(id)
        if (employee != null){
            employeeService.createEmployee(employee)
        }else{
            println()
        }
        return employee as Employee
    }

}
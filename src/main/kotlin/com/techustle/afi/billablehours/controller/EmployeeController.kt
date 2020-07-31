package com.techustle.afi.billablehours.controller

import com.techustle.afi.billablehours.model.Employee
import com.techustle.afi.billablehours.service.EmployeeService
import lombok.extern.log4j.Log4j
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.util.*

@Log4j
@RestController
@RequestMapping(name = "/api/v1")
class EmployeeController (val employeeService: EmployeeService) {



    @GetMapping(name = "/employee/{id}")
    fun getEmployee(@PathVariable(name ="id") id:Long): Employee? {
        logg
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
            employee.rate = rate
            employeeService.createEmployee(employee)
        }else{
            println()
        }
        return employee as Employee
    }

}
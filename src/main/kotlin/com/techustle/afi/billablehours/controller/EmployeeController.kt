package com.techustle.afi.billablehours.controller

import com.techustle.afi.billablehours.model.Employee
import com.techustle.afi.billablehours.repository.JobManagementRepository
import com.techustle.afi.billablehours.service.EmployeeManagementService
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.*

@Slf4j
@RestController
@RequestMapping("/api/v1")
class EmployeeController (val employeeManagementService: EmployeeManagementService) {


    @PostMapping("/employee")
    fun addNewEmployee(@RequestBody employee:Employee): Employee{
        return employeeManagementService.createEmployee(employee)
    }

    @GetMapping("/employee/{id}")
    fun getEmployee(@PathVariable(name ="id") id:Long): Employee? {
        return employeeManagementService.findEmployee(id)
    }

    @GetMapping("/employees")
    fun getAllEmployee(): MutableList<Employee> {
        return employeeManagementService.getEmployees()
    }


    @PutMapping("/employee/{id}/{rate}")
    fun changeEmployeeRate(@PathVariable(name="id")id: Long, @PathVariable(name="rate") rate: Double): Employee? {
        val employee: Employee?  = employeeManagementService.findEmployee(id)
        return if (employee != null){
            employee.rate = rate
            employeeManagementService.createEmployee(employee)
            employee
        }else{
            println()
            null
        }

    }

}
package com.techustle.afi.billablehours.controller

import com.techustle.afi.billablehours.model.Employee
import com.techustle.afi.billablehours.service.EmployeeManagementService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.*

@Slf4j
@Api("This controller handles management and operations of employees")
@RestController
@RequestMapping("/api/v1")
class EmployeeController (val employeeManagementService: EmployeeManagementService) {


    @ApiOperation(httpMethod = "POST", value = "This endpoint is used to create a new employee")
    @PostMapping("/employee")
    fun addNewEmployee(@RequestBody employee:Employee): Employee{
        return employeeManagementService.createEmployee(employee)
    }


    @ApiOperation(httpMethod = "GET", value = "This endpoint is used to get an employee by id")
    @GetMapping("/employee/{id}")
    fun getEmployee(@PathVariable(name ="id") id:Long): Employee? {
        return employeeManagementService.findEmployee(id)
    }


    @ApiOperation(httpMethod = "GET", value = "This endpoint is used to get a list of all employees")
    @GetMapping("/employees")
    fun getAllEmployee(): MutableIterable<Employee> {
        return employeeManagementService.getEmployees()
    }


    @ApiOperation(httpMethod = "PATCH", value = "This endpoint is used to change an employee rate")
    @PatchMapping("/employee/{id}/{rate}")
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
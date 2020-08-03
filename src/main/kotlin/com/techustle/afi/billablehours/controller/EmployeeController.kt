package com.techustle.afi.billablehours.controller

import com.techustle.afi.billablehours.data.AuthRequest
import com.techustle.afi.billablehours.data.AuthResponse
import com.techustle.afi.billablehours.data.EmployeeResponse
import com.techustle.afi.billablehours.model.Employee
import com.techustle.afi.billablehours.service.EmployeeManagementService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.*
import java.util.*

@Slf4j
@CrossOrigin(origins = ["*"])
@Api("This controller handles management and operations of employees")
@RestController
@RequestMapping("/api/v1")
class EmployeeController (val employeeManagementService: EmployeeManagementService) {


    @ApiOperation(httpMethod = "POST", value = "This endpoint is used to create a new employee")
    @PostMapping("/employee")
    fun addNewEmployee(@RequestBody employee:Employee): EmployeeResponse{
        var requestId: String = UUID.randomUUID().toString()
        var message: String
        var status: Int

       var employee = employeeManagementService.createEmployee(employee)

        if (employee!=null){
            message = "Success"
            status=200
        }else{
            message = "Error"
            status=400
        }

        return EmployeeResponse(requestId, message, status, mutableListOf(employee))
    }


    @ApiOperation(httpMethod = "GET", value = "This endpoint is used to get an employee by id")
    @GetMapping("/employee/{id}")
    fun getEmployee(@PathVariable(name ="id") id:Long): EmployeeResponse {

        var requestId: String = UUID.randomUUID().toString()
        var message: String
        var status: Int



        var employee =  employeeManagementService.findEmployee(id)

        if (employee!=null){
            message = "Success"
            status=200
        }else{
            message = "Error"
            status=400
        }

        return EmployeeResponse(requestId, message, status, mutableListOf(employee))
    }


    @ApiOperation(httpMethod = "GET", value = "This endpoint is used to get a list of all employees")
    @GetMapping("/employees")
    fun getAllEmployee():EmployeeResponse {

        var requestId: String = UUID.randomUUID().toString()
        var message: String
        var status: Int

        val employees = employeeManagementService.getEmployees()


        if (employees.isNotEmpty()){
            message = "Success"
            status = 200
        }else{
            message = "Error"
            status = 400
        }


        return EmployeeResponse(requestId, message, status, employees as MutableList<Employee?>)
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

    @ApiOperation(httpMethod = "POST", value = "This endpoint is used to login as an employee")
    @PostMapping("/login")
    fun employeeLogin(@RequestBody authRequest: AuthRequest): AuthResponse {
        val employee: Employee? =  employeeManagementService.login(authRequest.email, authRequest.password)

        val requestId: String  = UUID.randomUUID().toString()
        var message:String
        var status:Int
        if(employee != null && authRequest.password == employee.password){
            message = "Success"
            status = 200
        }else{
            message = "Invalid email or password"
            status = 404
        }

        return AuthResponse(requestId, message, status, employee)
    }

}
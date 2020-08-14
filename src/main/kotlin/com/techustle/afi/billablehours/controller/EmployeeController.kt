package com.techustle.afi.billablehours.controller

import com.techustle.afi.billablehours.data.AuthRequest
import com.techustle.afi.billablehours.data.AuthResponse
import com.techustle.afi.billablehours.data.EmployeeResponse
import com.techustle.afi.billablehours.model.Employee
import com.techustle.afi.billablehours.service.EmployeeManagementService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import lombok.extern.slf4j.Slf4j
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Slf4j
@CrossOrigin(origins = ["*"])
@Api("This controller handles management and operations of employees")
@RestController
@RequestMapping("/api/v1")
class EmployeeController (val employeeManagementService: EmployeeManagementService) {

    fun encoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @ApiOperation(httpMethod = "POST", value = "This endpoint is used to create a new employee")
    @PostMapping("/employee")
    fun addNewEmployee(
            @RequestBody employeeData:Employee,
            httpServletRequest: HttpServletRequest,
            httpServletResponse: HttpServletResponse): EmployeeResponse{
        val requestId: String = UUID.randomUUID().toString()

        var message = "Success"
        val securePassword = encoder().encode(employeeData.password)
        employeeData.password = securePassword
        val existingEmployee = employeeManagementService.findEmployeeByEmail(employeeData.email)
        message = existingEmployee.let {
            "Employee with email ${employeeData.email} exists"
        }?: "Employee created successfully"
        existingEmployee ?: employeeManagementService.createEmployee(employeeData)

        val status = httpServletResponse.status
        return EmployeeResponse(requestId, message, status, mutableListOf(existingEmployee))
    }


    @ApiOperation(httpMethod = "GET", value = "This endpoint is used to get an employee by id")
    @GetMapping("/employee/{id}")
    fun getEmployee(
            @PathVariable(name ="id") id:Long,
            httpServletRequest: HttpServletRequest,
            httpServletResponse: HttpServletResponse): EmployeeResponse {

        val requestId: String = UUID.randomUUID().toString()
        val employee =  employeeManagementService.findEmployee(id)
        val message =if (employee!=null){ "Success" }else{ "Error" }
        val status = httpServletResponse.status
        return EmployeeResponse(requestId, message, status, mutableListOf(employee))
    }


    @ApiOperation(httpMethod = "GET", value = "This endpoint is used to get a list of all employees")
    @GetMapping("/employees")
    fun getAllEmployee(
            httpServletRequest: HttpServletRequest,
            httpServletResponse: HttpServletResponse):EmployeeResponse {

        val requestId: String = UUID.randomUUID().toString()
        val employees = employeeManagementService.getEmployees()
        val message = if (employees.isNotEmpty()){ "Success" }else{ "Error" }
        val status = httpServletResponse.status
        return EmployeeResponse(requestId, message, status, employees)
    }


    @ApiOperation(httpMethod = "PATCH", value = "This endpoint is used to change an employee rate")
    @PatchMapping("/employee/{id}/{rate}")
    fun changeEmployeeRate(@PathVariable(name="id")id: Long,
                           @PathVariable(name="rate") rate: Double,
                           httpServletRequest: HttpServletRequest,
                           httpServletResponse: HttpServletResponse): EmployeeResponse {
        val requestId: String = UUID.randomUUID().toString()
        val employee: Employee?  = employeeManagementService.findEmployee(id)
        val message = if (employee != null){
            employee.rate = rate
            employeeManagementService.createEmployee(employee)
            "success"
        }else{ "No data found" }

        val status = httpServletResponse.status
        return EmployeeResponse(requestId, message, status, mutableListOf(employee))
    }

    @ApiOperation(httpMethod = "POST", value = "This endpoint is used to login as an employee")
    @PostMapping("/login")
    fun employeeLogin(
            @RequestBody authRequest: AuthRequest,
            httpServletRequest: HttpServletRequest,
            httpServletResponse: HttpServletResponse): AuthResponse {
        val employee: Employee? =  employeeManagementService.findEmployeeByEmail(authRequest.email)
        val requestId: String  = UUID.randomUUID().toString()

        val message =  if(encoder().matches(employee?.password, authRequest.password)){"success" }else{"Invalid username or password"}
        val status = httpServletResponse.status
        return AuthResponse(requestId, message, status, employee)
    }
}
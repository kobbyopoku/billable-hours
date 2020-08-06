package com.techustle.afi.billablehours

import com.techustle.afi.billablehours.model.Employee
import com.techustle.afi.billablehours.service.EmployeeManagementService
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@Component
class OnAppStart(val employeeManagementService: EmployeeManagementService): ApplicationListener<ContextRefreshedEvent> {

    fun encoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    fun createDefaultAdminUser(){

        val password = encoder().encode("admin123")
        val adminUser = Employee(
                0,
                "Super",
                "Admin",
                "admin@billablehours.com",
                password,
                "FINANCE ADMIN",
                "AAA",
                1.0
        )
        val existingEmployee = employeeManagementService.findEmployeeByEmail(adminUser.email)
        existingEmployee ?: employeeManagementService.createEmployee(adminUser)
    }

    override fun onApplicationEvent(p0: ContextRefreshedEvent) {
        createDefaultAdminUser()
    }
}
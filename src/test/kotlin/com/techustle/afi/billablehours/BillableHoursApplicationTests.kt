package com.techustle.afi.billablehours

import com.techustle.afi.billablehours.model.Employee
import com.techustle.afi.billablehours.model.EmployeeJobs
import com.techustle.afi.billablehours.service.EmployeeManagementService
import com.techustle.afi.billablehours.service.InvoiceManagementService
import com.techustle.afi.billablehours.service.JobManagementService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate

@SpringBootTest
class BillableHoursApplicationTests(val invoiceManagementService: InvoiceManagementService, val employeeManagementService: EmployeeManagementService, val jobManagementService: JobManagementService) {

    @Test
    fun contextLoads() {
    }

    @Test
    fun testSaveEmployee() {
        val employee: Employee =  Employee(1, "Godwin","Duah","gentlekobby@gmail.com","1234509876", "Software Engineer","4A",500.0)
        employeeManagementService.createEmployee(employee)
        Assertions.assertNotNull( employeeManagementService.findEmployee(1))
    }

    @Test
    fun testJobSave() {
        val employee: Employee =  Employee(1, "Godwin","Duah","gentlekobby@gmail.com","1234509876", "Software Engineer","4A",500.0)
        val employeeJobs: EmployeeJobs = EmployeeJobs(1, employee, "MTN", LocalDate.now(), "20:00:00", "21:00:00", true, true)
        Assertions.assertNotNull( jobManagementService.addNewJob(employeeJobs))
        Assertions.assertNotNull(jobManagementService.getAllCompanyJobs("MTN"))
    }
}

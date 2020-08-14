package com.techustle.afi.billablehours

import com.techustle.afi.billablehours.model.Employee
import com.techustle.afi.billablehours.model.EmployeeJobs
import com.techustle.afi.billablehours.service.EmployeeManagementService
import com.techustle.afi.billablehours.service.JobManagementService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDate
import java.time.LocalTime


//@DataJpaTest
@SpringBootTest
class BillableHoursApplicationTests() {

    @Test
    fun timeDifferenceTest() {
        Assertions.assertEquals(1,LocalTime.parse("21:00" ).hour - LocalTime.parse("20:00").hour)
    }
}

@SpringBootTest
class JpaTest(@Autowired val employeeManagementService: EmployeeManagementService, @Autowired val jobManagementService: JobManagementService){

    fun encoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }


    @Test
    fun testSaveEmployee() {
        val employee: Employee =  Employee(1, "Godwin","Duah","gentlekobby@gmail.com","1234509876", "LAWYER","4A",500.0)
        employeeManagementService.createEmployee(employee)
        Assertions.assertNotNull( employeeManagementService.findEmployee(1))
    }

    @Test
    fun testJobSave() {
        val employee: Employee =  Employee(1, "Anastasia","Amanfo","aamanfo@gmail.com","1234509876", "FINANCE","3A",400.0)
        val employeeJobs: EmployeeJobs = EmployeeJobs(1, employee, "MTN", LocalDate.now(), mutableListOf(),"20:00:00", "21:00:00", true, true)
        Assertions.assertNotNull( jobManagementService.addNewJob(employeeJobs))
        Assertions.assertNotNull(jobManagementService.getAllCompanyJobs("MTN"))
    }

    @Test
    fun passwordTest() {
        val onAppStart: OnAppStart = OnAppStart(employeeManagementService)
        onAppStart.createDefaultAdminUser()
        val employee = employeeManagementService.findEmployeeByEmail("admin@billablehours.com")
        Assertions.assertTrue(encoder().matches("admin123", employee?.password))
    }
}

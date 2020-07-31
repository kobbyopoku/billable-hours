package com.techustle.afi.billablehours

import com.techustle.afi.billablehours.controller.EmployeeController
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@SpringBootApplication
//@EnableAutoConfiguration
@EnableJpaRepositories("com.techustle.afi.billablehours.repository")
//@ComponentScan(basePackages = "com.techustle.afi.billablehours")
class BillableHoursApplication

fun main(args: Array<String>) {
    runApplication<BillableHoursApplication>(*args)
}

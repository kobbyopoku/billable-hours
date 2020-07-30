package com.techustle.afi.billablehours

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication(scanBasePackages = ["com.techustle.afi.billablehours"])
class BillableHoursApplication

fun main(args: Array<String>) {
    runApplication<BillableHoursApplication>(*args)
}

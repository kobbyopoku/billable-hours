package com.techustle.afi.billablehours.data

import org.springframework.data.annotation.Id
import java.time.LocalDate

data class Jobs(@Id val id:Long, val project: String, val date: LocalDate, val startTime: String, val endTime: String) {
}
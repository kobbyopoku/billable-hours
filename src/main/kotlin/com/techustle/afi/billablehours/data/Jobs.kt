package com.techustle.afi.billablehours.data

import org.springframework.data.annotation.Id
import java.time.LocalDate
import java.time.LocalTime

data class Jobs(@Id val id:Long, val project: String, val date: LocalDate, val startTime: LocalTime, val endTime: LocalTime) {
}
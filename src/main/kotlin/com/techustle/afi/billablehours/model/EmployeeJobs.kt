package com.techustle.afi.billablehours.model

import org.springframework.data.annotation.CreatedDate
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.*

@Entity
class EmployeeJobs(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,

        @ManyToOne
        var employee: Employee?,

        var project: String,

        @CreatedDate
        var date: LocalDate,

        var startTime: LocalTime,

        var endTime: LocalTime
) {



}
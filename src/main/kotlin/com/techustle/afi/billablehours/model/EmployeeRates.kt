package com.techustle.afi.billablehours.model

import org.springframework.data.annotation.CreatedDate
import java.sql.Timestamp
import java.time.LocalDate
import javax.persistence.*

@Entity
class EmployeeRates(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,

        @ManyToOne
        var employee: Employee,

        var project: String,

        @CreatedDate
        var date: LocalDate,

        var startTime: Timestamp,

        var endTime: Timestamp
) {



}
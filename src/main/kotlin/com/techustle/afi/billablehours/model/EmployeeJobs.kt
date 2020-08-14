package com.techustle.afi.billablehours.model

import org.springframework.data.annotation.CreatedDate
import java.time.LocalDate
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

        @OneToMany(fetch = FetchType.LAZY)
        var breaks: MutableList<Break>,

        var startTime: String,

        var endTime: String,

        var status : Boolean,

        var invoiceStatus: Boolean
) {}
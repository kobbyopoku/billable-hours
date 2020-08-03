package com.techustle.afi.billablehours.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Employee(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,
        var firstName: String,
        var lastName: String,
        var email: String,
        var password: String,
        var role: String,
        var grade:String,
        var rate:Double
)
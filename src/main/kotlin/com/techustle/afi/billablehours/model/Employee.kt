package com.techustle.afi.billablehours.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Employee(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,
        var name: String,
        var role: String,
        var level:String,
        var rate:String
) {
        constructor()
}

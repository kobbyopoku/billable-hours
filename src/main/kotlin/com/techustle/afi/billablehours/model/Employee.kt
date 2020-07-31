package com.techustle.afi.billablehours.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
open class Employee(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,
        var name: String,
        var role: String,
        var level:String,
        var rate:String
) {
//        constructor() : this()
}


//data class User(override val id:Long):Employee()
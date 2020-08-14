package com.techustle.afi.billablehours.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
class Break(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,
        var start: String,
        var end: String

) {


}
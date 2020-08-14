package com.techustle.afi.billablehours.repository

import com.techustle.afi.billablehours.model.Break
import org.springframework.data.repository.CrudRepository

interface BreakRepository: CrudRepository<Break, Long> {

}
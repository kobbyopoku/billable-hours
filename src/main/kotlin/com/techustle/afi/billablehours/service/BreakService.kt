package com.techustle.afi.billablehours.service

import com.techustle.afi.billablehours.model.Break
import com.techustle.afi.billablehours.repository.BreakRepository
import org.springframework.stereotype.Service


@Service
class BreakService(val breakRepository: BreakRepository) {

    fun recordBreak(b: Break): Break {
        return breakRepository.save(b)
    }
}
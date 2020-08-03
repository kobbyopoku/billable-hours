package com.techustle.afi.billablehours.data

import com.techustle.afi.billablehours.model.Employee

class AuthResponse(var requestId: String, var message: String, var status: Int, var employee: Employee?) {
}
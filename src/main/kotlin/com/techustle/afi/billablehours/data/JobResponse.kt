package com.techustle.afi.billablehours.data

import com.techustle.afi.billablehours.model.Employee
import com.techustle.afi.billablehours.model.EmployeeJobs

class JobResponse(var requestId: String, var message:String, var status: Int, var employee: Employee?, var jobList: MutableList<EmployeeJobs?>) {
}
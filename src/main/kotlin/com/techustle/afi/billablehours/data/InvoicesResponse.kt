package com.techustle.afi.billablehours.data

import com.techustle.afi.billablehours.model.Invoice

class InvoicesResponse(var requestId: String, var message: String, var status:Int, var itemCount: Int, var invoice: MutableList<Invoice>) {
}
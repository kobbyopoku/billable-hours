package com.techustle.afi.billablehours.data

import com.techustle.afi.billablehours.model.Invoice

class InvoiceResponseObject(var requestId: String, var message: String, var status:Int, var invoice: Invoice?) {
}
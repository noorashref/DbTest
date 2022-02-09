package com.example.storagetest.model.local

data class PersonTable(
    val firstName : String,
    val lastName : String,
    val email : String,
    val phoneNumber : String
)

object PersonSchema{
    const val FIRSTNAME = "first_name"
    const val LASTNAME = "last_name"
    const val EMAIL = "email"
    const val PHONENUMBER = "phone_number"
}

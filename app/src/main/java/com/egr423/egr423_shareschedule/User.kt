package com.egr423.egr423_shareschedule

import java.util.*

data class User (/*@PrimaryKey val userId: UUID = UUID.randomUUID()*/var userId: Int , var firstName: String, var lastName: String, var email: String, var password: String, var birthDate: Date, var userPrivilege: Int, var userComments: String)


//TODO FIRE BASE HAS A EMAIL REGISTRATION OPTION!!!!!!!!!!!!!!!
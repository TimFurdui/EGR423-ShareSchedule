package com.egr423.egr423_shareschedule

import com.google.firebase.firestore.IgnoreExtraProperties
import java.util.*

@IgnoreExtraProperties
data class User (var firstName: String, var lastName: String, var email: String, var password: String, var birthDate: Date, var userPrivilege: Int, var userComments: Array<String>)


//TODO FIRE BASE HAS A EMAIL REGISTRATION OPTION!!!!!!!!!!!!!!!
package com.egr423.egr423_shareschedule

import com.google.firebase.firestore.IgnoreExtraProperties
import java.util.*

@IgnoreExtraProperties
data class User (var firstName: String? = null, var lastName: String? = null, var email: String? = null, var password: String? = null, var birthDate: Date? = null, var userPrivilege: Int? = null/*, var userComments: Array<String>? = null*/)




//TODO FIRE BASE HAS A EMAIL REGISTRATION OPTION!!!!!!!!!!!!!!!
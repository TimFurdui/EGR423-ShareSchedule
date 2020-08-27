package com.egr423.egr423_shareschedule

import com.google.firebase.firestore.IgnoreExtraProperties
import java.util.*

@IgnoreExtraProperties


data class Event(
    var eventTitle: String? = null,
    var eventTime: Date? = null,
//    var eventDaysArray: ArrayList<Date>? = null,
    var attendees: ArrayList<String>,
    var comments: String? = null,
    var eventCreatorEmail: String? = null

) {
    constructor() : this("", null, arrayListOf<String>("testAttendees"), "testComments", "")

}
package com.egr423.egr423_shareschedule

import com.google.type.Date

data class Event(
    var eventTitle: String? = null,
    var eventTime: Date? = null,
    var attendees: List<String>,
    var comments: List<String>,
    var eventCreatorEmail: String? = null
)
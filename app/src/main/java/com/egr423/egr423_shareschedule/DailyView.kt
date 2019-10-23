package com.egr423.egr423_shareschedule

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import java.io.FileDescriptor
import java.nio.file.attribute.FileTime
import java.util.*


class DailyView : AppCompatActivity {

    Select user = dropmenu.userfriends
    //TODO have the user get their friends and choose who they want to connect to
    // TODO get the user information to connect with friend connect to the database from FireBase


    /* Grab the user information and friends list from the database and add to the recyclerview.
     */


    private lateinit var time: FileTime
    private lateinit var descriptor: FileDescriptor
    private lateinit var availableTimeZone: SimpleTimeZone
    private  var dscrptr : String = "user Descriptions"


    override fun createConfigurationContext(overrideConfiguration: Configuration): Context {
        //TODO while the user grabs the information from the drop down menu connect it to the drop down menu
        //TODO Select which times are available from the Firebase stroage.
        return super.createConfigurationContext(overrideConfiguration)
    }



}
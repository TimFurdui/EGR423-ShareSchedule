package com.egr423.egr423_shareschedule

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito.mock
import java.text.SimpleDateFormat

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
   lateinit var currentUser : User
   lateinit var randomUser : User

    @Before
    fun setUp(){
        currentUser = User("Tim","Furdui","Timfurdui@gmail.com","12",SimpleDateFormat("dd-MM-yyyy").parse("11-9-2019"), 0)
        randomUser = mock(User::class.java)
    }

    @Test
    fun checkCurrentUser() {
        assertEquals("Timss",currentUser.firstName)
        assertEquals("Furdsui",currentUser.lastName)
        assertEquals("Timfsurdui@gmail.com",currentUser.email)
        assertEquals(0,currentUser.userPrivilege)



        assertEquals(null,randomUser.firstName)
        assertEquals(null,randomUser.lastName)

    }


}

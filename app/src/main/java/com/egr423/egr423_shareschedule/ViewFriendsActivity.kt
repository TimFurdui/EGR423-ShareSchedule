package com.egr423.egr423_shareschedule

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class ViewFriendsActivity : AppCompatActivity() {

    //db
    private val db = FirebaseFirestore.getInstance()

    //views
    private lateinit var friendView: RecyclerView
    private lateinit var addFriendText: EditText
    private lateinit var addFriendButton: Button

    //adapter
    private lateinit var friendAdapter: RecyclerFriendsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewfriends)
        friendView = findViewById(R.id.friendsRecyclerView)

        addFriendText = findViewById(R.id.addFriendText)
        addFriendButton = findViewById(R.id.submitFriendButton)

        addFriendButton.setOnClickListener {
            if (addFriendText.text.isNotEmpty()) {
                checkIfEmailExists()
            } else {
                Toast.makeText(
                    this,
                    "Friend field is empty, please enter an email and try again.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        getFriends()
    }


    private fun addFriends() {
        if (addFriendText.text.toString().isNotEmpty()) {
            val userQuery = db.collection("users").document(CurrentUserSingleton.userEmail)
            userQuery.update("userFriends", FieldValue.arrayUnion(addFriendText.text.toString()))
        }
    }


    private fun getFriends() {

        db.collection("users").document(CurrentUserSingleton.userEmail).get()
            .addOnSuccessListener {
                try {
                    val friendsList = it.get("userFriends") as ArrayList<String>
                    var recyclerView: RecyclerView = findViewById(R.id.friendsRecyclerView)
                    friendAdapter = RecyclerFriendsAdapter(this, friendsList)
                    recyclerView.adapter = friendAdapter
//                    friendAdapter.queryDbForName()
                    recyclerView.layoutManager = LinearLayoutManager(this)

                } catch (e: TypeCastException) {
                    Log.e(TAG, "No friends exist in database".plus(e))
                }

            }
    }

    private fun realTimeFriendUpdate() {
        val userQuery = db.collection("users").document(CurrentUserSingleton.userEmail)
        userQuery.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }
            if (snapshot != null && snapshot.exists()) {
                getFriends()
                Log.d(TAG, "Current data: ${snapshot.data}")
            } else {
                Log.d(TAG, "Current data: null")
            }
        }
    }

    private fun checkIfEmailExists() {

        val usersReference = db.collection("users")
        usersReference.document(addFriendText.text.toString()).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    addFriends()
                    realTimeFriendUpdate()
                } else if (!document.exists()) {
                    Toast.makeText(
                        this,
                        "No such email exists!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }.addOnFailureListener { e ->
                Log.w(TAG, "Document doesn't exist.".plus(e))
                return@addOnFailureListener
            }
    }

    //TODO CHECK IF EMAIL EXISTS IN DATABASE IF IT DOES WE CAN SET THE SECOND PART OF FRIEND ITEM TO THE NAME FOR GIVEN EMAIL

    companion object {
        private val TAG = "ViewFriendsActivity"
    }
}
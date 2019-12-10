package com.egr423.egr423_shareschedule

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class RecyclerFriendsAdapter(
    contextParameterVal: Context,
    friendsParameterValue: ArrayList<String>?
) : RecyclerView.Adapter<RecyclerFriendsAdapter.ViewHolder>() {

    var context: Context = contextParameterVal
    var friendEmailsList: ArrayList<String>? = friendsParameterValue
//    var friendNameList: ArrayList<String>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.friend_list_item, parent, false)
        var holder: ViewHolder = ViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.w(TAG, "onBindViewHolder: called.")
//        holder.friendName.setText(friendNameList?.get(position))
        holder.friendEmail.setText(friendEmailsList?.get(position))

        holder.parentLayout.setOnClickListener {


            //TODO OPEN ANOTHER CALENDAR
            //TODO LET USER SELECT DATE
            //TODO QUERY DB AND SEE IF IT TIMES WORK FOR BOTH
            //TODO IF TIME WORKS FOR BOTH THEN ADD TO BOTH
        }
    }


    override fun getItemCount(): Int {
        return friendEmailsList!!.size
    }


    inner class ViewHolder : RecyclerView.ViewHolder {

        //        var friendName: TextView
        var friendEmail: TextView
        var parentLayout: LinearLayout


        constructor(itemView: View) :
                super(itemView) {
//            friendName = itemView.findViewById(R.id.friendItemName)
            friendEmail = itemView.findViewById(R.id.friendItemEmail)
            parentLayout = itemView.findViewById(R.id.friendParent_listItem_layout)
        }
    }


    companion object {
        private val TAG = "RecyclerFriendsAdapter"
    }

//    val db = FirebaseFirestore.getInstance()


//    fun queryDbForName() {
//
//
//            Log.w("FRIEND EMAIL LIST", friendEmailsList.toString())
//            var friendName: ArrayList<String>? = null
//
//            for (string in friendEmailsList!!) {
//                db.collection("users").document(string).get().addOnSuccessListener { document ->
//
//                    val firstName = document.get("firstName") as String?
//                    val lastName = document.get("lastName") as String?
//                    val name = "$firstName $lastName"
//
//                    friendName?.add(name)
//
//                    Log.w(TAG, document.toString())
//                    Log.w("NAME TEST", name)
//                    Log.w("LIST TEST ", friendName.toString())
//                }
//            }
//
//    }

}
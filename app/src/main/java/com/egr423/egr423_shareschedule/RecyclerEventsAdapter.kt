package com.egr423.egr423_shareschedule

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class RecyclerEventsAdapter(
    contextParameterVal: Context,
    eventTimesParameterVal: ArrayList<Event>?
) : RecyclerView.Adapter<RecyclerEventsAdapter.ViewHolder>() {

    var context: Context = contextParameterVal
    var eventArrayList: ArrayList<Event>? = eventTimesParameterVal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.event_list_item, parent, false)
        var holder: ViewHolder = ViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.w(TAG, "onBindViewHolder: called.")
        holder.eventTitle.setText(sortEvents()?.get(position)?.eventTitle)
        holder.eventTimeDate.setText(sortEvents()?.get(position)?.eventTime.toString())
        holder.eventComment.setText(sortEvents()?.get(position)?.comments.toString())
    }


    override fun getItemCount(): Int {
        return sortEvents()!!.size
    }


    inner class ViewHolder : RecyclerView.ViewHolder {

        var eventTimeDate: TextView
        var eventTitle: TextView
        var eventComment: TextView
        var parentLayout: LinearLayout


        constructor(itemView: View) :
                super(itemView) {
            eventTimeDate = itemView.findViewById(R.id.eventItemDate)
            eventComment = itemView.findViewById(R.id.eventItemComment)
            eventTitle = itemView.findViewById(R.id.eventItemName)
            parentLayout = itemView.findViewById(R.id.eventParent_listItem_layout)
        }
    }


    companion object {
        private val TAG = "RecyclerEventsAdapter"
    }

    private fun sortEvents(): ArrayList<Event>? {

        return eventArrayList?.sortedWith(compareBy { it.eventTime })!!.toArrayList()
    }

    private fun <T> List<T>.toArrayList(): ArrayList<T> {
        return ArrayList(this)
    }

}
package com.example.scheduler2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(val titleList: ArrayList<String?>, val descriptionList: ArrayList<String?>, val priorityList: ArrayList<String?>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    //returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(v)
    }

    //binding the data in the lists
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(titleList[position])
        holder.bindItems(descriptionList[position])
        holder.bindItems(priorityList[position])
    }

    //giving the size of the list
    override fun getItemCount(): Int {
        return titleList.size
    }

    //holding the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(title: String?, description: String?, priority: String?) {
            val textViewTitle = itemView.findViewById(R.id.textViewTitle) as TextView
            val textViewDescription  = itemView.findViewById(R.id.textViewDescription) as TextView
            val textViewPriority  = itemView.findViewById(R.id.textViewPriority) as TextView
        }
    }
}

private fun CustomAdapter.ViewHolder.bindItems(title: String?) {

}

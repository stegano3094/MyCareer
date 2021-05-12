package com.stegano.mycareer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class ProjectData(val resId: Int, val name: String?)

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val imageView: ImageView = view.findViewById(R.id.imageView)
    val textView: TextView = view.findViewById(R.id.textView)
}

class ProjectRecyclerViewAdapter(
    val context: Context,
    val items: List<ProjectData>) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.cardview_project, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.imageView.setImageResource(items[position].resId)
        holder.textView.text = items[position].name
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
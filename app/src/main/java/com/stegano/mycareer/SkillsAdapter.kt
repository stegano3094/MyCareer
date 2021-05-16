package com.stegano.mycareer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val imageView: ImageView = view.findViewById(R.id.imageView)
    val textView: TextView = view.findViewById(R.id.detailProjectName)
    val cardView: CardView = view.findViewById(R.id.cardView)
}

data class SkillData(val resId: Int, val name: String)

class SkillsAdapter(
    val context: Context, val items: List<SkillData>) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.cardview_skill, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.imageView.setImageResource(items[position].resId)
        holder.textView.text = items[position].name

//        holder.cardView.setOnClickListener {
//            val intent = Intent(context, DetailSkillActivity::class.java)
//            intent.putExtra("click_skill", items[position].name)
//            context.startActivity(intent)
//        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
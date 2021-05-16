package com.stegano.mycareer

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ProjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val projectImageThumbnail = view.findViewById<ImageView>(R.id.projectImage)
    val projectName = view.findViewById<TextView>(R.id.projectName)
    val projectSkills = view.findViewById<TextView>(R.id.projectSkills)
    val cardView = view.findViewById<CardView>(R.id.cardView)
}

data class ProjectData(val thumbnailName: String, val thumbnailSkills: String, val thumbnailUri: String)

class ProjectAdapter(val context: Context, val items: List<ProjectData>): RecyclerView.Adapter<ProjectViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_project, parent, false)

        return ProjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        Picasso.get()
            .load(Uri.parse(items[position].thumbnailUri))
            .fit()
            .centerCrop()
            .error(R.drawable.notebook)  // 이미지 로드 실패시 보여줄 이미지
            .into(holder.projectImageThumbnail)  // 이미지 로드 성공 시 이미지 삽입함
        holder.projectName.text = items[position].thumbnailName
        holder.projectSkills.text = items[position].thumbnailSkills
        holder.cardView.setOnClickListener {
            val intent = Intent(context, ProjectDetailActivity::class.java)
            intent.putExtra("clicked_project_name", items[position].thumbnailName)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
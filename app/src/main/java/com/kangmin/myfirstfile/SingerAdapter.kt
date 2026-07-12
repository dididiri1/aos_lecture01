package com.kangmin.myfirstfile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SingerAdapter(val items : MutableList<Course>) : RecyclerView.Adapter<SingerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SingerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.singer_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SingerAdapter.ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
       return items.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        private val courseThumbnail = itemView.findViewById<ImageView>(R.id.courseThumbnail)
        private val courseBadge = itemView.findViewById<TextView>(R.id.courseBadge)
        private val courseTitleText = itemView.findViewById<TextView>(R.id.courseTitleText)

        fun bindItems(item : Course) {
            courseThumbnail.setImageResource(item.thumbnailResId)
            courseBadge.text = item.badge
            courseTitleText.text = item.title
        }
    }
}
package com.kangmin.myfirstfile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListViewAdapter (val List : MutableList<ListViewModal>) : BaseAdapter() {
    override fun getCount(): Int {
        return List.size
    }

    override fun getItem(position: Int): Any? {
        return List[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?
    ): View? {
        var convertView = convertView

        if (convertView == null) {
            convertView = LayoutInflater.from(parent?.context).inflate(R.layout.listview_item, parent, false)
        }

        val title = convertView!!.findViewById<TextView>(R.id.itemTitle)
        val content = convertView!!.findViewById<TextView>(R.id.itemContent)
        title.text = List[position].title
        content.text = List[position].content

        return  convertView!!
    }
}
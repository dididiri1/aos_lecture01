package com.kangmin.myfirstfile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class SentenceListViewAdapter(var list : MutableList<String>) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any? {
        return list[position]
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
            convertView = LayoutInflater.from(parent?.context).inflate(R.layout.sentencelistview_item, parent, false)
        }

        val sentenceTextView = convertView.findViewById<TextView>(R.id.sentenceItemTextView)
        val authorTextView = convertView.findViewById<TextView>(R.id.sentenceItemAuthor)

        // "명언 - 저자" 형태를 마지막 " - " 기준으로 분리
        val text = list[position]
        val separatorIndex = text.lastIndexOf(" - ")
        if (separatorIndex != -1) {
            sentenceTextView.text = text.substring(0, separatorIndex)
            authorTextView.text = "- " + text.substring(separatorIndex + 3)
            authorTextView.visibility = View.VISIBLE
        } else {
            // 구분자가 없으면 전체를 명언으로 표시하고 저자는 숨김
            sentenceTextView.text = text
            authorTextView.visibility = View.GONE
        }

        return  convertView!!
    }
}
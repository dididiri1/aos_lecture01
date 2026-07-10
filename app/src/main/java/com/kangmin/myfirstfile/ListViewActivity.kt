package com.kangmin.myfirstfile

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ListViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        val list_item = mutableListOf<ListViewModal>()

        list_item.add(ListViewModal("제목입니다1", "내용입니다1"))
        list_item.add(ListViewModal("제목입니다2", "내용입니다2"))
        list_item.add(ListViewModal("제목입니다3", "내용입니다3"))

        val mainListView = findViewById<ListView>(R.id.mainListView)
        val listViewAdapter = ListViewAdapter(list_item)
        mainListView.adapter = listViewAdapter
    }
}

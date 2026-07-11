package com.kangmin.myfirstfile

import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_view)

        val list = mutableListOf<String>()
        list.add("a")
        list.add("b")
        list.add("c")

        val listViewAdapter = ListViewAdapter(list)
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = listViewAdapter
    }
}
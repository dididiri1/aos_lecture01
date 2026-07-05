package com.kangmin.myfirstfile

import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var backPressedTime: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list_item = mutableListOf<ListViewModal>()

        list_item.add(ListViewModal("제목입니다1", "내용입니다1"))
        list_item.add(ListViewModal("제목입니다2", "내용입니다2"))
        list_item.add(ListViewModal("제목입니다3", "내용입니다3"))

        val mainListView = findViewById<ListView>(R.id.mainListView)
        val listViewAdapter = ListViewAdapter(list_item)
        mainListView.adapter = listViewAdapter

        setupBackPressToExit()
    }

    private fun setupBackPressToExit() {
        onBackPressedDispatcher.addCallback(this) {
            if (System.currentTimeMillis() - backPressedTime < 2000) {
                finish()
            } else {
                backPressedTime = System.currentTimeMillis()
                Toast.makeText(this@MainActivity, "종료하려면 뒤로가기를 한번 더 눌러주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

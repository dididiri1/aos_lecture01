package com.kangmin.myfirstfile

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SentenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sentence)

        val list = mutableListOf<String>()
        list.add("성공은 최종적인 것이 아니고, 실패는 치명적인 것이 아니다. 중요한 것은 계속하는 용기다. - 윈스턴 처칠")
        list.add("당신이 할 수 있다고 믿든 할 수 없다고 믿든, 당신의 믿음이 옳다. - 헨리 포드")
        list.add("가장 큰 영광은 한 번도 실패하지 않음이 아니라 실패할 때마다 다시 일어서는 데에 있다. - 넬슨 만델라")
        list.add("오늘 할 수 있는 일에 전력을 다하라. 그러면 내일은 한 걸음 더 나아가 있을 것이다. - 아이작 뉴턴")
        list.add("시작이 반이다. - 아리스토텔레스")
        list.add("행복은 습관이다. 그것을 몸에 지녀라. - 허버트")
        list.add("천 리 길도 한 걸음부터 시작된다. - 노자")
        list.add("배움에는 끝이 없으며, 멈추는 순간 늙기 시작한다. - 헨리 포드")
        list.add("어제로부터 배우고, 오늘을 살며, 내일을 꿈꿔라. - 알베르트 아인슈타인")

        val listViewAdapter = SentenceListViewAdapter(list)
        val sentenceListView = findViewById<ListView>(R.id.sentenceListView)

        sentenceListView.adapter = listViewAdapter
    }
}
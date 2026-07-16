package com.kangmin.myfirstfile

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val examples = listOf(
            "연습중 (MainActivity)" to MainActivity::class.java,
            "BTS 연습" to Bts1Activity::class.java,
            "이미지 목록" to ImageListActivity::class.java,
            "주사위 굴리기" to DiceActivity::class.java,
            "ListView + 커스텀 어댑터" to ListViewActivity::class.java,
            "명언 (랜덤 + 전체 리스트)" to SentenceMainActivity::class.java,
            "RecyclerView 연습" to RVActivity::class.java,
            "Fragment + Navigation 연습" to FragmentNavActivity::class.java,
            "운동 메모 + Realtime Database" to WorkoutMemoActivity::class.java,
        )

        val buttonHeight = (56 * resources.displayMetrics.density).toInt()
        val buttonMargin = (10 * resources.displayMetrics.density).toInt()

        val container = findViewById<LinearLayout>(R.id.menuContainer)
        examples.forEach { (label, activityClass) ->
            val button = Button(this).apply {
                text = label
                isAllCaps = false
                textSize = 16f
                setTypeface(typeface, Typeface.BOLD)
                setTextColor(Color.WHITE)
                background = ContextCompat.getDrawable(this@MenuActivity, R.drawable.bg_button_gradient)
                stateListAnimator = null
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    buttonHeight
                ).apply { topMargin = buttonMargin }
                setOnClickListener {
                    startActivity(Intent(this@MenuActivity, activityClass))
                }
            }
            container.addView(button)
        }
    }
}

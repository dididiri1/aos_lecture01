package com.kangmin.myfirstfile

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ImageInsideMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_image_inside_main)

        val getDate = intent.getStringExtra("data")
        Toast.makeText(this, getDate,Toast.LENGTH_LONG).show()

        val memberImageArea = findViewById<ImageView>(R.id.memberImageArea);

        if (getDate == "1") {
            memberImageArea.setImageResource(R.drawable.react1)
        }
        if (getDate == "2") {
            memberImageArea.setImageResource(R.drawable.react2)
        }
        if (getDate == "3") {
            memberImageArea.setImageResource(R.drawable.react3);
        }
    }
}
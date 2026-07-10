package com.kangmin.myfirstfile

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val image1 = findViewById<ImageView>(R.id.btnImage1);
        image1.setOnClickListener {
            Toast.makeText(this, "1번 이미지 클릭!", Toast.LENGTH_LONG).show();
        }
    }
}

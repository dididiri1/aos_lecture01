package com.kangmin.myfirstfile

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. 화면 클릭
        val image1 = findViewById<ImageView>(R.id.btnImage1);
        image1.setOnClickListener {
            //Toast.makeText(this, "1번 이미지 클릭!", Toast.LENGTH_LONG).show();

            // 2. 화면 클릭하면, 화면으로 이동
            val intent = Intent(this, ImageInsideMainActivity::class.java);
            intent.putExtra("data", "1")
            startActivity(intent);
        }

        // 1. 화면 클릭
        val image2 = findViewById<ImageView>(R.id.btnImage2);
        image2.setOnClickListener {
            //Toast.makeText(this, "1번 이미지 클릭!", Toast.LENGTH_LONG).show();

            // 2. 화면 클릭하면, 화면으로 이동
            val intent = Intent(this, ImageInsideMainActivity::class.java);
            intent.putExtra("data", "2")
            startActivity(intent);
        }

        val image3 = findViewById<ImageView>(R.id.btnImage3);
        image3.setOnClickListener {
            //Toast.makeText(this, "1번 이미지 클릭!", Toast.LENGTH_LONG).show();

            // 2. 화면 클릭하면, 화면으로 이동
            val intent = Intent(this, ImageInsideMainActivity::class.java);
            intent.putExtra("data", "3")
            startActivity(intent);
        }
    }
}

package com.kangmin.myfirstfile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kangmin.myfirstfile.databinding.ActivityImageListBinding

class ImageListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_list)

        // 1. 화면 클릭
        binding.btnImage1.setOnClickListener {
            //Toast.makeText(this, "1번 이미지 클릭!", Toast.LENGTH_LONG).show();

            // 2. 화면 클릭하면, 화면으로 이동
            val intent = Intent(this, ImageInsideMainActivity::class.java);
            intent.putExtra("data", "1")
            startActivity(intent);
        }

        // 1. 화면 클릭
        binding.btnImage2.setOnClickListener {
            //Toast.makeText(this, "1번 이미지 클릭!", Toast.LENGTH_LONG).show();

            // 2. 화면 클릭하면, 화면으로 이동
            val intent = Intent(this, ImageInsideMainActivity::class.java);
            intent.putExtra("data", "2")
            startActivity(intent);
        }

        binding.btnImage3.setOnClickListener {
            //Toast.makeText(this, "1번 이미지 클릭!", Toast.LENGTH_LONG).show();

            // 2. 화면 클릭하면, 화면으로 이동
            val intent = Intent(this, ImageInsideMainActivity::class.java);
            intent.putExtra("data", "3")
            startActivity(intent);
        }
    }
}

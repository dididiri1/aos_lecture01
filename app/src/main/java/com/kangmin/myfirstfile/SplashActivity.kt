package com.kangmin.myfirstfile

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class SplashActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        auth = Firebase.auth

        if (auth.currentUser != null) {
            Toast.makeText(this, "로그인 되어있음", Toast.LENGTH_LONG).show()
            moveToMain()
        } else {
            auth.signInAnonymously()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "비회원 로그인 성공", Toast.LENGTH_LONG).show()
                        moveToMain()
                    } else {
                        Toast.makeText(this, "비회원 로그인 실패", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    private fun moveToMain() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)
    }
}
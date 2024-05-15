package com.example.loginpage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class ResultActivity : AppCompatActivity() {
    lateinit var tv_result :TextView
    lateinit var btn_GoBack : Button
    lateinit var btn_logout: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var intentGoback:Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        tv_result = findViewById(R.id.tv_result)


//        초기화면으로
        btn_GoBack = findViewById(R.id.btn_GoBack)
        btn_GoBack.setOnClickListener {
            intentGoback = Intent(this, MainActivity::class.java)
            startActivity(intentGoback)
        }

        if(intent.hasExtra("success")) //로그인
        {
            tv_result.text = intent.getStringExtra("success")
        }

////        로그아웃
//        val logout = findViewById<Button>(R.id.btn_logout)
//        logout.setOnClickListener {
//            Firebase.auth.signOut()
//            startActivity(intentGoback)
//        }
    }
}
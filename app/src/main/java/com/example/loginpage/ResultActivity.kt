package com.example.loginpage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class ResultActivity : AppCompatActivity() {
    lateinit var tv_result :TextView
    lateinit var btn_GoBack : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)


        tv_result = findViewById(R.id.tv_result)
        if(intent.hasExtra("success")) //로그인
        {
            tv_result.text = intent.getStringExtra("success")
        }
        else if(intent.hasExtra("find")) //ID,PW찾기
        {
            tv_result.text = intent.getStringExtra("find")
        }
//        초기화면으로
        btn_GoBack = findViewById(R.id.btn_GoBack)
        btn_GoBack.setOnClickListener {
            val intentGoback = Intent(this, MainActivity::class.java)
            startActivity(intentGoback)
        }
    }
}
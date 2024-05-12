package com.example.loginpage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var et_id :EditText
    lateinit var et_pw :EditText
    lateinit var btn_confirm : Button
    lateinit var btn_find : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        et_id = findViewById(R.id.et_id)
        et_pw = findViewById(R.id.et_pw)
        btn_confirm = findViewById(R.id.btn_confirm)
        btn_find = findViewById(R.id.btn_find)
//intent to ResultActivity
        val intentResult = Intent(this,ResultActivity::class.java)
        btn_confirm.setOnClickListener {
            if (et_id.text.toString()=="android"&& et_pw.text.toString()=="kotlin2024") {
                intentResult.putExtra("success", "안녕하세요\n"+et_id.text.toString()+"님!")
                startActivity(intentResult)
                finish()
            }
            else {
                // 아이디 또는 비밀번호가 잘못된 경우 에러 메시지 표시
                // .requestFocus(): 에러가 난 해당 textarea로 이동
                if (et_id.text.toString()!="android") {
                    et_id.error = "올바른 아이디를 입력하세요"
                    et_id.requestFocus()
                } else {
                    et_id.error = null
                }

                if (et_pw.text.toString()!="kotlin2024") {
                    et_pw.error = "올바른 비밀번호를 입력하세요"
                    et_id.requestFocus()
                } else {
                    et_pw.error = null
                }
            }
        }
//        ID,PW 찾기
        btn_find.setOnClickListener {
            intentResult.putExtra("find","id:android \npw:kotlin2024")
            startActivity((intentResult))
            finish()
        }


    }
}
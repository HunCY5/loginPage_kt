package com.example.loginpage

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {
    lateinit var et_id :EditText
    lateinit var et_pw :EditText
    lateinit var btn_join : Button
    lateinit var btn_search: Button
    private lateinit var auth: FirebaseAuth
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        et_id = findViewById(R.id.et_id)
        et_pw = findViewById(R.id.et_pw)
        val intentResult = Intent(this,ResultActivity::class.java)
        val email = findViewById<EditText>(R.id.et_id)
        val password = findViewById<EditText>(R.id.et_pw)
        val login = findViewById<Button>(R.id.btn_confirm)

//        로그인 버튼
        auth = Firebase.auth
        login.setOnClickListener {
            auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        intentResult.putExtra("success", "로그인 성공!")
                        startActivity(intentResult)
                        finish()
                    }
                    else {
                        Toast.makeText(this@MainActivity, "로그인 실패", Toast.LENGTH_LONG).show()
                        et_id.error = "로그인 실패"
                        et_pw.error = "로그인 실패"

                    }
                }
        }

//        intent to joinPage
        btn_join = findViewById(R.id.btn_join)
        btn_join.setOnClickListener {
            val intentJoin = Intent(this, JoinPage::class.java)
            startActivity(intentJoin)
        }
//        intent to searchPage
        btn_search = findViewById(R.id.btn_search)
        btn_search.setOnClickListener {
            val intentSearch = Intent(this, SearchActivity::class.java)
            startActivity(intentSearch)
        }


    }
}
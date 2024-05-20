package com.example.loginpage

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class SearchActivity : AppCompatActivity() {
    lateinit var btn_back: Button
    lateinit var btn_search: Button
    lateinit var tv_id : TextView
    lateinit var tv_pw : TextView
    lateinit var intent_back: Intent
    private val db = Firebase.firestore

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search)
//intent to 초기화면
        btn_back = findViewById(R.id.btn_back)
        btn_back.setOnClickListener {
            intent_back = Intent(this, MainActivity::class.java)
            startActivity(intent_back)
        }
// id,pw찾기 버튼
        tv_id = findViewById(R.id.tv_id)
        tv_pw = findViewById(R.id.tv_pw)
        btn_search = findViewById(R.id.btn_search)
        btn_search.setOnClickListener {
            val name = findViewById<EditText>(R.id.et_name).text.toString()
            if (name.isNotEmpty()) {
                db.collection("users")
                    .whereEqualTo("Name", name)
                    .get()
                    .addOnSuccessListener { documents ->
                        if (!documents.isEmpty) {
                            for (document in documents) {
                                tv_id.text = document.getString("ID")
                                tv_pw.text = document.getString("PW")
                            }
                        } else {
                            Toast.makeText(this, "일차하는 계정정보가 없습니다.", Toast.LENGTH_LONG).show()
                        }
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "불러오기 실패: ${e.message}", Toast.LENGTH_LONG)
                            .show()
                    }
            } else {
                Toast.makeText(this, "이름을 입력해주세요", Toast.LENGTH_LONG).show()
            }
        }
    }
}



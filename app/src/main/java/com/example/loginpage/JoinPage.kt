package com.example.loginpage

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
import com.google.firebase.firestore.firestore

class JoinPage : AppCompatActivity() {
    lateinit var btn_back: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var intent_back: Intent
    private val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_join_page)
//        intent to 초기화면
        btn_back = findViewById(R.id.btn_back)
        btn_back.setOnClickListener {
            intent_back = Intent(this, MainActivity::class.java)
            startActivity(intent_back)

        }


//        회원가입 버튼
        auth = Firebase.auth
        val join = findViewById<Button>(R.id.btn_join)
        join.setOnClickListener {
            val email = findViewById<EditText>(R.id.et_email)
            val password = findViewById<EditText>(R.id.et_pw)
            val nickname = findViewById<EditText>(R.id.et_nickname)
            val age = findViewById<EditText>(R.id.et_age)
            auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        if(user != null)
                        {
                            val userData = hashMapOf(
                                "uid" to user.uid,
                                "ID" to email.text.toString(),
                                "PW" to password.text.toString(),
                                "Name" to nickname.text.toString(),
                                "Age" to age.text.toString()
                            )

                            db.collection("users")
                                .document(user.uid)
                                .set(userData)
                                .addOnSuccessListener {
                                    Toast.makeText(this, "회원가입 완료", Toast.LENGTH_LONG).show()
//                        성공시, intent to 초기화면
                                    startActivity(intent_back)
                                }
                                .addOnFailureListener { e ->
                                    Toast.makeText(this, "Firestore 저장 실패: ${e.message}", Toast.LENGTH_LONG).show()
                                }

                        }

                    }
                    else {
                        Toast.makeText(this, "회원가입 실패", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}
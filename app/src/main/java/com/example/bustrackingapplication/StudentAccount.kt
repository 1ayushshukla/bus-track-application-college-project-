package com.example.bustrackingapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bustrackingapplication.databinding.ActivitySignInBinding
import com.example.bustrackingapplication.databinding.ActivityStudentAccountBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class StudentAccount : AppCompatActivity() {

    lateinit var binding : ActivityStudentAccountBinding
    lateinit var db : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnLogout.setOnClickListener {
            finish()
        }

        val id = intent.getStringExtra(SignIn.KEY1)
        val pass = intent.getStringExtra(SignIn.KEY2)
        val name = intent.getStringExtra(SignIn.KEY3)

        binding.tvId.text = "College Id - $id"
        binding.tvName.text = "Stu Name - $name"
        binding.tvPass.text = "Password - $pass"

    }
}
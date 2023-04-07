package com.example.bustrackingapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bustrackingapplication.databinding.ActivityBusAccountPageBinding
import com.example.bustrackingapplication.databinding.ActivityBusSignUpBinding
import com.example.bustrackingapplication.databinding.ActivityStudentAccountBinding
import com.google.firebase.database.DatabaseReference

class Bus_AccountPage : AppCompatActivity() {

    lateinit var binding: ActivityBusAccountPageBinding
    lateinit var db : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusAccountPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnLogout.setOnClickListener {
            finish()
        }

        val id = intent.getStringExtra(SignIn.KEY1)
        val pass = intent.getStringExtra(SignIn.KEY2)
        val name = intent.getStringExtra(SignIn.KEY3)

        binding.tvId.text = "Bus Id - $id"
        binding.tvName.text = "Owner Name - $name"
        binding.tvPass.text = "Password - $pass"

    }
}
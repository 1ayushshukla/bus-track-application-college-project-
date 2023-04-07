package com.example.bustrackingapplication

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.core.text.isDigitsOnly
import com.example.bustrackingapplication.databinding.ActivitySignUpBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SignUp : AppCompatActivity() {

    private lateinit var binding : ActivitySignUpBinding
    lateinit var db : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.btnSignUp.setOnClickListener {
            db = FirebaseDatabase.getInstance().getReference("Students")
            if(binding.etId.text?.isBlank() == true || binding.etName.text?.isBlank() == true || binding.etPass.text?.isBlank() == true || binding.etId.text?.isDigitsOnly() == false){
                Toast.makeText(this, "Fill Up All The Fields", Toast.LENGTH_SHORT).show()
            }else {
                val name = binding.etName.text.toString()
                val id = binding.etId.text.toString()
                val pass = binding.etPass.text.toString()

                val student = studentSignUp(id, name, pass)

                db.child(id).setValue(student).addOnSuccessListener {
                    val alert = AlertDialog.Builder(this)
                    alert.setTitle("Account Created Successfully.")
                    alert.setMessage("Login into the account.")
                    alert.setPositiveButton("Login", DialogInterface.OnClickListener { dialogInterface, i ->
                        finish()
                    })
                    alert.setIcon(R.drawable.baseline_done_24)
                    alert.show()
                }.addOnFailureListener {
                    Toast.makeText(this, "Try Again!", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }
}
package com.example.bustrackingapplication

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.text.isDigitsOnly
import com.example.bustrackingapplication.databinding.ActivityBusSignInBinding
import com.example.bustrackingapplication.databinding.ActivityBusSignUpBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Bus_SignUp : AppCompatActivity() {

    lateinit var binding : ActivityBusSignUpBinding
    lateinit var db : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener {
            db = FirebaseDatabase.getInstance().getReference("Bus")
            if(binding.etId.text?.isBlank() == true || binding.etName.text?.isBlank() == true || binding.etPass.text?.isBlank() == true){
                Toast.makeText(this, "Fill Up All The Fields", Toast.LENGTH_SHORT).show()
            }else {
                val name = binding.etName.text.toString()
                val id = binding.etId.text.toString()
                val pass = binding.etPass.text.toString()

                val bus = busSignUp(id, name, pass)

                db.child(id).setValue(bus).addOnSuccessListener {
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
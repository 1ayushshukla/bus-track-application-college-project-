package com.example.bustrackingapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bustrackingapplication.databinding.ActivityBusSignInBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Bus_signIn : AppCompatActivity() {

    lateinit var binding : ActivityBusSignInBinding
    lateinit var db : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusSignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btSignUpActivity.setOnClickListener {
            val intent = Intent(this, Bus_SignUp::class.java)
            startActivity(intent)
        }

        binding.btnSignIn.setOnClickListener {
            db = FirebaseDatabase.getInstance().getReference("Bus")
            if(binding.et2Id.text?.isBlank() == true || binding.et2Pass.text?.isBlank() == true){
                Toast.makeText(this, "Fill all the fields!", Toast.LENGTH_SHORT).show()
            }else{
                val id = binding.et2Id.text.toString()
                val pass = binding.et2Pass.text.toString()
                db.child(id).get().addOnSuccessListener {
                    // Does User exists?
                    if(it.exists()){
                        //Authenticate
                        val storedPass = it.child("pass").value
                        val storedName = it.child("owner_name").value
                        if(pass==storedPass){
                            Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show()
                            binding.et2Id.text?.clear()
                            binding.et2Pass.text?.clear()
                            val intent = Intent(this, Bus_AccountPage::class.java)
                            intent.putExtra(SignIn.KEY1, id)
                            intent.putExtra(SignIn.KEY2, pass)
                            intent.putExtra(SignIn.KEY3, storedName.toString())
                            startActivity(intent)
                        }else{
                            binding.et2Pass.text?.clear()
                            Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(this, "User does not exists.", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }



    }
}
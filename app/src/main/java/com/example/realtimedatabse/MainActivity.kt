package com.example.realtimedatabse

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.realtimedatabse.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binder : ActivityMainBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binder.root)

        binder.registerBtn.setOnClickListener {
            val firstName = binder.firstName.text.toString()
            val lastName = binder.lastName.text.toString()
            val age = binder.age.text.toString()
            val userName = binder.userName.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Users")
            val user = User(firstName,lastName,age,userName)
            database.child(userName).setValue(user).addOnSuccessListener {
                binder.firstName.text.clear()
                binder.lastName.text.clear()
                binder.age.text.clear()
                binder.userName.text.clear()

                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }

        }
    }
}
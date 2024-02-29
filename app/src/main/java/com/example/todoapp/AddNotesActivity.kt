package com.example.todoapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

class AddNotesActivity : AppCompatActivity() {
    private lateinit var addYourNotes : AppCompatEditText
    private lateinit var saveYourDataButton : AppCompatButton
    private lateinit var dataBaseActivity : TodoDataBaseHelper
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)

        addYourNotes = findViewById(R.id.add_yourDesc)
        saveYourDataButton = findViewById(R.id.saveYourData)

        dataBaseActivity = TodoDataBaseHelper(this)

        saveYourDataButton.setOnClickListener {
            val desc = addYourNotes.text.toString()
            val addNotes = UserNotesModel(0, desc)
            dataBaseActivity.insertUserNotes(addNotes)
            finish()
            Toast.makeText(this, "Your Note Save", Toast.LENGTH_SHORT).show()
        }
    }
    
}
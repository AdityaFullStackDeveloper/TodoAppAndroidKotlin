package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

class UpdateActivity : AppCompatActivity() {
    private lateinit var notesDataBaseHelper: TodoDataBaseHelper
    private lateinit var updateEditText : AppCompatEditText
    private lateinit var updateData : AppCompatButton
    private var notesId = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        notesDataBaseHelper = TodoDataBaseHelper(this)
        updateEditText = findViewById(R.id.update_yourDesc)
        updateData = findViewById(R.id.updateYourData)

        notesId = intent.getIntExtra("notes", -1)
        if (notesId == -1){
            finish()
            return
        }

        val notesUpdate = notesDataBaseHelper.getNoteById(notesId)
        updateEditText.setText(notesUpdate.userDesc)

        updateData.setOnClickListener {
            val newTitle = updateEditText.text.toString()
            val updateYourNotes = UserNotesModel(notesId, newTitle)

            notesDataBaseHelper.updateNotes(updateYourNotes)
            finish()
            Toast.makeText(this, "Update data Successful", Toast.LENGTH_SHORT).show()
        }

    }
}
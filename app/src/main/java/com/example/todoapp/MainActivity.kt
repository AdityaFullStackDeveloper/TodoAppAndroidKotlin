package com.example.todoapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var addNotesButton : FloatingActionButton
    private lateinit var todoRecyclerView : RecyclerView
    private lateinit var userAdapter: UserNotesAdapter
    private lateinit var userNotesDataBaseHelper: TodoDataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addNotesButton = findViewById(R.id.addNotes_Button)
        todoRecyclerView = findViewById(R.id.todo_recyclerView)

        userNotesDataBaseHelper = TodoDataBaseHelper(this)

        userAdapter = UserNotesAdapter(this, userNotesDataBaseHelper.insertedAllNotes())
        todoRecyclerView.layoutManager =LinearLayoutManager(this)
        todoRecyclerView.adapter = userAdapter

        addNotesButton.setOnClickListener {
            addDataFromAlertDialog()
        }
    }

    @SuppressLint("InflateParams", "NotifyDataSetChanged", "MissingInflatedId")
    private fun addDataFromAlertDialog(){
        startActivity(Intent(this, AddNotesActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        userAdapter.refresh(userNotesDataBaseHelper.insertedAllNotes())
    }
}
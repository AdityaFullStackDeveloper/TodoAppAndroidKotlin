package com.example.todoapp

import android.annotation.SuppressLint
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
    private lateinit var userAdapter: UserAdapter

    private val userList = ArrayList<UserModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addNotesButton = findViewById(R.id.addNotes_Button)
        todoRecyclerView = findViewById(R.id.todo_recyclerView)

        userList.add(UserModel("Aditya"))

        userAdapter =UserAdapter(this, userList)
        todoRecyclerView.layoutManager =LinearLayoutManager(this)
        todoRecyclerView.adapter = userAdapter

        addNotesButton.setOnClickListener {
            addDataFromAlertDialog()
        }
    }

    @SuppressLint("InflateParams", "NotifyDataSetChanged")
    private fun addDataFromAlertDialog(){
        val alertDialog = AlertDialog.Builder(this)
        val layout = LayoutInflater.from(this).inflate(R.layout.user_add_data, null, false)
        val userNotes : AppCompatEditText = findViewById(R.id.add_yourNotes)
        val saveData : AppCompatButton = findViewById(R.id.saveYourData)

        val dialog = alertDialog.create()
        dialog.setView(layout)
        dialog.show()

        saveData.setOnClickListener {
            userList.add(
                UserModel(
                userNotes.text.toString()
            )
            )
            userAdapter.notifyDataSetChanged()
            dialog.dismiss()
        }
    }
}


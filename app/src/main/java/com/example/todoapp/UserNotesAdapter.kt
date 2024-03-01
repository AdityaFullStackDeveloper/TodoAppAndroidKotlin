package com.example.todoapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class UserNotesAdapter(context: Context, var userList: List<UserNotesModel>) :
    RecyclerView.Adapter<UserNotesAdapter.UserNotesViewHolder>() {

        private val dataBaseHelper : TodoDataBaseHelper = TodoDataBaseHelper(context)
        class UserNotesViewHolder(userNotesItem: View):RecyclerView.ViewHolder(userNotesItem){
            val descTextView : TextView = userNotesItem.findViewById(R.id.desc_textView)
            val updateButton : ImageView = userNotesItem.findViewById(R.id.userUpdate_ImageView)
            val deleteButton : ImageView = userNotesItem.findViewById(R.id.userDelete_imageView)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserNotesViewHolder {
        val notesLayout = LayoutInflater.from(parent.context).inflate(R.layout.activity_user_data_model, null, false)
        return UserNotesViewHolder(notesLayout)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserNotesViewHolder, position: Int) {
        holder.descTextView.text = userList[position].userDesc
        holder.updateButton.setOnClickListener {
            val intent = Intent(holder.descTextView.context, UpdateActivity::class.java).apply {
                putExtra("notes",userList[position].userId)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.deleteButton.setOnClickListener {
            dataBaseHelper.deleteNotes(userList[position].userId)
            refreshYourData(dataBaseHelper.insertedAllNotes())
            Toast.makeText(holder.itemView.context, "Note Deleted", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshYourData(userNotes : List<UserNotesModel>){
        userList = userNotes
        notifyDataSetChanged()
    }
}
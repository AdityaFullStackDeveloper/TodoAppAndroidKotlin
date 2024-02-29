package com.example.todoapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class UserNotesAdapter(context: Context, var userList: List<UserNotesModel>) :
    RecyclerView.Adapter<UserNotesAdapter.UserNotesViewHolder>() {
        class UserNotesViewHolder(userNotesItem: View):RecyclerView.ViewHolder(userNotesItem){
            val descTextView : TextView = userNotesItem.findViewById(R.id.desc_textView)
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
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refresh(userNotes : List<UserNotesModel>){
        userList = userNotes
        notifyDataSetChanged()
    }
}
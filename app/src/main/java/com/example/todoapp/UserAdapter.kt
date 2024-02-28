package com.example.todoapp

import android.annotation.SuppressLint
import android.app.Activity
import android.media.Image
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager

class UserAdapter(private val context: Activity, private val userList: List<UserModel>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(userItem: View) : RecyclerView.ViewHolder(userItem) {
        val name: TextView = userItem.findViewById(R.id.title_textView)
        val userDeleteButton: ImageView = userItem.findViewById(R.id.userDelete_imageView)
        val userEditButton: ImageView = userItem.findViewById(R.id.userUpdate_ImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val userLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_user_data_model, parent, false)
        return ViewHolder(userLayout)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userHolder = userList[position]
        holder.name.text = userHolder.userName
        holder.userDeleteButton.setOnClickListener {
            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Delete")
            alertDialog.setMessage("Do you want to delete '${userHolder.userName}'?")

            alertDialog.setPositiveButton("Yes") { dialog, which ->
                Toast.makeText(context, "Deleted ${userHolder.userName}", Toast.LENGTH_SHORT).show()
//                userList.toMutableList().removeAt(position)
//                notifyItemRemoved(position)
//                notifyItemRangeChanged(position, userList.size)
//                notifyDataSetChanged()
            }
            alertDialog.setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }
            alertDialog.show()
        }
    }
}
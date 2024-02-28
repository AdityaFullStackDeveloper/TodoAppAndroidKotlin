package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView.LayoutManager

class UserDataModel : AppCompatActivity() {
    private lateinit var userDeleteImageView: ImageView
    private lateinit var userEditImageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_data_model)

        userEditImageView = findViewById(R.id.userUpdate_ImageView)
        userDeleteImageView = findViewById(R.id.userDelete_imageView)
    }
}
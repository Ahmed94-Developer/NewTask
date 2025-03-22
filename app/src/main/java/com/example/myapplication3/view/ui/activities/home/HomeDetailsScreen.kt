package com.example.myapplication3.view.ui.activities.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class HomeDetailsScreen : AppCompatActivity(){
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name : String = intent.getStringExtra("name")!!
        val description : String = intent.getStringExtra("description")!!
        val image : String = intent.getStringExtra("image")!!
        val votes : Double = intent.getDoubleExtra("votes",1.0)
            setContent{
        val navController: NavHostController = rememberNavController()
            HomeDetails(navController,name,description,image,votes)
        }
    }
}
package com.example.saurabh.jsonpractice

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    lateinit var postsIntent:Intent
    lateinit var albumsIntent:Intent
    lateinit var todosIntent: Intent
    lateinit var usersIntent:Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        posts.setOnClickListener(
                {
                    postsIntent= Intent(this,Posts::class.java)
                    startActivity(postsIntent)
                   // Log.d("Posts","Posts clicked")
                }
        )

        albums.setOnClickListener(
                {
                    albumsIntent= Intent(this,Albums::class.java)
                    startActivity(albumsIntent)
                }
        )

        todos.setOnClickListener({
        todosIntent =Intent(this,Todos::class.java)
            startActivity(todosIntent)
        })

        users.setOnClickListener({
            usersIntent= Intent(this,Users::class.java)
            startActivity(usersIntent)
        })
    }
}

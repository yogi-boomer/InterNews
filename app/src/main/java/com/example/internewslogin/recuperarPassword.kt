package com.example.internewslogin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_recuperar_password.*
import kotlinx.android.synthetic.main.toolbar.*

class recuperarPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_password)
        initToolBar()
        btnRecuperar.setOnClickListener {
            Toast.makeText(this, "Correo Enviado", Toast.LENGTH_SHORT).show()
        }
    }
    private fun initToolBar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title= "InterNews"
    }
}

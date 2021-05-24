package com.example.internewslogin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_card_view.*

class cardView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_view)
        btnInformacion.setOnClickListener {
            var intent: Intent = Intent(this, registrarse::class.java)
            startActivity(intent)
        }
    }

}
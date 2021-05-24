package com.example.internewslogin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_actividades_gen.*

class ActividadesGen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividades_gen)
        btInscribir.setOnClickListener {
                var intent: Intent = Intent(this, formRegistroActividad::class.java)
                startActivity(intent)
                finish()
        }
    }
}
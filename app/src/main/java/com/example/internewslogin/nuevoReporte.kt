package com.example.internewslogin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_nuevo_reporte.*

class nuevoReporte : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_reporte)
         btnCrear.setOnClickListener {
            var intent:Intent =Intent (this, formResumen::class.java)
            startActivity(intent)
        }

    }


}
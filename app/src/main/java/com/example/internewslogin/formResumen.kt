package com.example.internewslogin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_form_resumen.*

class formResumen : AppCompatActivity() {
    lateinit var opcion : Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_resumen)
        opcion= findViewById(R.id.spinerCarreras) as Spinner
        val opciones = arrayListOf("Ingenieria en sistemas computacionales","Gastronomia","Electromecanica","Electronica","Ingenieria industrial","Ingenieria civil")
        opcion.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,opciones)
        opcion.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        btnGuardar.setOnClickListener {
            Toast.makeText(this, "Guardando", Toast.LENGTH_SHORT).show()
        }
        btnEnviar.setOnClickListener {
            Toast.makeText(this, "Enviando el reporte", Toast.LENGTH_SHORT).show()
        }
        btnCancelar.setOnClickListener {
            var intent: Intent = Intent(this, nuevoReporte::class.java)
            startActivity(intent)
            finish()
        }
    }
}
package com.example.internewslogin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_registrarse.*
import kotlinx.android.synthetic.main.bg_layout_modal.view.*

class registrarse : AppCompatActivity() {
    lateinit var opcion : Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)
        opcion= findViewById(R.id.carreras) as Spinner
        val opciones = arrayListOf("Ingenieria en sistemas computacionales","Gastronomia","Electromecanica","Electronica","Ingenieria industrial","Ingenieria civil")
        val view = View.inflate(this,R.layout.bg_layout_contrasenia,null)
        val builder = AlertDialog.Builder(this)
        builder.setView(view)
        val dialog = builder.create()

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
        button4.setOnClickListener {
            if (editTextTextPersonName2.text.isEmpty() || editTextTextPersonName3.text.isEmpty()){
                Toast.makeText(this, "Todos los campos son necesarios", Toast.LENGTH_SHORT).show()
            }else if (editTextTextPersonName4.text.isEmpty() || editTextTextPersonName5.text.isEmpty()){
                Toast.makeText(this, "Todos los campos son necesarios", Toast.LENGTH_SHORT).show()
            }else if(editTextTextPassword2.text.isEmpty() || editTextTextPassword3.text.isEmpty()){
                Toast.makeText(this, "Todos los campos son necesarios", Toast.LENGTH_SHORT).show()
            }else if (editTextTextPassword2.text.toString() == editTextTextPassword3.text.toString()){
                var intent: Intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Registro realizado.", Toast.LENGTH_SHORT).show()
                finish()
            }else {
                dialog.show()
                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                view.btnConfirmar.setOnClickListener {
                    dialog.dismiss()
                }
            }
        }
    }
}
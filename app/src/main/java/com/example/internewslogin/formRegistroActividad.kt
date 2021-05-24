package com.example.internewslogin

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.internewslogin.activities.perfil
import kotlinx.android.synthetic.main.activity_form_registro_actividad.*
import kotlinx.android.synthetic.main.toolbar.*

class formRegistroActividad : AppCompatActivity() {
    lateinit var opcion : Spinner
    lateinit var opcion2 : Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_registro_actividad)
        initToolBar()

        //LLENADO DE LOS SPINER
        opcion= findViewById(R.id.spinerRegisCarreras) as Spinner
        opcion2= findViewById(R.id.spinerRegisSemestre) as Spinner
        val opciones = arrayListOf("Ingenieria en sistemas computacionales","Gastronomia","Electromecanica","Electronica","Ingenieria industrial","Ingenieria civil")
        val semestres = arrayListOf("Primer semestre","Segundo semestre","Tercer semestre","Cuarto semestre","Quinto semestre","Sexto semestre","Septimo semestre","Octavo semestre","Noveno semestre")
        opcion.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,opciones)
        opcion2.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,semestres)

        btnSearchFile.setOnClickListener {
            val intent2 = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "*/*"
            }
            startActivityForResult(intent2, 10)
        }
        terminarRegis.setOnClickListener {
            var intent: Intent = Intent(this, Actividades::class.java)
            startActivity(intent)
            finish()
        }
        btnSalir.setOnClickListener {
            var inten2:Intent = Intent(this, ActividadesGen::class.java)
            startActivity(inten2)
            finish()
        }

    }

    private fun initToolBar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title= "InterNews"
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuConvocatoria -> {
                Toast.makeText(this, "concocatorias", Toast.LENGTH_SHORT).show()
                var intent: Intent = Intent(this, convocatorias_Activity::class.java)
                startActivity(intent)
            }
            R.id.menuBecas ->{
                Toast.makeText(this, "becas", Toast.LENGTH_SHORT).show()
                var intent: Intent = Intent(this, becas_Activity::class.java)
                startActivity(intent)
            }
            R.id.menuEventos ->{
                Toast.makeText(this, "eventos", Toast.LENGTH_SHORT).show()
                var intent: Intent = Intent(this, eventos_Activity::class.java)
                startActivity(intent)
            }
            R.id.datosPersona -> {
                Toast.makeText(this, "datos personales", Toast.LENGTH_SHORT).show()
                var intent: Intent = Intent(this, perfil::class.java)
                startActivity(intent)
            }
            R.id.chat -> {
                Toast.makeText(this, "chat", Toast.LENGTH_SHORT).show()
                var intent: Intent = Intent(this, convocatorias_Activity::class.java)
                startActivity(intent)
            }
            R.id.misActividades ->{
                Toast.makeText(this, "chat", Toast.LENGTH_SHORT).show()
                var intent: Intent = Intent(this, nuevoReporte::class.java)
                startActivity(intent)
            }
        }
        return true
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menuresourse,menu)
        return true
    }
}
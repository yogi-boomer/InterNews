package com.example.internewslogin

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internewslogin.activities.perfil
import com.example.internewslogin.adapters.actividadesAdapter
import com.example.internewslogin.dataClases.Actividad
import kotlinx.android.synthetic.main.activity_actividades.*
import kotlinx.android.synthetic.main.toolbar.*

class Actividades : AppCompatActivity(){
    val actividad: List<Actividad> = listOf(
            Actividad("Retos Sustentables", "Participa en los retos sustentables con tus compañeros y profesores...", R.drawable.ejemplo1),
            Actividad("Beca Manutencion", "Se abre inscripcion a beca de manutención..", R.drawable.ejemplo2),
            Actividad("Convocatoria Investigaciones", "Participa en la convocatoria de investigacion con alumnos de otros institutos...", R.drawable.ejemplo3)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividades)
        initToolBar()
        setUpRecyclerView()

    }
    private  fun setUpRecyclerView(){
        rvActividades.layoutManager= LinearLayoutManager(this)
        val adapter = actividadesAdapter(actividad)
        rvActividades.adapter = adapter
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
                var intent: Intent = Intent(this, chat::class.java)
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
package com.example.internewslogin

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internewslogin.activities.perfil
import com.example.internewslogin.adapters.actividadesInscritasAdapter
import com.example.internewslogin.dataClases.ActividadInscrita
import kotlinx.android.synthetic.main.activity_actividades_inscritas.*
import kotlinx.android.synthetic.main.toolbar.*

class actividadesInscritas : AppCompatActivity() {
    val actividadInscrita: List<ActividadInscrita> = listOf(
            ActividadInscrita("Retos Sustentables", "Fechas de entrega de reporte:\n 12/06/2021, \n 29/06/2021.")
           )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividades_inscritas)
        setUpRecyclerView()
        initToolBar()
    }
    private  fun setUpRecyclerView(){
        rvMisActividades.layoutManager= LinearLayoutManager(this)
        val adapter = actividadesInscritasAdapter(actividadInscrita)
        rvMisActividades.adapter = adapter
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
            R.id.menuBecas -> {
                Toast.makeText(this, "becas", Toast.LENGTH_SHORT).show()
                var intent: Intent = Intent(this, becas_Activity::class.java)
                startActivity(intent)
            }
            R.id.menuEventos -> {
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
            R.id.misActividades -> {
                Toast.makeText(this, "nuevo reporte", Toast.LENGTH_SHORT).show()
                var intent: Intent = Intent(this, nuevoReporte::class.java)
                startActivity(intent)
            }
            R.id.menuGen ->{
                Toast.makeText(this, "eventos", Toast.LENGTH_SHORT).show()
                var intent: Intent = Intent(this, Actividades::class.java)
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
package com.example.internewslogin

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internewslogin.activities.perfil
import com.example.internewslogin.adapters.convocatoriasAdapter
import com.example.internewslogin.dataClases.Actividad
import kotlinx.android.synthetic.main.activity_becas_.*
import kotlinx.android.synthetic.main.toolbar.*

class becas_Activity : AppCompatActivity() {
    val actividad: List<Actividad> = listOf(
        Actividad("Beca Manutencion", "Se abre inscripcion a beca de manutención..", R.drawable.ejemplo2),
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_becas_)
        setUpRecyclerView()
        initToolBar()
    }
    private  fun setUpRecyclerView(){
        rvBecas.layoutManager= LinearLayoutManager(this)
        val adapter = convocatoriasAdapter(actividad)
        rvBecas.adapter = adapter
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
            R.id.menuGen ->{
                Toast.makeText(this, "eventos", Toast.LENGTH_SHORT).show()
                var intent: Intent = Intent(this, Actividades::class.java)
                startActivity(intent)
            }
            R.id.misActividades ->{
                Toast.makeText(this, "eventos", Toast.LENGTH_SHORT).show()
                var intent: Intent = Intent(this, convocatorias_Activity::class.java)
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
        }
        return true
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menuresourse,menu)
        return true
    }

}
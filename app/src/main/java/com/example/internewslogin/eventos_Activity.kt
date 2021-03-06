package com.example.internewslogin

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internewslogin.activities.perfil
import kotlinx.android.synthetic.main.activity_eventos_.*
import kotlinx.android.synthetic.main.toolbar.*

class eventos_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventos_)
        setUpRecyclerView()
        initToolBar()
    }
    private  fun setUpRecyclerView(){
        rvEventos.layoutManager= LinearLayoutManager(this)
      //  val adapter = actividadesAdapter(actividad)
      //  rvEventos.adapter = adapter
    }

    private fun initToolBar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title= "InterNews"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuConvocatoria -> {
                var intent: Intent = Intent(this, convocatorias_Activity::class.java)
                startActivity(intent)
            }
            R.id.menuBecas -> {
                var intent: Intent = Intent(this, becas_Activity::class.java)
                startActivity(intent)
            }
            R.id.menuEventos -> {
                var intent: Intent = Intent(this, eventos_Activity::class.java)
                startActivity(intent)
            }
            R.id.datosPersona -> {
                var intent: Intent = Intent(this, perfil::class.java)
                startActivity(intent)
            }
            R.id.misActividades -> {
                var intent: Intent = Intent(this, actividadesInscritas::class.java)
                startActivity(intent)
            }
            R.id.menuGen ->{
                var intent: Intent = Intent(this, Actividades::class.java)
                intent.putExtra("no_control", numeroControl)
                startActivity(intent)
            }
            R.id.menuSalir->{
                var intent: Intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        return true
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menuresourse,menu)
        return true
    }
}
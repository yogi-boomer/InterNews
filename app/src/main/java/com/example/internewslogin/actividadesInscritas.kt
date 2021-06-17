package com.example.internewslogin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internewslogin.activities.perfil
import com.example.internewslogin.adapters.actividadesInscritasAdapter
import com.example.internewslogin.dataClases.ActividadInscrita
import com.example.internewslogin.interfaces.ApiGet
import kotlinx.android.synthetic.main.activity_actividades_inscritas.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class actividadesInscritas : AppCompatActivity() {
    private lateinit var adapter : actividadesInscritasAdapter
    private var actividades = mutableListOf<ActividadInscrita>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividades_inscritas)
        setUpRecyclerView()
        initToolBar()
    }

    private fun geData(){
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiGet::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            val response = api.gatActivityRegist(numeroControl).awaitResponse()
            runOnUiThread {
                if (response.isSuccessful){
                    val data = response.body()!!
                    for(i in data.indices){
                        Log.d("revision", data[i].toString())
                        actividades.add(data[i])
                        adapter.notifyDataSetChanged()
                        Log.d("revision lista muteable", actividades.toString())
                    }
                }else{
                    showError()
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
    }

    private  fun setUpRecyclerView(){
        geData()
        rvMisActividades.layoutManager= LinearLayoutManager(this)
        adapter = actividadesInscritasAdapter(actividades)
        rvMisActividades.adapter = adapter
        adapter.notifyDataSetChanged()
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
            R.id.chat -> {
                var intent: Intent = Intent(this, chat::class.java)
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
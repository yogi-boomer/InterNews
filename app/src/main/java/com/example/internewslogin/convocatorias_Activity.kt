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
import com.example.internewslogin.adapters.actividadesAdapter
import com.example.internewslogin.dataClases.Actividad
import com.example.internewslogin.interfaces.ApiGet
import kotlinx.android.synthetic.main.activity_convocatorias_.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class convocatorias_Activity : AppCompatActivity() {
    private lateinit var adapter : actividadesAdapter
    private var nombre:String= ""
    private var actividades = mutableListOf<Actividad>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_convocatorias_)
        setUpRecyclerView()
        initToolBar()
    }
    private fun getCurrentData(){
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiGet::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            val response = api.getConvocatorias().awaitResponse()
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
    private  fun setUpRecyclerView(){
        getCurrentData()
        rvConvocatorias.layoutManager= LinearLayoutManager(this)
        adapter = actividadesAdapter(actividades)
        rvConvocatorias.adapter = adapter
        adapter.notifyDataSetChanged()
    }
    private fun showError() {
        Toast.makeText(this, "Error con el servidor", Toast.LENGTH_SHORT).show()
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
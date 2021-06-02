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
import com.example.internewslogin.interfaces.ApiGet
import kotlinx.android.synthetic.main.activity_actividades.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Actividades : AppCompatActivity(){
    private lateinit var adapter : actividadesAdapter
    private var actividades = mutableListOf<Actividad>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividades)
        initToolBar()
        setUpRecyclerView()

    }
    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://b5b22fe6daf3.ngrok.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun searchConvocatorias(){
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<Actividad> = getRetrofit().create(ApiGet::class.java).getConvocatorias("/announs/movil")
            val convocatorias: Actividad? = call.body()
            runOnUiThread {
                if (call.isSuccessful){
                    //show in recyclerview
                    var conv: List<Actividad> = (convocatorias?.titulo ?: emptyList<Actividad>()) as List<Actividad>
                    actividades.clear()
                    actividades.addAll(conv)
                    adapter.notifyDataSetChanged()

                }else{
                    //show error
                    showError()
                }
            }

        }
    }

    private fun showError() {
        Toast.makeText(this, "Error con el servidor", Toast.LENGTH_SHORT).show()
    }

    private  fun setUpRecyclerView(){
        rvActividades.layoutManager= LinearLayoutManager(this)
        adapter = actividadesAdapter(actividades)
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
                var intent: Intent = Intent(this, actividadesInscritas::class.java)
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
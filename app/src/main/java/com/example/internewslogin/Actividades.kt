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
import kotlinx.android.synthetic.main.activity_actividades.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL= "http://887e-177-240-166-31.ngrok.io/"
var nombreUsuario:String = ""
var apellidoUsuario:String = ""
var apellidoMaterno:String = ""
var numeroControl:String = ""
var correoUsuario:String = ""
var cargoUsuario:String = ""
var carreraUsuario:String = ""
var semestreUsuario:String =""
class Actividades : AppCompatActivity(){
    private lateinit var adapter : actividadesAdapter
    private var actividades = mutableListOf<Actividad>()
    private var nombre:String= ""
    private var apellidoP:String= ""
    private var apellidoM:String= ""
    private var carrera:String= ""
    private var semestre:String= ""
    private var correo:String= ""
    private var numeroC:String= ""
    private var cargo:String= ""
    override fun onCreate(savedInstanceState: Bundle?) {
        val bundle = intent.extras
        val dato = bundle?.getString("no_control")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividades)
        initToolBar()
        setUpRecyclerView()
        getInfoUser(dato)
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
    private fun getInfoUser(dato: String?) {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiGet::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            val response = api.getUser(dato.toString()).awaitResponse()
            runOnUiThread {
                if (response.isSuccessful){
                    val data = response.body()!!
                        Log.d("REVISION", data.toString())
                    if (data.cargo == "Alumno"){
                        nombreUsuario = data.nombre
                        apellidoUsuario=data.apellido_p
                        apellidoMaterno = data.apellido_m
                        carreraUsuario = data.carrera
                        semestreUsuario = data.semestre
                        correoUsuario = data.correo_ins
                        numeroControl = data.no_control
                        cargoUsuario = data.cargo
                    }else if(data.cargo == "Docente"){
                        nombreUsuario = data.nombre
                        apellidoUsuario=data.apellido_p
                        apellidoMaterno = data.apellido_m
                        carreraUsuario = data.carrera
                        correoUsuario = data.correo_ins
                        numeroControl = data.no_control
                        cargoUsuario = data.cargo
                    }
                }else{
                    showError()
                }
            }
        }
    }
   private  fun setUpRecyclerView(){
        getCurrentData()
        rvActividades.layoutManager= LinearLayoutManager(this)
        adapter = actividadesAdapter(actividades)
        rvActividades.adapter = adapter
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
package com.example.internewslogin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.internewslogin.activities.perfil
import com.example.internewslogin.dataClases.Participante
import com.example.internewslogin.interfaces.ApiGet
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_form_registro_actividad.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Matcher
import java.util.regex.Pattern

class formRegistroActividad : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //DATOS RECUPERADOS DEL USUARIO
         val bundle = intent.extras
         val dato = bundle?.getString("id_conv")
         val datoN= bundle?.getString("nombre")
         val datoP= bundle?.getString("apellidoP")
         val datoM= bundle?.getString("apellidoM")
         val datoNC= bundle?.getString("numeroControl")
         val datoC= bundle?.getString("carrera")
         val datoS= bundle?.getString("semestre")
         val datoT= bundle?.getString("titulo")


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_registro_actividad)
        initToolBar()
        Toast.makeText(this, dato, Toast.LENGTH_SHORT).show()
        getData(dato.toString())
        //PRECARGADO DE DATOS
        nombreRegist.setText(datoN)
        apellidoPRegist.setText(datoM)
        apellidoRegist.setText(datoP)
        numeroCRegist.setText(datoNC)
        carreraRegist.setText(datoC)
        if (datoS == ""){
            txtSem.isVisible = false
            semestreRegist.isVisible = false
            textoVariable.setText("Departamento")
        }else{
            semestreRegist.setText(datoS)
        }

        //BOTONES
        btnSearchFile.setOnClickListener {
            val intent2 = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "*/*"
            }
            startActivityForResult(intent2, 10)
        }
        terminarRegis.setOnClickListener {
            val correo = correoERegist.text.toString()
            var participante: Participante? =  Participante(datoN.toString(),datoP.toString(),datoM.toString(),datoNC.toString(),correo,"prueba.pdf",datoC.toString(),datoS.toString(),dato.toString().toInt(),datoT.toString(),cargoUsuario)
            var patron : Pattern = Pattern.compile("^[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\$")
            var comparador : Matcher = patron.matcher(correo)
            if(!comparador.find()){
                correoERegist.error = "Ingresa un correo valido"
                false
            }else{
                correoERegist.error= null
                true
                sendDataToApi(participante)
            }

        }
        btnSalir.setOnClickListener {
            var intent3: Intent = Intent(this,Actividades::class.java)
            intent3.putExtra("no_control",numeroControl)
            startActivity(intent3)
            finish()
        }

    }


    private fun getData(dato: String,) {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL2)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiGet::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            val response = api.getInfoConvocatoriaById(dato.toInt()).awaitResponse()
            runOnUiThread {
                Log.d("revision2", response.toString())
                if (response.isSuccessful) {
                    val data = response.body()!!
                        nombreActRegist.setText(data.titulo)
                } else {
                    showError()
                }
            }
        }
    }

    private fun sendDataToApi(participante: Participante?) {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL2)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = api.create(ApiGet::class.java)
        var partici : Participante? = participante
        service.postInscription(partici).enqueue(object : Callback<Participante>{
            override fun onResponse(call: Call<Participante>, response: Response<Participante>) {
                partici = response?.body()
                Log.d("revisionP",partici.toString())
                Log.d("Revision_participante", Gson().toJson(partici))
                previousPage()
            }
            override fun onFailure(call: Call<Participante>, t: Throwable) {
                showError()
            }
        })
    }

    private fun previousPage() {
        var intent: Intent = Intent(this, Actividades::class.java)
        intent.putExtra("no_control",numeroControl)
        startActivity(intent)
        finish()
    }

    private fun showError() {
        Toast.makeText(this, "Participante ya registrado.", Toast.LENGTH_SHORT).show()
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
package com.example.internewslogin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.internewslogin.dataClases.Reporte
import com.example.internewslogin.interfaces.ApiGet
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_form_resumen.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class formResumen : AppCompatActivity() {
    private var Key =" "
    private var titulo:String = " "
    private var descrip:String = " "
    override fun onCreate(savedInstanceState: Bundle?) {
        val bundle = intent.extras
        var datoID = bundle?.getString("id_convo")
        var datoF = bundle?.getString("fecha_ini")
        var dato = bundle?.getString("no_repo")
        Log.d("REVISION REPO", dato.toString())
        var no_repor = dato
        var datoRes = bundle?.getString("resumen")
        var resum = datoRes
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_resumen)
        val date = getCurrentDateTime()
        val dateInString = date.toString("yyyy-MM-dd")

        Log.d("date",dateInString)
        fechaEdit.setText(dateInString)
        //LLAMAMOS A TITULO
        get(datoID.toString())

        //RECUPERACION Y LLENADO DE DATOS DEL USUARIO
       // fechaEdit.setText(datoF)
        numeroCEdit.setText(numeroControl)
        correoIEdit.setText(correoUsuario)
        if (semestreUsuario  == ""){
            variateTxt.setText("Departamento")
            carreraEdit.setText(carreraUsuario)
        }else{
        carreraEdit.setText(carreraUsuario)
        }
        if (datoRes == ""){
            resumenEdit.setText("")
        }else{
            resumenEdit.setText(datoRes)
        }

        btnGuardar.setOnClickListener {
            Toast.makeText(this, "Guardando.", Toast.LENGTH_SHORT).show()
            descrip = resumenEdit.text.toString()
            val repo: Reporte = Reporte(datoF.toString(), numeroControl, nombreUsuario,
                apellidoUsuario, apellidoMaterno, carreraUsuario,datoID.toString().toInt(),titulo, descrip,no_repor.toString().toInt(),"Guardado")
            guardaRepo(repo)
        }
        btnEnviar.setOnClickListener {
            Toast.makeText(this, "Enviando el reporte", Toast.LENGTH_SHORT).show()
            titulo= tituloActividadEdit.text.toString()
            descrip= resumenEdit.text.toString()
            val repo: Reporte = Reporte(datoF.toString(), numeroControl, nombreUsuario,
                apellidoUsuario, apellidoMaterno, carreraUsuario,datoID.toString().toInt(),titulo, descrip,no_repor.toString().toInt(), "No Revisado")
            repo(repo)
        }
        btnCancelar.setOnClickListener {
            var intent: Intent = Intent(this, actividadesInscritas::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun get(datoID: String) {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL2)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiGet::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            val response = api.getInfoConvocatoriaById(datoID.toInt()).awaitResponse()
            runOnUiThread {
                Log.d("revision2", response.toString())
                if (response.isSuccessful) {
                    val data = response.body()!!
                    tituloActividadEdit.setText(data.titulo)
                    titulo = data.titulo
                } else {
                    showError()
                }
            }
        }
    }

    private fun showError() {
       val intent: Intent = Intent(this, actividadesInscritas::class.java)
        startActivity(intent)
    }
    private fun repo(repo:Reporte){
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = api.create(ApiGet::class.java)
        var report : Reporte? = repo
        service.postResumen(report).enqueue(object : Callback<Reporte> {
            override fun onResponse(call: Call<Reporte>, response: Response<Reporte>) {
                report = response?.body()
                Log.d("revisionP",report.toString())
                Log.d("Revision_participante", Gson().toJson(report))

            }
            override fun onFailure(call: Call<Reporte>, t: Throwable) {
                showError()
            }
        })
    }
    //DATE
    private fun  Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }
    private  fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }
    //FUNCION DE GUARDADO PREVIO A ENVIO DE REPORTE
    private fun guardaRepo(repo: Reporte){
        val apiPost: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = apiPost.create(ApiGet::class.java)
        var report : Reporte? = repo
        service.postGuardaResumen(report).enqueue(object : Callback<Reporte> {
            override fun onResponse(call: Call<Reporte>, response: Response<Reporte>) {
                report = response?.body()
                Log.d("revisionP",report.toString())
                Log.d("Revision_participante", Gson().toJson(report))

            }
            override fun onFailure(call: Call<Reporte>, t: Throwable) {
                showError()
            }
        })
    }
}

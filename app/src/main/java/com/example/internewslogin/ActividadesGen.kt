package com.example.internewslogin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.internewslogin.interfaces.ApiGet
import kotlinx.android.synthetic.main.activity_actividades_gen.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL2 = "http://887e-177-240-166-31.ngrok.io/"
class ActividadesGen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val bundle = intent.extras
        val dato = bundle?.getString("id_conv")
        val nombre: String = nombreUsuario
        val apellidoPaterno: String = apellidoUsuario
        val apellidoMaterno: String = apellidoMaterno
        val numeroControl: String = numeroControl
        val carrera: String = carreraUsuario
        val semestre: String = semestreUsuario
        val titulo = bundle?.getString("titulo")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividades_gen)
        getData(dato.toString())
        btInscribir.setOnClickListener {
            val intent: Intent = Intent(this, formRegistroActividad::class.java)
            intent.putExtra("id_conv",dato)
            intent.putExtra("nombre",nombre)
            intent.putExtra("apellidoP",apellidoPaterno)
            intent.putExtra("apellidoM",apellidoMaterno)
            intent.putExtra("numeroControl",numeroControl)
            intent.putExtra("carrera",carrera)
            intent.putExtra("semestre", semestre)
            intent.putExtra("titulo",titulo)
            startActivity(intent)
        }
    }

    private fun getData(dato: String) {
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
                    Log.i("Revision", data.titulo)
                        textDate.text = data.fecha_ini
                        textDate2.text = data.fecha_fin
                        stateActivity.text = data.estado
                        tituloActividad.text = data.titulo
                        description.text = data.descripcion
                        idCarrera.text = data.carrera
                        semestres.text = data.semestre
                        faq.text = data.pregunta
                        archivo.text = data.archivo
                } else {
                    showError()
                }
            }
        }
    }
    private fun showError(){
        Toast.makeText(this, "error con el servidor", Toast.LENGTH_SHORT).show()
    }
}



package com.example.internewslogin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.internewslogin.R.layout.activity_main
import com.example.internewslogin.dataClases.ParticipanteLG
import com.example.internewslogin.interfaces.ApiGet
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    var no_control ="187O03156"
    var password ="124"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        button.setOnClickListener {
            val usu = noControl.text.toString()
            val con = editTextContraseña.text.toString()
            val usuario: ParticipanteLG = ParticipanteLG("",usu,con)
            Log.d("REVISION", usuario.toString())
            if (noControl.text.isEmpty() || editTextContraseña.text.isEmpty()) {
                noControl.error = "Ingresa los datos solicitados"
                editTextContraseña.error = "Ingresa el dato solicitado"
            } else if(noControl.text.isNotEmpty() && editTextContraseña.text.isNotEmpty()){
                login(usuario)
            }
        }
        button2.setOnClickListener {
            var intent: Intent = Intent(this, registrarse::class.java)
            startActivity(intent)
        }

        button3.setOnClickListener {
            var intent: Intent = Intent(this, recuperarPassword::class.java)
            startActivity(intent)
        }
    }
    private fun login(usuario: ParticipanteLG) {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiGet::class.java)
        var partici : ParticipanteLG? = usuario
        GlobalScope.launch(Dispatchers.IO) {
            val response = api.Login(partici).awaitResponse()
            runOnUiThread {
                if (response.isSuccessful){
                    val data = response.body()!!
                    Log.d("Mensaje",data.toString())
                    if(data.mensaje == "Bienvenido"){
                        wellCome()
                    }else if (data.mensaje == "Contraseña Incorrecta"){
                        showErrorContraseña()
                    }else if (data.mensaje == "Usuario no encontrado"){
                        showErroUsuario()
                    }
                }else{
                    showError()
                }
            }
        }
    }
    private fun wellCome(){
        var intent: Intent = Intent (this, Actividades::class.java)
        intent.putExtra("no_control", noControl.text.toString())
        startActivity(intent)
    }
    private fun showError(){
        Toast.makeText(this, "Error con el servidor", Toast.LENGTH_SHORT).show()
    }
    private fun showErrorContraseña(){
        val s = ""
        editTextContraseña.setText(s)
        editTextContraseña.error = "Contraseña Incorrecta"
    }
    private fun showErroUsuario(){
        noControl.error = "Usuario no encontrado"
    }
}
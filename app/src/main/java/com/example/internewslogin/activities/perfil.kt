package com.example.internewslogin.activities

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.internewslogin.R
import com.example.internewslogin.apellidoMaterno
import com.example.internewslogin.apellidoUsuario
import com.example.internewslogin.nombreUsuario
import kotlinx.android.synthetic.main.activity_perfil.*


class perfil : AppCompatActivity() {
    val nombre = "Luis Fernando"
    val apellodoP = "Vazquez"
    val apellidoM = "Ceballos"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)
        nombrePerfil.setText(nombreUsuario)
        apellidoPerfil.setText(apellidoUsuario)
        apellidoMPerfil.setText(apellidoMaterno)
        editarNombre()
        editarApellido()
        editarAperllidoM()
        realizarCambio()
    }
    private fun editarNombre(){
        btnEditarNombre.setOnClickListener {
            if(nombrePerfil.isEnabled == false){
                nombrePerfil.isEnabled = true
            }else if(nombrePerfil.isEnabled == true){
                nombrePerfil.isEnabled = false
            }else{
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun editarApellido(){
        btnEditarApellidoP.setOnClickListener {
            if(apellidoPerfil.isEnabled == false){
                apellidoPerfil.isEnabled = true
            }else if(apellidoPerfil.isEnabled == true){
                apellidoPerfil.isEnabled = false
            }else{
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun editarAperllidoM(){
        btnEditarApellido.setOnClickListener {
            if(apellidoMPerfil.isEnabled == false){
                apellidoMPerfil.isEnabled = true
            }else if(apellidoMPerfil.isEnabled == true){
                apellidoMPerfil.isEnabled = false
            }else{
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun realizarCambio(){
        btnCambio.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("¿Guardar cambios?")
                .setPositiveButton("Si", DialogInterface.OnClickListener { dialog, which -> closeOptionsMenu() })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which -> closeOptionsMenu() })
            builder.create()
        }
    }

}
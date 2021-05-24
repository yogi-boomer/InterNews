package com.example.internewslogin

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.internewslogin.R.layout.activity_main
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bg_layout_modal.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        val usuarioPro:String = "1"
        val passWord:String = "1"
        val view = View.inflate(this,R.layout.bg_layout_modal,null)
        val builder = AlertDialog.Builder(this)
        builder.setView(view)
        val dialog = builder.create()

        button.setOnClickListener {
            if (email.text.toString() == usuarioPro && editTextContraseña.text.toString() == passWord){
                var intent2:Intent = Intent(this, Actividades::class.java)
                startActivity(intent2)
                finish()
            }else{
                dialog.show()
                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                view.btnConfirmar.setOnClickListener {
                    dialog.dismiss()
                }
            }
        }

        button2.setOnClickListener {
           var intent:Intent = Intent(this, registrarse::class.java)
            startActivity(intent)
            finish()
        }

        button3.setOnClickListener {
            var intent:Intent = Intent(this,recuperarPassword::class.java)
            startActivity(intent)
        }

    }
}
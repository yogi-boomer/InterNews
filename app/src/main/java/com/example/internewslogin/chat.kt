package com.example.internewslogin

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internewslogin.activities.perfil
import com.example.internewslogin.adapters.chatsAdapter
import com.example.internewslogin.dataClases.Chat
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlin.random.Random

class chat : AppCompatActivity() {
    val chat: MutableList<Chat> = mutableListOf()
    val adapter = chatsAdapter(chat)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        initToolBar()
        setUpRecyclerView()
        btnEnviarMensaje.setOnClickListener {
             insertItem()
        }
    }

    private  fun setUpRecyclerView(){
        rvChat.layoutManager= LinearLayoutManager(this)
        rvChat.adapter = adapter
        print(chat)

    }
    private fun insertItem() {
        val index = Random.nextInt(1)
        chat.add(index, Chat(mensaje.text.toString()))
        adapter.notifyItemInserted(index)
        rvChat.scrollToPosition(index)

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
                var intent: Intent = Intent(this, com.example.internewslogin.chat::class.java)
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
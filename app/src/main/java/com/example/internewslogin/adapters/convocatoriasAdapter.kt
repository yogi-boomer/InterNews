package com.example.internewslogin.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internewslogin.ActividadesGen
import com.example.internewslogin.R
import com.example.internewslogin.dataClases.Actividad
import kotlinx.android.synthetic.main.card_actividades.view.*

class convocatoriasAdapter(val actividades: List<Actividad>): RecyclerView.Adapter<convocatoriasAdapter.convocatoriaHolder>() {
    class convocatoriaHolder(view:View): RecyclerView.ViewHolder(view) {
        fun data(actividad: Actividad){
                itemView.textoTile2.text = actividad.titulo
                itemView.descripcion2.text = actividad.descripcion
                itemView.imgBackgrond2.setImageResource(actividad.img)
        }
        init {
            itemView.btnInformacion2.setOnClickListener {
                val intent: Intent = Intent(itemView.context, ActividadesGen::class.java)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): convocatoriasAdapter.convocatoriaHolder {
        val LayoutInflater= LayoutInflater.from(parent.context)
        return convocatoriaHolder(LayoutInflater.inflate(R.layout.card_actividades, parent, false)
        )
    }

    override fun onBindViewHolder(holder: convocatoriasAdapter.convocatoriaHolder, position: Int) {
        holder.data(actividades[position])
    }

    override fun getItemCount(): Int {
        return actividades.size
    }
}
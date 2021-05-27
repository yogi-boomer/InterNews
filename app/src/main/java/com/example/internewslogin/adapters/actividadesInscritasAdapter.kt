package com.example.internewslogin.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internewslogin.R
import com.example.internewslogin.dataClases.ActividadInscrita
import com.example.internewslogin.nuevoReporte
import kotlinx.android.synthetic.main.card_mis_actividades.view.*

class actividadesInscritasAdapter(val actividadesIns: List<ActividadInscrita>):RecyclerView.Adapter<actividadesInscritasAdapter.actividadesInsHolder>() {
    class actividadesInsHolder(view: View): RecyclerView.ViewHolder(view) {
        fun data(actividadesIns: ActividadInscrita){
            itemView.textoTileMisActividades.text = actividadesIns.titulo
            itemView.descripcionMisActividades.text = actividadesIns.fechas
        }
        init {
                itemView.botonCrearReporte.setOnClickListener {
                    val intent: Intent = Intent(itemView.context, nuevoReporte::class.java)
                    itemView.context.startActivity(intent)
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): actividadesInsHolder {
        val LayoutInflater = LayoutInflater.from(parent.context)
        return  actividadesInsHolder(LayoutInflater.inflate(R.layout.card_mis_actividades,parent, false ))
    }

    override fun onBindViewHolder(holder: actividadesInsHolder, position: Int) {
        val item = actividadesIns[position]
        holder.data(item)
    }

    override fun getItemCount(): Int {
        return actividadesIns.size
    }

}
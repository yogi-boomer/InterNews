package com.example.internewslogin.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internewslogin.R
import com.example.internewslogin.dataClases.ActividadInscrita
import com.example.internewslogin.reportView
import kotlinx.android.synthetic.main.card_mis_actividades.view.*

class actividadesInscritasAdapter(val actividadesIns: MutableList<ActividadInscrita>):RecyclerView.Adapter<actividadesInscritasAdapter.actividadesInsHolder>() {
    class actividadesInsHolder(view: View): RecyclerView.ViewHolder(view) {
        fun data(actividadesIns: ActividadInscrita){
            itemView.textoTileMisActividades.text = actividadesIns.titulo
            itemView.fechasMisActividades.text = actividadesIns.fecha_ini_repo
            itemView.fechasMisActividades2.text = actividadesIns.fecha_fin_repo
            itemView.idconas.text = actividadesIns.id_conv
        }
        init {
                itemView.botonCrearReporte.setOnClickListener {
                    val intent: Intent = Intent(itemView.context, reportView::class.java)
                    intent.putExtra("id_convo",itemView.idconas.text.toString())
                    intent.putExtra("fecha_ini",itemView.fechasMisActividades.text.toString())
                    intent.putExtra("fecha_fin",itemView.fechasMisActividades2.text.toString())
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
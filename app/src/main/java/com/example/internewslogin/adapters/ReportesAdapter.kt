package com.example.internewslogin.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internewslogin.R
import com.example.internewslogin.dataClases.Reporte
import com.example.internewslogin.formResumen
import kotlinx.android.synthetic.main.card_reportes.view.*

class ReportesAdapter(val reportes: List<Reporte>): RecyclerView.Adapter<ReportesAdapter.reportesHolder>() {
    class reportesHolder(view: View): RecyclerView.ViewHolder(view){
        fun data(reporte : Reporte){
            itemView.textoTileReportes.text = reporte.titulo
        }
        init {
                itemView.botonModificarReporte.setOnClickListener {
                    val intent: Intent = Intent(itemView.context, formResumen::class.java)
                    itemView.context.startActivity(intent)
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): reportesHolder {
        val LayoutInflater = LayoutInflater.from(parent.context)
        return reportesHolder(LayoutInflater.inflate(R.layout.card_reportes,parent, false))
    }

    override fun onBindViewHolder(holder: reportesHolder, position: Int) {
        val item = reportes[position]
        holder.data(item)
    }

    override fun getItemCount(): Int {
        return  reportes.size
    }
}
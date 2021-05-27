package com.example.internewslogin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internewslogin.adapters.ReportesAdapter
import com.example.internewslogin.dataClases.Reporte
import kotlinx.android.synthetic.main.activity_nuevo_reporte.*

class nuevoReporte : AppCompatActivity() {
    val reportes : List<Reporte> = listOf(
            Reporte("Reporte de avance."),
            Reporte("Reporte de finalización.")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_reporte)
        setUpRecyclerView()
        btnCrear.setOnClickListener {
            var intent:Intent =Intent (this, formResumen::class.java)
            startActivity(intent)
        }

    }
    private  fun setUpRecyclerView(){
        rvMisReportes.layoutManager= LinearLayoutManager(this)
        val adapter =ReportesAdapter(reportes)
        rvMisReportes.adapter = adapter
    }

}
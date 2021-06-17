package com.example.internewslogin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.internewslogin.dataClases.Resumen
import com.example.internewslogin.interfaces.ApiGet
import kotlinx.android.synthetic.main.activity_report_view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class reportView : AppCompatActivity() {
    private var datoId:String = ""
    private var datoFeF:String = ""
    private var datoFeI:String = ""
    private var datoN:Int = 1
    private var datoN2:Int = 2
    private var resumen: String = " "
    private var mensaje: String = " "
    override fun onCreate(savedInstanceState: Bundle?) {
        val bundle = intent.extras
        val datoF = bundle?.getString("fecha_ini")
        val datoFf = bundle?.getString("fecha_fin")
        val datoI = bundle?.getString("id_convo")

        datoId = datoI.toString()
        datoFeF = datoFf.toString()
        datoFeI = datoF.toString()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_view)
        //LLENADO DE DATOS FECHA
        textoVarianteFechaInicio.setText(datoF)
        textoVarianteFechaFin.setText(datoFf)
        //BOTONES
        btnRep.setOnClickListener {
            var res:Resumen = Resumen(numeroControl,datoI.toString().toInt(),datoN.toString().toInt(),resumen,mensaje)
            getRes(res)
        }
        btnRep2.setOnClickListener {
            var res:Resumen = Resumen(numeroControl,datoI.toString().toInt(),datoN2.toString().toInt(),resumen,mensaje)
            getRes(res)
        }
    }
    private fun getRes(res: Resumen) {
        val api  = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiGet::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            var resu: Resumen = res
            var numRes: Int
            val response= api.getResumen(resu.no_control, resu.id_conv, resu.no_repor).awaitResponse()
            runOnUiThread{
                if(response.isSuccessful){
                    var data = response.body()!!
                    if(data.no_repor == 1){
                        if (data.mensaje == "Reporte encontrado"){
                            resumen = data.descripcion
                            numRes = data.no_repor
                            nextPage(resumen, numRes)
                        }else if (data.mensaje =="Reporte no encontrado"){
                            resumen = data.descripcion
                            numRes = data.no_repor
                            nextPage(resumen, numRes)
                        }else if (data.mensaje == "Reporte enviado"){
                            showRepor()
                        }
                    }else if (data.no_repor == 2){
                        if (data.mensaje == "Reporte encontrado"){
                            resumen = data.descripcion
                            numRes = data.no_repor
                            nextPage2(resumen,numRes)
                        }else if (data.mensaje =="Reporte no encontrado"){
                            resumen = data.descripcion
                            numRes = data.no_repor
                            nextPage2(resumen,numRes)
                        }else if (data.mensaje == "Reporte enviado"){
                            showRepor()
                        }
                    }
                }else{
                    showError()
                }

            }
        }
    }

    private fun showRepor() {
        Toast.makeText(this, "tu reporte ya fue enviado.", Toast.LENGTH_SHORT).show()
    }

    private fun nextPage(resumen: String,nR:Int) {
        var res:String = resumen
        var noR:Int= nR
        val intent: Intent = Intent(this,formResumen::class.java)
        intent.putExtra("id_convo", datoId)
        intent.putExtra("fecha_ini", datoFeF)
        intent.putExtra("fecha_fin", datoFeI)
        intent.putExtra("no_repo", noR.toString())
        Log.d("Rev",noR.toString())
        intent.putExtra("resumen",res)
        Log.d("Rev",res)
        startActivity(intent)
    }
    private fun nextPage2(resumen: String,nR:Int){
        var res:String = resumen
        var noR:Int= nR
        val intent: Intent = Intent(this,formResumen::class.java)
        intent.putExtra("id_convo", datoId)
        intent.putExtra("fecha_ini", datoFeF)
        intent.putExtra("fecha_fin", datoFeI)
        intent.putExtra("no_repo", noR.toString())
        Log.d("Rev",noR.toString())
        intent.putExtra("resumen",res)
        Log.d("Rev",res)
        startActivity(intent)
    }
    private fun showError() {
        Toast.makeText(this, "eror", Toast.LENGTH_SHORT).show()
    }
}
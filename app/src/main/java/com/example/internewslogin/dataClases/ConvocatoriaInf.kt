package com.example.internewslogin.dataClases

data class ConvocatoriaInf(
    var id_conv: Int,
    var titulo: String,
    var imagen: String,
    var descripcion: String,
    var archivo: String,
    var pregunta: String,
    var estado: String,
    var carrera: String,
    var semestre: String,
    var fecha_ini: String,
    var fecha_fin: String,
    var fecha_ini_repo: String,
    var fecha_fin_repo: String,
)

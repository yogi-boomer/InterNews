package com.example.internewslogin.interfaces

import com.example.internewslogin.dataClases.Actividad
import retrofit2.Call
import retrofit2.http.GET

interface ApiGet {
    @GET("/announs/movil")
   suspend fun getConvocatorias(): Call<List<Actividad>>
}
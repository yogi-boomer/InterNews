package com.example.internewslogin.interfaces

import com.example.internewslogin.dataClases.Actividad
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiGet {
    @GET
    fun getConvocatorias(@Url url: String): Response<Actividad>
}
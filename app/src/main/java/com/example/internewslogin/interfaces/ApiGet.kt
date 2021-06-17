package com.example.internewslogin.interfaces

import com.example.internewslogin.dataClases.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiGet {

        @GET("/announs/movil")
        fun getConvocatorias(): Call<List<Actividad>>

        @GET("/announs/movil/view/{id_conv}")
        fun getInfoConvocatoriaById(@Path("id_conv") id_conv: Int): Call<ConvocatoriaInf>

        @POST("/announs/movil/register")
        fun postInscription(@Body participante: Participante?): Call<Participante>

        @POST( "/auth/movil/register")
        fun postRegistro(@Body participantead: ParticipanteAD?): Call<ParticipanteAD>

        @POST("/auth/movil/login")
        fun Login(@Body participantelg: ParticipanteLG?): Call<ParticipanteLG>

        @GET("/auth/movil/user/{no_control}")
        fun getUser(@Path("no_control") no_control: String): Call<ParticipanteAD>

        @GET("/tracings/movil/activity/report/{no_control}")
        fun gatActivityRegist(@Path("no_control") no_control: String): Call<List<ActividadInscrita>>

        @POST("/tracings/movil/add/report")
        fun postResumen(@Body reporte:Reporte?):Call<Reporte>

        @POST("/tracings/movil/save/report")
        fun postGuardaResumen(@Body reporte:Reporte?):Call<Reporte>

        @GET("/tracings/movil/save/{no_control}/{id_conv}/{no_repor}")
        fun getResumen(@Path("no_control")no_control: String, @Path("id_conv")id_conv:Int, @Path("no_repor")no_repor:Int): Call<Resumen>
}
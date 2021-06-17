package com.example.internewslogin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.internewslogin.dataClases.ParticipanteAD
import com.example.internewslogin.interfaces.ApiGet
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_registrarse.*
import kotlinx.android.synthetic.main.bg_layout_modal.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Matcher
import java.util.regex.Pattern

class registrarse : AppCompatActivity() {
    lateinit var opcion : Spinner
    lateinit var opcion2 : Spinner
    lateinit var editNombre: EditText
    lateinit var nombre :String
    lateinit var apellido:String
    var apellidoM :String= ""
    var numC :String= ""
    var cargo :String= ""
    var carrera :String= ""
    var semestre :String= ""
    var password :String= ""
    var correo :String= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)
        radioButtons()

        //IMPLEMENTACION DEL SPINNER
        opcion= findViewById(R.id.carreras) as Spinner
        opcion2 = findViewById(R.id.semestresSpin) as Spinner
        val opciones = arrayListOf("Elige uno","Ing. Bioquímica","Ing. Civil","Ing. Electromecanica","Ing. en Electronica","Ing. en Gestion Empresarial","Ing. civil","Ing. en Industrias Alimentarias", "Ing. en Sistemas Computacionales", "Ing. Industrial", "Ing. Mecatrónica", "Lic. en Gastronomia")
        val opcionSemestre = arrayListOf("Elige uno","Primer semestre","Segundo semestre","Tercer semestre","Cuarto semestre", "Quinto semestre", "Sexto semestre", "Séptimo semestre","Octavo semestre", "Noveno semestre")

        //SPINNER CARRERAS
       opcion.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,opciones)
        opcion.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                carrera = opcion.getItemAtPosition(position) as String
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                carrera = opcion.getItemAtPosition(0) as String
            }
        }

        //SPINNER SEMESTRES
        opcion2.adapter = ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,opcionSemestre)
        opcion2.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                semestre = opcion2.getItemAtPosition(position) as String
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                semestre = opcion2.getItemAtPosition(0) as String
            }
        }

        //IMPLEMENTACION DEL MENSAJE EMERGENTE
        val view = View.inflate(this,R.layout.bg_layout_contrasenia,null)
        val builder = AlertDialog.Builder(this)
        builder.setView(view)
        val dialog = builder.create()


        //BOTTON REGISTRO
        button4.setOnClickListener {
            if (editNombreP.text.isEmpty() || editApellidoP.text.isEmpty()){
                Toast.makeText(this, "Todos los campos son necesarios", Toast.LENGTH_SHORT).show()
            }else if (editApellidoM.text.isEmpty() || editNumeroC.text.isEmpty()){
                Toast.makeText(this, "Todos los campos son necesarios", Toast.LENGTH_SHORT).show()
            }else if(editPass.text.isEmpty() || editPass2.text.isEmpty()){
                Toast.makeText(this, "Todos los campos son necesarios", Toast.LENGTH_SHORT).show()
            }else if(editCorreoP.text.isEmpty()){
                editCorreoP.error = "Por favor llena el campo"
            } else if (editPass.text.toString() == editPass2.text.toString()){
                editNombre = findViewById(R.id.editNombreP) as EditText
                val nombreP = editNombre.text.toString()
                nombre = nombreP
                apellido = editApellidoP.text.toString()
                apellidoM = editApellidoM.text.toString()
                numC = editNumeroC.text.toString()
                password = editPass.text.toString()
                correo = editCorreoP.text.toString()
                nombreValidar()
            }else {
                dialog.show()
                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                view.btnConfirmar.setOnClickListener {
                    dialog.dismiss()
                }
            }
        }
    }

    //VALIDACION DE NOMBRE DE USUARIO SOLO LETRAS
    private fun nombreValidar(){
        if (nombre.length<3){
            editNombre.error = "tu nombre no puede ser menor a 3 caracteres."
        }else{
            var patronNombre : Pattern = Pattern.compile("^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]{3,}+\$")
            var comparadorNombre: Matcher = patronNombre.matcher(nombre)
            if (!comparadorNombre.find()){
                editNombreP.error = "Esto no es un nombre valido."
                false
            }else{
                true
                apellidosValidar()
            }
        }
    }

    //VALIDACION DEL APELLIDO PATERNO
    private fun apellidosValidar(){
        if (apellido.length<3){
            editApellidoP.error = "El apellido no suele ser menor a 3 caracteres."
        }else{
            var patronApellidoP : Pattern = Pattern.compile("^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð,.'-]{3,}+\$")
            var comparadorApellidoP: Matcher = patronApellidoP.matcher(apellido)
            if (!comparadorApellidoP.find()){
                editApellidoP.error = "Esto no es un apellido valido."
                false
            }else{
                true
                apellidoMValidar()
            }
        }
    }

    //VALIDACION DE APELLIDO MATERNO
    private fun apellidoMValidar(){
        if (apellidoM.length<3){
            editApellidoM.error = "El apellido no suele ser menor a 3 caracteres."
        }else{
            var patronApellidoM : Pattern = Pattern.compile("^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð,.'-]{3,}+\$")
            var comparadorApellidoM: Matcher = patronApellidoM.matcher(apellidoM)
            if (!comparadorApellidoM.find()){
                editApellidoM.error = "Esto no es un apellido valido."
                false
            }else{
                true
                contraValidar()
            }
        }
    }

    //VALIDACION DE CONTRASEÑA DE USUARIO
    private fun contraValidar(){
        if(password.length < 8){
            editPass.error = "Usa minimo 8 caracteres."
            editPass2.error = "Usa minimo 8 caracteres."
        }else{
            var patronPass : Pattern = Pattern.compile("^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}\$")
            var comparadorPass : Matcher = patronPass.matcher(password)
            if(!comparadorPass.find()){
                editPass.error = "La contraseña debe contener Almenos un caracter especial, mayusculas, minusculas y un numero."
                false
            } else{
                editPass.error= null
                true
                if (cargo == "Alumno"){
                    correoAlumno()
                }else if ( cargo == "Docente"){
                    correoDocente()
                }
            }
        }
    }

    //VALIDACION AL CORREO DEL ALUMNO
    private fun correoAlumno(){
        //PATRON PARA ALUMNO
        var patron : Pattern = Pattern.compile("$numC+@+itsx.edu.mx")
        var comparador : Matcher = patron.matcher(correo)
        if(!comparador.find()){
            editCorreoP.error = "Ingresa un correo institucional."
            false
        }else{
            editCorreoP.error= null
            true
            numeroControl()
        }
    }

    //VALIDACION A CORREO DEL DOCENTE
    private fun correoDocente(){
        Log.i("entro","INICIOANDO")
        var adaptaNombre = nombre.toLowerCase()
        var adaptarApellido = apellido.toLowerCase()
        var adaptarApellidoM = apellidoM.toLowerCase()
        var extraerLetra = adaptarApellido.get(0).toString()
        var extraerLetra2 = adaptarApellidoM.get(0).toString()
        var juntarLetras= "$extraerLetra$extraerLetra2"
        var primerNombre = nombre.split("")[0]

        if (correo == "$nombre.$apellido@itsx.edu.mx"){
            Log.d("entro","INICIOANDO")
            //PATRON PARA CORREO ITSX.EDU.MX DOCENTE
            var patron2: Pattern = Pattern.compile("$primerNombre.$apellido+@+itsx.edu.mx")
            var comparador2 : Matcher = patron2.matcher(correo)
            if (!comparador2.find()){
                editCorreoP.error = "Ingresa un correo institucional."
                false
            }else{
                true
               numeroControlDocente()
            }
        }else if (correo == "$adaptaNombre.$juntarLetras@xalapa.tecnm.mx"){
            //PATRON PARA XALAPA.TECNM.MX
            Log.i("entro","INICIOANDO")
            var patron3 : Pattern = Pattern.compile("$adaptaNombre.$juntarLetras+@+xalapa.tecnm.mx")
            Log.d("REVISION PAT", patron3.toString())
            var comparador3 : Matcher = patron3.matcher(correo)
            Log.d("REVISION CORREO", correo)
            if (!comparador3.find()){
                editCorreoP.error = "Ingresa un correo institucional."
                false
            }else{
                true
               numeroControlDocente()
            }
        }else{
            someError()
        }
    }

    //VALIDACION DEL NUMERO DE CONTROL DEL ALUMNO
    private fun numeroControl(){
        if (numC.length<9 || numC.length >9){
            editNumeroC.error = "Tu numero de control debe poseer almenos 9 digitos."
        }else{
            //PATRON NUMERO CONTROL ALUMNO
            var patronNumero : Pattern = Pattern.compile("\\d{3}+O+\\d+\\d")
            var comparadorNumero:Matcher = patronNumero.matcher(numC)
            if (!comparadorNumero.find()){
                editNumeroC.error = "El numero de control debe ser valido."
                false
            }else{
                true
                var participantead: ParticipanteAD = ParticipanteAD(nombre,apellido,apellidoM,numC,cargo,correo,carrera,semestre,password)
                if (carrera == "Elige uno"){
                    Toast.makeText(this, "Debes elegir una carrera.", Toast.LENGTH_SHORT).show()
                }else if (semestre == "Elige uno"){
                    Toast.makeText(this, "Debes elegir un semestre.", Toast.LENGTH_SHORT).show()
                }else if (cargo == ""){
                    Toast.makeText(this, "Selecciona un cargo.", Toast.LENGTH_SHORT).show()
                }else{
                    resitro(participantead)
                }
            }
        }
    }
    //VALIDAR NUMERO CONTROL DEL DOCENTE
    private fun numeroControlDocente(){
        if(numC.length<3 || numC.length>3){
            editNumeroC.error = "tu numero de control debe ser de maximo 3 digitos."
        }else{
            var patronNumeroDocente: Pattern = Pattern.compile("\\d{3}")
            var comparadorNumeroDocente: Matcher = patronNumeroDocente.matcher(numC)
            if (!comparadorNumeroDocente.find()){
                editNumeroC.error = "Tu numero de control no es valido."
                false
            }else{
                true
                var participantead: ParticipanteAD = ParticipanteAD(nombre,apellido,apellidoM,numC,cargo,correo,carrera,semestre,password)
                if (carrera == "Elige uno"){
                    Toast.makeText(this, "Debes elegir un departamento", Toast.LENGTH_SHORT).show()
                }else if (radioAlumno.isChecked == false && radioDocente.isChecked==false){
                    Toast.makeText(this, "Selecciona un cargo.", Toast.LENGTH_SHORT).show()
                }else{
                    resitro(participantead)
                }
            }
        }
    }
    //ERROR A CORREOS DOCENTES
    private fun someError() {
        editCorreoP.error = "Proporciona un correo institucional"
    }

    //DESABILITADO Y ASIGNACION DE VALORES DE RADIO BUTTONS
    private fun radioButtons(){
        radioAlumno.setOnClickListener {
            val nText = "Carrera."
            cargo = "Alumno"
            if(opcion2.isVisible == false){
                opcion2.isVisible = true
                textoSemestre.isVisible = true
                textoVariante.setText(nText)
            }
        }
        radioDocente.setOnClickListener {
            val newText = "Departamento"
            cargo = "Docente"
            textoVariante.setText(newText)
            opcion2.isVisible = false
            textoSemestre.isVisible = false
        }
    }


    //PETICION A API PARA REGISTRO DEL USUARIO
    private fun resitro(participantead: ParticipanteAD) {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = api.create(ApiGet::class.java)
        var partici : ParticipanteAD? = participantead
        service.postRegistro(partici).enqueue(object : Callback<ParticipanteAD> {
            override fun onResponse(call: Call<ParticipanteAD>, response: Response<ParticipanteAD>) {
                partici = response?.body()
                Log.d("revisionP",partici.toString())
                Log.d("Revision_participante", Gson().toJson(partici))

            }
            override fun onFailure(call: Call<ParticipanteAD>, t: Throwable) {
                exito()
            }
        })
    }
    
    private fun showError(){
        Toast.makeText(this, "Error al mandar datos al servidor.", Toast.LENGTH_SHORT).show()
    }
    private fun exito(){
        var intent:Intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}
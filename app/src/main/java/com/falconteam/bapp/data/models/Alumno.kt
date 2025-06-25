package com.falconteam.bapp.data.models

data class Alumno(
    val id: String = "",
    val nombre: String = "",
    val idCurso: String = "",
    val idPadre: String = "",
    val fechaNacimiento: String? = null // formato esperado: "YYYY-MM-DD"
) {
    fun edad(): Int? {
        return try {
            fechaNacimiento?.let {
                val partes = it.split("-").map { p -> p.toInt() }
                val (anio, mes, dia) = Triple(partes[0], partes[1], partes[2])
                val hoy = java.time.LocalDate.now()
                val nacimiento = java.time.LocalDate.of(anio, mes, dia)
                java.time.Period.between(nacimiento, hoy).years
            }
        } catch (e: Exception) {
            null
        }
    }
}

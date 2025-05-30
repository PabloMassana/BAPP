package com.falconteam.bapp.data.models

data class Indicador(
    val id: String = "",
    val alumnoId: String = "",
    val fechaEvaluacion: String = "", // ISO
    val habilidadesMotoras: Int = 0, // 0-10
    val lenguaje: Int = 0,
    val socializacion: Int = 0,
    val observaciones: String = "",
    val evaluadorId: String = "" // Maestro que evalúa
)

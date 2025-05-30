package com.falconteam.bapp.data.models

data class Bitacora(
    val id: String = "",
    val fecha: String = "", // Formato ISO (YYYY-MM-DD)
    val alimentacion: String = "",
    val descanso: String = "",
    val aprendizaje: String = "",
    val comportamiento: String = "",
    val evidencias: List<String> = emptyList(), // IDs o URLs de imágenes
    val autorId: String = "", // Maestro que registró
    val alumnoId: String = "" // Niño relacionado
)

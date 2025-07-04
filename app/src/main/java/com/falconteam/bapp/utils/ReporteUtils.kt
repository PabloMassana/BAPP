package com.falconteam.bapp.utils

object ReporteUtils {

    val mensajesEstadoAnimo = listOf(
        "Se mostró muy apático y retraído durante el día.",
        "Tuvo momentos de tristeza o molestia.",
        "Estuvo con ánimo neutral, sin cambios notables.",
        "Estuvo alegre la mayor parte del tiempo.",
        "Se mostró muy alegre y entusiasta durante todas las actividades."
    )

    val mensajesInteracciones = listOf(
        "Evitó interactuar con sus compañeros.",
        "Se mostró reacio al compartir o colaborar.",
        "Participó en algunas interacciones básicas.",
        "Habló y compartió con varios compañeros.",
        "Interactuó positivamente y con empatía en todo momento."
    )

    val mensajesParticipacion = listOf(
        "No participó en clase y se mostró desinteresado.",
        "Participó muy poco y con dificultad para concentrarse.",
        "Tuvo participación esporádica.",
        "Participó activamente, con pocas distracciones.",
        "Participó con entusiasmo y atención constante."
    )

    fun obtenerMensaje(estrellas: Int, mensajes: List<String>): String {
        return mensajes.getOrElse(estrellas.coerceIn(1, 5) - 1) { "Sin evaluación." }
    }
}
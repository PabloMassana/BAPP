package com.falconteam.bapp.ui.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun formatDate(date: Date): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return formatter.format(date)
    }
}

fun obtenerDiaDesdeFecha(fecha: Date): String {
    val formato = SimpleDateFormat("EEEE", Locale("es", "ES"))
    return formato.format(fecha).replaceFirstChar { it.uppercaseChar() }
}
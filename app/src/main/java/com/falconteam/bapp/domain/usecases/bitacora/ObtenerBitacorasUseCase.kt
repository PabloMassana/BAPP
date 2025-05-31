package com.falconteam.bapp.domain.usecases.bitacora

import com.falconteam.bapp.data.models.Bitacora
import com.falconteam.bapp.data.repository.SupabaseRepository

class ObtenerBitacorasUseCase(private val repository: SupabaseRepository) {
    suspend operator fun invoke(alumnoId: String): List<Bitacora> {
        return repository.obtenerBitacorasPorAlumno(alumnoId)
    }
}

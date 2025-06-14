package com.falconteam.bapp.domain.usecases.bitacora

import com.falconteam.bapp.data.models.Bitacora
import com.falconteam.bapp.data.repository.MainRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ObtenerBitacorasUseCase : KoinComponent {
    private val repository: MainRepository by inject()

    suspend operator fun invoke(alumnoId: String): List<Bitacora> {
        return repository.obtenerBitacorasPorAlumno(alumnoId)
    }
}

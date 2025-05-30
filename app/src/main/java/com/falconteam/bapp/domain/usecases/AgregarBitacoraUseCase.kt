package com.falconteam.bapp.domain.usecases

import com.falconteam.bapp.data.models.Bitacora
import com.falconteam.bapp.data.repository.SupabaseRepository

class AgregarBitacoraUseCase(
    private val repository: SupabaseRepository
) {
    suspend operator fun invoke(bitacora: Bitacora) {
        repository.agregarBitacora(bitacora)
    }
}

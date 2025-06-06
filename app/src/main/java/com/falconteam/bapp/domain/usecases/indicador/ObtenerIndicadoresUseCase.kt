package com.falconteam.bapp.domain.usecases.indicador

import com.falconteam.bapp.data.models.Indicador
import com.falconteam.bapp.data.repository.SupabaseRepository

class ObtenerIndicadoresUseCase(private val repository: SupabaseRepository) {
    suspend operator fun invoke(alumnoId: String): List<Indicador> {
        return repository.obtenerIndicadores(alumnoId)
    }
}

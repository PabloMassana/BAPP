package com.falconteam.bapp.data.supabase

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseService {
    private const val SUPABASE_URL = "https://your-project-id.supabase.co"
    private const val SUPABASE_API_KEY = "your-anon-key"

    val client: SupabaseClient = createSupabaseClient(SUPABASE_URL) {
        install(GoTrue)
        install(Postgrest)
        // Puedes añadir Storage u otros módulos si son necesarios
    }

    val auth get() = client.auth
    val database get() = client.postgrest
}

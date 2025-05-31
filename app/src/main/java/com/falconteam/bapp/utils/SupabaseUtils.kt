package com.falconteam.bapp.utils

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.storage

object SupabaseUtils {
    val client: SupabaseClient by lazy {
        createSupabaseClient(
            supabaseUrl = "https://your-project.supabase.co",
            supabaseKey = "your-anon-key"
        ) {
            install(io.github.jan.supabase.gotrue.GoTrue)
            install(io.github.jan.supabase.postgrest.Postgrest)
            install(io.github.jan.supabase.storage.Storage)
        }
    }

    val auth get() = client.gotrue
    val postgrest get() = client.postgrest
    val storage get() = client.storage
}

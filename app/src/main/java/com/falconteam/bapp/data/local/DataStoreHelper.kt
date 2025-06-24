package com.falconteam.bapp.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.falconteam.bapp.utils.Constants.DATA_STORE_FILENAME
import kotlinx.coroutines.flow.first
import okio.Path.Companion.toPath

fun createDataStore(context: Context): DataStore<Preferences> {
    println("DataStore created")
    return PreferenceDataStoreFactory.createWithPath(
        produceFile = {
            context.filesDir.resolve(DATA_STORE_FILENAME).absolutePath.toPath()
        }
    )
}

class DataStoreHelper(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        val USER_JWT_TOKEN_KEY = stringPreferencesKey("user_token")
        val USER_ID = stringPreferencesKey("user_id")
        val USER_ROLE = stringPreferencesKey("user_role")
    }

    // Insert or Update value in DataStore
    suspend fun <T> putValue(key: Preferences.Key<T>, value: T) {
        try {
            dataStore.edit { preferences ->
                preferences[key] = value
            }
        } catch (e: Exception) {
            println("Put value datastore exception: ${e.message}")
        }
    }

    // Retrieve value from DataStore
    suspend fun <T> getValue(key: Preferences.Key<T>, defaultValue: T): T {
        val preferences = dataStore.data.first()
        return preferences[key] ?: defaultValue
    }

    // Delete a specific key-value pair
    suspend fun <T> deleteValue(key: Preferences.Key<T>) {
        dataStore.edit { preferences ->
            preferences.remove(key)
        }
    }

    suspend fun <T> clearValue(key: Preferences.Key<T>) {
        dataStore.edit { preferences ->
            preferences.remove(key)
        }
    }

    // Clear all values in the DataStore
    suspend fun clearAll() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
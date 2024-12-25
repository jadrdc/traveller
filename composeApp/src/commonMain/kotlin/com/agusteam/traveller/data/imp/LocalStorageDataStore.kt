package com.agusteam.traveller.data.imp

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.agusteam.traveller.domain.interfaces.LocalStoragePreferenceRepository
import kotlinx.coroutines.flow.Flow

class LocalStorageDataStore(private val storage: DataStore<Preferences>) :
    LocalStoragePreferenceRepository {
    override suspend fun save(key: String, value: String) {
        storage.edit { dataStore ->
            val valueKey = stringPreferencesKey(key)
            dataStore[valueKey] = value
        }
    }

    override suspend fun getValue(): Flow<Preferences> {
        return storage.data
    }
}
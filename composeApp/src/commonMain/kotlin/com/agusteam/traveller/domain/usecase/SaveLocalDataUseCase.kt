package com.agusteam.traveller.domain.usecase

import com.agusteam.traveller.data.util.EMAIL
import com.agusteam.traveller.data.util.LAST_NAME
import com.agusteam.traveller.data.util.NAME
import com.agusteam.traveller.data.util.PHONE
import com.agusteam.traveller.data.util.USER_ID
import com.agusteam.traveller.domain.interfaces.LocalStoragePreferenceRepository
import com.agusteam.traveller.domain.models.LoginModel

class SaveLocalDataUseCase(private val localStorageDataStore: LocalStoragePreferenceRepository) {
    suspend operator fun invoke(model: LoginModel) {
        localStorageDataStore.save(EMAIL, model.email)
        localStorageDataStore.save(USER_ID, model.id)
        localStorageDataStore.save(NAME, model.name)
        localStorageDataStore.save(LAST_NAME, model.lastname)
        localStorageDataStore.save(PHONE, model.phone)
    }
}
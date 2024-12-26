package com.agusteam.traveller.core.di

import com.agusteam.traveller.data.imp.CategoryRepositoryImpl
import com.agusteam.traveller.data.imp.LocalStorageDataStore
import com.agusteam.traveller.data.imp.LoginRepositoryImpl
import com.agusteam.traveller.data.imp.TripProviderRepositoryImp
import com.agusteam.traveller.data.imp.TripRepositoryImp
import com.agusteam.traveller.domain.interfaces.CategoryRepository
import com.agusteam.traveller.domain.interfaces.LocalStoragePreferenceRepository
import com.agusteam.traveller.domain.interfaces.LoginRepository
import com.agusteam.traveller.domain.interfaces.TripProviderRepository
import com.agusteam.traveller.domain.interfaces.TripRepository
import com.agusteam.traveller.domain.usecase.GetCategoryUseCase
import com.agusteam.traveller.domain.usecase.GetPaginatedTripsUseCase
import com.agusteam.traveller.domain.usecase.GetProfileUseCase
import com.agusteam.traveller.domain.usecase.GetTripProviderDetailsUseCase
import com.agusteam.traveller.domain.usecase.LoginUseCase
import com.agusteam.traveller.domain.usecase.RequestResetPasswordEmailUseCase
import com.agusteam.traveller.domain.usecase.SaveLocalDataUseCase
import com.agusteam.traveller.domain.usecase.SignUpUseCase
import com.agusteam.traveller.domain.validators.FieldValidator
import org.koin.dsl.module

val diDomainModule = module {
    single<FieldValidator> { FieldValidator() }
    single<LoginRepository> { LoginRepositoryImpl(get()) }
    single<LoginUseCase> { LoginUseCase(get()) }
    single<RequestResetPasswordEmailUseCase> { RequestResetPasswordEmailUseCase(get()) }
    single<SignUpUseCase> { SignUpUseCase(get()) }
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }
    single<TripProviderRepository> { TripProviderRepositoryImp(get()) }
    single<GetCategoryUseCase> { GetCategoryUseCase(get()) }
    single<SaveLocalDataUseCase> { SaveLocalDataUseCase(get()) }
    single<GetProfileUseCase> { GetProfileUseCase(get()) }
    single<GetTripProviderDetailsUseCase> { GetTripProviderDetailsUseCase(get()) }
    single<LocalStoragePreferenceRepository> { LocalStorageDataStore(get()) }
    single<TripRepository> { TripRepositoryImp(get()) }
    single<GetPaginatedTripsUseCase> { GetPaginatedTripsUseCase(get()) }
}

package com.agusteam.traveller.core.di

import com.agusteam.traveller.data.imp.LoginRepositoryImpl
import com.agusteam.traveller.domain.interfaces.LoginRepository
import com.agusteam.traveller.domain.usecase.LoginUseCase
import com.agusteam.traveller.domain.usecase.RequestResetPasswordEmailUseCase
import com.agusteam.traveller.domain.usecase.SignUpUseCase
import com.agusteam.traveller.domain.validators.FieldValidator
import org.koin.dsl.module

val diDomainModule = module {
    single<FieldValidator> { FieldValidator() }
    single<LoginRepository> { LoginRepositoryImpl(get()) }
    single<LoginUseCase> { LoginUseCase(get()) }
    single<RequestResetPasswordEmailUseCase> { RequestResetPasswordEmailUseCase(get()) }
    single<SignUpUseCase> { SignUpUseCase(get()) }
}
package com.ssag.ssag_admin.di

import com.ssag.data.repository.AuthRepositoryImpl
import com.ssag.data.repository.CleanRepositoryImpl
import com.ssag.domain.feature.auth.repository.AuthRepository
import com.ssag.domain.feature.clean.repository.CleanRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    abstract fun provideCleanRepository(
        cleanRepositoryImpl: CleanRepositoryImpl
    ): CleanRepository
}
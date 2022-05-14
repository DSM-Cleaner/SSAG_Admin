package com.ssag.ssag_admin.di

import com.ssag.data.remote.datasource.AuthRemoteDataSource
import com.ssag.data.remote.datasource.AuthRemoteDataSourceImpl
import com.ssag.data.remote.datasource.CleanRemoteDataSource
import com.ssag.data.remote.datasource.CleanRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun provideAuthRemoteDataSource(
        authRemoteDataSourceImpl: AuthRemoteDataSourceImpl
    ): AuthRemoteDataSource

    @Binds
    abstract fun provideCleanRemoteDataSource(
        cleanRemoteDataSourceImpl: CleanRemoteDataSourceImpl
    ): CleanRemoteDataSource
}
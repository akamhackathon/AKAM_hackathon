package com.ryan.project.di

import com.ryan.project.authentication.repository.AuthRepo
import com.ryan.project.authentication.repository.DefaultAuthRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {



}
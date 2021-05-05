package com.example.imagepagelist.di

import com.example.imagepagelist.remote.ImageRepo
import com.example.imagepagelist.remote.ImageRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {



    @Binds
    abstract fun bindImageRepository(
       imageRepoImpl: ImageRepoImpl
    ): ImageRepo


}
package com.example.myapplication3.core.di
import com.example.myapplication3.data.remote.ProjectApiServices
import com.example.myapplication3.data.repositoryImpl.MovieRepositoryImpl
import com.example.myapplication3.domain.repositories.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun providesRepository(projectApiServices: ProjectApiServices) : MovieRepository{
        return MovieRepositoryImpl(projectApiServices = projectApiServices)

    }

}
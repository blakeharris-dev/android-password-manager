package dev.blakeharris.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.blakeharris.data.CredentialRepository
import dev.blakeharris.data.CredentialRepositoryImpl
import dev.blakeharris.database.credential.CredentialDao
import javax.inject.Singleton

/**
 * Provides repository singletons. Each `@Provides` returns the public interface so the `internal`
 * implementation is never leaked.
 */
@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {
    @Provides
    @Singleton
    fun provideCredentialRepository(dao: CredentialDao): CredentialRepository = CredentialRepositoryImpl(dao)
}

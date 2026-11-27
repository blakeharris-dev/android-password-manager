package dev.blakeharris.data

import dev.blakeharris.database.credential.CredentialDao
import dev.blakeharris.model.Credential
import dev.blakeharris.model.CredentialPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class CredentialRepositoryImpl(private val credentialDao: CredentialDao) : CredentialRepository {

    override fun previews(): Flow<List<CredentialPreview>> = credentialDao.previews().map { previews ->
        previews.map {
            CredentialPreview(
                id = it.id,
                name = it.name,
                createdAt = it.createdAt,
                lastUpdated = it.lastUpdated
            )
        }
    }

    override suspend fun getById(id: String): Credential? = credentialDao.getById(id)?.toDomain()

    override suspend fun add(credential: Credential): String {
        credentialDao.add(credential.toEntity())
        return credential.id
    }

    override suspend fun update(credential: Credential): String? {
        val updated: Int = credentialDao.update(credential.toEntity())
        if (updated == 1) return credential.id
        return null
    }

    override suspend fun deleteById(id: String): Boolean {
        val deleted: Int = credentialDao.deleteById(id)
        if (deleted == 1) return true
        return false
    }
}

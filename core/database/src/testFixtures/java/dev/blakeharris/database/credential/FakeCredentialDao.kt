package dev.blakeharris.database.credential

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class FakeCredentialDao : CredentialDao {

    private val credentials = MutableStateFlow<Map<String, CredentialEntity>>(emptyMap())

    override fun previews(): Flow<List<CredentialPreview>> = credentials.map { map ->
        map.values
            .sortedBy { it.name.lowercase() }
            .map { CredentialPreview(it.id, it.name, it.createdAt, it.lastUpdated) }
    }

    override suspend fun getById(id: String): CredentialEntity? = credentials.value[id]

    override suspend fun add(credential: CredentialEntity) {
        check(credential.id !in credentials.value) { "Duplicate ID: ${credential.id}" }
        credentials.update { it + (credential.id to credential) }
    }

    override suspend fun update(credential: CredentialEntity): Int {
        if (credential.id !in credentials.value) return 0
        credentials.update { it + (credential.id to credential) }
        return 1
    }

    override suspend fun deleteById(id: String): Int {
        if (id !in credentials.value) return 0
        credentials.update { it - id }
        return 1
    }
}

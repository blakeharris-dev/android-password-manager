package dev.blakeharris.data

import dev.blakeharris.model.Credential
import dev.blakeharris.model.CredentialPreview
import kotlinx.coroutines.flow.Flow

/**
 * Provides access to stored credential data.
 */
interface CredentialRepository {
    /**
     * Observes credential summaries.
     *
     * @return A flow that emits the latest previews whenever credential data changes.
     */
    fun previews(): Flow<List<CredentialPreview>>

    /**
     * Retrieves a credential by its unique ID
     *
     * @param id Unique identifier of the credential
     * @return The credential or `null` if none exists
     */
    suspend fun getById(id: String): Credential?

    /**
     * Stores a credential.
     *
     * @param credential Credential to store
     * @return The unique identifier for the credential
     */
    suspend fun add(credential: Credential): String

    /**
     * Updates an existing credential.
     *
     * @param credential The credential with updated values
     * @return The unique identifier if the credential was updated, or `null` if it wasn't found
     */
    suspend fun update(credential: Credential): String?

    /**
     * Deletes a credential by its unique ID.
     *
     * @param id Unique identifier of the credential
     * @return `true` if the credential was deleted, `false` otherwise
     */
    suspend fun deleteById(id: String): Boolean
}

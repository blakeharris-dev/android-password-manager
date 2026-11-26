package dev.blakeharris.model

/**
 * Represents summarised information for a credential.
 */
data class CredentialPreview(
    val id: String,
    val name: String,
    val createdAt: Long,
    val lastUpdated: Long
)

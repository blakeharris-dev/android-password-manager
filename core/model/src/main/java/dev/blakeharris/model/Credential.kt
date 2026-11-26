package dev.blakeharris.model

/**
 * Domain model for a stored account credential.
 */
data class Credential(
    val id: String,
    val name: String,
    val createdAt: Long,
    val lastUpdated: Long,
    val username: String?,
    val emailAddress: String,
    val password: String,
    val servicePin: String?,
    val twoFactorEnabled: Boolean,
    val notes: String?
)

package dev.blakeharris.data

import dev.blakeharris.database.credential.CredentialEntity
import dev.blakeharris.model.Credential

/**
 * Converts this database entity to a domain credential.
 */
internal fun CredentialEntity.toDomain(): Credential = Credential(
    id = id,
    name = name,
    createdAt = createdAt,
    lastUpdated = lastUpdated,
    username = username,
    emailAddress = emailAddress,
    password = password,
    servicePin = servicePin,
    twoFactorEnabled = twoFactorEnabled,
    notes = notes
)

/**
 * Converts this domain credential to a database entity.
 */
internal fun Credential.toEntity(): CredentialEntity = CredentialEntity(
    id = id,
    name = name,
    createdAt = createdAt,
    lastUpdated = lastUpdated,
    username = username,
    emailAddress = emailAddress,
    password = password,
    servicePin = servicePin,
    twoFactorEnabled = twoFactorEnabled,
    notes = notes
)

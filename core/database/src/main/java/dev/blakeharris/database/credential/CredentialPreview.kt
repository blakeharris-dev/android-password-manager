package dev.blakeharris.database.credential

import androidx.room3.ColumnInfo

/**
 * A lightweight projection of credential fields used for list previews.
 */
data class CredentialPreview(
    val id: String,
    val name: String,
    @ColumnInfo(name = "created_at") val createdAt: Long,
    @ColumnInfo(name = "last_updated") val lastUpdated: Long
)

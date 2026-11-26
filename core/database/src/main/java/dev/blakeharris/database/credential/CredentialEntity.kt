package dev.blakeharris.database.credential

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey

/**
 * Room entity representing a stored account credential.
 */
@Entity(tableName = "credentials")
data class CredentialEntity(
    @PrimaryKey val id: String,
    val name: String,
    @ColumnInfo(name = "created_at") val createdAt: Long,
    @ColumnInfo(name = "last_updated") val lastUpdated: Long,
    val username: String?,
    @ColumnInfo(name = "email_address") val emailAddress: String,
    val password: String,
    @ColumnInfo(name = "two_factor_enabled") val twoFactorEnabled: Boolean,
    @ColumnInfo(name = "service_pin") val servicePin: String?,
    val notes: String?
) {
    fun toPreview(): CredentialPreview = CredentialPreview(
        id = id,
        name = name,
        createdAt = createdAt,
        lastUpdated = lastUpdated
    )
}

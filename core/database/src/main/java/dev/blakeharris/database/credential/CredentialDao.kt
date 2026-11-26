package dev.blakeharris.database.credential

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import kotlinx.coroutines.flow.Flow

/**
 * Provides database operations for stored credentials.
 */
@Dao
interface CredentialDao {

    /**
     * Observes credential previews ordered alphabetically by name.
     */
    @Query("SELECT id, name, created_at, last_updated FROM credentials ORDER BY name COLLATE NOCASE ASC")
    fun previews(): Flow<List<CredentialPreview>>

    /**
     * Returns the credential with [id] or `null` if none exist.
     */
    @Query("SELECT * FROM credentials WHERE id = :id")
    suspend fun getById(id: String): CredentialEntity?

    /**
     * Inserts [credential] into the credentials table.
     */
    @Insert
    suspend fun add(credential: CredentialEntity)

    /**
     * Updates [credential] and returns the number of affected rows.
     */
    @Update
    suspend fun update(credential: CredentialEntity): Int

    /**
     * Deletes the credential with [id] and returns the number of affected rows.
     */
    @Query("DELETE FROM credentials WHERE id = :id")
    suspend fun deleteById(id: String): Int
}

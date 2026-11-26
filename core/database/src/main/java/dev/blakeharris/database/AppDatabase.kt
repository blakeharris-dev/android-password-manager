package dev.blakeharris.database

import androidx.room3.Database
import androidx.room3.RoomDatabase
import dev.blakeharris.database.credential.CredentialDao
import dev.blakeharris.database.credential.CredentialEntity

/**
 * The app's database. Other modules never see it: they inject DAOs instead.
 *
 * When an entity changes, bump `version` and add a migration. The exported schema in `core/
 * database/schemas/` is committed so Room can check migrations against it.
 */
@Database(
    entities = [CredentialEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun credentialDao(): CredentialDao
}

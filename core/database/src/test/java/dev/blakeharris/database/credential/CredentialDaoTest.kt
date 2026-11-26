package dev.blakeharris.database.credential

import androidx.sqlite.SQLiteException
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import dev.blakeharris.database.InMemoryDatabaseRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CredentialDaoTest {

    @get:Rule
    val databaseRule = InMemoryDatabaseRule()

    private val credentialDao get() = databaseRule.database.credentialDao()

    @Test
    fun `previews maps stored credentials to previews`() = runTest {
        val credential = CredentialEntityTestData.minimal()
        credentialDao.add(credential)

        credentialDao.previews().test {
            val expected = CredentialPreview(
                id = credential.id,
                name = credential.name,
                createdAt = credential.createdAt,
                lastUpdated = credential.lastUpdated
            )
            assertEquals(listOf(expected), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `previews emits an empty list when the database is empty`() = runTest {
        credentialDao.previews().test {
            assertEquals(emptyList<CredentialPreview>(), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `previews sorts credentials alphabetically by name`() = runTest {
        val mike = CredentialEntityTestData.minimal(id = "credential_1", name = "Mike")
        val alpha = CredentialEntityTestData.minimal(id = "credential_2", name = "Alpha")
        val zulu = CredentialEntityTestData.minimal(id = "credential_3", name = "Zulu")

        credentialDao.add(mike)
        credentialDao.add(zulu)
        credentialDao.add(alpha)

        credentialDao.previews().test {
            assertEquals(
                listOf(alpha.toPreview(), mike.toPreview(), zulu.toPreview()),
                awaitItem()
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `previews ignores case`() = runTest {
        val zulu = CredentialEntityTestData.minimal(id = "credential_3", name = "Zulu")
        val alpha = CredentialEntityTestData.minimal(id = "credential_2", name = "alpha")

        credentialDao.add(zulu)
        credentialDao.add(alpha)

        credentialDao.previews().test {
            assertEquals(
                listOf(alpha.toPreview(), zulu.toPreview()),
                awaitItem()
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `previews re-emits when a new credential is added`() = runTest {
        val first = CredentialEntityTestData.minimal(
            id = "credential_1",
            name = "First"
        )
        val second = CredentialEntityTestData.minimal(
            id = "credential_2",
            name = "Second"
        )

        credentialDao.add(first)

        credentialDao.previews().test {
            assertEquals(listOf(first.toPreview()), awaitItem())

            credentialDao.add(second)

            assertEquals(listOf(first.toPreview(), second.toPreview()), awaitItem())
        }
    }

    @Test
    fun `previews re-emits when a credential is updated`() = runTest {
        val credential = CredentialEntityTestData.minimal(
            id = "credential_1",
            name = "First"
        )
        credentialDao.add(credential)

        credentialDao.previews().test {
            assertEquals(listOf(credential.toPreview()), awaitItem())

            val updated = credential.copy(name = "Updated", lastUpdated = 5_000L)
            credentialDao.update(updated)

            assertEquals(listOf(updated.toPreview()), awaitItem())
        }
    }

    @Test
    fun `previews re-emits when a credential is deleted`() = runTest {
        val credential = CredentialEntityTestData.minimal()
        credentialDao.add(credential)

        credentialDao.previews().test {
            assertEquals(listOf(credential.toPreview()), awaitItem())

            credentialDao.deleteById(credential.id)

            assertEquals(emptyList<CredentialPreview>(), awaitItem())
        }
    }

    @Test
    fun `getById returns the credential with the matching ID`() = runTest {
        val first = CredentialEntityTestData.complete(id = "credential_1", name = "First")
        val second = CredentialEntityTestData.complete(id = "credential_2", name = "Second")
        credentialDao.add(first)
        credentialDao.add(second)

        assertEquals(second, credentialDao.getById(second.id))
    }

    @Test
    fun `getById returns null when no credential has the ID`() = runTest {
        credentialDao.add(CredentialEntityTestData.complete(id = "credential_1"))
        assertNull(credentialDao.getById("missing"))
    }

    @Test
    fun `getById returns null optional fields as null`() = runTest {
        val credential = CredentialEntityTestData.minimal()
        credentialDao.add(credential)
        assertEquals(credential, credentialDao.getById(credential.id))
    }

    @Test
    fun `add persists all fields of a credential`() = runTest {
        val credential = CredentialEntityTestData.complete()
        credentialDao.add(credential)
        assertEquals(credential, credentialDao.getById(credential.id))
    }

    @Test
    fun `add throws when a credential with the same ID exists`() = runTest {
        val first = CredentialEntityTestData.complete(id = "credential_1")
        val second = first.copy(id = "credential_2")

        credentialDao.add(first)
        credentialDao.add(second)

        assertEquals(first, credentialDao.getById(first.id))
        assertEquals(second, credentialDao.getById(second.id))
    }

    @Test
    fun `add persists two credentials with identical content but different IDs`() = runTest {
        val original = CredentialEntityTestData.complete(id = "credential_1")
        val duplicate = CredentialEntityTestData.minimal(id = "credential_1")
        credentialDao.add(original)

        val result = runCatching { credentialDao.add(duplicate) }

        assertTrue(result.exceptionOrNull() is SQLiteException)
        assertEquals(original, credentialDao.getById(original.id))
    }

    @Test
    fun `update replaces the stored credential`() = runTest {
        val original = CredentialEntityTestData.complete(id = "credential_1")
        credentialDao.add(original)

        val updated = CredentialEntityTestData.minimal(id = "credential_1")
        val rowsUpdated = credentialDao.update(updated)

        assertEquals(1, rowsUpdated)
        assertEquals(updated, credentialDao.getById("credential_1"))
    }

    @Test
    fun `update leaves other credentials unchanged`() = runTest {
        val target = CredentialEntityTestData.complete(id = "credential_1")
        val other = CredentialEntityTestData.complete(id = "credential_2")
        credentialDao.add(target)
        credentialDao.add(other)

        credentialDao.update(target.copy(name = "Updated"))

        assertEquals(other, credentialDao.getById(other.id))
    }

    @Test
    fun `update returns 1 when the credential is unchanged`() = runTest {
        val credential = CredentialEntityTestData.complete()
        credentialDao.add(credential)

        val rowsUpdated = credentialDao.update(credential)

        assertEquals(1, rowsUpdated)
        assertEquals(credential, credentialDao.getById(credential.id))
    }

    @Test
    fun `update returns 0 and inserts nothing when the credential doesn't exist`() = runTest {
        val credential = CredentialEntityTestData.complete()

        val rowsUpdated = credentialDao.update(credential)

        assertEquals(0, rowsUpdated)
        assertNull(credentialDao.getById(credential.id))
    }

    @Test
    fun `deleteById returns 1 and removes the credential`() = runTest {
        val credential = CredentialEntityTestData.complete()
        credentialDao.add(credential)

        val rowsDeleted = credentialDao.deleteById(credential.id)

        assertEquals(1, rowsDeleted)
        assertNull(credentialDao.getById(credential.id))
    }

    @Test
    fun `deleteById leaves other credentials unchanged`() = runTest {
        val target = CredentialEntityTestData.complete(id = "credential_1")
        val other = CredentialEntityTestData.complete(id = "credential_2")
        credentialDao.add(target)
        credentialDao.add(other)

        credentialDao.deleteById(target.id)

        assertEquals(other, credentialDao.getById(other.id))
    }

    @Test
    fun `deleteById returns 0 and removes nothing when the credential doesn't exist`() = runTest {
        val credential = CredentialEntityTestData.complete()
        credentialDao.add(credential)

        val rowsDeleted = credentialDao.deleteById("missing")

        assertEquals(0, rowsDeleted)
        assertEquals(credential, credentialDao.getById(credential.id))
    }
}

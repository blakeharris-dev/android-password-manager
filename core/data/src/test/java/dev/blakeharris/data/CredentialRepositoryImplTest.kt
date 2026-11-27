package dev.blakeharris.data

import app.cash.turbine.test
import dev.blakeharris.database.credential.FakeCredentialDao
import dev.blakeharris.model.CredentialPreview
import dev.blakeharris.model.CredentialTestData
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CredentialRepositoryImplTest {

    private val repository = CredentialRepositoryImpl(FakeCredentialDao())

    @Test
    fun `previews map stored credentials to domain previews`() = runTest {
        val credential = CredentialTestData.complete()
        repository.add(credential)

        repository.previews().test {
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
    fun `add stores the credential and returns its ID`() = runTest {
        val credential = CredentialTestData.complete()

        val id = repository.add(credential)

        assertEquals(credential.id, id)
        assertEquals(credential, repository.getById(id))
    }

    @Test
    fun `add stores a credential with null optional fields`() = runTest {
        val credential = CredentialTestData.minimal()

        repository.add(credential)

        assertEquals(credential, repository.getById(credential.id))
    }

    @Test
    fun `getById returns null when the credential doesn't exist`() = runTest {
        assertNull(repository.getById("missing"))
    }

    @Test
    fun `update returns the ID and stores the changes when the credential exists`() = runTest {
        val original = CredentialTestData.complete()
        repository.add(original)
        val updated = original.copy(name = "Updated", lastUpdated = 5_000L)

        val id = repository.update(updated)

        assertEquals(updated.id, id)
        assertEquals(updated, repository.getById(updated.id))
    }

    @Test
    fun `update returns null when the credential doesn't exist`() = runTest {
        assertNull(repository.update(CredentialTestData.complete()))
    }

    @Test
    fun `deleteById returns true and removes the credential when it exists`() = runTest {
        val credential = CredentialTestData.complete()
        repository.add(credential)

        assertTrue(repository.deleteById(credential.id))
        assertNull(repository.getById(credential.id))
    }

    @Test
    fun `deleteById returns false when the credential doesn't exist`() = runTest {
        assertFalse(repository.deleteById("missing"))
    }
}

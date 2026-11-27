package dev.blakeharris.model

object CredentialTestData {
    fun minimal(id: String = "minimal-id", name: String = "Minimal") = Credential(
        id = id,
        name = name,
        createdAt = 1_000L,
        lastUpdated = 1_000L,
        username = null,
        emailAddress = "minimal@example.com",
        password = "password",
        twoFactorEnabled = false,
        servicePin = null,
        notes = null
    )

    fun complete(id: String = "complete-id", name: String = "Complete") = Credential(
        id = id,
        name = name,
        createdAt = 1_000L,
        lastUpdated = 2_000L,
        username = "username",
        emailAddress = "complete@example.com",
        password = "password",
        twoFactorEnabled = true,
        servicePin = "1234",
        notes = "Some notes"
    )
}

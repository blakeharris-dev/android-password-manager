package dev.blakeharris.database

import android.content.Context
import androidx.room3.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.rules.ExternalResource

class InMemoryDatabaseRule : ExternalResource() {

    lateinit var database: AppDatabase
        private set

    override fun before() {
        val ctx = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder<AppDatabase>(ctx).build()
    }

    override fun after() {
        database.close()
    }
}

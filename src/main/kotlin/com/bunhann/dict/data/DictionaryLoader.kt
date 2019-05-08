package com.bunhann.dict.data

import java.util.concurrent.CompletableFuture

class DictionaryLoader(private val iterator: Iterator<DictionaryEntry>,
                       private val dbConnectionProvider: DbConnectionProvider = DbConnectionProvider.defaultProvider) {

    fun loadDictionary(): CompletableFuture<Void> {
        return CompletableFuture.runAsync {
            dbConnectionProvider.getConnection().use { conn-> conn.prepareStatement("INSERT INTO entries(entry, explanation) VALUE (?,?)").use {
                stmnt -> iterator.forEach { e->
                    stmnt.setString(1, e.term)
                    stmnt.setString(2, e.explanation)
                    stmnt.executeUpdate()
                    }
                }
            }
        }
    }
}
package com.alexeykorshun.android.skydictonary.network

import com.alexeykorshun.android.skydictonary.store.DictionaryStore

/**
 * @author Alexei Korshun on 29.09.2020.
 */
class DictionaryApiImpl : DictionaryApi {

    private val client = Client()

    override fun find(word: String): List<DictionaryStore.State.Meanings> = Request.Builder()
        .url("https://dictionary.skyeng.ru/api/public/v1/words/search?search=$word")
        .build()
        .let { client.execute(it) }
        .let { DictionaryDeserializer.parse(it) }
}
package com.alexeykorshun.skydictonary.network

import com.alexeykorshun.skydictonary.store.DictionaryStore

/**
 * @author Alexei Korshun on 29.09.2020.
 */
expect class DictionaryDeserializer {

    fun parse(raw: String): List<DictionaryStore.State.Meanings>
}
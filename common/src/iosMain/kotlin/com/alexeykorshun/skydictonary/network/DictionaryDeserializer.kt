package com.alexeykorshun.skydictonary.network

import com.alexeykorshun.skydictonary.store.DictionaryStore

/**
 * @author Alexei Korshun on 02.04.2021.
 */
class IosDictionaryDeserializer : DictionaryDeserializer {

    override fun parse(raw: String): List<DictionaryStore.State.Meanings> = emptyList()
}
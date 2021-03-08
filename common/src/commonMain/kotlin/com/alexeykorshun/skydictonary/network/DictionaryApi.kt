package com.alexeykorshun.skydictonary.network

import com.alexeykorshun.skydictonary.store.DictionaryStore

/**
 * @author Alexei Korshun on 29.09.2020.
 */
interface DictionaryApi {

    fun find(word: String): List<DictionaryStore.State.Meanings>
}
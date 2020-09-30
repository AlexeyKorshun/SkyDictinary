package com.alexeykorshun.android.skydictonary.store

import com.arkivanov.mvikotlin.core.store.Store

/**
 * @author Alexei Korshun on 29.09.2020.
 */
interface DictionaryStore : Store<DictionaryStore.Intent, DictionaryStore.State, Nothing> {

    data class State(
        val error: Boolean,
        val progress: Boolean,
        val meanings: List<Meanings>
    ) {

        data class Meanings(
            val text: String,
            val translations: List<Translation>
        )

        data class Translation(
            val translation: String,
            val images: Images
        )

        data class Images(
            val preview: String,
            val full: String
        )
    }

    sealed class Intent {

        class Find(val word: String) : Intent()
    }
}
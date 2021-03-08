package com.alexeykorshun.skydictonary.ui.list

import com.arkivanov.mvikotlin.core.view.MviView
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * @author Alexei Korshun on 08.03.2021.
 */
interface ListView : MviView<ListView.Model, ListView.Event> {

    data class Model(
        val error: Boolean,
        val progress: Boolean,
        val items: List<MeaningItem>
    )

    sealed class Event {

        class Translate(val word: String) : Event()

        class Clicked(val item: MeaningItem) : Event()
    }

    @Serializable
    data class MeaningItem constructor(
            val text: String,
            val translations: List<TranslationItem>
    ) {

        companion object {

            fun fromString(rawString: String): MeaningItem = Json.decodeFromString(rawString)
        }

        override fun toString(): String = Json.encodeToString(this)

        @Serializable
        data class TranslationItem(
                val text: String,
                val fullImageUrl: String,
                val previewImageUrl: String
        ) {

            override fun toString(): String = Json.encodeToString(this)
        }
    }
}
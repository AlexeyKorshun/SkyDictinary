package com.alexeykorshun.android.skydictonary.ui.list

import com.arkivanov.mvikotlin.core.view.MviView

/**
 * @author Alexei Korshun on 29.09.2020.
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
}
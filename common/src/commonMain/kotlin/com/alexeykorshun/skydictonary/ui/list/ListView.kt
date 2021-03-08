package com.alexeykorshun.skydictonary.ui.list

import com.alexeykorshun.skydictonary.data.MeaningItem
import com.arkivanov.mvikotlin.core.view.MviView

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
}
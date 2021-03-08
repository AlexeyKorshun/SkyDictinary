package com.alexeykorshun.android.skydictonary.ui.list

import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.ContentLoadingProgressBar
import androidx.core.widget.doAfterTextChanged
import com.alexeykorshun.skydictonary.ui.list.ListView
import com.alexeykorshun.skydictonary.ui.list.ListView.MeaningItem
import com.arkivanov.mvikotlin.core.view.BaseMviView
import kotlinx.android.synthetic.main.f_list.view.*

/**
 * @author Alexei Korshun on 29.09.2020.
 */
class ListViewImpl(
    private val view: View
) : BaseMviView<ListView.Model, ListView.Event>(), ListView {

    private val clickListener: (MeaningItem) -> Unit = { item -> dispatch(ListView.Event.Clicked(item)) }

    private val adapter = MeaningsListAdapter(clickListener)

    init {
        view.buttonTranslate.setOnClickListener { dispatch(ListView.Event.Translate(view.inputWord.text.toString())) }
        view.inputWord.doAfterTextChanged { text -> view.buttonTranslate.isEnabled = !text.isNullOrBlank() }
        view.listMeanings.adapter = adapter
    }

    override fun render(model: ListView.Model) {
        view.buttonTranslate.isEnabled = !model.progress && !view.inputWord.text.isNullOrBlank()
        view.inputWord.isEnabled = !model.progress
        view.error.isVisible = model.error
        view.empty.isVisible = model.items.isEmpty() && !model.progress && !model.error
        view.progress.show(model.progress)
        adapter.showItems(model.items)
    }

    private fun ContentLoadingProgressBar.show(show: Boolean) {
        if (show) show()
        else hide()
    }
}
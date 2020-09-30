package com.alexeykorshun.android.skydictonary.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alexeykorshun.android.skydictonary.R
import kotlinx.android.synthetic.main.i_meaning.view.*
import java.io.Serializable

/**
 * @author Alexey Korshun on 30.09.2020.
 */
class MeaningsListAdapter(
    private val clickListener: (MeaningItem) -> Unit
) : RecyclerView.Adapter<MeaningViewHolder>() {

    private var items: List<MeaningItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningViewHolder =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.i_meaning, parent, false)
            .let { view -> MeaningViewHolder(view, clickListener) }

    override fun onBindViewHolder(holder: MeaningViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    fun showItems(items: List<MeaningItem>) {
        DiffCallback(this.items, items)
            .let { callback -> DiffUtil.calculateDiff(callback) }
            .also { this.items = items }
            .run { dispatchUpdatesTo(this@MeaningsListAdapter) }
    }
}

class MeaningViewHolder(view: View, clickListener: (MeaningItem) -> Unit) : RecyclerView.ViewHolder(view) {

    private lateinit var item: MeaningItem

    init {
        itemView.setOnClickListener { clickListener(item) }
    }

    fun bind(item: MeaningItem) {
        this.item = item
        itemView.textMeaning.text = item.text
    }
}

data class MeaningItem(
    val text: String,
    val translations: List<TranslationItem>
) : Serializable {

    data class TranslationItem(
        val text: String,
        val fullImageUrl: String,
        val previewImageUrl: String
    ) : Serializable
}
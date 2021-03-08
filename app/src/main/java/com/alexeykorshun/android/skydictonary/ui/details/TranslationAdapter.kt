package com.alexeykorshun.android.skydictonary.ui.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexeykorshun.android.skydictonary.R
import com.alexeykorshun.skydictonary.data.MeaningItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.i_translation.view.*

/**
 * @author Alexey Korshun on 30.09.2020.
 */
class TranslationAdapter(private val items: List<MeaningItem.TranslationItem>) : RecyclerView.Adapter<TranslationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranslationViewHolder =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.i_translation, parent, false)
            .let { view -> TranslationViewHolder(view) }

    override fun onBindViewHolder(holder: TranslationViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}

class TranslationViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: MeaningItem.TranslationItem) {
        itemView.textTranslation.text = item.text
        Picasso.get()
            .load(item.previewImageUrl)
            .into(itemView.image)
    }
}
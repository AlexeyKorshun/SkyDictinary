package com.alexeykorshun.android.skydictonary.ui.list

import androidx.recyclerview.widget.DiffUtil

/**
 * @author Alexey Korshun on 30.09.2020.
 */
class DiffCallback(
    private val oldItems: List<MeaningItem>,
    private val newItems: List<MeaningItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItems[oldItemPosition].text == newItems[newItemPosition].text

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItems[oldItemPosition] == newItems[newItemPosition]
}
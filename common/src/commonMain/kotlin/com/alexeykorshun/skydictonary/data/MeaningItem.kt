package com.alexeykorshun.skydictonary.data

/**
 * @author Alexei Korshun on 08.03.2021.
 */
data class MeaningItem(
    val text: String,
    val translations: List<TranslationItem>
) {

    data class TranslationItem(
        val text: String,
        val fullImageUrl: String,
        val previewImageUrl: String
    )
}
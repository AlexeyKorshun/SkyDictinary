package com.alexeykorshun.skydictonary.network

import com.alexeykorshun.skydictonary.store.DictionaryStore
import org.json.JSONArray
import org.json.JSONObject

/**
 * @author Alexei Korshun on 08.03.2021.
 */
class AndroidDictionaryDeserializer : DictionaryDeserializer {

    override fun parse(raw: String): List<DictionaryStore.State.Meanings> = JSONArray(raw)
        .asSequence()
        .map {
            DictionaryStore.State.Meanings(
                it.getString("text"),
                it.translations()
            )
        }
        .toList()

    private fun JSONObject.translations(): List<DictionaryStore.State.Translation> =
        meanings()
            .asSequence()
            .map {
                DictionaryStore.State.Translation(
                    it.translation(),
                    it.images()
                )
            }
            .toList()

    private fun JSONObject.meanings() = getJSONArray("meanings")

    private fun JSONObject.translation() = getJSONObject("translation").getString("text")

    private fun JSONObject.images() = DictionaryStore.State.Images(
        "https:${getString("previewUrl")}",
        "https:${getString("imageUrl")}"
    )

    private fun JSONArray.asSequence(): Sequence<JSONObject> =
        (0 until length())
            .asSequence()
            .map { getJSONObject(it) }
}
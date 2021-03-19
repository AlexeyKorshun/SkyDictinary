package com.alexeykorshun.android.skydictonary.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.alexeykorshun.android.skydictonary.extension.argument
import com.alexeykorshun.skydictonary.ui.list.ListView.MeaningItem
import com.google.accompanist.coil.CoilImage

/**
 * @author Alexey Korshun on 30.09.2020.
 */
class MeaningDetailsFragment : Fragment() {

    companion object {

        fun newInstance(item: MeaningItem): Fragment =
            MeaningDetailsFragment()
                .apply { meaningItem = item.toString() }
    }

    private var meaningItem: String by argument()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext())
            .apply {
                setContent { Content(MeaningItem.fromString(meaningItem).translations) }
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = MeaningItem.fromString(meaningItem).text
    }
}

@Composable
private fun Content(items: List<MeaningItem.TranslationItem>) {

    LazyColumn {
        items(items.size) { index ->
            TranslationView((items[index]))
        }
    }
}

@Composable
private fun TranslationView(translation: MeaningItem.TranslationItem) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .height(72.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(Modifier.size(8.dp))
        CoilImage(
            data = translation.previewImageUrl,
            contentDescription = "Icon",
            modifier = Modifier.size(56.dp),
            fadeIn = true
        )
        Spacer(Modifier.size(16.dp))
        Text(
            text = translation.text,
        )
    }
}
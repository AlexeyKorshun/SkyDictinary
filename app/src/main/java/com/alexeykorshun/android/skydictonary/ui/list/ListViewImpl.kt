package com.alexeykorshun.android.skydictonary.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alexeykorshun.android.skydictonary.R
import com.alexeykorshun.skydictonary.ui.list.ListView
import com.alexeykorshun.skydictonary.ui.list.ListView.MeaningItem
import com.arkivanov.mvikotlin.core.view.BaseMviView

/**
 * @author Alexei Korshun on 29.09.2020.
 */
class ListViewImpl(
    private val view: ComposeView
) : BaseMviView<ListView.Model, ListView.Event>(), ListView {

    override fun render(model: ListView.Model) {
        view.setContent {
            Column {
                InputArea(!model.progress) { text -> dispatch(ListView.Event.Translate(text)) }
                when {
                    model.progress -> Progress()
                    model.error -> Error()
                    model.items.isEmpty() && !model.progress && !model.error -> Empty()
                }
                for (item in model.items) {
                    Meaning(item) { clickedItem -> dispatch(ListView.Event.Clicked(clickedItem)) }
                }
            }
        }
    }
}

@Composable
private fun InputArea(
    enabled: Boolean,
    onClick: (String) -> Unit
) {
    Row(
        Modifier.fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 24.dp)
    ) {
        val text = rememberSaveable { mutableStateOf("") }

        TextField(
            value = text.value,
            onValueChange = { text.value = it },
            modifier = Modifier.weight(1f),
            enabled = enabled
        )

        IconButton(
            onClick = { onClick(text.value) },
            enabled = enabled && text.value.isNotBlank(),
        ) {
            Icon(
                painterResource(R.drawable.ic_send), "",
                tint = if (enabled && text.value.isNotBlank()) Color.Blue else Color.Gray
            )
        }
    }
}

@Composable
private fun Progress() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun Error() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(stringResource(R.string.text_error))
    }
}

@Composable
private fun Empty() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(stringResource(R.string.text_empty))
    }
}

@Composable
private fun Meaning(item: MeaningItem, onClick: (MeaningItem) -> Unit) {
    Row(
        modifier = Modifier.height(56.dp)
            .fillMaxWidth()
            .clickable { onClick(item) }
            .padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = item.text,
            textAlign = TextAlign.Start,
        )
    }
}
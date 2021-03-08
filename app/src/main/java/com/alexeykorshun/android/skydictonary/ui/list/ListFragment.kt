package com.alexeykorshun.android.skydictonary.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.alexeykorshun.android.skydictonary.R
import com.alexeykorshun.android.skydictonary.ui.details.MeaningDetailsFragment
import com.alexeykorshun.skydictonary.data.MeaningItem
import com.alexeykorshun.skydictonary.network.DictionaryApiImpl
import com.alexeykorshun.skydictonary.ui.list.ListViewController
import com.arkivanov.mvikotlin.extensions.androidx.instancekeeper.getInstanceKeeperProvider
import com.arkivanov.mvikotlin.extensions.androidx.lifecycle.asMviLifecycle

/**
 * @author Alexei Korshun on 29.09.2020.
 */
class ListFragment : Fragment(R.layout.f_list) {

    private val controller: ListViewController by lazy {
        ListViewController(
            getInstanceKeeperProvider(),
            lifecycle.asMviLifecycle(),
            DictionaryApiImpl(),
            this::showDetails
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller.onViewCreated(ListViewImpl(view))
        requireActivity().title = getString(R.string.app_name)
    }

    private fun showDetails(item: MeaningItem) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, MeaningDetailsFragment.newInstance(item))
            .addToBackStack(MeaningDetailsFragment.javaClass.canonicalName)
            .commit()
    }
}
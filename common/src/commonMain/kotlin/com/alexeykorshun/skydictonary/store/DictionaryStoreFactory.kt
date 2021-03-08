package com.alexeykorshun.skydictonary.store

import com.alexeykorshun.skydictonary.network.DictionaryApi
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.reaktive.ReaktiveExecutor
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.doOnBeforeSubscribe
import com.badoo.reaktive.single.map
import com.badoo.reaktive.single.observeOn
import com.badoo.reaktive.single.singleFromFunction
import com.badoo.reaktive.single.subscribeOn

/**
 * @author Alexei Korshun on 29.09.2020.
 */
class DictionaryStoreFactory(
    private val storeFactory: StoreFactory,
    private val dictionaryApi: DictionaryApi
) {

    fun create(): DictionaryStore = object : DictionaryStore,
        Store<DictionaryStore.Intent, DictionaryStore.State, Nothing> by storeFactory.create(
            name = "dictionary_store",
            initialState = DictionaryStore.State(false, false, emptyList()),
            executorFactory = this::createExecutor,
            reducer = ReducerImpl
        ) {}

    private fun createExecutor() = ExecutorImpl()

    private sealed class Result {

        object Loading : Result()
        object Error : Result()
        class Data(val data: List<DictionaryStore.State.Meanings>) : Result()
    }

    private object ReducerImpl : Reducer<DictionaryStore.State, Result> {

        override fun DictionaryStore.State.reduce(result: Result): DictionaryStore.State {
            return when (result) {
                Result.Loading -> copy(error = false, progress = true)
                Result.Error -> copy(error = true, progress = false)
                is Result.Data -> copy(error = false, progress = false, meanings = result.data)
            }
        }
    }

    private inner class ExecutorImpl :
        ReaktiveExecutor<DictionaryStore.Intent, Nothing, DictionaryStore.State, Result, Nothing>() {

        override fun executeIntent(
            intent: DictionaryStore.Intent,
            getState: () -> DictionaryStore.State
        ) {
            when (intent) {
                is DictionaryStore.Intent.Find -> find(intent.word)
            }
        }

        private fun find(word: String) {
            singleFromFunction { dictionaryApi.find(word) }
                .subscribeOn(ioScheduler)
                .doOnBeforeSubscribe { dispatch(Result.Loading) }
                .map { Result.Data(it) }
                .observeOn(mainScheduler)
                .subscribeScoped(
                    onSuccess = ::dispatch,
                    onError = {
                        it.printStackTrace()
                        dispatch(Result.Error)
                    }
                )

        }
    }
}
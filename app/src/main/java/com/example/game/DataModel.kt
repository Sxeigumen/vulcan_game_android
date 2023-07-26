package com.example.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel : ViewModel() {
    val potentialElementsToAdd: MutableLiveData<List<Element>> by lazy {
        MutableLiveData<List<Element>>()
    }
    val elementToList: MutableLiveData<Element> by lazy {
        MutableLiveData<Element>()
    }
    val elementFromList: MutableLiveData<Element> by lazy {
        MutableLiveData<Element>()
    }
}
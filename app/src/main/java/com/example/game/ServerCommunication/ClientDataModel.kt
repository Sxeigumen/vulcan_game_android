package com.example.game.ServerCommunication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class ClientDataModel : ViewModel() {
    val client: MutableLiveData<Client> by lazy {
        MutableLiveData<Client>()
    }
}

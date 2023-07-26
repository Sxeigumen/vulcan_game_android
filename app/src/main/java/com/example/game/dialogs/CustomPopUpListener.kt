package com.example.game.dialogs

import androidx.fragment.app.DialogFragment

interface CustomPopUpListener {
    fun onSuccessfulReceive(dialog: DialogFragment)
    fun onFailedReceive(dialog: DialogFragment)
}
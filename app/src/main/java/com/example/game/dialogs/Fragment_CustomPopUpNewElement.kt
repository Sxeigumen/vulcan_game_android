package com.example.game.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.example.game.Element
import com.example.game.databinding.CustompopupNewElementBinding

class Fragment_CustomPopUpNewElement(private val element: Element) : DialogFragment() {
    private lateinit var binding: CustompopupNewElementBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CustompopupNewElementBinding.inflate(inflater)
        binding.newElementImage.setImageResource(element.ImageId)
        binding.newElementTitle.text = element.NameId
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.closepopup.setOnClickListener { dismiss() }
        binding.okButton.setOnClickListener { dismiss() }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }
}
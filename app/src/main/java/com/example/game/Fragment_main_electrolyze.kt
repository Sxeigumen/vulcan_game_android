package com.example.game

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.game.databinding.FragmentMainElectrolyzeBinding
import com.example.game.dialog.Fragment_CustomPopUpElectrolyze
import com.example.game.elementsCreation.ElectrolyzeResult
import com.example.game.elementsCreation.Elements

class Fragment_main_electrolyze : Fragment() {
    /** dataModel для взаимодействия с другими элементами UI */
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentMainElectrolyzeBinding
    private var freeBoxIndex_elec: Int = 0

    /** диалог для процесса получения элемента */
    lateinit var dialog: Dialog

    /** Массив выбранных элементов */
    private val elements = Elements()

    /** Массив imageId для корректного удаления выбранных элементов */
    private val imageIdList = arrayListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainElectrolyzeBinding.inflate(inflater)
        dialog = Dialog(requireContext())
        elements.empty()
        imageIdList.clear()

        dataModel.elementFromList.observe(viewLifecycleOwner, Observer {
            when (freeBoxIndex_elec) {
                0 -> {
                    freeBoxIndex_elec++
                }

                1 -> {
                    binding.iv1Elec.visibility = View.VISIBLE
                    binding.iv1Elec.setImageResource(it.ImageId)
                    elements.add(it)
                    Log.i("elements", it.ImageId.toString())
                    imageIdList.add(it.ImageId)
                    freeBoxIndex_elec++
                }

                else -> {
                    if (!GETNEWELEMENT)
                        MAIN.customToast(R.string.containersFilled)
                    GETNEWELEMENT = false
                }
            }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        freeBoxIndex_elec = 0
        Log.i("test", "testElec")
        binding.iv1Elec.setImageDrawable(null)
        binding.iv1Elec.setOnClickListener {
            if (binding.iv1Elec.drawable != null) {
                binding.iv1Elec.visibility = View.INVISIBLE
                freeBoxIndex_elec--
                Log.i("elements", imageIdList[0].toString())
                elements.remove(imageIdList[0])
                imageIdList.removeAt(0)
            }
        }
        binding.btnGetElec.setOnClickListener {
            val resElements = ElectrolyzeResult.get(elements)
            GETNEWELEMENT = true
            if (resElements != null) {
                showPopUp()
                dataModel.potentialElementsToAdd.value = resElements
                binding.iv1Elec.callOnClick()
            } else {
                MAIN.customToast(R.string.noResult)
            }
        }

    }

    fun showPopUp() {
        Fragment_CustomPopUpElectrolyze().show(
            requireActivity().supportFragmentManager,
            "CustomPopUpElectrolyze"
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() = Fragment_main_electrolyze()
    }
}
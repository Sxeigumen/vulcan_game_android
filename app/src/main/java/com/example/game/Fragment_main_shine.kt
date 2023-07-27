package com.example.game

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.game.databinding.FragmentMainShineBinding
import com.example.game.dialogs.Fragment_CustomPopUpShine
import com.example.game.elementsCreation.Elements
import com.example.game.elementsCreation.ShineResult

class Fragment_main_shine : Fragment() {
    /** dataModel для взаимодействия с другими элементами UI */
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentMainShineBinding
    private var freeBoxIndex_shine: Int = 0

    /** диалог для процесса получения элемента */
    lateinit var dialog: Dialog

    /** Массив выбранных элементов */
    private val elements = Elements()

    /** Массив imageId для корректного удаления выбранных элементов */
    private val imageIdList = arrayListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainShineBinding.inflate(inflater)
        dialog = Dialog(requireContext())
        elements.empty()
        imageIdList.clear()

        elements.empty()
        imageIdList.clear()
        dataModel.elementFromList.observe(viewLifecycleOwner) {
            if (FIRSTMIX) {
                freeBoxIndex_shine++
                FIRSTMIX = false
            }
            when (freeBoxIndex_shine) {
                0 -> {
                    freeBoxIndex_shine++
                }

                1 -> {
                    binding.iv1Shine.visibility = View.VISIBLE
                    binding.iv1Shine.setImageResource(it.ImageId)
                    elements.add(it)
                    Log.i("elements", it.ImageId.toString())
                    imageIdList.add(it.ImageId)
                    freeBoxIndex_shine++
                }

                else -> {
                    if (!GETNEWELEMENT)
                        MAIN.customToast(getString(R.string.containersFilled))
                    GETNEWELEMENT = false
                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        freeBoxIndex_shine = 0
        Log.i("test", "testShine")
        binding.iv1Shine.setImageDrawable(null)
        binding.iv1Shine.setOnClickListener {
            if (binding.iv1Shine.drawable != null) {
                binding.iv1Shine.visibility = View.INVISIBLE
                freeBoxIndex_shine--
                Log.i("elements", imageIdList[0].toString())
                elements.remove(imageIdList[0])
                imageIdList.removeAt(0)
            }
        }
        binding.btnGetShine.setOnClickListener {
            val resElements = ShineResult.get(elements)
            GETNEWELEMENT = true
            if (resElements != null) {
                showPopUp()
                dataModel.potentialElementsToAdd.value = resElements
                binding.iv1Shine.callOnClick()
            } else {
                MAIN.customToast(getString(R.string.noResult))
            }
        }

    }

    fun showPopUp() {
        Fragment_CustomPopUpShine().show(
            requireActivity().supportFragmentManager,
            "CustomPopUpShine"
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() = Fragment_main_shine()
    }
}
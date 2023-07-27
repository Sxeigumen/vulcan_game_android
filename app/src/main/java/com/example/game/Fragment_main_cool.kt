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
import com.example.game.databinding.FragmentMainCoolBinding
import com.example.game.dialogs.Fragment_CustomPopUpCool
import com.example.game.elementsCreation.CoolResult
import com.example.game.elementsCreation.Elements

class Fragment_main_cool : Fragment() {
    /** dataModel для взаимодействия с другими элементами UI */
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentMainCoolBinding
    private var freeBoxIndex_cool: Int = 0

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
        binding = FragmentMainCoolBinding.inflate(inflater)
        dialog = Dialog(requireContext())
        elements.empty()
        imageIdList.clear()

        dataModel.elementFromList.observe(viewLifecycleOwner, Observer {
            if(FIRSTMIX){
                freeBoxIndex_cool++
                FIRSTMIX = false
            }
            when (freeBoxIndex_cool) {
                0 -> {
                    freeBoxIndex_cool++
                }

                1 -> {
                    binding.iv1Cool.visibility = View.VISIBLE
                    binding.iv1Cool.setImageResource(it.ImageId)
                    elements.add(it)
                    Log.i("elements", it.ImageId.toString())
                    imageIdList.add(it.ImageId)
                    freeBoxIndex_cool++
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
        freeBoxIndex_cool = 0
        Log.i("test", "testCool")
        binding.iv1Cool.setImageDrawable(null)
        binding.iv1Cool.setOnClickListener {
            if (binding.iv1Cool.drawable != null) {
                binding.iv1Cool.visibility = View.INVISIBLE
                freeBoxIndex_cool--
                Log.i("elements", imageIdList[0].toString())
                elements.remove(imageIdList[0])
                imageIdList.removeAt(0)
            }
        }
        binding.btnGetCool.setOnClickListener {
            val resElements = CoolResult.get(elements)
            GETNEWELEMENT = true
            if (resElements != null) {
                showPopUp()
                dataModel.potentialElementsToAdd.value = resElements
                binding.iv1Cool.callOnClick()
            } else {
                MAIN.customToast(R.string.noResult)
            }
        }
    }

    fun showPopUp() {
        Fragment_CustomPopUpCool().show(requireActivity().supportFragmentManager, "CustomPopUpCool")
    }

    companion object {
        @JvmStatic
        fun newInstance() = Fragment_main_cool()
    }
}

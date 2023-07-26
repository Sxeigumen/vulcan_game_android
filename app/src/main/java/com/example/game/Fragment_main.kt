package com.example.game

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.game.databinding.FragmentMainBinding
import com.example.game.dialogs.Fragment_CustomPopUpMix
import com.example.game.elementsCreation.Elements
import com.example.game.elementsCreation.MixResults


class Fragment_main : Fragment() {
    /** dataModel для взаимодействия с другими элементами UI */
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentMainBinding
    private var freeBoxIndex_main: Int = 0

    /** диалог для процесса получения элемента */
    private lateinit var dialog: Dialog

    /** Массив выбранных элементов */
    private val elements = Elements()

    /** Массив imageId для корректного удаления выбранных элементов */
    private val imageIdList = arrayListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        dialog = Dialog(requireContext())
        elements.empty()
        imageIdList.clear()

        /** слушатель получения элемента из списка */
        dataModel.elementFromList.observe(viewLifecycleOwner) {
            Log.i("fisrt", "test")
            when (freeBoxIndex_main) {
                0 -> {
                    freeBoxIndex_main++
                }

                1, 4, 6, 9 -> {
                    Log.i("test", "add1")
                    binding.iv1Main.visibility = View.VISIBLE
                    binding.iv1Main.setImageResource(it.ImageId)
                    binding.iv1Main.tag = it.ImageId
                    elements.add(it)
                    Log.i("elements", it.ImageId.toString())
                    imageIdList.add(it.ImageId)
                    freeBoxIndex_main++
                }

                2, 7 -> {
                    binding.iv2Main.visibility = View.VISIBLE
                    binding.iv2Main.setImageResource(it.ImageId)
                    binding.iv2Main.tag = it.ImageId
                    elements.add(it)
                    Log.i("elements", it.ImageId.toString())
                    imageIdList.add(it.ImageId)
                    freeBoxIndex_main += 3
                }

                5 -> {
                    binding.iv3Main.visibility = View.VISIBLE
                    binding.iv3Main.setImageResource(it.ImageId)
                    binding.iv3Main.tag = it.ImageId
                    elements.add(it)
                    Log.i("elements", it.ImageId.toString())
                    imageIdList.add(it.ImageId)
                    freeBoxIndex_main += 5
                }

                else -> {
                    if (!GETNEWELEMENT)
                        MAIN.customToast(R.string.containersFilled)
                    GETNEWELEMENT = false
                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i("test", "testMain")
        freeBoxIndex_main = 0
        binding.iv1Main.setImageDrawable(null)
        binding.iv2Main.setImageDrawable(null)
        binding.iv3Main.setImageDrawable(null)

        binding.iv1Main.setOnClickListener {
            if (binding.iv1Main.drawable != null) {
                binding.iv1Main.visibility = View.INVISIBLE
                val indexElement: Int = binding.iv1Main.tag as Int
                freeBoxIndex_main--
                Log.i("elements", indexElement.toString())
                elements.remove(indexElement)
            }
        }

        binding.iv2Main.setOnClickListener {
            if (binding.iv2Main.drawable != null) {
                binding.iv2Main.visibility = View.INVISIBLE
                val indexElement: Int = binding.iv2Main.tag as Int
                freeBoxIndex_main -= 3
                Log.i("elements", indexElement.toString())
                elements.remove(indexElement)
            }
        }

        binding.iv3Main.setOnClickListener {
            if (binding.iv3Main.drawable != null) {
                binding.iv3Main.visibility = View.INVISIBLE
                val indexElement: Int = binding.iv3Main.tag as Int
                freeBoxIndex_main -= 5
                Log.i("elements", indexElement.toString())
                elements.remove(indexElement)
            }
        }

        binding.btnGetMain.setOnClickListener {
            val resElements = MixResults.get(elements)
            GETNEWELEMENT = true
            if (resElements != null) {
                showPopUp()
                dataModel.potentialElementsToAdd.value = resElements
                binding.iv3Main.callOnClick()
                binding.iv2Main.callOnClick()
                binding.iv1Main.callOnClick()
            } else {
                MAIN.customToast(R.string.noResult)
            }
        }
    }

    private fun showPopUp() {
        Fragment_CustomPopUpMix().show(requireActivity().supportFragmentManager, "CustomPopUpMix")
    }

    companion object {
        @JvmStatic
        fun newInstance() = Fragment_main()
    }
}
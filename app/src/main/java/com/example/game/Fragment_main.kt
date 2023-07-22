package com.example.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.game.databinding.FragmentMainBinding
import com.example.game.elementsCreation.Elements
import com.example.game.elementsCreation.MixResults


class Fragment_main : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentMainBinding
    private var freeBoxIndex: Int = 1

    /** Массив выбранных элементов */
    private val elements = Elements()

    /** Массив imageId для корректного удаления выбранных элементов */
    private val imageIdList = arrayListOf<Int>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i("test", "testMain")
        binding.iv1Main.setImageDrawable(null)
        binding.iv2Main.setImageDrawable(null)
        binding.iv3Main.setImageDrawable(null)
        binding.iv1Main.setOnClickListener {
            if (binding.iv1Main.drawable != null) {
                binding.iv1Main.visibility = View.INVISIBLE
                freeBoxIndex--

                elements.remove(imageIdList[0])
                imageIdList.removeAt(0)
            }

        }
        binding.iv2Main.setOnClickListener {
            if (binding.iv2Main.drawable != null) {
                binding.iv2Main.visibility = View.INVISIBLE
                freeBoxIndex -= 3

                elements.remove(imageIdList[1])
                imageIdList.removeAt(1)
            }
        }
        binding.iv3Main.setOnClickListener {
            if (binding.iv3Main.drawable != null) {
                binding.iv3Main.visibility = View.INVISIBLE
                freeBoxIndex -= 5

                elements.remove(imageIdList[2])
                imageIdList.removeAt(2)
            }
        }

        dataModel.message.observe(viewLifecycleOwner, Observer {
            Log.i("fisrt", "test")
            elements.add(it)
            imageIdList.add(it.ImageId)
            when (freeBoxIndex) {
                1, 4, 6, 9 -> {
                    binding.iv1Main.visibility = View.VISIBLE
                    binding.iv1Main.setImageResource(it.ImageId)
                    freeBoxIndex++
                }

                2, 7 -> {
                    binding.iv2Main.visibility = View.VISIBLE
                    binding.iv2Main.setImageResource(it.ImageId)
                    freeBoxIndex += 3
                }

                5 -> {
                    binding.iv3Main.visibility = View.VISIBLE
                    binding.iv3Main.setImageResource(it.ImageId)
                    freeBoxIndex += 5
                }

                else -> Toast.makeText(context, R.string.containersFilled, Toast.LENGTH_LONG)
                    .show()
            }
        })

        binding.btnGetMain.setOnClickListener {
            val resElement = MixResults.get(elements)
            if (resElement != null) {
                dataModel.message.value = resElement
            } else {
                Toast.makeText(context, R.string.noResult, Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = Fragment_main()
    }
}
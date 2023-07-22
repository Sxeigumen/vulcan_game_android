package com.example.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.game.databinding.FragmentMainBinding
import com.example.game.elementsCreation.Elements
import com.example.game.elementsCreation.MixResults


class Fragment_main : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentMainBinding
    private var freeBoxIndex_main: Int = 0

    /** Массив выбранных элементов */
    private val elements = Elements()

    /** Массив imageId для корректного удаления выбранных элементов */
    private val imageIdList = arrayListOf<Int>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        dataModel.message.observe(viewLifecycleOwner, Observer {
            Log.i("fisrt", "test")
            when (freeBoxIndex_main) {
                0 -> {
                    freeBoxIndex_main++
                }
                1, 4, 6, 9 -> {
                    Log.i("test", "add1")
                    binding.iv1Main.visibility = View.VISIBLE
                    binding.iv1Main.setImageResource(it.ImageId)
                    elements.add(it)
                    Log.i("elements", it.ImageId.toString())
                    imageIdList.add(it.ImageId)
                    freeBoxIndex_main++
                }

                2, 7 -> {
                    binding.iv2Main.visibility = View.VISIBLE
                    binding.iv2Main.setImageResource(it.ImageId)
                    elements.add(it)
                    Log.i("elements", it.ImageId.toString())
                    imageIdList.add(it.ImageId)
                    freeBoxIndex_main += 3
                }

                5 -> {
                    binding.iv3Main.visibility = View.VISIBLE
                    binding.iv3Main.setImageResource(it.ImageId)
                    elements.add(it)
                    Log.i("elements", it.ImageId.toString())
                    imageIdList.add(it.ImageId)
                    freeBoxIndex_main += 5
                }

                else -> Toast.makeText(context, R.string.containersFilled, Toast.LENGTH_LONG).show()
            }
        })
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
                freeBoxIndex_main--
                Log.i("elements", imageIdList[0].toString())
                elements.remove(imageIdList[0])
                imageIdList.removeAt(0)
            }

        }

        binding.iv2Main.setOnClickListener {
            if (binding.iv2Main.drawable != null) {
                binding.iv2Main.visibility = View.INVISIBLE
                freeBoxIndex_main -= 3
                Log.i("elements", imageIdList[1].toString())
                elements.remove(imageIdList[1])
                imageIdList.removeAt(1)
            }
        }

        binding.iv3Main.setOnClickListener {
            if (binding.iv3Main.drawable != null) {
                binding.iv3Main.visibility = View.INVISIBLE
                freeBoxIndex_main -= 5
                Log.i("elements", imageIdList[2].toString())
                elements.remove(imageIdList[2])
                imageIdList.removeAt(2)
            }
        }

        binding.btnGetMain.setOnClickListener {
            val resElement = MixResults.get(elements)
//            for (el in elements) {
//                Toast.makeText(context, el.toString(), Toast.LENGTH_LONG).show()
//            }
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
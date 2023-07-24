package com.example.game

import android.accounts.NetworkErrorException
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.game.ServerCommunication.Client
import com.example.game.ServerCommunication.ClientDataModel
import com.example.game.databinding.FragmentMainBinding
import com.example.game.elementsCreation.Elements
import com.example.game.elementsCreation.MixResults
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Fragment_main : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentMainBinding
    private var freeBoxIndex_main: Int = 0

    /** Массив выбранных элементов */
    private val elements = Elements()

    /** Массив imageId для корректного удаления выбранных элементов */
    private val imageIdList = arrayListOf<Int>()

    /** Принятие клиента от activity */
    private val clientDataModel: ClientDataModel by activityViewModels()

    /** Клиент для связи с сервером */
    private lateinit var client: Client

    /** Слушатель сообщений сервера */
    private lateinit var messageListener: Disposable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)

        /** Инициализация клиента из activity*/
        clientDataModel.client.observe(viewLifecycleOwner) {
            client = it
            messageListener = it.messageEmitter.subscribe(
                /** onNext */
                { stringFromServer -> binding.tempTextView.text = stringFromServer },
                /** onError */
                {
                    if (it is NetworkErrorException) {
                        Toast.makeText(context, "Couldn't connect to server", Toast.LENGTH_LONG)
                            .show()
                    }
                },
            )
        }

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
            val resElements = MixResults.get(elements)
            if (resElements != null) {
                for (resElement in resElements) {
                    dataModel.message.value = resElement
                    Toast.makeText(
                        context,
                        "Получен элемент ${resElement.NameId}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                binding.iv3Main.callOnClick()
                binding.iv2Main.callOnClick()
                binding.iv1Main.callOnClick()
            } else {
                Toast.makeText(context, R.string.noResult, Toast.LENGTH_LONG).show()
            }

            /** Отправка сообщения серверу */
            if (client.connected) {
                CoroutineScope(Dispatchers.IO).launch { client.send("Get button pressed") }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = Fragment_main()
    }
}
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
import com.example.game.databinding.FragmentMainCoolBinding
import com.example.game.elementsCreation.CoolResult
import com.example.game.elementsCreation.Elements
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Fragment_main_cool : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentMainCoolBinding
    private var freeBoxIndex_cool: Int = 0

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
    ): View? {
        binding = FragmentMainCoolBinding.inflate(inflater)

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

        elements.empty()
        imageIdList.clear()
        dataModel.message.observe(viewLifecycleOwner, Observer {
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

                else -> Toast.makeText(context, R.string.containersFilled, Toast.LENGTH_LONG)
                    .show()
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
//            for (el in elements) {
//                Toast.makeText(context, el.toString(), Toast.LENGTH_LONG).show()
//            }
            if (resElements != null) {
                for (resElement in resElements) {
                    dataModel.message.value = resElement
                    Toast.makeText(
                        context,
                        "Получен элемент ${resElement.NameId}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                binding.iv1Cool.callOnClick()
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
        fun newInstance() = Fragment_main_cool()
    }
}
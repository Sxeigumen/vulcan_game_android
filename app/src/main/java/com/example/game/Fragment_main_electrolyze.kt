package com.example.game

import android.accounts.NetworkErrorException
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.game.ServerCommunication.Client
import com.example.game.ServerCommunication.ClientDataModel
import com.example.game.databinding.FragmentMainElectrolyzeBinding
import com.example.game.elementsCreation.ElectrolyzeResult
import com.example.game.elementsCreation.Elements
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Fragment_main_electrolyze : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentMainElectrolyzeBinding
    private var freeBoxIndex_elec: Int = 0
    lateinit var myDialog: Dialog
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
        binding = FragmentMainElectrolyzeBinding.inflate(inflater)
        myDialog = Dialog(requireContext())
        elements.empty()
        imageIdList.clear()
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
//            for (el in elements) {
//                Toast.makeText(context, el.toString(), Toast.LENGTH_LONG).show()
//            }
            if (resElements != null) {
                var stringResult: String = "Вы получите: "
                for (resElement in resElements){
                    stringResult+=resElement.NameId.toString()
                }
                showPopUp()
                for (resElement in resElements) {
                    dataModel.message.value = resElement
                    Toast.makeText(
                        context,
                        "Получен элемент ${resElement.NameId}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                binding.iv1Elec.callOnClick()
            } else {
                MAIN.customToast(R.string.noResult)
            }
            /** Отправка сообщения серверу */
            if (client.connected) {
                CoroutineScope(Dispatchers.IO).launch { client.send("Get button pressed") }
            }
        }

    }

    fun showPopUp(){
        myDialog.setContentView(R.layout.custompopup_elec)
        val textClose: TextView = myDialog.findViewById(R.id.closepopup_elec)
        textClose.setOnClickListener{
            myDialog.dismiss()
        }
        myDialog.show()
    }
    companion object {
        @JvmStatic
        fun newInstance() = Fragment_main_electrolyze()
    }
}
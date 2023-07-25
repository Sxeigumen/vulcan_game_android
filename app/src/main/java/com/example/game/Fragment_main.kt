package com.example.game

import android.accounts.NetworkErrorException
import android.app.Dialog
import android.graphics.drawable.Drawable
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
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        myDialog = Dialog(requireContext())
        elements.empty()
        imageIdList.clear()
        /** Инициализация клиента из activity*/
        clientDataModel.client.observe(viewLifecycleOwner) {
            client = it
            if (client.connected) {
                messageListener = it.messageEmitter.subscribe {
                    /** onNext */
                        stringFromServer ->
                    binding.tempTextView.text = stringFromServer
                }
            }
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
                    binding.iv1Main.setTag(it.ImageId)
                    elements.add(it)
                    Log.i("elements", it.ImageId.toString())
                    imageIdList.add(it.ImageId)
                    freeBoxIndex_main++
                }

                2, 7 -> {
                    binding.iv2Main.visibility = View.VISIBLE
                    binding.iv2Main.setImageResource(it.ImageId)
                    binding.iv2Main.setTag(it.ImageId)
                    elements.add(it)
                    Log.i("elements", it.ImageId.toString())
                    imageIdList.add(it.ImageId)
                    freeBoxIndex_main += 3
                }

                5 -> {
                    binding.iv3Main.visibility = View.VISIBLE
                    binding.iv3Main.setImageResource(it.ImageId)
                    binding.iv3Main.setTag(it.ImageId)
                    elements.add(it)
                    Log.i("elements", it.ImageId.toString())
                    imageIdList.add(it.ImageId)
                    freeBoxIndex_main += 5
                }

                else -> {
                    if(!GETNEWELEMENT)
                        MAIN.customToast(R.string.containersFilled)
                    GETNEWELEMENT = false
                }
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
                val indexElement: Int = binding.iv1Main.getTag() as Int
                freeBoxIndex_main--
                Log.i("elements", indexElement.toString())
                elements.remove(indexElement)
            }
        }

        binding.iv2Main.setOnClickListener {
            if (binding.iv2Main.drawable != null) {
                binding.iv2Main.visibility = View.INVISIBLE
                val indexElement: Int = binding.iv2Main.getTag() as Int
                freeBoxIndex_main -= 3
                Log.i("elements", indexElement.toString())
                elements.remove(indexElement)
            }
        }

        binding.iv3Main.setOnClickListener {
            if (binding.iv3Main.drawable != null) {
                binding.iv3Main.visibility = View.INVISIBLE
                val indexElement: Int = binding.iv3Main.getTag() as Int
                freeBoxIndex_main -= 5
                Log.i("elements", indexElement.toString())
                elements.remove(indexElement)
            }
        }

        binding.btnGetMain.setOnClickListener {
            val resElements = MixResults.get(elements)
            GETNEWELEMENT = true
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
                binding.iv3Main.callOnClick()
                binding.iv2Main.callOnClick()
                binding.iv1Main.callOnClick()
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
        myDialog.setContentView(R.layout.custompopup)
        val textClose: TextView = myDialog.findViewById(R.id.closepopup)
        textClose.setOnClickListener{
            myDialog.dismiss()
        }
        myDialog.show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = Fragment_main()
    }
}
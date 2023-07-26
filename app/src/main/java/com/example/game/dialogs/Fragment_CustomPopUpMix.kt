package com.example.game.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.example.game.R
import com.example.game.ServerCommunication.Client
import com.example.game.databinding.CustompopupMixBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable

class Fragment_CustomPopUpMix : DialogFragment() {
    private lateinit var client: Client
    private lateinit var binding: CustompopupMixBinding

    /** this для таймеров */
    private lateinit var this_: DialogFragment

    /** Activity, отвечающее за обработку успеха или не успеха */
    private lateinit var listenerForResult: CustomPopUpListener

    /** таймер, закрывающий фрагмент при отсутсвии соединения с сервером */
    private val noConnectionTimer = object : CountDownTimer(5000, 5000) {
        override fun onTick(millisUntilFinished: Long) {}

        override fun onFinish() {
            listenerForResult.onSuccessfulReceive(this_)
            dismiss()
        }
    }

    /** слушатель сообщений от сервера */
    private lateinit var messageListener: Disposable

    /** проверка реализации вызвавшим контекстом интерфейса CustomPopUpListener */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listenerForResult = context as CustomPopUpListener
        } catch (e: ClassCastException) {
            throw ClassCastException(("$context must implement NoticeDialogListener"))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /** инициализация и запуск клиента */
        client = Client()
        client.run()

        this_ = this
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CustompopupMixBinding.inflate(inflater)

        /** проверка установления соединения с сервером */
        object : CountDownTimer(500, 500) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                if (client.connected) {
                    /** установка текста ожидания ответа от сервера*/
                    binding.actionTitle.text = getString(R.string.mixWithServer)
                    /** слушатель сообщений от сервера */
                    messageListener = client.messageEmitter
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            /** onNext */
                            {
                                when {
                                    (it.endsWith(Client.HttpAnswers.unsuccess)) -> {
                                        listenerForResult.onFailedReceive(this_)
                                        dismiss()
                                    }

                                    (it.endsWith(Client.HttpAnswers.success)) -> {
                                        listenerForResult.onSuccessfulReceive(this_)
                                        dismiss()
                                    }
                                }
                            },
                            /** onError */
                            {
                                listenerForResult.onFailedReceive(this_)
                                dismiss()
                            },
                        )

                    /** отправка запроса на сервер */
                    client.send(Client.HttpRequests.mix)
                } else {
                    /** сервер не подключен */
                    /** установка текста ожидания таймера закрытия */
                    binding.actionTitle.text = getString(R.string.mixNoServer)
                    /** запуск таймера закрытия */
                    noConnectionTimer.start()
                }
            }
        }.start()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.closepopup.setOnClickListener {
            listenerForResult.onFailedReceive(this)
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onStop() {
        super.onStop()
        if (client.connected) {
            client.close()
        }
    }
}
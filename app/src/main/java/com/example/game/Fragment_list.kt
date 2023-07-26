package com.example.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.game.databinding.FragmentListBinding


class Fragment_list : Fragment(), ElementAdapter.Listener {
    private lateinit var binding: FragmentListBinding

    /** dataModel для обмена данными с другими элементами UI */
    private val dataModel: DataModel by activityViewModels()

    /** elementAdapter для контроля списка */
    private lateinit var elementAdapter: ElementAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater)

        /** слушатель для добавления нового элемента в список */
        dataModel.elementToList.observe(viewLifecycleOwner) {
            if (it.ImageId == R.drawable.tok) {
                ELECTROLYZE = true
            }
            elementAdapter.add(it)
            Toast.makeText(context, "Получен элемент ${it.NameId}", Toast.LENGTH_LONG).show()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        elementAdapter = ElementAdapter(this)
        generate(elementAdapter)
        val recyclerView: RecyclerView = view.findViewById(R.id.ListsItem)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = elementAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = Fragment_list()
    }

    /** генерация начальных элементов списка */
    private fun generate(adapter: ElementAdapter) {
        val elements = listOf(
            Element(R.drawable.vodorod, "Водород"),
            Element(R.drawable.kislorod, "Кислород"),
            Element(R.drawable.marganec, "Марганец"),
            Element(R.drawable.ftor, "Фтор"),
            Element(R.drawable.sol, "Соль"),
            Element(R.drawable.kremniy, "Кремний"),
            Element(R.drawable.sera, "Сера")
        )
        for (element in elements) {
            adapter.add(element)
        }
    }

    override fun onClick(element: Element) {
        Log.i("fisrt", "test1")
        /** отправление элемента в другие элементы UI */
        dataModel.elementFromList.value = element
    }
}
package com.example.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.game.databinding.ActivityLevelHardBinding
import com.example.game.databinding.FragmentListBinding


class Fragment_list : Fragment(), ElementAdapter.Listener  {
    private lateinit var binding : FragmentListBinding
    private val dataModel: DataModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val elementAdapter = ElementAdapter(this)
        generate(elementAdapter)
        val recyclerView: RecyclerView = view.findViewById(R.id.ListsItem)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = elementAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = Fragment_list()
    }

    private fun generate(adapter: ElementAdapter){
        var counter = 1
        for(counter in 1..30){
            adapter.addElement(Element(R.drawable.ftor, "element $counter"))
        }
    }

    override fun onClick(element: Element) {
        Log.i("fisrt", "test1")
        dataModel.message.value = element.ImageId
    }
}
package com.example.game

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.game.databinding.ElementItemBinding

class ElementAdapter(private val listener: Listener) :
    RecyclerView.Adapter<ElementAdapter.ElementHolder>() {
    private val elementList = ArrayList<Element>()

    class ElementHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ElementItemBinding.bind(item)
        fun bind(element: Element, listener: Listener) = with(binding) {
            binding.ivItem.setImageResource(element.imageId)
            binding.label.text = element.title
            itemView.setOnClickListener {
                Log.i("fisrt", "test2")
                listener.onClick(element)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.element_item, parent, false)
        return ElementHolder(view)
    }

    override fun onBindViewHolder(holder: ElementHolder, position: Int) {
        holder.bind(elementList[position], listener)
    }

    override fun getItemCount(): Int {
        return elementList.size
    }

    /**
    Добавляет элемент в RecyclerView.
    Если добавляемый элемент НЕ содержался в RecyclerView и был добавлен, возвращает true.
    Если добавляемый элемент содержался в RecyclerView и НЕ был добавлен, возвращает false.
     */
    fun addElement(element: Element): Boolean {
        if (!elementList.contains(element)) {
            var indexToInsert =
                elementList.indexOfLast { elInList -> elInList.title < element.title }
            elementList.add(++indexToInsert, element)
            notifyItemInserted(indexToInsert)
            return true
        }
        return false
    }

    interface Listener {
        fun onClick(element: Element)
    }
}
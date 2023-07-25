package com.example.game

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.game.databinding.ElementItemBinding

class ElementAdapter(private val listener: Listener) :
    RecyclerView.Adapter<ElementAdapter.ItemHolder>() {
    private val itemList = ArrayList<Element>()

    class ItemHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ElementItemBinding.bind(item)
        fun bind(item: Element, listener: Listener) = with(binding) {
            binding.imageItem.setImageResource(item.ImageId)
            binding.nameItem.text = item.NameId
            itemView.setOnClickListener {
                Log.i("fisrt", "test2")
                listener.onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.element_item, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(itemList[position], listener)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    /**
    Добавляет элемент в RecyclerView.
    Если добавляемый элемент НЕ содержался в RecyclerView и был добавлен, возвращает true.
    Если добавляемый элемент содержался в RecyclerView и НЕ был добавлен, возвращает false.
     */
    fun add(item: Element): Boolean {
        if (!itemList.contains(item)) {
            var indexToInsert =
                itemList.indexOfLast { elInList -> elInList.NameId < item.NameId }
            itemList.add(++indexToInsert, item)
            notifyItemInserted(indexToInsert)
            return true
        }
        return false
    }

    interface Listener {
        fun onClick(element: Element)
    }
}
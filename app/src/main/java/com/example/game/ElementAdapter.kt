package com.example.game

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.game.databinding.ElementItemBinding

class ElementAdapter(val listener: Listener): RecyclerView.Adapter<ElementAdapter.ItemHolder>() {
    val itemList = ArrayList<Element>()
    class ItemHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = ElementItemBinding.bind(item)
        fun bind(item: Element, listener: Listener) = with(binding){
            binding.imageItem.setImageResource(item.ImageId)
            binding.nameItem.text = item.NameId
            itemView.setOnClickListener{
                Log.i("fisrt", "test2")
                listener.onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.element_item, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(itemList[position], listener)
    }

    fun addElement(item : Element){
        itemList.add(item)
        notifyDataSetChanged()
    }

    interface Listener{
        fun onClick(element: Element)
    }
}

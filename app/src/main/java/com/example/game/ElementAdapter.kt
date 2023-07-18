package com.example.game

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.game.databinding.ElementItemBinding

class ElementAdapter: RecyclerView.Adapter<ElementAdapter.ElementHolder>() {
    val elementList = ArrayList<Element>()
    class ElementHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = ElementItemBinding.bind(item)
        fun bind(element : Element) = with(binding) {
            binding.ivItem.setImageResource(element.imageId)
            binding.label.text = element.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.element_item, parent, false)
        return ElementHolder(view)
    }

    override fun onBindViewHolder(holder: ElementHolder, position: Int) {
        holder.bind(elementList[position])
    }

    override fun getItemCount(): Int {
        return elementList.size
    }
    fun addElement(element: Element) {
        elementList.add(element)
        notifyDataSetChanged()
    }
}
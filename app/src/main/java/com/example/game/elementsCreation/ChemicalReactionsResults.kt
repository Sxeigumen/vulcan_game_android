package com.example.game.elementsCreation

import com.example.game.Element

interface ChemicalReactionsResults {
    val results: Map<Elements, List<Element>>

    fun get(elements: Elements): List<Element>? {
        return results[elements]
    }
}
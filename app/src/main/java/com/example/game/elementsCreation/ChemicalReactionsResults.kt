package com.example.game.elementsCreation

import com.example.game.Element

interface ChemicalReactionsResults {
    val results: Map<Elements, Element>

    fun get(elements: Elements): Element? {
        return results[elements]
    }
}
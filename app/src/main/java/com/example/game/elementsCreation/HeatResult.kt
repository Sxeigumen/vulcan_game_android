package com.example.game.elementsCreation

import com.example.game.Element
import com.example.game.R

object HeatResult: ChemicalReactionsResults {
    override val results: Map<Elements, Element> = mapOf(
        /** Пар */
        Elements(
            Pair(Element(R.drawable.water, "Вода"), 1u)
        ) to Element(R.drawable.par, "Пар")
    )
}
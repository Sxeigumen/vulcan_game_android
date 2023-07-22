package com.example.game.elementsCreation

import com.example.game.Element
import com.example.game.R

object CoolResult: ChemicalReactionsResults {
    override val results: Map<Elements, List<Element>> = mapOf(
        /** Лед */
        Elements(
            Pair(Element(R.drawable.water, "Вода"), 1u)
        ) to listOf(
            Element(R.drawable.led, "Лед")
        )
    )
}
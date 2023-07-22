package com.example.game.elementsCreation

import com.example.game.Element
import com.example.game.R

object CoolResult: ChemicalReactionsResults {
    override val results: Map<Elements, Element> = mapOf(
        /** Лед */
        Elements(
            Pair(Element(R.drawable.water, "Вода"), 1u)
        ) to Element(R.drawable.led, "Лед")
    )
}
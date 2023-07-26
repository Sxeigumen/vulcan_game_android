package com.example.game.elementsCreation

import com.example.game.Element
import com.example.game.R

object HeatResult : ChemicalReactionsResults {
    override val results: Map<Elements, List<Element>> = mapOf(
        /** Пар */
        Elements(
            Pair(Element(R.drawable.voda, "Вода"), 1u)
        ) to listOf(
            Element(R.drawable.par, "Пар")
        ),
        /** Сероводород */
        Elements(
            Pair(Element(R.drawable.vodorod, "Водород"), 2u),
            Pair(Element(R.drawable.sera, "Сера"), 1u)
        ) to listOf(
            Element(R.drawable.par, "Сероводород")
        ),
    )
}
package com.example.game.elementsCreation

import com.example.game.Element
import com.example.game.R

object ElectrolyzeResult: ChemicalReactionsResults {
    override val results: Map<Elements, Element> = mapOf(
        /** Получение натрия + хлор */
        Elements(
            Pair(Element(R.drawable.sol, "Соль"), 1u)
        ) to Element(R.drawable.natriy, "Натрий"),
        Elements(
            Pair(Element(R.drawable.sol, "Соль"), 1u)
        ) to Element(R.drawable.chlor, "Хлор")
    )
}
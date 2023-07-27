package com.example.game.elementsCreation

import com.example.game.Element
import com.example.game.R

object ElectrolyzeResult : ChemicalReactionsResults {
    override val results: Map<Elements, List<Element>> = mapOf(
        /** Получение натрия + хлор */
        Elements(
            Pair(Element(R.drawable.sol, "Соль"), 1u)
        ) to listOf(
            Element(R.drawable.natriy, "Натрий"),
            Element(R.drawable.hlor, "Хлор")
        ),
        /** Получение калия */
        Elements(
            Pair(Element(R.drawable.gidroksid_kaliya, "Гидроксид калия"), 1u)
        ) to listOf(
            Element(R.drawable.kaliy, "Калий")
        )
    )
}
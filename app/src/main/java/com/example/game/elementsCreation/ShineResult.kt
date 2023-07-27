package com.example.game.elementsCreation

import com.example.game.Element
import com.example.game.R

object ShineResult : ChemicalReactionsResults {
    override val results: Map<Elements, List<Element>> = mapOf(
        /** Ток */
        Elements(
            Pair(Element(R.drawable.kremniy, "Кремний"), 1u)
        ) to listOf(
            Element(R.drawable.tok, "Ток")
        ),

        /** led-куб */
        Elements(
            Pair(Element(R.drawable.diod_normal, "Диод"), 1u)
        ) to listOf(
            Element(R.drawable.led_kyb, "Ток")
        )
    )
}
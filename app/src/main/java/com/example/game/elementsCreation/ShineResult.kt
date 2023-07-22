package com.example.game.elementsCreation

import com.example.game.Element
import com.example.game.R

object ShineResult: ChemicalReactionsResults {
    override val results: Map<Elements, Element> = mapOf(
        /** Ток */
        Elements(
            Pair(Element(R.drawable.kremniy, "Кремний"), 1u)
        ) to Element(R.drawable.tok, "Ток")
    )

}
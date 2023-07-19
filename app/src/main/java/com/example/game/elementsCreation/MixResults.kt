package com.example.game.elementsCreation

import com.example.game.Element
import com.example.game.R

object MixResults : ChemicalReactionsResults {
    override val results: Map<Elements, Element> = mapOf(
        /** Вода */
        Elements(
            Pair(Element(R.drawable.vodorod, "Водород"), 2u),
            Pair(Element(R.drawable.kislorod, "Кислород"), 1u)
        ) to Element(R.drawable.water, "Вода"),

        /** Фторид Марганца */
        Elements(
            Pair(Element(R.drawable.marganec, "Марганец"), 1u),
            Pair(Element(R.drawable.ftor, "Фтор"), 2u)
        ) to Element(R.drawable.ftorid_marganca, "Фторид Марганца (II)")
    )
}
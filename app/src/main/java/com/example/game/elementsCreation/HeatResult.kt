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
            Element(R.drawable.serovodorod, "Сероводород")
        ),

        /** Ортофосфорная кислота */
        Elements(
            Pair(Element(R.drawable.fosfor, "Фосфор"), 1u),
            Pair(Element(R.drawable.voda, "Вода"), 1u)
        ) to listOf(
            Element(R.drawable.ortofosfornaya_kislota, "Ортофосфорная кислота")
        ),

        /** Гремучий газ */
        Elements(
            Pair(Element(R.drawable.vodorod, "Водород"), 2u),
            Pair(Element(R.drawable.kislorod, "Кислород"), 1u)
        ) to listOf(
            Element(R.drawable.gremuchy_gaz, "Гремучий газ")
        ),

        /** Расплав песка */
        Elements(
            Pair(Element(R.drawable.pesok, "Песок"), 1u)
        ) to listOf(
            Element(R.drawable.rasplavlennoe_steklo, "Жидкое стекло")
        ),

        /** PN-переход */
        Elements(
            Pair(Element(R.drawable.p_perehod, "P-тип"), 1u),
            Pair(Element(R.drawable.n_perehod, "N-тип"), 1u)
        ) to listOf(
            Element(R.drawable.diod_normal, "PN-переход")
        ),

        /** Транзистор(npn) */
        Elements(
            Pair(Element(R.drawable.p_perehod, "P-тип"), 1u),
            Pair(Element(R.drawable.n_perehod, "N-тип"), 2u)
        ) to listOf(
            Element(R.drawable.transistor_npn, "Транзистор(npn)")
        ),

        /** Транзистор(pnp) */
        Elements(
            Pair(Element(R.drawable.p_perehod, "P-тип"), 2u),
            Pair(Element(R.drawable.n_perehod, "N-тип"), 1u)
        ) to listOf(
            Element(R.drawable.transistor_pnp, "Транзистор(pnp)")
        ),
//
//        /** Расплав песка */
//        Elements(
//            Pair(Element(R.drawable.pesok, "Песок"), 1u)
//        ) to listOf(
//            Element(R.drawable.gidkoe_steklo, "Жидкое стекло")
//        ),
        /** Каучук, парафин */
        Elements(
            Pair(Element(R.drawable.neft, "Нефть"), 1u)
        ) to listOf(
            Element(R.drawable.kauchuk, "Каучук"),
            Element(R.drawable.parafin, "Парафин")
        ),

        /** Шина */
        Elements(
            Pair(Element(R.drawable.kauchuk, "Каучук"), 1u),
            Pair(Element(R.drawable.parafin, "Парафин"), 1u),
            Pair(Element(R.drawable.vosk, "Воск"), 1u),
        ) to listOf(
            Element(R.drawable.shina, "Шина")
        ),
    )
}
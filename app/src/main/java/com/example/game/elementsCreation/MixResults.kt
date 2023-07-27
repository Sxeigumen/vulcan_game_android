package com.example.game.elementsCreation

import com.example.game.Element
import com.example.game.R

object MixResults : ChemicalReactionsResults {
    override val results: Map<Elements, List<Element>> = mapOf(
        /** Вода */
        Elements(
            Pair(Element(R.drawable.vodorod, "Водород"), 2u),
            Pair(Element(R.drawable.kislorod, "Кислород"), 1u)
        ) to listOf(
            Element(R.drawable.voda, "Вода")
        ),

        /** Фторид Марганца */
        Elements(
            Pair(Element(R.drawable.marganec, "Марганец"), 1u),
            Pair(Element(R.drawable.ftor, "Фтор"), 2u)
        ) to listOf(
            Element(R.drawable.ftorid_marganca, "Фторид Марганца (II)")
        ),

        /** Оксид серы */
        Elements(
            Pair(Element(R.drawable.serovodorod, "Сероводород"), 1u),
            Pair(Element(R.drawable.kislorod, "Кислород"), 1u)
        ) to listOf(
            Element(R.drawable.oksid_sery, "Оксид серы (IV)")
        ),

        /** Напиток со льдом */
        Elements(
            Pair(Element(R.drawable.voda, "Вода"), 1u),
            Pair(Element(R.drawable.led, "Лед"), 1u)
        ) to listOf(
            Element(R.drawable.napitoc_so_ldom, "Напиток со льдом")
        ),

        /** Фосфин и фосфорная кислота */
        Elements(
            Pair(Element(R.drawable.par, "Пар"), 1u),
            Pair(Element(R.drawable.fosfor, "фосфор"), 1u)
        ) to listOf(
            Element(R.drawable.fosfin, "Фосфин"),
            Element(R.drawable.fosfornaya_kislota, "Фосфорная кислота")
        ),

        /** Фтороводород */
        Elements(
            Pair(Element(R.drawable.ftor, "Фтор"), 1u),
            Pair(Element(R.drawable.vodorod, "Водород"), 1u)
        ) to listOf(
            Element(R.drawable.ftorovodorod, "Фтороводород")
        ),

        /** Метиловый спирт */
        Elements(
            Pair(Element(R.drawable.vodorod, "Водород"), 2u),
            Pair(Element(R.drawable.ugarny_gaz, "Угарный газ"), 1u)
        ) to listOf(
            Element(R.drawable.metil, "Метиловый спирт")
        ),

        /** Песок */
        Elements(
            Pair(Element(R.drawable.kremniy, "Кремний"), 1u),
            Pair(Element(R.drawable.kislorod, "Кислород"), 2u)
        ) to listOf(
            Element(R.drawable.pesok, "Песок")
        ),

        /** USB */
        Elements(
            Pair(Element(R.drawable.uran, "Уран"), 1u),
            Pair(Element(R.drawable.sera, "Сера"), 1u),
            Pair(Element(R.drawable.bor, "Бор"), 1u)
        ) to listOf(
            Element(R.drawable.usb_normal, "USB")
        ),

        /** N-тип */
        Elements(
            Pair(Element(R.drawable.myshiyak, "Мышьяк"), 1u),
            Pair(Element(R.drawable.kremniy, "Кремний"), 1u)
        ) to listOf(
            Element(R.drawable.n_perehod, "N-тип")
        ),

        /** P-тип */
        Elements(
            Pair(Element(R.drawable.germaniy, "Германий"), 1u),
            Pair(Element(R.drawable.kremniy, "Кремний"), 1u)
        ) to listOf(
            Element(R.drawable.p_perehod, "P-тип")
        ),
//        /** Песок */
//        Elements(
//            Pair(Element(R.drawable.kremniy, "Кремний"), 1u),
//            Pair(Element(R.drawable.kislorod, "Кислород"), 2u)
//        ) to listOf(
//            Element(R.drawable.pesok, "Песок")
//        ),

        /** Диск */
        Elements(
            Pair(Element(R.drawable.aluminiy, "Алюминий"), 1u),
            Pair(Element(R.drawable.kremniy, "Кремний"), 1u)
        ) to listOf(
            Element(R.drawable.disk, "Диск")
        ),

        /** Колесо */
        Elements(
            Pair(Element(R.drawable.disk, "Диск"), 1u),
            Pair(Element(R.drawable.shina, "Шина"), 1u),
        ) to listOf(
            Element(R.drawable.koleso, "Колесо")
        ),
    )
}
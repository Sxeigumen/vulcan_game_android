package com.example.game.elementsCreation

import com.example.game.Element

class Elements() : HashMap<Element, UInt>() {

    constructor(vararg elements: Element) : this() {
        for (element in elements) {
            add(element)
        }
    }

    constructor(elements: Iterable<Element>) : this() {
        for (element in elements) {
            add(element)
        }
    }

    constructor(vararg pairs: Pair<Element, UInt>) : this() {
        for (pair in pairs) {
            super.put(pair.first, pair.second)
        }
    }

    fun add(element: Element) {
        if (super.containsKey(element)) {
            super.put(element, super.get(element)!! + 1u)
        } else {
            super.put(element, 1u)
        }
    }
    fun empty(){
        super.clear()
    }

    fun remove(imageId: Int) {
        val key = super.keys.find { element -> element.ImageId == imageId }
        if (key != null) {
            super.replace(key, super.get(key)!! - 1u)
            if (super.get(key)!! == 0u){
                super.remove(key)
            }
        }
    }
}
package com.example.hahahub

fun main() {
    val store = PetStore()

    val husky = Husky(weight = 25.0, age = 3)
    val corgi = Corgi(weight = 10.0, age = 2)
    val scottishCat = ScottishCat(weight = 4.0, age = 5)
    val siameseCat = SiameseCat(weight = 3.5, age = 4)

    println("${husky::class.simpleName} - ${store.determineAnimalType(husky)}")
    println("${corgi::class.simpleName} - ${store.determineAnimalType(corgi)}")
    println("${scottishCat::class.simpleName} - ${store.determineAnimalType(scottishCat)}")
    println("${siameseCat::class.simpleName} - ${store.determineAnimalType(siameseCat)}")
}
package com.example.hahahub

class PetStore {
    fun determineAnimalType(animal: Animal): String {
        return when (animal) {
            is Husky, is Corgi -> "Собака"
            is ScottishCat, is SiameseCat -> "Кошка"
            else -> throw IllegalArgumentException("Неизвестный тип животного")
        }
    }
}
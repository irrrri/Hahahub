package com.example.hahahub

class PetStore {
    fun determineAnimalType(animal: Animal): String {
        return when (animal) {
            is Dog -> "Собака"
            is Cat -> "Кошка"
            else -> throw IllegalArgumentException("Неизвестный тип животного: ${animal::class.simpleName}")
        }
    }
}
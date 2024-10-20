package com.example.hahahub

fun main() {
    val shop = PetStore()

    // Создаем животное
    val animal = shop.determineAnimal("хаски", 25.0, 3)
    println(animal)  // Output: Husky(weight=25.0, age=3)

    // Получаем породу по классу
    val breed = shop.getBreed(animal)
    println("Порода: $breed")  // Output: Порода: хаски
}
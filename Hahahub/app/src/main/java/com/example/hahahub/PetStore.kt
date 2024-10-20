package com.example.hahahub

class PetStore {
    fun determineAnimal(breed: String, weight: Double, age: Int) : IAnimal {
        return when (breed.lowercase()) {
            "хаски" -> Husky(weight, age)
            "корги" -> Corgi(weight, age)
            "шотландский кот" -> ScottishCat(weight, age)
            "сиамский кот" -> SiameseCat(weight, age)
            else -> throw IllegalArgumentException("Неизвестная порода")
        }
    }

    fun getBreed(animal: IAnimal) : String {
         return when (animal) {
             is Husky -> "хаски"
             is Corgi -> "корги"
             is ScottishCat -> "шотландский кот"
             is SiameseCat -> "сиамский кот"
             else -> "Неизвестная порода"
         }
    }
}
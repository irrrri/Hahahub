package com.example.hahahub

class Husky(override val weight: Double, override val age: Int) : IDog {
    override val biteType = BiteType.STRAIGHT

    override fun getFoodRecommendation(): String {
        return "Рекомендуем корм для хаски"
    }
}
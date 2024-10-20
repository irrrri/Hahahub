package com.example.hahahub

class Corgi(override val weight: Double, override val age: Int) : IDog {
    override val biteType = BiteType.OVERBITE

    override fun getFoodRecommendation(): String {
        return "Рекомендуем корм для корги"
    }
}
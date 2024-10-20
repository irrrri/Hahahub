package com.example.hahahub

class ScottishCat(override val weight: Double, override val age: Int): ICat {
    override val behaviorType = BehaviorType.PASSIVE

    override fun getFoodRecommendation(): String {
        return "Рекомендуем корм для шотландского кота"
    }
}
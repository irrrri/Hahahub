package com.example.hahahub

class SiameseCat(override val weight: Double, override val age: Int) : ICat {
    override val behaviorType = BehaviorType.ACTIVE

    override fun getFoodRecommendation(): String {
        return "Рекомендуем корм для сиамских котов"
    }
}
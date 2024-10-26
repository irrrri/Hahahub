package com.example.hahahub

class Husky(override val weight: Double, override val age: Int) : Dog {
    override val biteType = BiteType.STRAIGHT
}
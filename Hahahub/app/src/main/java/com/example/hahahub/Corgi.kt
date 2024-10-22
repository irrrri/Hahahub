package com.example.hahahub

class Corgi(override val weight: Double, override val age: Int) : Dog {
    override val biteType = BiteType.OVERBITE
}
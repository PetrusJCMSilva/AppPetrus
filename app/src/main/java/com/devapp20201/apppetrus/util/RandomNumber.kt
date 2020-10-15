package com.devapp20201.apppetrus.util

import kotlin.random.Random

class RandomNumber {

   public fun randomId(): Int {
        return Random.nextInt(4, 100)
    }

}
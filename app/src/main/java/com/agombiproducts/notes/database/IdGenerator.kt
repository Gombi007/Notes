package com.agombiproducts.notes.database

import kotlin.random.Random

class IdGenerator {
    private val leftLimit = 65 //letter A
    private val rightLimit = 90 //letter Z
    private var lengthLimit = 6
    private lateinit var newId: String

    private fun getRandomIdByCharCode() {
        val builder = StringBuilder()
        while (lengthLimit > 0) {
            val codePoint = Random.nextInt(leftLimit, rightLimit + 1)
            builder.appendCodePoint(codePoint)
            lengthLimit--
        }
        newId = builder.toString()
    }

    fun getNewId(): String {
        getRandomIdByCharCode()
        return newId
    }
}
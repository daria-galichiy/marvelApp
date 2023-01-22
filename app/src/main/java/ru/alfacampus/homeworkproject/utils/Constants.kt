package ru.alfacampus.homeworkproject.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constants {

    companion object {
        const val BASE_URL = "https://gateway.marvel.com/"
        const val MARVEL_DETAILS_URL = "https://www.marvel.com/?utm_campaign=apiRef&utm_source=4a8b7d20efb0bc6fbc3840a53d7795c9"
        const val API_PUBLIC_KEY = "4a8b7d20efb0bc6fbc3840a53d7795c9"
        private const val API_PRIVATE_KEY = "352ad5fab5067929e4c1c78fbd13a9fd84d7b425"
        val timeStamp = Timestamp(System.currentTimeMillis()).time.toString()

        const val API_KEY = "apikey"
        const val HASH = "hash"
        const val TIMESTAMP = "ts"

        const val SEARCH_DELAY = 700L

        fun generateHashValue(): String {
            val input = timeStamp + API_PRIVATE_KEY + API_PUBLIC_KEY
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }
    }
}

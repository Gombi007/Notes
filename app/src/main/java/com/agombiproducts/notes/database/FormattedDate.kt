package com.agombiproducts.notes.database

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FormattedDate {
    companion object {
        fun getCurrentDate(): String {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            return current.format(formatter)
        }
    }
}
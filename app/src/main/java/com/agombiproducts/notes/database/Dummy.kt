package com.agombiproducts.notes.database

import com.agombiproducts.notes.models.Note

class Dummy {
    object testNotes {
        val notes = listOf<Note>(
            Note("1", "Kutyát megetetni", true, false, "2022-12-11", "2022-12-23"),
            Note("1", "Kenyeret venni a tescoba", true, false, "2022-12-11", "2022-12-23"),
            Note("1", "Megvenni az új autót", true, false, "2022-12-11", "2022-12-23"),
            Note("1", "Elzárni a gázt a sütőben", true, false, "2022-12-11", "2022-12-23"),
            Note("1", "Új billentyűzetet venni", true, false, "2022-12-11", "2022-12-23"),
            Note("1", "Macska kaját venni", true, false, "2022-12-11", "2022-12-23"),
            Note("1", "Elmenni a gyerekért az oviba", true, false, "2022-12-11", "2022-12-23"),
            Note("1", "Elindítani egy mosást", true, false, "2022-12-11", "2022-12-23"),
        )

    }
}
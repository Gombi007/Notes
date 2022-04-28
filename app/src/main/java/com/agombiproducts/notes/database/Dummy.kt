package com.agombiproducts.notes.database

import com.agombiproducts.notes.models.Note

class Dummy {
    object Data {
        val notes = listOf<Note>(
            Note("1", "Go for a walk with my dog in the afternoon", true, false, "2022-12-11", "2022-12-23"),
            Note("2", "Buying a fresh bread in the Tesco", false, false, "2022-12-11", "2022-12-23"),
            Note("3", "Buying a new car", false, false, "2022-12-11", "2022-12-23"),
            Note("4", "Turn off the cooker", true, false, "2022-12-11", "2022-12-23"),
            Note("5", "Go to the IT shop and buying a new keyboard", false, false, "2022-12-11", "2022-12-23"),
            Note("6", "Feed cats", false, false, "2022-12-11", "2022-12-23"),
            Note("7", "Go to the kindergarten for my son", true, false, "2022-12-11", "2022-12-23"),
            Note("8", "Watering my plants in the office", true, false, "2022-12-11", "2022-12-23"),
            Note("9", "Start the washing machine with a dose white clothes", true, false, "2022-12-11", "2022-12-23"),
            Note("10", "Wash the car before the rain", true, false, "2022-12-11", "2022-12-23"),
            Note("11", "Pay the electricity bill before they power off", true, false, "2022-12-11", "2022-12-23"),
            Note("12", "Go at the gym and work for body health", true, false, "2022-12-11", "2022-12-23"),
            Note("13", "Read a few pages from the new book in the evening", true, false, "2022-12-11", "2022-12-23"),
            Note("14", "Practicing the new jetpack compose at the weekend", true, false, "2022-12-11", "2022-12-23"),
            Note("15", "Buy few bottle of mineral water because the water at home is horrible", true, false, "2022-12-11", "2022-12-23"),
        )
    }
}
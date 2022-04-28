package com.agombiproducts.notes.routes

sealed class NavRoute(val route: String) {
    object Home : NavRoute("home")
    object Create : NavRoute("create")
    object Modify : NavRoute("modify")

}
package com.example.enactmart.data

sealed class Category(val category: String) {

    object Agni: Category("Agni")
    object Annotsav: Category("Annotsav")
    object Clair: Category("Clair")
    object Accessory: Category("Accessory")
    object Urja: Category("URJA")
}
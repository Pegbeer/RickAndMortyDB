package me.pegbeer.rickandmortydb.core.model

data class Character(
    val id:Long,
    val name:String,
    val imageUrl:String,
    val gender:String
)
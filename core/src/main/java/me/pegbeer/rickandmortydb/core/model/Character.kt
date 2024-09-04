package me.pegbeer.rickandmortydb.core.model

data class Character(
    val id:Int,
    val name:String,
    val imageUrl:String,
    val gender:String,
    val status:String,
    val created:String,
    val origin:String,
    val dimension:String
)
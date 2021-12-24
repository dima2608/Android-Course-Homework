package com.triare.p092recyclerview

data class User (
    val id: Int,
    val avatar: String,
    val feed: String,
    val name: String,
    val massage: String,
    val isOnline: Boolean
)

val ic_avatar = "https://1.bp.blogspot.com/-wcGBBlbjSgY/XyOHzJP5hmI/AAAAAAAAFXA/pj-_e6iUCYUXaBqFYFnaLBJ2Z3-A6hz3ACLcBGAsYHQ/s750/2.jpg"
val online = "Online"
val offline = "Offline"
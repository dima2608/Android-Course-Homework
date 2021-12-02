package com.triare.p092recyclerview.storage

import android.content.Context
import com.triare.p092recyclerview.User
import kotlin.random.Random.Default.nextBoolean

object UserStorage {
    fun random(context: Context) = MutableList(100) {
        User(
            it,
            RandomAvatar.randomAvatar(),
            RandomAvatar.randomAvatar(),
            DummyNameStorage.firstName(),
            RandomMessage.randomMessage(),
            nextBoolean()
        )
    }
}
package com.triare.fragments.seasons

import androidx.fragment.app.Fragment
import com.triare.fragments.seasons.fragments.AutumnFragment
import com.triare.fragments.seasons.fragments.SpringFragment
import com.triare.fragments.seasons.fragments.SummerFragment
import com.triare.fragments.seasons.fragments.WinterFragment

enum class Season {
    WINTER,
    SPRING,
    SUMMER,
    AUTUMN;

    val fragment: Fragment
        get() = when (this) {
            WINTER -> WinterFragment()
            SPRING -> SpringFragment()
            SUMMER -> SummerFragment()
            AUTUMN -> AutumnFragment()
        }

    val next: Season
        get() = when (this) {
            WINTER -> SPRING
            SPRING -> SUMMER
            SUMMER -> AUTUMN
            AUTUMN -> WINTER
        }

    val previous: Season
        get() = when (this) {
            WINTER -> AUTUMN
            SPRING -> WINTER
            SUMMER -> SPRING
            AUTUMN -> SUMMER
        }
}
package eu.ciambella.dailytest.common.resource

import androidx.annotation.ColorRes
import androidx.annotation.StringRes

interface ResourcesProvider {
    fun getString(@StringRes resId: Int, vararg arg: Any?): String
    fun getColor(@ColorRes colorId: Int): Int
}

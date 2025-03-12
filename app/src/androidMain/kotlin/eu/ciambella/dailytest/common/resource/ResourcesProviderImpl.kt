package eu.ciambella.dailytest.common.resource

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

class ResourcesProviderImpl(
    private val context: Context,
) : ResourcesProvider {

    override fun getString(
        @StringRes resId: Int,
        vararg arg: Any?
    ): String = context.getString(resId, *arg)

    override fun getColor(
        @ColorRes colorId: Int
    ): Int = ContextCompat.getColor(context, colorId)

}

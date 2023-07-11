package ken.projects.infit.core.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiText {

    data class DynamicString(val value: String) : UiText()
    class StringResource(@StringRes val resId: Int,
                         vararg val args: Any) : UiText()

    @Composable
    fun getString():String{
        return when(this){
            is DynamicString -> value
            is StringResource -> stringResource(id = resId, *args)
        }
    }

    fun getString(context:Context):String{
        return when(this){
            is DynamicString -> value
            is StringResource -> context.getString(resId,*args)
        }
    }
}
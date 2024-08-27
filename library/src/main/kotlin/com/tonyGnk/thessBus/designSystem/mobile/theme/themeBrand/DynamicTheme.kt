package com.tonyGnk.thessBus.designSystem.mobile.theme.themeBrand
import android.content.Context
import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import com.tonyGnk.thessBus.designSystem.mobile.theme.appTypography


@Composable
internal fun getDynamicTheme(context: Context): AppTheme {
    return if (supportsDynamicTheming()) {
        AppTheme(
            lightColorScheme = dynamicLightColorScheme(context),
            darkColorScheme = dynamicDarkColorScheme(context),
            typography = appTypography()
        )
    } else {
        GreenAppTheme
    }
}

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun supportsDynamicTheming() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

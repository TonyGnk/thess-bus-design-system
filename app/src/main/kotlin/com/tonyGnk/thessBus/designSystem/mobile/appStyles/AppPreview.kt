package com.tonyGnk.thessBus.designSystem.mobile.appStyles

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.Wallpapers.BLUE_DOMINATED_EXAMPLE
import androidx.compose.ui.tooling.preview.Wallpapers.GREEN_DOMINATED_EXAMPLE
import androidx.compose.ui.tooling.preview.Wallpapers.RED_DOMINATED_EXAMPLE
import androidx.compose.ui.tooling.preview.Wallpapers.YELLOW_DOMINATED_EXAMPLE

@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.FUNCTION
)
annotation class AppPreview {
    @Target(AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.RUNTIME)
    @AppPreview
    @PreviewPhoneScale
    annotation class Scale

    @Target(AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.RUNTIME)
    @AppPreview
    @PreviewFont
    annotation class Font

    @Target(AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.RUNTIME)
    @AppPreview
    @PreviewScaleAndFont
    annotation class ScaleAndFont

    @Target(AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.RUNTIME)
    @AppPreview
    @PreviewBrightness
    annotation class Brightness

    @Target(AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.RUNTIME)
    @AppPreview
    @PreviewBrightnessGreek
    annotation class BrightnessGreek

    @Target(AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.RUNTIME)
    @AppPreview
    @PreviewDynamicColor
    annotation class DynamicColors

    @Target(AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.RUNTIME)
    @AppPreview
    @PreviewLight
    annotation class Light

    @Target(AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.RUNTIME)
    @AppPreview
    @PreviewLightGreek
    annotation class LightGreek

    @Target(AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.RUNTIME)
    @AppPreview
    @PreviewDark
    annotation class Dark

    @Target(AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.RUNTIME)
    @AppPreview
    @PreviewDarkGreek
    annotation class DarkGreek
}


//16:9 Aspect Ratio (*1,77) - The "1" (561,996) is included in tablet preview
@Preview(name = "2", widthDp = 490, heightDp = 870)
@Preview(name = "3", widthDp = 436, heightDp = 774)
@Preview(name = "4", widthDp = 392, heightDp = 694)
@Preview(name = "5", widthDp = 352, heightDp = 623)
@Preview(name = "6", widthDp = 320, heightDp = 567)
private annotation class PreviewPhoneScale


@Preview(name = "85%", fontScale = 0.85f)
@Preview(name = "100%", fontScale = 1.0f)
@Preview(name = "115%", fontScale = 1.15f)
@Preview(name = "130%", fontScale = 1.3f)
@Preview(name = "150%", fontScale = 1.5f)
@Preview(name = "180%", fontScale = 1.8f)
@Preview(name = "200%", fontScale = 2f)
private annotation class PreviewFont


@Preview(name = "2 - 085%", widthDp = 490, heightDp = 870, fontScale = 0.85f)
@Preview(name = "2 - 100%", widthDp = 490, heightDp = 870, fontScale = 1.0f)
@Preview(name = "2 - 115%", widthDp = 490, heightDp = 870, fontScale = 1.15f)
@Preview(name = "2 - 130%", widthDp = 490, heightDp = 870, fontScale = 1.3f)
@Preview(name = "2 - 150%", widthDp = 490, heightDp = 870, fontScale = 1.5f)
@Preview(name = "2 - 180%", widthDp = 490, heightDp = 870, fontScale = 1.8f)
@Preview(name = "2 - 200%", widthDp = 490, heightDp = 870, fontScale = 2f)

@Preview(name = "3 - 085%", widthDp = 436, heightDp = 774, fontScale = 0.85f)
@Preview(name = "3 - 100%", widthDp = 436, heightDp = 774, fontScale = 1.0f)
@Preview(name = "3 - 115%", widthDp = 436, heightDp = 774, fontScale = 1.15f)
@Preview(name = "3 - 130%", widthDp = 436, heightDp = 774, fontScale = 1.3f)
@Preview(name = "3 - 150%", widthDp = 436, heightDp = 774, fontScale = 1.5f)
@Preview(name = "3 - 180%", widthDp = 436, heightDp = 774, fontScale = 1.8f)
@Preview(name = "3 - 200%", widthDp = 436, heightDp = 774, fontScale = 2f)

@Preview(name = "4 - 085%", widthDp = 392, heightDp = 694, fontScale = 0.85f)
@Preview(name = "4 - 100%", widthDp = 392, heightDp = 694, fontScale = 1.0f)
@Preview(name = "4 - 115%", widthDp = 392, heightDp = 694, fontScale = 1.15f)
@Preview(name = "4 - 130%", widthDp = 392, heightDp = 694, fontScale = 1.3f)
@Preview(name = "4 - 150%", widthDp = 392, heightDp = 694, fontScale = 1.5f)
@Preview(name = "4 - 180%", widthDp = 392, heightDp = 694, fontScale = 1.8f)
@Preview(name = "4 - 200%", widthDp = 392, heightDp = 694, fontScale = 2f)

@Preview(name = "5 - 085%", widthDp = 352, heightDp = 623, fontScale = 0.85f)
@Preview(name = "5 - 100%", widthDp = 352, heightDp = 623, fontScale = 1.0f)
@Preview(name = "5 - 115%", widthDp = 352, heightDp = 623, fontScale = 1.15f)
@Preview(name = "5 - 130%", widthDp = 352, heightDp = 623, fontScale = 1.3f)
@Preview(name = "5 - 150%", widthDp = 352, heightDp = 623, fontScale = 1.5f)
@Preview(name = "5 - 180%", widthDp = 352, heightDp = 623, fontScale = 1.8f)
@Preview(name = "5 - 200%", widthDp = 352, heightDp = 623, fontScale = 2f)

@Preview(name = "6 - 085%", widthDp = 320, heightDp = 567, fontScale = 0.85f)
@Preview(name = "6 - 100%", widthDp = 320, heightDp = 567, fontScale = 1.0f)
@Preview(name = "6 - 115%", widthDp = 320, heightDp = 567, fontScale = 1.15f)
@Preview(name = "6 - 130%", widthDp = 320, heightDp = 567, fontScale = 1.3f)
@Preview(name = "6 - 150%", widthDp = 320, heightDp = 567, fontScale = 1.5f)
@Preview(name = "6 - 180%", widthDp = 320, heightDp = 567, fontScale = 1.8f)
@Preview(name = "6 - 200%", widthDp = 320, heightDp = 567, fontScale = 2f)
private annotation class PreviewScaleAndFont


@Preview(name = "Light", locale = "en")
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL, locale = "en")
annotation class PreviewBrightness

@Preview(name = "Light", locale = "el")
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL, locale = "el")
annotation class PreviewBrightnessGreek

@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.FUNCTION
)
@Preview(name = "Red Light", wallpaper = RED_DOMINATED_EXAMPLE)
@Preview(
    name = "Red Dark",
    wallpaper = RED_DOMINATED_EXAMPLE,
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL
)
@Preview(name = "Blue Light", wallpaper = BLUE_DOMINATED_EXAMPLE)
@Preview(
    name = "Blue Dark",
    wallpaper = BLUE_DOMINATED_EXAMPLE,
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL
)
@Preview(name = "Green Light", wallpaper = GREEN_DOMINATED_EXAMPLE)
@Preview(
    name = "Green Dark",
    wallpaper = GREEN_DOMINATED_EXAMPLE,
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL
)
@Preview(name = "Yellow Light", wallpaper = YELLOW_DOMINATED_EXAMPLE)
@Preview(
    name = "Yellow Dark",
    wallpaper = YELLOW_DOMINATED_EXAMPLE,
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL
)
private annotation class PreviewDynamicColor


@Preview(name = "Light", showBackground = true, locale = "en")
private annotation class PreviewLight

@Preview(name = "Light", locale = "el")
private annotation class PreviewLightGreek


@Preview(name = "Dark", showBackground = true, uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL, locale = "en")
private annotation class PreviewDark

@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL, locale = "el")
private annotation class PreviewDarkGreek

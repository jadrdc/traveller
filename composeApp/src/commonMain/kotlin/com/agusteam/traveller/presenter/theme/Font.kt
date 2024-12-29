package com.agusteam.traveller.presenter.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import traveller.composeapp.generated.resources.Res
import traveller.composeapp.generated.resources.poppins_bold
import traveller.composeapp.generated.resources.poppins_light
import traveller.composeapp.generated.resources.poppins_medium
import traveller.composeapp.generated.resources.poppins_regular
import traveller.composeapp.generated.resources.poppins_semi_bold

@Composable
fun CustomFontFamily() = FontFamily(
    Font(Res.font.poppins_light, weight = FontWeight.Light),
    Font(Res.font.poppins_regular, weight = FontWeight.Normal),
    Font(Res.font.poppins_medium, weight = FontWeight.Medium),
    Font(Res.font.poppins_semi_bold, weight = FontWeight.SemiBold),
    Font(Res.font.poppins_bold, weight = FontWeight.Bold)
)
@Composable
fun CustomTypography() = Typography().run {
    val fontFamily = CustomFontFamily()
    copy(
        displayLarge = displayLarge.copy(fontFamily = fontFamily),
        displayMedium = displayMedium.copy(fontFamily = fontFamily),
        displaySmall = displaySmall.copy(fontFamily = fontFamily),
        headlineLarge = headlineLarge.copy(fontFamily = fontFamily),
        headlineMedium = headlineMedium.copy(fontFamily = fontFamily),
        headlineSmall = headlineSmall.copy(fontFamily = fontFamily),
        titleLarge = titleLarge.copy(fontFamily = fontFamily),
        titleMedium = titleMedium.copy(fontFamily = fontFamily),
        titleSmall = titleSmall.copy(fontFamily = fontFamily),
        bodyLarge = bodyLarge.copy(fontFamily =  fontFamily),
        bodyMedium = bodyMedium.copy(fontFamily = fontFamily),
        bodySmall = bodySmall.copy(fontFamily = fontFamily),
        labelLarge = labelLarge.copy(fontFamily = fontFamily),
        labelMedium = labelMedium.copy(fontFamily = fontFamily),
        labelSmall = labelSmall.copy(fontFamily = fontFamily)
    )
}

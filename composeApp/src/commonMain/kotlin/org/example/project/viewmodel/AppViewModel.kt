package org.example.project.viewmodel
import androidx.compose.ui.unit.LayoutDirection
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.project.expectClass.changeLanguage
import org.example.project.expectClass.getCurrentLanguage

class AppViewModel {
    private val _currentLanguage = MutableStateFlow(getCurrentLanguage())
    val currentLanguage = _currentLanguage.asStateFlow()

    private val _layoutDirection = MutableStateFlow(
        if (getCurrentLanguage() == "fa") LayoutDirection.Rtl else LayoutDirection.Ltr
    )
    val layoutDirection = _layoutDirection.asStateFlow()

    fun switchToPersian() {
        changeLanguage("fa")
        _currentLanguage.value = "fa"
        _layoutDirection.value = LayoutDirection.Rtl
    }

    fun switchToEnglish() {
        changeLanguage("en")
        _currentLanguage.value = "en"
        _layoutDirection.value = LayoutDirection.Ltr
    }

    fun toggleLanguage() {
        val newLang = if (getCurrentLanguage() == "fa") "en" else "fa"
        changeLanguage(newLang)
        _currentLanguage.value = newLang
        _layoutDirection.value = if (newLang == "fa") LayoutDirection.Rtl else LayoutDirection.Ltr
    }
}
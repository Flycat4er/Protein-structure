package org.example.proteinstructure.ui

import androidx.compose.ui.graphics.Color
import org.example.proteinstructure.domain.ProteinMethod

/**
 * Цвета, несущие предметный смысл данных.
 *
 * Согласно критериям приёмки, семантические цвета данных не меняются при переключении темы,
 * тогда как поверхности, карточки и текст используют токены MaterialTheme.colorScheme.
 */
object SemanticColors {
    // Цвета методов исследования белка:
    val methodXray = Color(0xFF1976D2)       // Синий: рентгеноструктурный анализ
    val methodCryoEm = Color(0xFF00897B)     // Бирюзовый: крио-ЭМ
    val methodNmr = Color(0xFFFB8C00)        // Оранжевый: ЯМР
    val methodNeutron = Color(0xFF8E24AA)    // Фиолетовый: нейтронография
    val methodOther = Color(0xFF607D8B)      // Сине-серый: прочие методы

    // Цвета качества разрешения структуры (Å):
    val resolutionHigh = Color(0xFF2E7D32)   // Зелёный: высокое разрешение (< 1.8 Å)
    val resolutionMedium = Color(0xFFF57F17) // Янтарный: среднее разрешение (1.8 - 2.5 Å)
    val resolutionLow = Color(0xFFC62828)    // Красный: низкое разрешение (> 2.5 Å)
    val resolutionNmr = Color(0xFF546E7A)    // Серо-голубой: ЯМР (неприменимо)

    fun colorForMethod(method: ProteinMethod): Color = when (method) {
        ProteinMethod.XRAY -> methodXray
        ProteinMethod.ELECTRON_MICROSCOPY -> methodCryoEm
        ProteinMethod.NMR -> methodNmr
        ProteinMethod.NEUTRON -> methodNeutron
        ProteinMethod.OTHER -> methodOther
    }

    fun colorForResolution(resolution: Double?): Color = when {
        resolution == null -> resolutionNmr
        resolution < 1.8 -> resolutionHigh
        resolution <= 2.5 -> resolutionMedium
        else -> resolutionLow
    }
}

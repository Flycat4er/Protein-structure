package org.example.proteinstructure.list

/**
 * Намерения пользователя на экране списка белков.
 */
sealed interface ProteinListIntent {
    data class CardClicked(val id: String) : ProteinListIntent
    data class QueryChanged(val value: String) : ProteinListIntent
}

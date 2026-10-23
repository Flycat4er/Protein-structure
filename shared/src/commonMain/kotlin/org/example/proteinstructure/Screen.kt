package org.example.proteinstructure

/**
 * Экраны приложения. В Navigation 3 маршрут — это объект, а не строка:
 * аргумент хранится полем типа String (PDB ID структуры), опечатки исключены на уровне типов.
 */
sealed interface Screen {
    data object List : Screen
    data class Detail(val id: String) : Screen
}

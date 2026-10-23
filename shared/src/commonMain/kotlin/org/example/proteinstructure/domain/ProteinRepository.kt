package org.example.proteinstructure.domain

/**
 * Доменный интерфейс доступа к каталогу белков.
 * В В1 за ним стоят моки (22 структуры).
 * В В2 этот интерфейс реализуется сетевым клиентом Ktor к RCSB API.
 */
interface ProteinRepository {
    suspend fun getProteins(query: String? = null): List<Protein>
    suspend fun getProtein(id: String): Protein
    suspend fun getRelatedProteins(id: String): List<Protein>
}

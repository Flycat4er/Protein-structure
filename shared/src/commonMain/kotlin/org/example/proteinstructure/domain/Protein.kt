package org.example.proteinstructure.domain

/**
 * Метод определения пространственной структуры белка из RCSB PDB.
 */
enum class ProteinMethod(val rawName: String) {
    XRAY("X-RAY DIFFRACTION"),
    ELECTRON_MICROSCOPY("ELECTRON MICROSCOPY"),
    NMR("SOLUTION NMR"),
    NEUTRON("NEUTRON DIFFRACTION"),
    OTHER("OTHER");

    companion object {
        fun fromRaw(raw: String): ProteinMethod {
            val upper = raw.uppercase()
            return when {
                "X-RAY" in upper -> XRAY
                "MICROSCOPY" in upper || "CRYO" in upper -> ELECTRON_MICROSCOPY
                "NMR" in upper -> NMR
                "NEUTRON" in upper -> NEUTRON
                else -> OTHER
            }
        }
    }
}

/**
 * Доменная модель структуры белка.
 * Поля названы и типизированы в соответствии со схемой RCSB PDB API
 * (search.rcsb.org/rcsbsearch/v2/query и data.rcsb.org/rest/v1/core/entry/{id}),
 * чтобы в вехе В2 моки заменялись сетью без переписывания модели.
 */
data class Protein(
    val id: String,                         // PDB ID (например "4HHB")
    val shortName: String,                  // Общепринятое краткое название ("Hemoglobin")
    val title: String,                      // Полный научный заголовок PDB
    val organism: String,                   // Научное название организма ("Homo sapiens")
    val classification: String,             // Класс белка ("OXYGEN TRANSPORT", "VIRAL PROTEIN")
    val method: ProteinMethod,              // Экспериментальный метод
    val resolution: Double?,                // Разрешение в Ангстремах (null для ЯМР)
    val polymerComposition: String,         // Тип полимера ("protein/protein", "protein/DNA")
    val monomerCount: Int,                  // Количество аминокислотных остатков
    val molecularWeightKDa: Double,         // Молекулярная масса в кДа
    val releaseDate: String,                // Дата депонирования (ISO формат: "1984-07-17")
    val authors: List<String>,              // Список авторов структуры
    val citationTitle: String?,             // Название публикации
    val relatedIds: List<String> = emptyList(), // Связанные PDB ID (гомологи, комплексы, мутанты)
)

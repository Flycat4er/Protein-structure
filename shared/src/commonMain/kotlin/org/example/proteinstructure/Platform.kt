package org.example.proteinstructure

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

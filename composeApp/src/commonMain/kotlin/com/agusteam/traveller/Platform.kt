package com.agusteam.traveller

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
package com.goboard.model

data class KeyboardLayout(
    val rows: List<List<Key>>
)

data class Key(
    val label: String,
    val value: String,
    val width: Float = 1f,
    val isModifier: Boolean = false
)

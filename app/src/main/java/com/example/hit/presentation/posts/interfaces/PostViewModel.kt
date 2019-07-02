package com.example.teat.presentation.posts.interfaces

import java.util.*

interface PostViewModel {
    val title: CharSequence?
    val creationDate: Date?
    val isSelected: Boolean
}
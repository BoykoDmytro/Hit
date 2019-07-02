package com.example.hit.presentation.posts.interfaces

interface PostViewModel {
    val title: CharSequence?
    val creationDate: String?
    var isSelected: Boolean
}
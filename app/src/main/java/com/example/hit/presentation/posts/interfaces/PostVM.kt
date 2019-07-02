package com.example.hit.presentation.posts.interfaces

data class PostVM(
        override val title: CharSequence?,
        override val creationDate: String?,
        override var isSelected: Boolean = false
) : PostViewModel
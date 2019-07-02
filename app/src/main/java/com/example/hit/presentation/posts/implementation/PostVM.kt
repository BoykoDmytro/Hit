package com.example.teat.presentation.posts.implementation

import com.example.teat.presentation.posts.interfaces.PostViewModel
import java.util.*

data class PostVM(
        override val title: CharSequence?,
        override val creationDate: Date?,
        override val isSelected: Boolean = false
) : PostViewModel
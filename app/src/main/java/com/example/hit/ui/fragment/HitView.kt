package com.example.hit.ui.fragment

import com.example.hit.presentation.base.interfaces.BaseView
import com.example.hit.presentation.posts.interfaces.PostViewModel

interface HitView : BaseView {

    fun hideDeleteMode()

    fun onPostsReady(list: List<PostViewModel>)

    fun onPostChanged(postViewModel: PostViewModel, position: Int)
}
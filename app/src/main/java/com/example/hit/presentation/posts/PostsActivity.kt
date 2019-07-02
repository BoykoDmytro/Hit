package com.example.hit.presentation.posts

import android.os.Bundle
import com.example.hit.presentation.base.implementation.view.BaseFragmentActivity
import com.example.hit.ui.fragment.HitFragment

class PostsActivity: BaseFragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showFragment(HitFragment())
    }

}
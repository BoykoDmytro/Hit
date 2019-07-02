package com.example.hit.ui.fragment

import androidx.paging.PagedList
import com.example.hit.data.models.Hit
import com.example.hit.data.models.HitPaginationModel
import com.example.hit.data.pagination.DEFAULT_CONFIG
import com.example.hit.data.pagination.PaginationDataSource
import com.example.hit.domain.PostUseCase
import com.example.hit.presentation.base.implementation.presenter.BasePresenter
import com.example.hit.presentation.posts.interfaces.PostVM
import com.example.hit.presentation.posts.interfaces.PostViewModel
import moxy.InjectViewState

@InjectViewState
class HitPresenter(private val postsUseCase: PostUseCase) : BasePresenter<HitView>() {

    fun deleteItems(selectedItems: List<PostViewModel>) {
    }

    private val postsDataSource = PaginationDataSource<PostViewModel> {
        val page = executeApiRequest<HitPaginationModel> {
            postsUseCase.fetchPosts(it)
        }
        val list: List<Hit> = page?.hits ?: listOf()
        return@PaginationDataSource list.map {
            PostVM(title = it.title, creationDate = it.createdAt)
        }
    }.also {
        it["tags"] = "story"
    }

    private val pagedList: PagedList<PostViewModel>
        get() = PagedList.Builder(postsDataSource, DEFAULT_CONFIG)
            .setFetchExecutor {
                executeOnUI { executePending { it.run() } }
            }
            .setNotifyExecutor {
                executeOnUI { it.run() }
            }
            .build()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        refresh()
    }

    fun proceedItem(item: PostViewModel, position: Int) {
        item.isSelected = !item.isSelected
        viewState.onPostChanged(item, position)
    }

    fun refresh() = viewState.onPostsReady(pagedList)
}
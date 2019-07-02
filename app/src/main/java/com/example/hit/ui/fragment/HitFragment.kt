package com.example.hit.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hit.R
import com.example.hit.data.http.RestServiceFactory
import com.example.hit.data.http.services.PostService
import com.example.hit.domain.PostUseCase
import com.example.hit.presentation.base.implementation.view.BaseFragment
import com.example.hit.presentation.posts.interfaces.PostViewModel
import com.example.hit.ui.adapter.HitAdapter
import com.example.hit.ui.listener.OnChangedToolbarListener
import com.example.hit.ui.listener.OnItemClickListener
import kotlinx.android.synthetic.main.activity_with_fragment.toolbar
import kotlinx.android.synthetic.main.fragment_hit.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.util.*

class HitFragment : BaseFragment(), HitView, OnItemClickListener<PostViewModel>, OnChangedToolbarListener {

    @InjectPresenter
    lateinit var presenter: HitPresenter

    @ProvidePresenter
    fun createPresenter(): HitPresenter {
        val service = RestServiceFactory.createService(PostService::class.java)
        return HitPresenter(PostUseCase(service))
    }

    private val hitAdapter = HitAdapter(this, this)

    private var actionMode: ActionMode? = null
    private val actionModeCallback = object : ActionMode.Callback {
        override fun onPrepareActionMode(p0: ActionMode?, p1: Menu?): Boolean {
            return false
        }

        override fun onDestroyActionMode(p0: ActionMode?) {
            hideDeleteMode()
        }

        override fun onActionItemClicked(mode: ActionMode?, menu: MenuItem?): Boolean {
            return false
        }

        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            val inflater = mode?.menuInflater
            inflater?.inflate(R.menu.menu_selection, menu)
            return true
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_hit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.takeIf { it is AppCompatActivity }?.let { it.toolbar.title = getString(R.string.hits) }
        initRV()
    }

    private fun initRV() {
        context?.let {
            hitRV.layoutManager = LinearLayoutManager(it)
            hitRV.adapter = hitAdapter
            hitSRL.setOnRefreshListener {
                presenter.refresh()
            }
        }
    }

    override fun changedToolBar(isSelected: Boolean) {
        when (actionMode) {
            null -> actionMode = activity?.let { it as? AppCompatActivity }?.startActionMode(actionModeCallback)
            else -> hideDeleteMode()
        }
    }

    override fun updateSelectedItemCount(size: Int) {
        actionMode?.title = String.format(Locale.getDefault(), getString(R.string.selected_item), size)
    }

    override fun onItemClick(item: PostViewModel, position: Int) {
        presenter.proceedItem(item, position)
    }

    override fun hideDeleteMode() {
        hitAdapter.isInChoiceMode = false
        actionMode?.finish()
        actionMode = null
    }

    override fun onPostsReady(list: List<PostViewModel>) {
        val pL = list as PagedList<PostViewModel>
        pL.addWeakCallback(null, object: PagedList.Callback(){
            override fun onChanged(position: Int, count: Int) {
            }

            override fun onInserted(position: Int, count: Int) {
                hitSRL.isRefreshing = false
            }

            override fun onRemoved(position: Int, count: Int) {
            }
        })
        hitAdapter.submitList(pL)
    }

    override fun onPostChanged(postViewModel: PostViewModel, position: Int) {
        hitAdapter.notifyItemChanged(position)
    }
}
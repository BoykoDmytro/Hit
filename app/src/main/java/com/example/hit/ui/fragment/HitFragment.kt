package com.example.hit.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hit.R
import com.example.hit.data.models.Hit
import com.example.hit.presentation.base.implementation.view.BaseFragment
import com.example.hit.ui.adapter.HitAdapter
import com.example.hit.ui.listener.OnChangedToolbarListener
import com.example.hit.ui.listener.OnItemClickListener
import kotlinx.android.synthetic.main.fragment_hit.*
import moxy.presenter.InjectPresenter
import java.util.*
import kotlin.collections.ArrayList

class HitFragment : BaseFragment(),HitView, OnItemClickListener<Hit>, OnChangedToolbarListener {

    @InjectPresenter
    lateinit var presenter: HitPresenter

    private val hitAdapter = HitAdapter(arrayListOf(), this, this)

    private var actionMode: ActionMode? = null
    private val actionModeCallback = object : ActionMode.Callback {
        override fun onPrepareActionMode(p0: ActionMode?, p1: Menu?): Boolean {
            return false
        }

        override fun onDestroyActionMode(p0: ActionMode?) {
            hideDeleteMode()
        }

        override fun onActionItemClicked(mode: ActionMode?, menu: MenuItem?): Boolean {
            presenter.deleteItems(hitAdapter.getSelectedItems())
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initRV()
        activity?.takeIf { it is AppCompatActivity }?.let { it.toolbar.title = getString(R.string.hits) }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun initRV() {
        context?.let {
            hitRV.layoutManager = LinearLayoutManager(it)
            hitRV.adapter = hitAdapter
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

    override fun onItemClick(item: Hit, position: Int) {

    }

    override fun hideDeleteMode() {
        hitAdapter.isInChoiceMode = false
        actionMode?.finish()
        actionMode = null
    }

    override fun addNewItems(data: ArrayList<Hit>) {
        hitAdapter.addItems(data)
    }
}
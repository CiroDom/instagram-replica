package co.tiagoaguiar.course.instagram.ui.commons

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.core.commons.BasePresenter
import co.tiagoaguiar.course.instagram.databinding.FragProfileBinding
import co.tiagoaguiar.course.instagram.ui.views.main.PostAdapter

abstract class BaseFrag<T ,P : BasePresenter>(
    val bind : (View) -> T,
    @LayoutRes fragId: Int) : Fragment(fragId) {

    protected var binding: T? = null
    abstract var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getMenu().let {
            setHasOptionsMenu(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        getMenu()?.let {
            inflater.inflate(it, menu)
            super.onCreateOptionsMenu(menu, inflater)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = bind(view)

        setupViews()
    }

    override fun onDestroy() {
        binding = null
        presenter.onDestroy()
        super.onDestroy()
    }

    abstract fun setupPresenter()

    abstract fun setupViews()

    @MenuRes
    open fun getMenu() : Int? {
        return null
    }
}
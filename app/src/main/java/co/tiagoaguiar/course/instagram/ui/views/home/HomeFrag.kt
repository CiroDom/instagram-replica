package co.tiagoaguiar.course.instagram.ui.views.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.databinding.FragHomeBinding
import co.tiagoaguiar.course.instagram.ui.commons.BaseFrag
import co.tiagoaguiar.course.instagram.ui.views.main.PostAdapter

class HomeFrag : BaseFrag<FragHomeBinding, HomePresenter>(
    FragHomeBinding::bind,
    R.layout.frag_home
) {
    override var presenter: HomePresenter? = null

    override fun setupPresenter() {
    }

    override fun getMenu() : Int {
        return R.menu.home_dot_menu
    }

    override fun setupViews() {
        val recyView = binding?.homefragRecyview
        with(recyView!!) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = PostAdapter()
        }
    }


}
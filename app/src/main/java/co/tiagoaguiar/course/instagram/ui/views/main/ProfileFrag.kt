package co.tiagoaguiar.course.instagram.ui.views.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.core.commons.ProfilePresenter
import co.tiagoaguiar.course.instagram.databinding.FragProfileBinding
import co.tiagoaguiar.course.instagram.ui.commons.BaseFrag

class ProfileFrag : BaseFrag<FragProfileBinding, ProfilePresenter>(
    FragProfileBinding::bind,
    R.layout.frag_profile) {

    override lateinit var presenter: ProfilePresenter

    override fun setupPresenter() {

    }

    override fun getMenu() : Int {
        return R.menu.profile_dot_menu
    }

    override fun setupViews() {
        val recyView = binding?.proffragRecyview
        with(recyView!!) {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = PostAdapter()
        }

        presenter.apply {
            fetchUserProfile()
            fetchUserPosts()
        }
    }
}
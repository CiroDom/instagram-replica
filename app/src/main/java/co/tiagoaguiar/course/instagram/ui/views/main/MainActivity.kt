package co.tiagoaguiar.course.instagram.ui.views.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.databinding.ActivityMainBinding
import co.tiagoaguiar.course.instagram.ui.interfaces.FragReplacer
import co.tiagoaguiar.course.instagram.ui.views.camera.CameraFrag
import co.tiagoaguiar.course.instagram.ui.views.home.HomeFrag
import co.tiagoaguiar.course.instagram.ui.views.search.SearchFrag
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener, FragReplacer {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val homeFrag by lazy {
        HomeFrag()
    }
    private val searchFrag by lazy {
        SearchFrag()
    }
    private val cameraFrag by lazy {
        CameraFrag()
    }
    private val profileFrag by lazy {
        ProfileFrag()
    }
    private var currentFrag: Fragment = homeFrag
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        replaceFrag(this, currentFrag, R.id.main_frag)

        val toolbar = binding.mainToolbar
        setSupportActionBar(toolbar)

        with(supportActionBar!!) {
            setHomeAsUpIndicator(R.drawable.ic_insta_camera)
            setDisplayHomeAsUpEnabled(true)
            title = ""
        }

        with(binding.mainBotnavMain) {
            setOnNavigationItemSelectedListener(this@MainActivity)
            selectedItemId = R.id.menu_bottom_home
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var scrollToolbarEnabled = false

        currentFrag = when(item.itemId) {
            R.id.menu_bottom_home -> {
                if (currentFrag == homeFrag) return false

                homeFrag
            }
            R.id.menu_bottom_search -> {
                if (currentFrag == searchFrag) return false

                searchFrag
            }
            R.id.menu_bottom_add -> {
                if (currentFrag == cameraFrag) return false

                cameraFrag
            }
            R.id.menu_bottom_profile -> {
                if (currentFrag == profileFrag) return false

                scrollToolbarEnabled = true

                profileFrag
            }
            else -> {
                Log.e("MainFrag", "ItemId não identificado")
                return false
            }
        }

        replaceFrag(this, currentFrag, R.id.main_frag)

        setScrollToolbarEnabled(scrollToolbarEnabled)

        return true
    }

    private fun setScrollToolbarEnabled(enabled: Boolean) {
        val toolbarParams = binding.mainToolbar.layoutParams as AppBarLayout.LayoutParams
        val coordinatorParams = binding.mainAppbar.layoutParams as CoordinatorLayout.LayoutParams

        if (enabled) {
            toolbarParams.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
            coordinatorParams.behavior = AppBarLayout.Behavior()
            binding.mainAppbar.layoutParams = coordinatorParams
        }
        else {
            toolbarParams.scrollFlags = 0
            coordinatorParams.behavior = null
            binding.mainAppbar.layoutParams = coordinatorParams
        }
    }
}
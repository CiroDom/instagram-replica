package co.tiagoaguiar.course.instagram.ui.interfaces

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import co.tiagoaguiar.course.instagram.R

interface FragReplacer {

    fun replaceFrag(activity: AppCompatActivity, frag: Fragment, @IdRes idFrag: Int) {
        val suppFragManager = activity.supportFragmentManager

        if (suppFragManager.findFragmentById(idFrag) == null) {
            suppFragManager.beginTransaction().apply {
                add(idFrag, frag)
                commit()
            }
        }
        else {
            suppFragManager.beginTransaction().apply {
                replace(idFrag, frag)
                addToBackStack(null)
                commit()
            }
        }
    }

}
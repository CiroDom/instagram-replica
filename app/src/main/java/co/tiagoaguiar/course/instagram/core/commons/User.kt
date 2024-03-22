package co.tiagoaguiar.course.instagram.core.commons

import android.content.Context
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import co.tiagoaguiar.course.instagram.ui.commons.LoadingButton
import co.tiagoaguiar.course.instagram.ui.views.main.MainActivity

interface User {

    fun showProg(enabled: Boolean, button: LoadingButton) {
        button.showProgBar(enabled)
    }

    fun displayFailure(@StringRes errorMsg: Int?, textInput: EditText) {
        textInput.error = errorMsg?.let {textInput.context.getString(it)}
    }

    fun onUserAuth(actv: AppCompatActivity) {
        val intent = Intent(actv, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        actv.startActivity(intent)
    }

    fun onUserUnauth(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}
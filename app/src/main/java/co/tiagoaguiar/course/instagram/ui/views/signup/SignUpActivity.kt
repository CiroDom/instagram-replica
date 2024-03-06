package co.tiagoaguiar.course.instagram.ui.views.signup

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.databinding.ActivitySignUpBinding
import co.tiagoaguiar.course.instagram.ui.commons.Keys
import co.tiagoaguiar.course.instagram.ui.interfaces.FragReplacer
import co.tiagoaguiar.course.instagram.ui.interfaces.KeyboardHider
import co.tiagoaguiar.course.instagram.ui.views.main.MainActivity

class SignUpActivity : AppCompatActivity(), KeyboardHider, FragReplacer {

    private val binding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }

    private var photoInBitmap: Bitmap? = null

    val fragId = R.id.sign_up_frag

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        keyboardHiding(this, binding.root)

        val emailFrag = SignUpEmailFrag()
        replaceFrag(this, emailFrag, fragId)

        binding.signUpTxtHasAlreadyAccount.setOnClickListener {
            finish()
        }
    }

    fun bitmapToActv(bitmap: Bitmap?) {
        photoInBitmap = bitmap
    }

    fun goToNameAndPassw(email: String, photo: ByteArray? = null) {
        val bundle = Bundle()
        bundle.putString(Keys.EMAIL_SIGN_UP, email)

        if (photo != null) {
            bundle.putByteArray(Keys.PHOTO_SIGN_UP, photo)
        }

        val nameFrag = SignUpNameFrag()
        nameFrag.arguments = bundle

        replaceFrag(this, nameFrag, fragId)
    }

    fun goToWelcome(name: String) {
        val bundle = Bundle()
        bundle.putString(Keys.NAME_SIGN_UP, name)

        val welcomeFrag = SignUpWelcomeFrag()
        welcomeFrag.arguments = bundle

        replaceFrag(this, welcomeFrag, fragId)
    }

    fun goToPhoto() {
        val photoFrag = SignUpPhotoFrag()
        replaceFrag(this, photoFrag, fragId)
    }

    fun goToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}
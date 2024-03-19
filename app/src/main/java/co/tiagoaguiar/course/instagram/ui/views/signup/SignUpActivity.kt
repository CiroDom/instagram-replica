package co.tiagoaguiar.course.instagram.ui.views.signup

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.databinding.ActivitySignUpBinding
import co.tiagoaguiar.course.instagram.ui.commons.CropperImgFrag
import co.tiagoaguiar.course.instagram.ui.commons.Keys
import co.tiagoaguiar.course.instagram.ui.interfaces.FragReplacer
import co.tiagoaguiar.course.instagram.ui.interfaces.KeyboardHider
import co.tiagoaguiar.course.instagram.ui.views.main.MainActivity

class SignUpActivity : AppCompatActivity(), KeyboardHider, FragReplacer {

    private val binding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) {uri: Uri? ->
        uri?.let { openImgCropper(uri) }
    }


    val getCamera = registerForActivityResult(ActivityResultContracts.TakePicture()) { wasSaved ->
        if (wasSaved) {
            openImgCropper(currentPhoto!!)
        }
    }

    var photoInBitmap: Bitmap? = null

    var currentPhoto: Uri? = null

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

    private fun openImgCropper(uri: Uri) {
        val frag = CropperImgFrag().apply {
            arguments = Bundle().apply {
                putParcelable(Keys.URI, uri)
            }
        }
        replaceFrag(this, frag, R.id.sign_up_frag)
    }

    fun getUri(uri: Uri) {
        currentPhoto = uri
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

    fun goToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}
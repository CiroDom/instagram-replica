package co.tiagoaguiar.course.instagram.ui.views.signup

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.core.commons.DependencyInjector
import co.tiagoaguiar.course.instagram.core.signup.presentation.SignUpEmailPresenter
import co.tiagoaguiar.course.instagram.databinding.FragSignUpEmailBinding
import co.tiagoaguiar.course.instagram.ui.commons.Keys
import co.tiagoaguiar.course.instagram.ui.commons.LoadingButton
import co.tiagoaguiar.course.instagram.ui.commons.OurTextWatcher
import co.tiagoaguiar.course.instagram.core.commons.PhotoChanger
import co.tiagoaguiar.course.instagram.ui.interfaces.User
import de.hdodenhof.circleimageview.CircleImageView

class SignUpEmailFrag : Fragment(), User {

    private var binding: FragSignUpEmailBinding? = null

    private var signUpActivity: SignUpActivity? = null

    private var photoChanger: PhotoChanger? = PhotoChanger()

    private lateinit var presenter: SignUpEmailPresenter

    private lateinit var imgView: CircleImageView

    lateinit var editEmail: EditText

    lateinit var button: LoadingButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragSignUpEmailBinding.inflate(layoutInflater, container, false)

        imgView = binding?.signUpImgIcon!!

        setFragmentResultListener(Keys.CROP) { requestKey, bundle ->
            val uri = bundle.getParcelable<Uri>(Keys.URI)
            val bitmap = photoChanger?.onCropImgResult(requireContext(), uri)
            signUpActivity?.photoInBitmap = bitmap
            imgView.setImageBitmap(bitmap)
        }

        return binding?.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is SignUpActivity) {
            signUpActivity = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editEmail = binding!!.signUpEditEmail
        button = binding!!.signUpButtonNextEmail

        val repository = DependencyInjector.createSignUpRepo()
        presenter = SignUpEmailPresenter(this, repository)

        imgView.setOnClickListener {
            val actv = activity as SignUpActivity
            photoChanger?.photoChangerDialog(actv, actv.getContent, actv.getCamera, actv::getUri)
        }

        val watcher = OurTextWatcher {
            button.isEnabled = editEmail.text.toString().isNotEmpty()
        }
        editEmail.apply {
            addTextChangedListener(watcher)
            addTextChangedListener {
                displayFailure(null, this)
            }
        }

        button.setOnClickListener {
            presenter.create(editEmail.text.toString())
        }
    }

    override fun onDestroy() {
        binding = null
        signUpActivity = null
        photoChanger = null
        presenter.onDestroy()
        super.onDestroy()
    }

    fun goToNameAndPassw(email: String, photo: ByteArray? = null) {
        signUpActivity?.goToNameAndPassw(email, photo)
    }

    fun onEmailFailure(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

}
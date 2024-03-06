package co.tiagoaguiar.course.instagram.ui.commons

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.databinding.FragImgCropperBinding
import java.io.File

class CropperImgFrag : Fragment(R.layout.frag_img_cropper) {

    private var binding: FragImgCropperBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragImgCropperBinding.bind(view)

        val uri = arguments?.getParcelable<Uri>(Keys.URI)

        binding?.let {
            with(it) {
                with(cropperContainer) {
                    setAspectRatio(1,1)
                    setFixedAspectRatio(true)
                    setImageUriAsync(uri)
                    setOnCropImageCompleteListener { view, result ->
                        setFragmentResult(Keys.CROP, bundleOf(Keys.URI to result.uri))

                        parentFragmentManager.popBackStack()
                    }
                }

                cropperButtonCancel.setOnClickListener {
                    parentFragmentManager.popBackStack()
                }

                cropperButtonSave.setOnClickListener {
                    val dir = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

                    if (dir != null) {
                        val savedUri = Uri.fromFile(File(dir.path, System.currentTimeMillis().toString() + ".jpeg"))
                        cropperContainer.saveCroppedImageAsync(savedUri)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        binding = null

        super.onDestroy()
    }

}
package co.tiagoaguiar.course.instagram.ui.commons

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import co.tiagoaguiar.course.instagram.databinding.DialogOurBinding

class OurDialog(context: Context) : Dialog(context) {

    private val bindind by lazy {
        DialogOurBinding.inflate(layoutInflater)
    }

    private val dialogLinearLay by lazy {
        bindind.dialogourContainer
    }

    private val titleTxt by lazy {
        bindind.dialogourTxtTitle
    }

    private lateinit var txtOptions: Array<TextView>

    private var titleId: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(bindind.root)
    }


    override fun show() {
        super.show()

        for (textView in txtOptions) {
            val layParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            )
            layParams.setMargins(30, 50, 30, 50)

            dialogLinearLay.addView(textView, layParams)
        }

        titleId?.let {id ->
            titleTxt.setText(id)
        }
    }

    fun addOption(vararg texts: Int, listener: View.OnClickListener) {
        txtOptions = Array(texts.size) {
            TextView(context)
        }
        texts.forEachIndexed { index, txtId ->
            with(txtOptions[index]) {
                setText(txtId)
                setOnClickListener {
                    listener.onClick(it)
                    dismiss()
                }
            }
        }
    }

}
package co.tiagoaguiar.course.instagram.ui.commons

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.StringRes
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

    private lateinit var optionTxts: Array<TextView>

    private var idTitle: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(bindind.root)
    }


    override fun show() {
        super.show()

        idTitle?.let { id ->
            titleTxt.setText(id)
        }

        for (textView in optionTxts) {
            val layParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            )
            layParams.setMargins(30, 50, 30, 50)

            dialogLinearLay.addView(textView, layParams)
        }
    }

    fun addOptions(@StringRes vararg textIds: Int, listener: View.OnClickListener) {
        optionTxts = Array(textIds.size) {
            TextView(context)
        }

        textIds.forEachIndexed { index, txtId ->
            optionTxts[index].apply {
                setText(txtId)
                setOnClickListener {
                    Log.i("Teste", "setOnClick")
                    listener.onClick(it)
                    dismiss()
                }
            }
        }
    }
}
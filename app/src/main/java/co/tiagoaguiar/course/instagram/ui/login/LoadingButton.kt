package co.tiagoaguiar.course.instagram.ui.login

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ProgressBar
import co.tiagoaguiar.course.instagram.R

class LoadingButton : FrameLayout {

    private lateinit var button: Button
    private lateinit var progBar: ProgressBar
    private var textFromStyleble: String? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setup(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setup(context, attrs)
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        button.isEnabled = enabled
    }

    override fun setOnClickListener(l: OnClickListener?) {
        button.setOnClickListener(l)
    }
    private fun setup(context: Context, attrs: AttributeSet?) {
        val layInflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        layInflater.inflate(R.layout.button_loading, this)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingButton, 0, 0)
        textFromStyleble = typedArray.getString(R.styleable.LoadingButton_text)

        button = getChildAt(0) as Button
        progBar = getChildAt(1) as ProgressBar

        button.text = textFromStyleble
        button.isEnabled = false

        typedArray.recycle()
    }

    private fun showProgBar(enabled: Boolean) {
        if (enabled) {
            button.isEnabled = false
            button.text = ""
            progBar.visibility = VISIBLE
        } else {
            button.isEnabled = true
            button.text = textFromStyleble
            progBar.visibility = GONE
        }
    }

}
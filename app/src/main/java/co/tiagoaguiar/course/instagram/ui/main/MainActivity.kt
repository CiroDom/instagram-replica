package co.tiagoaguiar.course.instagram.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
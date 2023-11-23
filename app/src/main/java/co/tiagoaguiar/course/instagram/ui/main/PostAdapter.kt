package co.tiagoaguiar.course.instagram.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.databinding.ItemProffragGridBinding

class PostAdapter : RecyclerView.Adapter<PostAdapter.PostVHolder>() {
    inner class PostVHolder(private val binding: ItemProffragGridBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(img: Int) {
            binding.itemImgGrid.setImageResource(img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostVHolder {
        val layInf = LayoutInflater.from(parent.context)
        val binding = ItemProffragGridBinding.inflate(layInf) // no curso há mais parâmetros

        return PostVHolder(binding)
    }

    override fun onBindViewHolder(holder: PostVHolder, position: Int) {
        holder.bind(R.drawable.ic_insta_add)
    }

    override fun getItemCount(): Int {
        return 15 // valor teste
    }
}
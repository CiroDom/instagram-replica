package co.tiagoaguiar.course.instagram.ui.views.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.databinding.ItemPostListBinding
import co.tiagoaguiar.course.instagram.databinding.ItemProffragGridBinding

class PostHomeAdapter : RecyclerView.Adapter<PostHomeAdapter.PostHomeVHolder>() {

    inner class PostHomeVHolder(private val binding: ItemPostListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(img: Int) {
            binding.itemImgPhoto.setImageResource(img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHomeVHolder {
        val layInf = LayoutInflater.from(parent.context)
        val binding = ItemPostListBinding.inflate(layInf, parent, false)

        return PostHomeVHolder(binding)
    }

    override fun onBindViewHolder(holder: PostHomeVHolder, position: Int) {
        holder.bind(R.drawable.ic_insta_add)
    }

    override fun getItemCount(): Int {
        return 15 // valor teste
    }
}
package co.tiagoaguiar.course.instagram.ui.views.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.databinding.ItemPostListBinding
import co.tiagoaguiar.course.instagram.databinding.ItemSearchBinding

class ItemSearchAdapter : RecyclerView.Adapter<ItemSearchAdapter.PostHomeVHolder>() {

    inner class PostHomeVHolder(private val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(img: Int) {
            binding.itemImgSearch.setImageResource(img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHomeVHolder {
        val layInf = LayoutInflater.from(parent.context)
        val binding = ItemSearchBinding.inflate(layInf) // no curso há mais parâmetros

        return PostHomeVHolder(binding)
    }

    override fun onBindViewHolder(holder: PostHomeVHolder, position: Int) {
        holder.bind(R.drawable.ic_insta_add)
    }

    override fun getItemCount(): Int {
        return 15 // valor teste
    }
}
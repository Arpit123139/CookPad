package com.example.Adapters

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.data.Category
import com.example.data.PopularItem
import com.example.foodreciepie.R

class CategoryAdapter(private val listener: (Category) -> Unit) :ListAdapter<Category,CategoryAdapter.CategoryViewHolder>(DiffCallback1()) {

    inner class  CategoryViewHolder(view: View): RecyclerView.ViewHolder(view){

        val categoryImage=view.findViewById<ImageView>(R.id.img_category)
        val categoryName=view.findViewById<TextView>(R.id.tvCategoryName)

        init {
            itemView.setOnClickListener {
                listener.invoke(getItem(adapterPosition))
            }
        }
        fun bind(item:Category){
            with(item){
                categoryName.text=this.strCategory

                Glide.with(itemView)
                    .load(this.strCategoryThumb)
                    .into(categoryImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layout=LayoutInflater.from(parent.context).inflate(R.layout.category_item,parent,false)
        return CategoryViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DiffCallback1 : DiffUtil.ItemCallback<Category>()
{
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
       return  oldItem.idCategory==newItem.idCategory
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem==newItem
    }
}
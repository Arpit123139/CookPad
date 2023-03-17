package com.example.Category

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.Adapters.DiffCallback
import com.example.data.PopularItem
import com.example.foodreciepie.R
import org.w3c.dom.Text

class Category_Meal_Adapter(private val listener:(PopularItem) -> Unit):ListAdapter<PopularItem,Category_Meal_Adapter.CategoryViewHolder>(DiffCallback2()) {

    inner class CategoryViewHolder(view: View):RecyclerView.ViewHolder(view){
        val img=view.findViewById<ImageView>(R.id.img_meal)
        val name1=view.findViewById<TextView>(R.id.tv_meal_name)
        val count=view.findViewById<TextView>(R.id.tv_category_count)

        init {
            itemView.setOnClickListener {
                listener.invoke(getItem(adapterPosition))
            }
        }
        fun bind(item:PopularItem){
            with(item){
                name1.text=this.strMeal;

                Glide.with(itemView)
                    .load(this.strMealThumb)
                    .into(img)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layout=LayoutInflater.from(parent.context).inflate(R.layout.meal_item,parent,false)
        return CategoryViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DiffCallback2 : DiffUtil.ItemCallback<PopularItem>()
{
    override fun areItemsTheSame(oldItem: PopularItem, newItem: PopularItem): Boolean {
        return oldItem.idMeal==newItem.idMeal
    }

    override fun areContentsTheSame(oldItem: PopularItem, newItem: PopularItem): Boolean {
        return oldItem==newItem
    }
}
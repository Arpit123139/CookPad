package com.example.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.MealResponse
import com.example.data.PopularItem
import com.example.foodreciepie.R

class MostPopularAdapter(private val listener: (PopularItem) -> Unit): ListAdapter<PopularItem,MostPopularAdapter.MostPopularViewHolder>(DiffCallback()){

    inner class MostPopularViewHolder(view: View):RecyclerView.ViewHolder(view){
        val PopularImage=view.findViewById<ImageView>(R.id.img_popular_meal)

        init {
            itemView.setOnClickListener {
                listener.invoke(getItem(adapterPosition))
            }
        }
        fun bind(PopulerItemData:PopularItem){

            with(PopulerItemData){
                Log.d("ARPIT ADAPTER ", "${PopulerItemData}")
                Glide.with(itemView)
                    .load(strMealThumb)
                    .into(PopularImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MostPopularViewHolder {

        val itemLayout=LayoutInflater.from(parent.context).inflate(R.layout.popular_item,parent,false)
        return MostPopularViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: MostPopularViewHolder, position: Int) {
        holder.bind(getItem(position))
    }



}
class DiffCallback : DiffUtil.ItemCallback<PopularItem>()
{
    override fun areItemsTheSame(oldItem: PopularItem, newItem: PopularItem): Boolean {
        return oldItem.idMeal==newItem.idMeal
    }

    override fun areContentsTheSame(oldItem: PopularItem, newItem: PopularItem): Boolean {
        return oldItem==newItem
    }
}
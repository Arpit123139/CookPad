package com.example.Favourite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.MealXX
import com.example.data.PopularItem
import com.example.foodreciepie.R

class FavouriteAdapter(private val listener:(MealXX)->Unit):ListAdapter<MealXX, FavouriteAdapter.FavouriteViewHolder>(DiffCallback3()) {

    inner class FavouriteViewHolder(view: View):RecyclerView.ViewHolder(view){

        val img=view.findViewById<ImageView>(R.id.img_meal)
        val name1=view.findViewById<TextView>(R.id.tv_meal_name)

        init {
            itemView.setOnClickListener {
                listener.invoke(getItem(adapterPosition))
            }
        }
       fun bind (item:MealXX){
           with(item){
               name1.text=this.name.toString();

               Glide.with(itemView)
                   .load(this.image)
                   .into(img)

           }
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val layout=LayoutInflater.from(parent.context).inflate(R.layout.meal_item,parent,false)
        return FavouriteViewHolder((layout))
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}

class DiffCallback3 : DiffUtil.ItemCallback<MealXX>()
{
    override fun areItemsTheSame(oldItem: MealXX, newItem: MealXX): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: MealXX, newItem: MealXX): Boolean {
        return oldItem==newItem
    }


}
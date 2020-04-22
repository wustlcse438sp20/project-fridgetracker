package com.example.fridgetracker.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fridgetracker.R
import com.example.fridgetracker.activities.RecipeInfo
//import com.example.fridgetracker.activities.RecipeInfo
import com.example.fridgetracker.data.Recipe
import com.squareup.picasso.Picasso


//define the binding for the view holder
class RecipeViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.recipe_search_item, parent, false)) {
    private val recipeName: TextView
    private val recipeImage: ImageView
    val context=parent.context

    init {
        recipeName = itemView.findViewById(R.id.recipe_search_name)
        recipeImage = itemView.findViewById(R.id.recipe_search_image)
    }
    //take id of track and send to new activity for track
    fun bind(recipe: Recipe) {
        recipeName?.text = recipe.strMeal

        //Display album cover
        Picasso.get().load(recipe.strMealThumb).into(recipeImage)
        //onClickList on picture
        recipeImage.setOnClickListener{
            val intent= Intent(context, RecipeInfo::class.java)
            intent.putExtra("idMeal", Integer.parseInt(recipe.idMeal))
            intent.putExtra("comingFrom", "SearchRecipe")
            context.startActivity(intent)
        }

    }

}


//define the adapter for the recycler view
class RecipeListAdapter(private val list: ArrayList<Recipe>)
    : RecyclerView.Adapter<RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RecipeViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe: Recipe = list[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = list.size

}
package com.example.fridgetracker.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fridgetracker.R
import com.example.fridgetracker.activities.RecipeInfo
import com.example.fridgetracker.data.SavedRecipe

// taken from PlaylistAdapter
class SavedRecipeViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.recipe_save_item, parent, false)) {
    private val recipeSaveName : TextView
    //private val recipeSaveItem: LinearLayout

    val context=parent.context

    init {
        recipeSaveName = itemView.findViewById(R.id.recipeSaveName)
        //recipeSaveItem = itemView.findViewById(R.id.recipeSaveItem)
    }

    fun bind(sr: SavedRecipe) {
        recipeSaveName.text = sr.strMeal
        recipeSaveName.setOnClickListener(){
            // take user to recipe view screen
            // put in id into intent to send to
            val intent= Intent(context, RecipeInfo::class.java)
            intent.putExtra("idMeal", Integer.parseInt(sr.idMeal))
            intent.putExtra("comingFrom", "SaveRecipe")
            context?.startActivity(intent)
        }
//        recipeSaveItem.setOnClickListener(){
//
//        }

    }

}

//create the listener for the recycler view
class SavedRecipeAdaptor(private val list: ArrayList<SavedRecipe>?)
    : RecyclerView.Adapter<SavedRecipeViewHolder>() {
    private var recipeItemList : ArrayList<SavedRecipe>? = list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedRecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SavedRecipeViewHolder(inflater, parent)
    }

    //bind the object
    override fun onBindViewHolder(holder: SavedRecipeViewHolder, position: Int) {
        val event: SavedRecipe = recipeItemList!!.get(position)
        holder.bind(event)
    }

    //set the count
    override fun getItemCount(): Int = list!!.size

}
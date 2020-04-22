package com.example.fridgetracker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.fridgetracker.R
import com.example.fridgetracker.adapters.SavedRecipeAdaptor
import com.example.fridgetracker.data.SavedRecipe
import com.example.fridgetracker.viewModels.RecipeViewModel
import kotlinx.android.synthetic.main.recipe_save_tab.*


/**
 * A simple [Fragment] subclass.
 * Use the [RecipeSaveFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecipeSaveFragment : Fragment() {
    lateinit var adapter: SavedRecipeAdaptor
    private lateinit var viewModel: RecipeViewModel
    private var savedRecipeList: ArrayList<SavedRecipe> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.recipe_save_tab, container, false)
    }

    override fun onStart() {
        super.onStart()


        // recycler for saved recipes
        var adapter = SavedRecipeAdaptor(savedRecipeList)
        recipeSaveItemRecycler.adapter = adapter
        recipeSaveItemRecycler.layoutManager = LinearLayoutManager(activity)
        recipeSaveItemRecycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        viewModel!!.getAllRecipes()
        // observer on _savedRecipesList
        viewModel!!._savedRecipesList.observe(this, Observer { recipes ->
            // Update the cached copy of the words in the adapter.
            savedRecipeList.clear()
            savedRecipeList.addAll(recipes)
            adapter.notifyDataSetChanged()
            recipeSaveItemRecycler!!.adapter?.notifyDataSetChanged()
        })

    }



}

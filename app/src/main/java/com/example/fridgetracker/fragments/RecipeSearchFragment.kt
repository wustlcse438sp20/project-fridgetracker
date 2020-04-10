package com.example.fridgetracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fridgetracker.R
import com.example.fridgetracker.adapters.RecipeListAdapter
import com.example.fridgetracker.data.Recipe
import com.example.fridgetracker.viewModels.RecipeViewModel
import kotlinx.android.synthetic.main.recipe_search_tab.*


class RecipeSearchFragment() : Fragment() {

    private lateinit var viewModel: RecipeViewModel
    lateinit var searchButton: SearchView
    lateinit var searchBox: EditText

    private var recipeList: ArrayList<Recipe> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.recipe_search_tab, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchBox = view.findViewById<EditText>(R.id.search_box)
        viewModel.getRandomRecipe() // wah


        var adapter = RecipeListAdapter(recipeList)
        recipeSearchRecyclerView.adapter = adapter
        recipeSearchRecyclerView.layoutManager = GridLayoutManager(this.context,2)
        recipeSearchRecyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        viewModel!!.recipeList.observe(viewLifecycleOwner, Observer { recipes ->
            // Update the cached copy of the words in the adapter.
            recipeList.clear()
            recipeList.addAll(recipes)
            adapter.notifyDataSetChanged()
        })

        //search bar: search by
        searchBox.setOnEditorActionListener() { v, actionId, event ->
            val input: String = searchBox.text.toString()
            viewModel!!.getRandomRecipe()

            viewModel!!.recipeList.observe(viewLifecycleOwner, Observer { recipes ->
                // Update the cached copy of the words in the adapter.
                recipeList.clear()
                recipeList.addAll(recipes)
                adapter.notifyDataSetChanged()
            })
            true
        }
    }
}

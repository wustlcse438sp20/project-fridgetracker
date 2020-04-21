package com.example.fridgetracker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.fridgetracker.R
import com.example.fridgetracker.fragments.RecipeSaveFragment
import com.example.fridgetracker.fragments.RecipeSearchFragment
import kotlinx.android.synthetic.main.content_tabs_holder.*


class RecipeActivity : AppCompatActivity() {
//    private var id:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_tabs_holder)

        val fragmentAdapter =
            MyPagerAdapter(
                supportFragmentManager
            )
        viewpager_content.adapter = fragmentAdapter

        tabs_content.setupWithViewPager(viewpager_content)
    }
    override fun onStart() {
        super.onStart()
    }

    class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getCount() : Int {
            return 2
        }

        override fun getItem(position: Int) : Fragment {
            return when (position) {
                0 -> { RecipeSearchFragment() } // recipe search tab
                1 -> { RecipeSaveFragment() } // recipe_save_tab.xml is implemented
                else -> RecipeSearchFragment()
            }
        }

        override fun getPageTitle(position: Int): CharSequence {
            return when (position) {
                0 -> "Search Recipes"
                else -> "Save Recipes"
            }
        }



    }
}

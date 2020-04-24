package com.example.fridgetracker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.fridgetracker.R
import com.example.fridgetracker.fragments.FreezerFragment
import com.example.fridgetracker.fragments.FridgeFragment
import com.example.fridgetracker.fragments.PantryFragment
import kotlinx.android.synthetic.main.content_tabs_holder.*


class InventoryActivity : AppCompatActivity() {
    private var id:String = ""

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
            return 3
        }

        override fun getItem(position: Int) : Fragment {
            return when (position) {
                0 -> { FridgeFragment() }
                1 -> { FreezerFragment() }
                else -> PantryFragment()
            }
        }

        override fun getPageTitle(position: Int): CharSequence {
            return when (position) {
                0 -> "Fridge"
                1 -> "Freezer"
                else -> "Pantry"
            }
        }



    }
}

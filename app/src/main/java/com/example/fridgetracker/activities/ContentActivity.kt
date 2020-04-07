package com.example.fridgetracker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.fridgetracker.R
import com.example.fridgetracker.fragments.FridgeFragment
import kotlinx.android.synthetic.main.tabs_holder.*


class ContentActivity : AppCompatActivity() {

//    lateinit var viewModel: MusicViewModel
//    var musicList: ArrayList<Music> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tabs_holder)

        val fragmentAdapter =
            MyPagerAdapter(
                supportFragmentManager
            )
        viewpager_content.adapter = fragmentAdapter

        tabs_content.setupWithViewPager(viewpager_content)
    }

    class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getCount() : Int {
            return 3
        }

        override fun getItem(position: Int) : Fragment {
            return when (position) {
                0 -> { FridgeFragment() } // wah temporary test
                1 -> { FridgeFragment() }
                else -> FridgeFragment()
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

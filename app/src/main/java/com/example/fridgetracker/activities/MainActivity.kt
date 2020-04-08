package com.example.fridgetracker.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.cse438.cse438_assignment4.fragments.LoginFragment
import com.example.cse438.cse438_assignment4.fragments.SignUpFragment
import com.example.fridgetracker.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentAdapter =
            MyPagerAdapter(
                supportFragmentManager
            )
        viewpager_main.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(viewpager_main)
    }

    class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getCount() : Int {
            return 2
        }

        override fun getItem(position: Int) : Fragment {
            return when (position) {
                0 -> { LoginFragment() }
                else -> SignUpFragment()
            }
        }

        override fun getPageTitle(position: Int): CharSequence {
            return when (position) {
                0 -> "Login"
                else -> "Sign Up"
            }
        }



    }
}




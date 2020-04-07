package com.example.fridgetracker.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fridgetracker.R
import com.example.fridgetracker.adapters.FridgeAdapter
import kotlinx.android.synthetic.main.enter_food_information.*
import kotlinx.android.synthetic.main.enter_food_information.view.*
import kotlinx.android.synthetic.main.fridge_tab.*



// taken from PlaylistFragment
class FridgeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fridge_tab, container, false)
    }

    override fun onStart() {
        super.onStart()

        // adapter stuff
        val testList = arrayListOf("testName")
        var adapter = FridgeAdapter(testList)
        fridgeItemRecycler.adapter = adapter
        fridgeItemRecycler.layoutManager = LinearLayoutManager(this.context)
        fridgeItemRecycler.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        // viewmodel stuff here?

        addFridgeItemButton.setOnClickListener {
            dialogView()
        }
    }

    /**
     * Displays the dialog box asking the user for the item's info
     */
    private fun dialogView() {
        // Opens the dialog view asking the user for
        val dialogView = LayoutInflater.from(this.getActivity()!!).inflate(R.layout.enter_food_information, null)
        val mBuilder = AlertDialog.Builder(this.getActivity())
            .setView(dialogView)
            .setTitle("Enter Food Information")
        val mAlertDialog = mBuilder.show()

        // Sets an onclick listener on the dialog box button
        mAlertDialog.addFoodButton.setOnClickListener {
            val foodName = dialogView.foodNameEntered.text.toString()
            val foodDate = dialogView.foodDateEntered.text.toString()
            val foodQuantity = dialogView.foodQuantityEntered.text.toString()
            val foodNote = dialogView.foodNoteEntered.text.toString()
            // If the string is empty, we do not want to accept that as an input
            if(foodName != "" && foodDate != "" && foodQuantity != "" && foodNote != ""){
//                val p = TrackPlaylist(
//                    playlistNameEntered,
//                    playlistDescEntered,
//                    playlistRatingEntered.toIntOrNull()!!,
//                    playlistGenreEntered
//                )
//                viewModel!!.insertPlayList(p)
                mAlertDialog.dismiss()
            } else {
                val myToast = Toast.makeText(this.getActivity(), "Please enter valid values", Toast.LENGTH_SHORT)
                myToast.show()
            }
        }
    }



}

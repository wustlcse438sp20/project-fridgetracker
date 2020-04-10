package com.example.fridgetracker.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
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
import android.content.Intent
import androidx.lifecycle.Observer
import com.example.fridgetracker.activities.ContentActivity
import com.example.fridgetracker.adapters.FridgeAdapter
import com.example.fridgetracker.data.Food
import com.example.fridgetracker.viewModel.FoodViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.enter_food_information.*
import kotlinx.android.synthetic.main.enter_food_information.view.*
import kotlinx.android.synthetic.main.fridge_tab.*


//
// taken from PlaylistFragment
class FreezerFragment : Fragment() {

    lateinit var adapter: FridgeAdapter
    private lateinit var viewModel: FoodViewModel
    private var foodItemList: ArrayList<Food> = ArrayList()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.freezer_tab, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FoodViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()
        var adapter = FridgeAdapter(foodItemList)
        fridgeItemRecycler.adapter = adapter
        fridgeItemRecycler.layoutManager = LinearLayoutManager(activity)
        fridgeItemRecycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        viewModel!!.getFreezerItems()

        viewModel!!._foodItemList.observe(this, Observer { playlists ->
            // Update the cached copy of the words in the adapter.
            foodItemList.clear()
            foodItemList.addAll(playlists)
            adapter.notifyDataSetChanged()
            fridgeItemRecycler!!.adapter?.notifyDataSetChanged()
        })


        addFridgeItemButton.setOnClickListener {
            dialogView()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //id = arguments!!.getString("user","")
//        println("fragment" + id)
    }

    /**
     * Displays the dialog box asking the user for the item's info
     */
    private fun dialogView() {
        // Opens the dialog view asking the user for
        //println("fragment" + id)
        val dialogView = LayoutInflater.from(this.getActivity()!!).inflate(R.layout.enter_food_information, null)
        val mBuilder = AlertDialog.Builder(this.getActivity())
            .setView(dialogView)
            .setTitle("Enter Food Information")
        val mAlertDialog = mBuilder.show()

        // Sets an onclick listener on the dialog box button
        mAlertDialog.addFoodButton.setOnClickListener {
            val foodName = dialogView.foodNameEntered.text.toString()
            val foodDate = dialogView.foodDateEntered.text.toString()
            val foodQuantity = dialogView.foodQuantityEntered.text.toString().toInt()
            val foodNote = dialogView.foodNoteEntered.text.toString()

            //store food into Food
            // If the string is empty, we do not want to accept that as an input
            if(foodName != "" && foodDate != "" && foodQuantity.toString() != "" && foodNote != ""){
                val food = Food("freezer",foodName,foodDate,foodQuantity,foodNote)
                viewModel!!.insertFood(food)
                mAlertDialog.dismiss()
            } else {
                val myToast = Toast.makeText(this.getActivity(), "Please enter valid values", Toast.LENGTH_SHORT)
                myToast.show()
            }
        }
    }


}
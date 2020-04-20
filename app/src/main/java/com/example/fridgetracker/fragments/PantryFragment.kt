package com.example.fridgetracker.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fridgetracker.R
import androidx.lifecycle.Observer
import com.example.fridgetracker.activities.MenuActivity
import com.example.fridgetracker.adapters.FridgeAdapter
import com.example.fridgetracker.data.Food
import com.example.fridgetracker.viewModel.FoodViewModel
import kotlinx.android.synthetic.main.enter_food_information.*
import kotlinx.android.synthetic.main.enter_food_information.view.*
import kotlinx.android.synthetic.main.pantry_tab.*


//
// taken from PlaylistFragment
class PantryFragment : Fragment() {

    lateinit var adapter: FridgeAdapter
    private lateinit var viewModel: FoodViewModel
    private var foodItemList: ArrayList<Food> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.pantry_tab, container, false)
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

        viewModel!!.getPantryItems()

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
        menuButton.setOnClickListener {
            var main = Intent(getActivity(), MenuActivity::class.java)
            //main.putExtra("id", 1)
            startActivity(main)
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
            if(foodName != "" && foodDate.toString() != "" && foodQuantity.toString() != "" && foodNote != ""){
                val food = Food("pantry",foodName,foodDate,foodQuantity,foodNote)
                viewModel!!.insertFood(food)
                mAlertDialog.dismiss()
            } else {
                val myToast = Toast.makeText(this.getActivity(), "Please enter valid values", Toast.LENGTH_SHORT)
                myToast.show()
            }
        }
    }

}


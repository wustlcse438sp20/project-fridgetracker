package com.example.fridgetracker.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fridgetracker.R
import com.example.fridgetracker.activities.MenuActivity
import com.example.fridgetracker.adapters.FridgeAdapter
import com.example.fridgetracker.data.Food
import com.example.fridgetracker.viewModel.FoodViewModel
import kotlinx.android.synthetic.main.enter_food_information.*
import kotlinx.android.synthetic.main.enter_food_information.view.*
import kotlinx.android.synthetic.main.fridge_tab.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.format.ResolverStyle
import java.util.*
import kotlin.collections.ArrayList


//
// taken from PlaylistFragment
class FridgeFragment : Fragment() {
    lateinit var adapter: FridgeAdapter
    private lateinit var viewModel: FoodViewModel
    private var foodItemList: ArrayList<Food> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fridge_tab, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FoodViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()
        var adapter = FridgeAdapter(foodItemList)
        val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")

        fridgeItemRecycler.adapter = adapter
        fridgeItemRecycler.layoutManager = LinearLayoutManager(activity)
        fridgeItemRecycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        viewModel!!.getFridgeItems()

        viewModel!!._foodItemList.observe(this, Observer { foods ->
            // Update the cached copy of the words in the adapter.
            foodItemList.clear()
            foodItemList.addAll(foods)

            foodItemList.sortBy { LocalDate.parse(it.foodDate, dateTimeFormatter) }
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


    /**
     * Displays the dialog box asking the user for the item's info
     */
    @RequiresApi(Build.VERSION_CODES.O)
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
            val month = dialogView.month.text.toString()
            val date = dialogView.date.text.toString()
            val year = dialogView.year.text.toString()
            val foodDate = month + "/" + date + "/" + year
            val foodQuantity = dialogView.foodQuantityEntered.text.toString()
            val foodNote = dialogView.foodNoteEntered.text.toString()


            //store food into Food
            // If the string is empty, we do not want to accept that as an input
            if(foodName != "" && foodDate != "" && foodQuantity != "" && foodNote != ""
                && foodDate.length == 10
                && month.toIntOrNull() != null && date.toIntOrNull() != null && year.toIntOrNull() != null
                && month.toInt() <= 12  && date.toInt() <= 31){
                //store food into user's food stuff
//                val dateTimeFormatter =
//                    DateTimeFormatter
//                        .ofPattern("MM/dd/yyyy", Locale.US)
//                        .withResolverStyle(ResolverStyle.STRICT)
//                try {
//                    val date: LocalDateTime = LocalDateTime.parse(foodDate, dateTimeFormatter)
//                    System.out.println(date)
//                } catch (e: DateTimeParseException) {
//                    // Throw invalid date message
//                    println("Exception was thrown")
//                    val myToast = Toast.makeText(this.getActivity(), "Please enter date with valid format", Toast.LENGTH_SHORT)
//                    myToast.show()
//                }

                val food = Food("fridge",foodName,foodDate,foodQuantity.toInt(),foodNote)
                viewModel!!.insertFood(food)
                mAlertDialog.dismiss()
            } else {
                val myToast = Toast.makeText(this.getActivity(), "Please enter valid values", Toast.LENGTH_SHORT)
                myToast.show()
            }
        }
    }


}

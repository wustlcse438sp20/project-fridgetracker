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
import kotlinx.android.synthetic.main.fridge_tab.*


//
// taken from PlaylistFragment
class FridgeFragment : Fragment() {
    //lateinit var database: FirebaseFirestore
    //private lateinit var auth: FirebaseAuth
    //private var currentUser: String = ""
    //lateinit var query: Query

    lateinit var adapter: FridgeAdapter
    private lateinit var viewModel: FoodViewModel
    private var foodItemList: ArrayList<Food> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fridge_tab, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FoodViewModel::class.java)
        //auth = FirebaseAuth.getInstance()
//        database = FirebaseFirestore.getInstance()
//        currentUser = auth.currentUser?.email.toString()
//
//        val docRef = database.collection("users").document(currentUser)
//        docRef.get()
//            .addOnSuccessListener { document ->
//                if (document != null) {
//                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
//
//
//                } else {
//                    Log.d(TAG, "No such document")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.d(TAG, "get failed with ", exception)
//            }

        //query = database.collection(currentUser)
//        query =database.collection("users").document(currentUser).collection("foods")
//        adapter = object : FridgeAdapter(query,this@FridgeFragment) {
//            override fun onDataChanged() {
//                // Show/hide content if the query returns empty.
//                if (itemCount == 0) {
//                    fridgeItemRecycler.visibility = View.GONE
//                } else {
//                    fridgeItemRecycler.visibility = View.VISIBLE
//                    fridgeItemRecycler.layoutManager = LinearLayoutManager(getActivity())
//                    fridgeItemRecycler.adapter = adapter
//                }
//            }
//        }


    }

    override fun onStart() {
        super.onStart()
        var adapter = FridgeAdapter(foodItemList)
        fridgeItemRecycler.adapter = adapter
        fridgeItemRecycler.layoutManager = LinearLayoutManager(activity)
        fridgeItemRecycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        viewModel!!.getFridgeItems()

        viewModel!!._foodItemList.observe(this, Observer { foods ->
            // Update the cached copy of the words in the adapter.
            foodItemList.clear()
            foodItemList.addAll(foods)
            adapter.notifyDataSetChanged()
            fridgeItemRecycler!!.adapter?.notifyDataSetChanged()
        })

        // adapter stuff
        //val testList = arrayListOf("testName")
        //var adapter = FridgeAdapter()
//        fridgeItemRecycler.adapter = adapter
//        fridgeItemRecycler.layoutManager = LinearLayoutManager(this.context)
//        fridgeItemRecycler.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        // viewmodel stuff here?
        //adapter.startListening()

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
            if(foodName != "" && foodDate != "" && foodQuantity.toString() != "" && foodNote != ""){
                //store food into user's food stuff
//                val userFoods = database.collection(currentUser).document()
//                userFoods.update("foods", FieldValue.arrayUnion(food))
//                adapter.notifyDataSetChanged()
                val food = Food("fridge",foodName,foodDate,foodQuantity,foodNote)
                viewModel!!.insertFood(food)
                mAlertDialog.dismiss()
            } else {
                val myToast = Toast.makeText(this.getActivity(), "Please enter valid values", Toast.LENGTH_SHORT)
                myToast.show()
            }
        }
    }

//    companion object {
//
//        private val TAG = "FridgeFragment"
//
//
//    }


}

package com.example.fridgetracker.adapters

//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.fridgetracker.R
//import com.example.fridgetracker.activities.ContentActivity
//import com.example.fridgetracker.data.Food
//import com.example.fridgetracker.fragments.FridgeFragment
//import com.google.firebase.firestore.DocumentSnapshot
//import com.google.firebase.firestore.Query
//import kotlinx.android.synthetic.main.fridge_item.view.*

//open class FridgeAdapter(query: Query, private val listener: FridgeFragment) :
//    FirestoreAdapter<FridgeAdapter.ViewHolder>(query) {
//
//    interface OnRestaurantSelectedListener {
//
//        fun onRestaurantSelected(restaurant: DocumentSnapshot)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        return ViewHolder(inflater.inflate(R.layout.fridge_item, parent, false))
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(getSnapshot(position), listener)
//    }
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//        fun bind(
//            snapshot: DocumentSnapshot,
//            listener: FridgeFragment
//        ) {
//
//            val food = snapshot.toObject(Food::class.java)
//            if (food == null) {
//                return
//            }
//
//            val resources = itemView.resources
//
//            itemView.fridgeItemName.text = food.foodName
//            itemView.fridgeItemDate.text = food.foodDate
//        }
//    }
//}

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.fridgetracker.R
import com.example.fridgetracker.activities.MainActivity
import com.example.fridgetracker.data.Food
import com.example.fridgetracker.fragments.FridgeFragment
import com.example.fridgetracker.viewModel.CostViewModel
import com.example.fridgetracker.viewModel.FoodViewModel
import kotlinx.android.synthetic.main.fridge_item.view.*

// taken from PlaylistAdapter
class FridgeViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.fridge_item, parent, false)) {
    private val itemName : TextView
    private val itemDate : TextView
    private val fridgeItem: LinearLayout
    private val deleteButtonCostItem: Button

    private lateinit var viewModel: FoodViewModel


    val context=parent.context

    init {
        itemName = itemView.findViewById(R.id.fridgeItemName)
        itemDate = itemView.findViewById(R.id.fridgeItemDate)
        fridgeItem = itemView.findViewById(R.id.fridgeItem)
        deleteButtonCostItem = itemView.findViewById(R.id.deleteButtonCostItem)

        viewModel =
            ViewModelProviders.of((context as FragmentActivity)!!).get(FoodViewModel::class.java)
    }

    fun bind(food: Food) {

        itemName.text = food.foodName
        itemDate.text = food.foodDate.toString()

        deleteButtonCostItem.setOnClickListener(){
            viewModel.deleteFoodItem(food)
        }

    }

}

//create the listener for the recycler view
class FridgeAdapter(private val list: ArrayList<Food>?)
    : RecyclerView.Adapter<FridgeViewHolder>() {
    private var fridgeItemList : ArrayList<Food>? = list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FridgeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FridgeViewHolder(inflater, parent)
    }

    //bind the object
    override fun onBindViewHolder(holder: FridgeViewHolder, position: Int) {
        val event: Food = fridgeItemList!!.get(position)
        holder.bind(event)
    }

    //set the count
    override fun getItemCount(): Int = list!!.size
//    *@SuppressLint("NewApi")
//     fun deleteItem(){
//         list.removeIf{it.isChecked}
//        notifyDataSetChanged()
//         }
//    }

}
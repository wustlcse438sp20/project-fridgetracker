package com.example.fridgetracker.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fridgetracker.R
import com.example.fridgetracker.data.Food

// taken from PlaylistAdapter
class FridgeViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.fridge_item, parent, false)) {
    private val itemName : TextView
    private val itemDate : TextView
    private val fridgeItem: LinearLayout

    val context=parent.context

    init {
        itemName = itemView.findViewById(R.id.fridgeItemName)
        itemDate = itemView.findViewById(R.id.fridgeItemDate)
        fridgeItem = itemView.findViewById(R.id.fridgeItem)
    }

    fun bind(item: Food) {

        itemName.text = item.foodName
        itemDate.text = item.foodDate

        fridgeItem.setOnClickListener(){
//            val intent= Intent(context, TracksOfPlaylist::class.java)
//            intent.putExtra("playlistName", playlist.playlistName)
//            context?.startActivity(intent)
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

}
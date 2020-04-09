package com.example.fridgetracker.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fridgetracker.R
import com.example.fridgetracker.activities.ContentActivity
import com.example.fridgetracker.data.Food
import com.example.fridgetracker.fragments.FridgeFragment
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fridge_item.view.*

open class FridgeAdapter(query: Query, private val listener: FridgeFragment) :
    FirestoreAdapter<FridgeAdapter.ViewHolder>(query) {

    interface OnRestaurantSelectedListener {

        fun onRestaurantSelected(restaurant: DocumentSnapshot)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.fridge_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getSnapshot(position), listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
            snapshot: DocumentSnapshot,
            listener: FridgeFragment
        ) {

            val food = snapshot.toObject(Food::class.java)
            if (food == null) {
                return
            }

            val resources = itemView.resources

            itemView.fridgeItemName.text = food.foodName
            itemView.fridgeItemDate.text = food.foodDate
        }
    }
}

//import android.view.LayoutInflater
//import android.view.ViewGroup
//import android.widget.LinearLayout
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.fridgetracker.R
//
//// taken from PlaylistAdapter
//class FridgeViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
//    RecyclerView.ViewHolder(inflater.inflate(R.layout.fridge_item, parent, false)) {
//    private val itemName : TextView
//    private val itemDate : TextView
//    private val fridgeItem: LinearLayout
//
//    val context=parent.context
//
//    init {
//        itemName = itemView.findViewById(R.id.fridgeItemName)
//        itemDate = itemView.findViewById(R.id.fridgeItemDate)
//        fridgeItem = itemView.findViewById(R.id.fridgeItem)
//    }
//
//    fun bind(item: String/*TrackPlaylist*/) {
//
//        itemName.text = item
//        //itemDate.text = playlist.playlistDesc
//
//        fridgeItem.setOnClickListener(){
////            val intent= Intent(context, TracksOfPlaylist::class.java)
////            intent.putExtra("playlistName", playlist.playlistName)
////            context?.startActivity(intent)
//        }
//
//    }
//
//}
//
////create the listener for the recycler view
//class FridgeAdapter(private val list: ArrayList</*TrackPlaylist*/String>?)
//    : RecyclerView.Adapter<FridgeViewHolder>() {
//    private var fridgeItemList : ArrayList</*TrackPlaylist*/String>? = list
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FridgeViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        return FridgeViewHolder(inflater, parent)
//    }
//
//    //bind the object
//    override fun onBindViewHolder(holder: FridgeViewHolder, position: Int) {
//        val event: String/*TrackPlaylist*/ = fridgeItemList!!.get(position)
//        holder.bind(event)
//    }
//
//    //set the count
//    override fun getItemCount(): Int = list!!.size
//
//}
package com.example.fridgetracker.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fridgetracker.activities.CameraActivity
import com.example.fridgetracker.activities.RecipeInfo
import com.example.fridgetracker.data.User
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.camera_recyclerview_item.view.*
import android.widget.ImageView
import android.widget.TextView
import com.example.fridgetracker.R



//define the binding for the view holder
class CameraViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.camera_recyclerview_item, parent, false)) {
    private val cameraItem: ImageView
    val context=parent.context

    init {
        cameraItem = itemView.findViewById(R.id.cameraItem)
    }
    //take id of track and send to new activity for track
    fun bind(url: String) {
        //Display album cover
        Picasso.get().load(url).into(cameraItem)
        //onClickList on picture
        cameraItem.setOnClickListener{
//            val intent= Intent(context, RecipeInfo::class.java)
//            intent.putExtra("idMeal", Integer.parseInt(recipe.idMeal))
//            intent.putExtra("comingFrom", "SearchRecipe")
//            context.startActivity(intent)
        }

    }

}


//define the adapter for the recycler view
class CameraAdapter(private val urls: ArrayList<String>)
    : RecyclerView.Adapter<CameraViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CameraViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CameraViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CameraViewHolder, position: Int) {
        val url: String = urls[position]
        holder.bind(url)
    }

    override fun getItemCount(): Int = urls.size

}

//open class CameraAdapter(query: Query, private val listener: CameraActivity) :
//    FirestoreAdapter<CameraAdapter.ViewHolder>(query) {
//
//    interface OnRestaurantSelectedListener {
//
//        fun onRestaurantSelected(restaurant: DocumentSnapshot)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        return ViewHolder(inflater.inflate(R.layout.camera_recyclerview_item, parent, false))
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
//            listener: CameraActivity
//        ) {
//
//            val user = snapshot.toObject(User::class.java)
//            if (user == null) {
//                return
//            }
//
//            val resources = itemView.resources
//
//            for (url in user.receiptsUrl) {
//
//            }
//            Picasso.get().load(recipe.strMealThumb).into(itemView.cameraItem)
//            //onClickList on picture
//            recipeImage.setOnClickListener{
//                val intent= Intent(context, RecipeInfo::class.java)
//                intent.putExtra("idMeal", Integer.parseInt(recipe.idMeal))
//                intent.putExtra("comingFrom", "SearchRecipe")
//                context.startActivity(intent)
//            }
//
////            itemView.idUser.text = Integer.toString(position + 1)
////            itemView.userScore.text = user.username +" with "+ user.chips.toString() + " chips"
//        }
//    }
//}
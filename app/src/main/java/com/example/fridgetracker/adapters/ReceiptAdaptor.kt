package com.example.fridgetracker.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fridgetracker.R
import com.example.fridgetracker.data.Cost

// taken from PlaylistAdapter
class ReceiptViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.receipts_item, parent, false)) {
    private val receiptItemDate : TextView
    private val receiptItemCost : TextView
    private val costItem: LinearLayout

    val context=parent.context

    init {
        receiptItemDate = itemView.findViewById(R.id.receiptItemDate)
        receiptItemCost = itemView.findViewById(R.id.receiptItemCost)
        costItem = itemView.findViewById(R.id.costItem)
    }

    fun bind(cost: Cost) {
        receiptItemDate.text = cost.dateOfPurchase
        receiptItemCost.text = "$" + cost.cost.toString()

        costItem.setOnClickListener(){

        }

    }

}

//create the listener for the recycler view
class ReceiptAdaptor(private val list: ArrayList<Cost>?)
    : RecyclerView.Adapter<ReceiptViewHolder>() {
    private var costItemList : ArrayList<Cost>? = list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ReceiptViewHolder(inflater, parent)
    }

    //bind the object
    override fun onBindViewHolder(holder: ReceiptViewHolder, position: Int) {
        val event: Cost = costItemList!!.get(position)
        holder.bind(event)
    }

    //set the count
    override fun getItemCount(): Int = list!!.size

}
package com.example.fridgetracker.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.fridgetracker.R
import com.example.fridgetracker.data.Cost
import com.example.fridgetracker.viewModel.CostViewModel


// taken from PlaylistAdapter
class ReceiptViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.receipts_item, parent, false)) {
    private val receiptItemDate : TextView
    private val receiptItemCost : TextView
    private val costItem: LinearLayout
    private val deleteButtonCostItem: Button

    private lateinit var viewModel: CostViewModel

    val context=parent.context

    init {
        receiptItemDate = itemView.findViewById(R.id.receiptItemDate)
        receiptItemCost = itemView.findViewById(R.id.receiptItemCost)
        costItem = itemView.findViewById(R.id.costItem)
        deleteButtonCostItem = itemView.findViewById(R.id.deleteButtonCostItem)

        viewModel =
            ViewModelProviders.of((context as FragmentActivity)!!).get(CostViewModel::class.java)
    }

    fun bind(cost: Cost) {
        receiptItemDate.text = cost.dateOfPurchase
        receiptItemCost.text = "$" + cost.cost.toString()

        deleteButtonCostItem.setOnClickListener(){
//            var receiptActIntent = Intent(context, ReceiptsActivity::class.java)
//            receiptActIntent.putExtra("costId",cost.id)
//
//            context.startActivity(receiptActIntent)
                viewModel.deleteCost(cost)
        }

    }

}

//create the listener for the recycler view
class ReceiptAdaptor(private val list: ArrayList<Cost>)
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

    fun removeItem(position:Int){
        list.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position,list.size)
    }
}
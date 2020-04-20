package com.example.fridgetracker.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fridgetracker.R
import androidx.lifecycle.Observer
import com.example.fridgetracker.adapters.ReceiptAdaptor
import com.example.fridgetracker.data.Cost
import com.example.fridgetracker.viewModel.CostViewModel
import kotlinx.android.synthetic.main.receipts_enter_info.*
import kotlinx.android.synthetic.main.receipts_enter_info.view.*
import kotlinx.android.synthetic.main.receipts_tab.*


class ReceiptsActivity : AppCompatActivity() {

    lateinit var adapter: ReceiptAdaptor
    private lateinit var viewModel: CostViewModel
    private var costItemList: ArrayList<Cost> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.receipts_tab)
        viewModel = ViewModelProvider(this).get(CostViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()
        var adapter = ReceiptAdaptor(costItemList)
        receiptsRecycler.adapter = adapter
        receiptsRecycler.layoutManager = LinearLayoutManager(this)
        receiptsRecycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        viewModel!!.getAllCosts()

        viewModel!!._costItemList.observe(this, Observer { costs ->
            // Update the cached copy of the words in the adapter.
            costItemList.clear()
            costItemList.addAll(costs)
            adapter.notifyDataSetChanged()
            receiptsRecycler!!.adapter?.notifyDataSetChanged()
        })


        addReceiptButton.setOnClickListener {
            dialogView()
        }
        menuButton.setOnClickListener {
            var main = Intent(this, MenuActivity::class.java)
            //main.putExtra("id", 1)
            startActivity(main)
        }
    }



    /**
     * Displays the dialog box asking the user for the item's info
     */
    private fun dialogView() {
        // Opens the dialog view asking the user for
        //println("fragment" + id)
        val dialogView = LayoutInflater.from(this!!).inflate(R.layout.receipts_enter_info, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Enter Total Food Costs")
        val mAlertDialog = mBuilder.show()

        // Sets an onclick listener on the dialog box button
        mAlertDialog.addReceiptInfoButton.setOnClickListener {
            val dateOfPurchase = dialogView.dateOfPurchase.text.toString()
            val costThatDay = dialogView.costThatDay.text.toString().toInt()

            //store food into Food
            // If the string is empty, we do not want to accept that as an input
            if(costThatDay.toString() != "" && dateOfPurchase != ""){
                val cost = Cost(dateOfPurchase,costThatDay)
                viewModel!!.insertCost(cost)
                mAlertDialog.dismiss()
            } else {
                val myToast = Toast.makeText(this, "Please enter valid values", Toast.LENGTH_SHORT)
                myToast.show()
            }
        }
    }


}
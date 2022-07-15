package com.example.expensetracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.database.expense.Expense
import com.example.expensetracker.databinding.ExpenseViewBinding


class ExpenseAdapter( private val onClick: (Expense) -> Unit , val onDelete: (Expense) -> Unit) : ListAdapter<Expense,ExpenseAdapter.ExpenseHolder>(
    DIFF
) {
    companion object{
        val DIFF = object:DiffUtil.ItemCallback<Expense>(){
            override fun areItemsTheSame(oldItem: Expense, newItem: Expense): Boolean {
                return oldItem.Id == newItem.Id
            }

            override fun areContentsTheSame(oldItem: Expense, newItem: Expense): Boolean {
                return oldItem == newItem
            }
        }
    }



    inner class ExpenseHolder(private val binding: ExpenseViewBinding): RecyclerView.ViewHolder(binding.root){
       fun bind(position: Int){
           val exp: Expense = getItem(position)
           binding.run {
               if(position == itemCount -1)
                   divider.visibility = View.INVISIBLE
               else{
                   divider.visibility = View.VISIBLE
               }
               expenseTitle.text = exp.expenseTitle
               expense.text = exp.expense.toString()
               `when`.text = exp.`when`
               category.text = exp.category
               root.setOnClickListener{
                   onClick(exp)
               }

//               deleteButton.setOnClickListener {
//                   onDelete(exp)
           }
       }


    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseHolder {
        val binding = ExpenseViewBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ExpenseHolder(binding)
    }


    override fun onBindViewHolder(holder: ExpenseHolder, position: Int) {
        holder.bind(position)
    }

    fun getItemAtPosition(position: Int):Expense{
        return getItem(position)
    }


}
package com.example.expensetracker.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.databinding.ItemCategoryBinding


class CategoryAdapter() :
    ListAdapter<String, CategoryAdapter.CategoryHolder>(
        DIFF
    ) {
    companion object {
        val DIFF = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }

    private var currentSelectedCategories: MutableList<String> = mutableListOf()

    fun setCurrentCategory(categories: List<String>) {
        currentSelectedCategories.clear()
        currentSelectedCategories.addAll(categories)
    }

    inner class CategoryHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.run {
                val category: String = getItem(position)
                itemText.isChecked = currentSelectedCategories.contains(category)

                itemText.text = category
                itemText.setOnClickListener {
                    if (!itemText.isChecked) {
                        removeSelection(position)
                        itemText.isChecked = false
                    } else {
                        addSelection(position)
                        itemText.isChecked = true
                    }

                }

            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.CategoryHolder {
        val binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryHolder(binding)
    }


    override fun onBindViewHolder(holder: CategoryAdapter.CategoryHolder, position: Int) {
        holder.bind(position)
    }

    fun addSelection(position: Int) {
        currentSelectedCategories.add(getItem(position))
        notifyItemChanged(position)
    }

    fun removeSelection(position: Int) {
        currentSelectedCategories.remove(getItem(position))
        notifyItemChanged(position)
    }

    fun removeAllSelection() {
        currentSelectedCategories.clear()
        notifyDataSetChanged()
    }

    fun getSelectedCategories(): List<String> {
        return currentSelectedCategories.distinct()
    }

    fun isItemSelected(item: String): Boolean {
        return currentSelectedCategories.contains(item)
    }
}
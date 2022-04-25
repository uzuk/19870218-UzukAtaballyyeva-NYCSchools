package com.example.nycschools

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.school_item_view.view.*

class SchoolsAdapter ( private val schoolsList: List<School>, private val itemSelector: ItemSelector<School>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.school_item_view, parent, false)
        return SchoolsItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rows = schoolsList
        val item = rows[position]
        (holder as SchoolsItemViewHolder).bindData(item, itemSelector)
    }

    override fun getItemCount(): Int {
        return schoolsList.size
    }

    internal class SchoolsItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(schoolData: School,  delegate: ItemSelector<School>) {
            itemView.schoolName.text = schoolData.school_name
            itemView.acaApart1.text = schoolData.academicopportunities1
            itemView.acaApart2.text = schoolData.academicopportunities2
            itemView.schoolWeb.text =  schoolData.website
            itemView.location.text = schoolData.location
            itemView.requirement1_1.text = schoolData.requirement1_1
            itemView.phoneNumber.text = schoolData.phone_number

            itemView.setOnClickListener {
                delegate.itemSelected(schoolData)
            }
        }
    }
}
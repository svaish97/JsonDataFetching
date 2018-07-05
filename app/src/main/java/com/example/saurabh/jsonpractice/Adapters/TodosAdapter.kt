package com.example.saurabh.jsonpractice.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.saurabh.jsonpractice.R
import kotlinx.android.synthetic.main.todos_view.view.*


data class TodosPojo(val userId:Int,val id:Int,val title:String,val completed: Boolean)

class TodosAdapter(val data:ArrayList<TodosPojo>):RecyclerView.Adapter<TodosAdapter.TodosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodosViewHolder {
        val li=parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val holder=li.inflate(R.layout.todos_view,parent,false)
        return TodosViewHolder(holder)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TodosViewHolder, position: Int) {

        holder.itemView.todosUserId.text=data[position].userId.toString()
        holder.itemView.todosId.text=data[position].id.toString()
        holder.itemView.todosTitle.text=data[position].title
        holder.itemView.todosCheckBox.isChecked=data[position].completed
    }

    class TodosViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

    }
}
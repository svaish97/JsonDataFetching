package com.example.saurabh.jsonpractice.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.saurabh.jsonpractice.R
import kotlinx.android.synthetic.main.post_view.view.*

data class PostsPojo(val userId:Int,val id:Int,val title:String)

class PostsAdapter(val data:ArrayList<PostsPojo>):RecyclerView.Adapter<PostsAdapter.PostsViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val li=parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val holder=li.inflate(R.layout.post_view,parent,false)
        return PostsViewHolder(holder)

    }

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.itemView.tvUserId.text=data[position].userId.toString()
        holder.itemView.tvId.text=data[position].id.toString()
        holder.itemView.tvTitle.text=data[position].title;

    }


    class PostsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {

    }
}
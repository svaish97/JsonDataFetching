package com.example.saurabh.jsonpractice.Adapters


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.saurabh.jsonpractice.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album_view.view.*


data class AlbumPojo(val albumId:Int,val id:Int,val title:String,val thumbUrl:String)



class AlbumAdapter(val data:ArrayList<AlbumPojo>):RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder{
        val li=parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val holder=li.inflate(R.layout.album_view,parent,false)
        return  AlbumViewHolder(holder)
    }

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: AlbumViewHolder ,position: Int) {
        holder.itemView.albumId.text = data[position].albumId.toString()
        holder.itemView.iD.text = data[position].id.toString()
        holder.itemView.titleId.text=data[position].title
        holder.itemView.thumbnailUrl.text=data[position].thumbUrl

        Picasso.get().load(data[position].thumbUrl).resize(150,250).into(holder.itemView.imageId)

    }
    class AlbumViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }

}
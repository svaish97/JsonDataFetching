package com.example.saurabh.jsonpractice

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.saurabh.jsonpractice.Adapters.AlbumAdapter
import com.example.saurabh.jsonpractice.Adapters.AlbumPojo
import com.example.saurabh.jsonpractice.Adapters.PostsAdapter
import com.example.saurabh.jsonpractice.Adapters.PostsPojo
import kotlinx.android.synthetic.main.activity_albums.*
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class Albums : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)
        val networkState=NetworkState()
        networkState.execute("https://jsonplaceholder.typicode.com/photos")
    }


  inner  class NetworkState:AsyncTask<String,Void,String>(){
        override fun doInBackground(vararg params: String?): String {
            val stringUrl=params[0];

            try {
                val url=URL(stringUrl)
                val httpConnection=url.openConnection() as HttpURLConnection
                val inputStream=httpConnection.inputStream
                val s=Scanner(inputStream)
                s.useDelimiter("\\A")
                if(s.hasNext())
                {
                    val str=s.next()
                    return str;
                }
            }
            catch (e: MalformedURLException)
            {
                Log.d("Error","HttpConnection error")
            }
            catch (e: IOException)
            {
                Log.d("Error","Input Stream Error")
            }

            return "Failed to load"
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            val albums=ArrayList<AlbumPojo>()
            try {
                val items= JSONArray(result)
                for(i in 0..items.length()-1)
                {
                    val item=items.getJSONObject(i)
                    val aId=item.getInt("albumId")
                    val id=item.getInt("id")
                    val title=item.getString("title")
                    val thumbUrl=item.getString("thumbnailUrl")

                    val albumadd= AlbumPojo(aId, id, title,thumbUrl)
                    albums.add(albumadd)
                }
            }
            catch (e: JSONException)
            {
                Log.e("Error","Exception in String passed in parameter")
            }

            val albumAdapter=AlbumAdapter(albums)
            rvAlbums.layoutManager=LinearLayoutManager(this@Albums)
            rvAlbums.adapter=albumAdapter
        }
    }
}

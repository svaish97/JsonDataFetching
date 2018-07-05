package com.example.saurabh.jsonpractice

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.saurabh.jsonpractice.Adapters.TodosAdapter
import com.example.saurabh.jsonpractice.Adapters.TodosPojo
import kotlinx.android.synthetic.main.activity_todos.*
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class Todos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todos)

        val networkState =NetworkState();
        networkState.execute("https://jsonplaceholder.typicode.com/todos")
    }

    inner class NetworkState:AsyncTask<String,Void,String>()
    {
        override fun doInBackground(vararg params: String?): String {
            var stringUrl=params[0]
            try {
                val url= URL(stringUrl)
                val httpconncetion=url.openConnection() as HttpURLConnection
                val inputStream = httpconncetion.inputStream
                val s=Scanner(inputStream)
                s.useDelimiter("\\A")
                if(s.hasNext())
                {
                    val str=s.next()
                    return  str
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

            val todos=ArrayList<TodosPojo>()
            try {
                val items=JSONArray(result)
                for(i in 0..items.length()-1)
                {
                    val item=items.getJSONObject(i)
                    val uId=item.getInt("userId")
                    val id=item.getInt("id")
                    val title=item.getString("title")
                    val checked=item.getBoolean("completed")
                    val todosadd= TodosPojo(uId, id, title,checked)
                    todos.add(todosadd)
                }
            }
            catch (e: JSONException)
            {
                Log.e("Error","Exception in String passed in parameter")
            }

            val todosAdapter= TodosAdapter(todos)

            rvTodos.layoutManager=LinearLayoutManager(this@Todos)
            rvTodos.adapter=todosAdapter

        }
    }




}

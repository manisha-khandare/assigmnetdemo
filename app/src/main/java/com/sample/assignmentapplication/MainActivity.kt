package com.sample.assignmentapplication

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.security.AccessController.getContext

class MainActivity : AppCompatActivity() {

    internal lateinit var adapter: RowsAdapter
    internal lateinit var rowssArray: java.util.ArrayList<RowData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getCategories()

        button.setOnClickListener(View.OnClickListener {
            getCategories()
        })
    }

    fun getCategories() {

        val stringRequest = @RequiresApi(Build.VERSION_CODES.N)
        object : StringRequest(
                Request.Method.GET, "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json",
                Response.Listener { ServerResponse ->
                    // Hiding the progress dialog after all task complete.
                    //   progressDialog.dismiss();

                    // toast(ServerResponse)
                    rowssArray = ArrayList()


                    if (!ServerResponse.contains("error")) {

                        try {

                            //val jsonArray = JSONArray(ServerResponse)
//creating json object

                            val json_contact: JSONObject = JSONObject(ServerResponse)
                            //creating json array
                            var title=json_contact.getString("title")
                            setActionBar(title)
                            var jsonarray_info:JSONArray= json_contact.getJSONArray("rows")
                            var i:Int = 0
                            var size:Int = jsonarray_info.length()

                            for (i in 0.. size-1) {
                            }
                                //now looping through all the elements of the json array
                            for (i in 0 until jsonarray_info.length()) {
                                //getting the json object of the particular index inside the array
                                val rowData = RowData()

                                val jsonObject = jsonarray_info.getJSONObject(i)
                                //   Log.d("Title : ", jsonObject.getString("Title"))
                                rowData.title= jsonObject.getString("title")
                                rowData.desc = jsonObject.getString("description")
                                rowData.image_path = jsonObject.getString("imageHref")

                                rowssArray.add(rowData)

                            }

                            setRecyclerViewAdapter()
                        }
                        catch (e: JSONException) {
                            e.printStackTrace()
                        }

                    } else {

                        //  Toast.makeText(getContext(), "ERROR " + ServerResponse, Toast.LENGTH_LONG).show();
                    }
                },
                Response.ErrorListener {
                    // Hiding the progress dialog after all task complete.
                    // progressDialog.dismiss();

                    // Showing error message if something goes wrong.
                    //  Toast.makeText(getContext(), "ERROR  " + volleyError.toString(), Toast.LENGTH_LONG).show();
                }) {

        }

        // Creating RequestQueue.
        val requestQueue = Volley.newRequestQueue(applicationContext)

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest)
    }

    private fun setRecyclerViewAdapter() {
        //  Toast.makeText(getContext(), "Size " + newsArray.size(), Toast.LENGTH_LONG).show();
        adapter = RowsAdapter(this, rowssArray)
        recyclerView.adapter = adapter
     /*   mShimmerViewContainer.stopShimmerAnimation()
        mShimmerViewContainer.visibility = View.GONE*/
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
    }


    fun setActionBar(title:String){
        val actionBar = supportActionBar
        actionBar!!.title = title
    }
}

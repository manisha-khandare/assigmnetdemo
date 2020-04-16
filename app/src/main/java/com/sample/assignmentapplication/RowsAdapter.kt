package com.sample.assignmentapplication

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class RowsAdapter(private val context: Context?, data: List<RowData>) : androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {

    private val inflater: LayoutInflater
    var data = emptyList<RowData>()
    var current: RowData? = null
    var currentPos = 0
    var activity: Activity? = null

    init {
        inflater = LayoutInflater.from(context)
        this.data = data
    }

    // Inflate the layout when viewholder created
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        val view = inflater.inflate(R.layout.item_country, parent, false)

        /* view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"position : "+getItemViewType(currentPos),Toast.LENGTH_LONG).show();
            }
        });*/
        return MyHolder(view)
    }

    override fun onBindViewHolder(viewHolder: androidx.recyclerview.widget.RecyclerView.ViewHolder, i: Int) {
        // Get current position of item in recyclerview to bind data and assign values from list
        val holder = viewHolder as MyHolder
        val current = data[i]
        //  Videoc videoc = getItem(position);

        holder.title.text = current.title
        holder.desc.text = current.desc


        // load image into imageview using glide

        Glide.with(context).load( current.image_path)
                    .into(holder.image);

        viewHolder.setIsRecyclable(true)

               return
    }

    override fun getItemCount(): Int {

        return data.size
    }


    private class MyHolder(v: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(v) {

        val title: TextView
        val desc: TextView
        val image: ImageView

        init {
            title = v.findViewById<View>(R.id.title) as TextView
            //  image = (ImageView) v.findViewById(R.id.thumbnail);
            desc = v.findViewById<View>(R.id.desc) as TextView
            image = v.findViewById<View>(R.id.imageView) as ImageView
        }
    }

    fun switchContent(id: Int, fragment: Fragment) {
        if (context == null)
            return
        if (context is MainActivity) {
            val mainActivity = context as MainActivity?
            val frag = fragment
            // mainActivity.switchContent(id, frag);
        }
    }

    /* private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            Fragment frag = new HomeFragment();
            frag.onDetach();
            ((MainActivity)context).getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack("a")
                    .commit();
            return true;
        }
        return false;
    }*/
}
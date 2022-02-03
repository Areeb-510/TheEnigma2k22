package com.example.theenigma.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.theenigma.R
import com.example.theenigma.data.User
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import de.hdodenhof.circleimageview.CircleImageView

class HomeScreenRecyclerViewAdapter(options: FirestoreRecyclerOptions<User>) :
    FirestoreRecyclerAdapter<User, HomeScreenRecyclerViewAdapter.HomeScreenViewHolder>(options) {


    public class HomeScreenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var image : CircleImageView = itemView.findViewById(R.id.image_view)
        var name : TextView = itemView.findViewById(R.id.name_view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeScreenViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_listitem, parent, false)

        return HomeScreenViewHolder(view)
    }


    override fun onBindViewHolder(holder: HomeScreenViewHolder, position: Int, model: User) {

        holder.name.text = model.displayName
        Glide.with(holder.image.context).load("${model.imageURL}").into(holder.image)
    }
}
package com.example.theenigma.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.theenigma.R
import com.example.theenigma.data.User
import com.example.theenigma.data.UserStats
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import de.hdodenhof.circleimageview.CircleImageView

class LeaderBoardsAdapter(options: FirestoreRecyclerOptions<UserStats>)
    : FirestoreRecyclerAdapter<UserStats,LeaderBoardsAdapter.LeaderBoardsViewHolder>(options) {


    public class LeaderBoardsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var image : CircleImageView = itemView.findViewById(R.id.leaderboards_image)
        var name : TextView = itemView.findViewById(R.id.leaderboards_name)
        var score : TextView = itemView.findViewById(R.id.leaderboards_score)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderBoardsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_leader_board, parent, false)

        return LeaderBoardsAdapter.LeaderBoardsViewHolder(view)
    }

    override fun onBindViewHolder(holder: LeaderBoardsViewHolder, position: Int, model: UserStats) {

        holder.name.text = model.username
        holder.score.text = model.score.toString()
        Glide.with(holder.image.context).load("${model.imageUrl}").into(holder.image);

    }
}
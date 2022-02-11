package com.example.theenigma.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.theenigma.R
import com.example.theenigma.data.ExeMembers
import com.example.theenigma.data.ExeTeamMembers
import com.example.theenigma.data.ExeTeamMembersItem
import de.hdodenhof.circleimageview.CircleImageView

class ExeMembersRecyclerViewAdapter :
    RecyclerView.Adapter<ExeMembersRecyclerViewAdapter.ExeMembersViewHolder>() {

    var members = ArrayList<ExeTeamMembersItem>()

    public class ExeMembersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image : ImageView = itemView.findViewById(R.id.member_photo)
        val name : TextView = itemView.findViewById(R.id.member_name)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExeMembersViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_member_card, parent, false)

        return ExeMembersViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExeMembersViewHolder, position: Int) {
        holder.name.text = members[position].name.toString()
        Glide.with(holder.image.context).load(members[position].image).into(holder.image)
    }

    fun updateList(current_members : ArrayList<ExeTeamMembersItem>){
        members.clear()
        members.addAll(current_members)

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return members.size
    }
}
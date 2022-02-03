package com.example.theenigma.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.theenigma.R
import com.example.theenigma.data.User
import com.example.theenigma.data.UserStatDao
import com.example.theenigma.data.UserStats
import com.example.theenigma.recycler_view.LeaderBoardsAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query


class LeaderBoardFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    private lateinit var adapter: LeaderBoardsAdapter
    private lateinit var userStat : UserStatDao
    private lateinit var leaderboard_text : TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leader_board, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerview = view.findViewById<RecyclerView>(R.id.recycler_view_leaders)

        recyclerview.layoutManager = LinearLayoutManager(activity)

        userStat = UserStatDao()

        val leaderboardsCollection = userStat.leaderboardCollection
        val query = leaderboardsCollection.orderBy("score", Query.Direction.DESCENDING)
        val recyclerViewOptions = FirestoreRecyclerOptions.Builder<UserStats>().setQuery(query, UserStats::class.java).build()

        adapter = LeaderBoardsAdapter(recyclerViewOptions)

        recyclerview.adapter = adapter

        leaderboard_text = view.findViewById(R.id.display)

        leaderboard_text.text = "LEADERBOARD"
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}
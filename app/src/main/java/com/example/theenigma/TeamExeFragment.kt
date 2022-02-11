package com.example.theenigma

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.theenigma.interfaces.MemberAPI
import com.example.theenigma.interfaces.MemberAPIRetrofitHelper
import com.example.theenigma.models.MemberViewModelFactory
import com.example.theenigma.models.MembersViewModel
import com.example.theenigma.recycler_view.ExeMembersRecyclerViewAdapter
import com.example.theenigma.repository.MemberRepository
import retrofit2.create


class TeamExeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_exe, container, false)
    }

    private lateinit var adapter : ExeMembersRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ExeMembersRecyclerViewAdapter()
        val recyclerView : RecyclerView = view.findViewById(R.id.final_year_list)

        val memberApi = MemberAPIRetrofitHelper.getInstance().create(MemberAPI::class.java)

        val repository = MemberRepository(memberApi)

        val viewModel = ViewModelProvider(this,MemberViewModelFactory(repository)).get(MembersViewModel::class.java)

        recyclerView.layoutManager = GridLayoutManager(activity,2)
        recyclerView.adapter = adapter

        viewModel.allMembers.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
        })

    }
}
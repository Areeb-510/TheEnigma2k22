package com.example.theenigma.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import com.example.theenigma.R


class SelectOptionFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_select_option, container, false)
    }
    private lateinit var getSession : CardView
    private lateinit var enigmabtn : CardView
    private lateinit var joinsession : CardView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSession = view.findViewById(R.id.getSession)
        enigmabtn = view.findViewById(R.id.TheEnigma)
        joinsession = view.findViewById(R.id.JoinSession)

        getSession.setOnClickListener {
            val action = SelectOptionFragmentDirections.actionSelectOptionFragmentToHomeScreenFragment()
            findNavController().navigate(action)
        }
        joinsession.setOnClickListener {
            val action = SelectOptionFragmentDirections.actionSelectOptionFragmentToJoinSessionFragment()
            findNavController().navigate(action)
        }
    }
}
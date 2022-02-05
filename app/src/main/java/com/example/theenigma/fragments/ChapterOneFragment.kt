package com.example.theenigma.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.theenigma.applications.QuestionApplication
import com.example.theenigma.R
import com.example.theenigma.data.QuestionDao
import com.example.theenigma.data.QuestionDataBase
import com.example.theenigma.models.ChapterOneViewModel
import com.example.theenigma.models.ChapterOneViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import info.hoang8f.widget.FButton


class ChapterOneFragment : Fragment() {

    private lateinit var dataBase : QuestionDao
    override fun onCreate(savedInstanceState: Bundle?) {

        dataBase = QuestionDataBase.getDatabase(requireActivity().application).getQuestionDao()
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    private lateinit var btmNavBar : BottomNavigationView
    lateinit var shine: View
    private lateinit var question_description : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chapter_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        question_description = view.findViewById(R.id.question_description)

//        lifecycleScope.launch {
//            val result = questionAPI.getQuestion()
//
//            if(result!=null){
//                question_description.text = result.body()?.get(0)?.description
//            }
//        }
        val repository = (activity?.application as QuestionApplication).questionRepository




        val chap_one_view_model = ViewModelProvider(this,ChapterOneViewModelFactory(repository)).get(ChapterOneViewModel::class.java)

        chap_one_view_model.questions.observe(viewLifecycleOwner, Observer {
            question_description.text = it.get(0).description
        })
//
//        lifecycleScope.launch {
//            question_description.text = dataBase.getSecificQuestion(1).toString()
//        }

        // MIGRATION LAGANI HAI !!!
        val user = FirebaseAuth.getInstance().currentUser
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("Leaderboards").document("${user?.displayName}")

        val submit_button = view.findViewById<FButton>(R.id.submit_button)
        submit_button.setOnClickListener {
            docRef
                .update("questions_solved", 98,"score",500)
                .addOnSuccessListener { Log.d("TAG", "DocumentSnapshot successfully updated!") }
                .addOnFailureListener { e -> Log.w("TAG", "Error updating document", e) }
        }
    }





}
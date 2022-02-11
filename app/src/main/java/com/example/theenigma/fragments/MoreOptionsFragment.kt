package com.example.theenigma.fragments

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.theenigma.R
import kotlinx.coroutines.launch


class MoreOptionsFragment : Fragment() {

    private lateinit var options_text : TextView

    private lateinit var ar_test_card : CardView


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
        return inflater.inflate(R.layout.fragment_more_options, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val options = view.findViewById<CardView>(R.id.team_exe)

        options_text = view.findViewById(R.id.display)
        ar_test_card = view.findViewById(R.id.AR_Test)

        val exeButton = view.findViewById<CardView>(R.id.team_exe)

        exeButton.setOnClickListener {
            val action = MoreOptionsFragmentDirections.actionMoreOptionsFragmentToTeamExeFragment()
            findNavController().navigate(action)
        }

        options_text.text = "MORE OPTIONS"

        options.setOnClickListener {
            val action = MoreOptionsFragmentDirections.actionMoreOptionsFragmentToTeamExeFragment()
            findNavController().navigate(action)
        }

        ar_test_card.setOnClickListener {
            lifecycleScope.launch {
                try {
                    // some code
                    ARView()
                } catch (e: Exception) {
                    // handler
                    Toast.makeText(activity,"$e", Toast.LENGTH_SHORT).show()
                    val builder = AlertDialog.Builder(activity)
                    //set title for alert dialog
                    builder.setTitle("Not Compatible")
                    //set message for alert dialog
                    builder.setMessage("Sorry but your phone don't look to be compatible with Augmented visualisations. Try using an even advanced phone.")
                    builder.setIcon(android.R.drawable.ic_dialog_alert)
                }
            }
        }
    }

    suspend fun ARView(){
        val sceneViewerIntent = Intent(Intent.ACTION_VIEW)
        val intentUri: Uri = Uri.parse("https://arvr.google.com/scene-viewer/1.0").buildUpon()
            .appendQueryParameter(
                "file",
                "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/BrainStem/glTF/BrainStem.gltf"
            )
            .appendQueryParameter("mode", "ar_only")

            .appendQueryParameter("resizable","false")
            .build()
        sceneViewerIntent.data = intentUri
        sceneViewerIntent.setPackage("com.google.ar.core")
        startActivity(sceneViewerIntent)


    }
}
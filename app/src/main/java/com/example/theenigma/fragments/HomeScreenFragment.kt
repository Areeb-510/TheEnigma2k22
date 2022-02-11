package com.example.theenigma.fragments

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.graphics.drawable.ColorDrawable
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import android.os.VibrationEffect

import android.os.Build

import android.os.Vibrator
import android.text.Html
import android.util.Log
import android.widget.GridLayout.HORIZONTAL
import android.widget.LinearLayout.HORIZONTAL
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.constraintlayout.solver.widgets.ConstraintWidget.HORIZONTAL
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.bumptech.glide.Glide
import com.example.theenigma.R
import com.example.theenigma.activities.MainActivity2
import com.example.theenigma.data.QuestionDao
import com.example.theenigma.data.User
import com.example.theenigma.data.UserDao
import com.example.theenigma.data.UserStats
import com.example.theenigma.models.HomeViewModel
import com.example.theenigma.models.HomeViewModelFactory
import com.example.theenigma.myList
import com.example.theenigma.recycler_view.HomeScreenRecyclerViewAdapter
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import de.hdodenhof.circleimageview.CircleImageView


class HomeScreenFragment : Fragment() {

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
        val view = activity?.findViewById<View>(android.R.id.content)

        if (view != null && MainActivity2.checker == false) {
            MainActivity2.checker = true
            val snackbar = Snackbar.make(view,
                Html.fromHtml("<strong>Welcome to The Enigma</strong>"),Snackbar.LENGTH_LONG)

            val snackBarView: View = snackbar.getView()

            snackBarView.translationY = (-200).toFloat()
            snackBarView.setBackgroundColor(resources.getColor(R.color.app_blue))
            snackbar.show()
        }

        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }


    private lateinit var AR_Button : CardView
    // FusedLocationProviderClient - Main class for receiving location updates.
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    lateinit var latitude : String
    lateinit var longitude : String
    private lateinit var location : Location
    private lateinit var targetlocation : Location
    private lateinit var prog_bar : ProgressBar
    private lateinit var prog_text : TextView
    private var prog = 91
    private lateinit var chapter1 : CardView
    private lateinit var chapter2 : CardView
    private lateinit var chapter3 : CardView
    private lateinit var chapter4 : CardView


    private lateinit var adapter : HomeScreenRecyclerViewAdapter

    private lateinit var homeViewModel : HomeViewModel
    private lateinit var userDao : UserDao


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

//        Toast.makeText(activity,"${user?.displayName}",Toast.LENGTH_SHORT).show()
//
//        Toast.makeText(activity,"${user?.photoUrl}",Toast.LENGTH_SHORT).show()

        Log.v("Photo","${user?.photoUrl}")

        val view_line = view.findViewById<View>(R.id.line)
        chapter1 = view.findViewById(R.id.chapter_one)
        chapter2 = view.findViewById(R.id.chapter_two)
        chapter3 = view.findViewById(R.id.chapter_three)
        chapter4 = view.findViewById(R.id.chapter_four)
//        val card_back_view = view.findViewById<CardView>(R.id.card)

        homeViewModel = ViewModelProvider(this,HomeViewModelFactory(0)).get(HomeViewModel::class.java)

        val db = FirebaseFirestore.getInstance()

        val docRef = db.collection("Leaderboards").document("${user?.displayName}")

        docRef.get().addOnSuccessListener { documentSnapshot ->
            val city = documentSnapshot.toObject<UserStats>()
            Log.d("Progress",city?.questions_solved.toString())
            prog_bar.progress = city?.questions_solved!!
            prog_text.text = "${city?.questions_solved.toString()}%\nProgress"
        }
//        homeViewModel.updateProgress()

        val animationDrawable : AnimationDrawable = view_line.background as AnimationDrawable
//        animationDrawable.setEnterFadeDuration(5000)
        animationDrawable.setExitFadeDuration(1000)
        animationDrawable.start()

        chapter1.setOnClickListener {
            val action = HomeScreenFragmentDirections.actionHomeScreenFragment2ToChapterOneFragment()
            findNavController().navigate(action)
        }

        chapter2.setOnClickListener {
            val action = HomeScreenFragmentDirections.actionHomeScreenFragment2ToChapterTwoFragment()
            findNavController().navigate(action)
        }

        chapter3.setOnClickListener {
            val action = HomeScreenFragmentDirections.actionHomeScreenFragment2ToChapterThreeFragment()
            findNavController().navigate(action)
        }

        chapter4.setOnClickListener {
            val action = HomeScreenFragmentDirections.actionHomeScreenFragment2ToChapterFourFragment()
            findNavController().navigate(action)
        }

        val mAuth = FirebaseAuth.getInstance()
        val my_image = view.findViewById<CircleImageView>(R.id.my_image)

        Glide.with(this).load(mAuth.currentUser?.photoUrl.toString()).into(my_image)


        val recyclerview = view.findViewById<RecyclerView>(R.id.recycler_view_players)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)


        userDao = UserDao()
        val userCollection = userDao.userCollection
        val query = userCollection.orderBy("displayName")
        val recyclerViewOptions = FirestoreRecyclerOptions.Builder<User>().setQuery(query,User::class.java).build()

        adapter = HomeScreenRecyclerViewAdapter(recyclerViewOptions)

        recyclerview.adapter = adapter

        AR_Button = view.findViewById(R.id.ARButton)
        prog_bar = view.findViewById(R.id.progress_bar)
        prog_text = view.findViewById(R.id.text_progress)


//        prog_bar.progress = homeViewModel.prog.value!!
//        prog_text.text = "${homeViewModel.prog.value}%\nProgress"


        fusedLocationProviderClient = activity?.let {
            LocationServices.getFusedLocationProviderClient(
                it
            )
        }!!



        targetlocation = Location(LocationManager.GPS_PROVIDER)
        targetlocation.latitude = 30.5617084
        targetlocation.longitude = 77.2927094


        AR_Button.setOnClickListener {
            lifecycleScope.launch {

                try {
                    // some code
                    ARView()
                } catch (e: Exception) {
                    // handler
                    Toast.makeText(activity,"$e",Toast.LENGTH_SHORT).show()
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

    fun updateProgress(){

    }


    // Location("26.8896512","80.9703636")
    @RequiresApi(Build.VERSION_CODES.DONUT)
    suspend fun ARView(){
        val sceneViewerIntent = Intent(Intent.ACTION_VIEW)
        val intentUri: Uri = Uri.parse("https://arvr.google.com/scene-viewer/1.0").buildUpon()
            .appendQueryParameter(
                "file",
                "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/CesiumMan/glTF/CesiumMan.gltf"
            )
            .appendQueryParameter("mode", "ar_only")

            .appendQueryParameter("resizable","false")
            .build()
        sceneViewerIntent.data = intentUri
        sceneViewerIntent.setPackage("com.google.ar.core")
        startActivity(sceneViewerIntent)

//        if(location.checkIsInBound(100.0, targetlocation)){
//            vibratePhone()
//            val sceneViewerIntent = Intent(Intent.ACTION_VIEW)
//            val intentUri: Uri = Uri.parse("https://arvr.google.com/scene-viewer/1.0").buildUpon()
//                .appendQueryParameter(
//                    "file",
//                    "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/BrainStem/glTF/BrainStem.gltf"
//                )
//                .appendQueryParameter("mode", "ar_only")
//
//                .appendQueryParameter("resizable","false")
//                .build()
//            sceneViewerIntent.data = intentUri
//            sceneViewerIntent.setPackage("com.google.ar.core")
//            startActivity(sceneViewerIntent)
//        }else{
//            Toast.makeText(activity,"No clue in this region",Toast.LENGTH_SHORT).show()
//            Toast.makeText(activity,"$latitude $longitude",Toast.LENGTH_SHORT).show()
//        }

    }

    fun vibratePhone() {
        val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(1000)
        }
    }

    fun Location.checkIsInBound(radius: Double,center:Location):Boolean
            =  this.distanceTo(center)<radius

    fun getLocation(){

        if (activity?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED && activity?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        fusedLocationProviderClient.lastLocation.addOnCompleteListener {
            location = it.getResult()

            latitude = location.latitude.toString()
            longitude = location.longitude.toString()
//            Toast.makeText(activity,"$latitude $longitude",Toast.LENGTH_SHORT).show()

            if(location.checkIsInBound(100.0, targetlocation)){
                vibratePhone()
            }
        }

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
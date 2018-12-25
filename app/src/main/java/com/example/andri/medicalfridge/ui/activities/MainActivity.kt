package com.example.andri.medicalfridge.ui.activities


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.andri.medicalfridge.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar_actionbar)
        setSupportActionBar(toolbar)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        mNavController = findNavController(R.id.nav_host_fragment)
    }

    override fun onSupportNavigateUp() = Navigation.findNavController(this, R.id.navigation_graph).navigateUp()
}

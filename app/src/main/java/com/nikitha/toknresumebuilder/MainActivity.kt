package com.nikitha.toknresumebuilder

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import com.nikitha.toknresumebuilder.databinding.ActivityMainBinding
import android.view.MotionEvent

import android.view.View.OnTouchListener
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.webkit.WebViewAssetLoader
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewClientCompat
import np.com.susanthapa.curved_bottom_navigation.CbnMenuItem


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var appBarConfiguration: AppBarConfiguration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding  = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //Displays the back arrow button
        val colorDrawable = ColorDrawable(Color.TRANSPARENT)
        supportActionBar?.setBackgroundDrawable(colorDrawable)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        title = "Welcome"
        setSupportActionBar(binding.toolbar)

        val menuItems = arrayOf(
            CbnMenuItem(R.drawable.ic_tutorial, R.drawable.avd_blog, R.id.tutorialFragment2),
            CbnMenuItem(R.drawable.ic_home, R.drawable.avd_home, R.id.homeFragment2),
            CbnMenuItem(R.drawable.ic_profile, R.drawable.avd_person, R.id.profileFragment2),
        )
        binding.navView.setMenuItems(menuItems, 1)


        val navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration.Builder(R.id.homeFragment2).build()
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration!!)
        binding.navView.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main)
        return NavigationUI.navigateUp(navController, appBarConfiguration!!) || super.onSupportNavigateUp()
    }

}
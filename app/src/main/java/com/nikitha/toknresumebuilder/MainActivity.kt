package com.nikitha.toknresumebuilder

import android.content.Intent
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
import androidx.webkit.WebViewAssetLoader
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewClientCompat


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding  = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setTitle("Sections")

        //Displays the back arrow button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

       binding.tvPerSection.setOnClickListener {
           val intent = Intent(this, HolderActivity::class.java)
           startActivity(intent)
       }

    }

    //For the back arrow to work
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    //For the view resume menu option
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //Handle the menu click
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       when(item.itemId)
       {
           R.id.view_icon -> Toast.makeText(this, "View resume clicked", Toast.LENGTH_SHORT).show()
       }

        return super.onOptionsItemSelected(item)
    }




}
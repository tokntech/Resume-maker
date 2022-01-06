package com.nikitha.toknresumebuilder

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.nikitha.toknresumebuilder.databinding.ActivityHolderBinding


class HolderActivity : AppCompatActivity()  {
    private lateinit var binding: ActivityHolderBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHolderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val colorDrawable = ColorDrawable(Color.TRANSPARENT)
        supportActionBar?.setBackgroundDrawable(colorDrawable)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.back_arrow)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        title = "Sections"

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment_holder)
        appBarConfiguration = AppBarConfiguration.Builder(R.id.sectionsFragment).build()
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.view_tips, menu)
        return super.onCreateOptionsMenu(menu)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
         R.id.preview -> {

         }
           /*android.R.id.home -> {
                onBackPressed()
                finish()
                return true
            }*/
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment_holder)
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp()
    }


}
package com.nikitha.toknresumebuilder

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.nikitha.toknresumebuilder.databinding.ActivityHolderBinding
import com.nikitha.toknresumebuilder.fragments.PreviewFragment


class HolderActivity : AppCompatActivity()  {
    private lateinit var binding: ActivityHolderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHolderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Sections"

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
         R.id.view_icon -> {
             val fragment = PreviewFragment()
             this.supportFragmentManager.beginTransaction()
                 .replace(R.id.fragmentContainerView, fragment).commit()
         }
            android.R.id.home -> {
                onBackPressed()
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
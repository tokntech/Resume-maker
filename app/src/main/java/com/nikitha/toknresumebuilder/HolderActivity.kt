package com.nikitha.toknresumebuilder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.nikitha.toknresumebuilder.databinding.ActivityHolderBinding
import com.nikitha.toknresumebuilder.fragments.RearrangeFragment


class HolderActivity : AppCompatActivity()  {
    private lateinit var binding: ActivityHolderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHolderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Sections"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options, menu)
        return super.onCreateOptionsMenu(menu)
    }


}
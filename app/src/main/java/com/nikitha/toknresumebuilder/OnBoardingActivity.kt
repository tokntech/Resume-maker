package com.nikitha.toknresumebuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.nikitha.toknresumebuilder.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPagerAdapter = ViewPagerAdapter()
        binding.viewPagerStartup.adapter = viewPagerAdapter

        binding.btnGetStarted.setOnClickListener {
            val intent = Intent(this, HolderActivity::class.java)
            startActivity(intent)
        }

    }

    private class ViewPagerAdapter : PagerAdapter() {
        var resources = intArrayOf(
            R.drawable.onboarding1,
            R.drawable.onboarding2,
            R.drawable.onbaording3
        )

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = LayoutInflater.from(container.context)
                .inflate(R.layout.startup_view_pager_final_item, container, false)
            container.addView(view)
            val imageView = view.findViewById<ImageView>(R.id.iv_startup)
            imageView.setImageResource(resources[position])
            return view

        }

        override fun getCount(): Int {
            return resources.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }


        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)

        }
    }
}
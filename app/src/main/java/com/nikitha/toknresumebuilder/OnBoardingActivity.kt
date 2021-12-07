package com.nikitha.toknresumebuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.nikitha.toknresumebuilder.databinding.ActivityOnBoardingBinding
import java.util.ArrayList

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding
    private var mIntroItems : ArrayList<String> = ArrayList()
    private  var mIntroSubItems : ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mIntroItems.addAll(this.resources.getStringArray(R.array.onboarding_text))
        mIntroSubItems.addAll(this.resources.getStringArray(R.array.onboarding_subtext))

        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPagerAdapter = ViewPagerAdapter()
        binding.viewPagerStartup.adapter = viewPagerAdapter

        binding.btnGetStarted.setOnClickListener {
            val intent = Intent(this, HolderActivity::class.java)
            startActivity(intent)
        }


        binding.viewPagerStartup.addOnPageChangeListener(object : OnPageChangeListener
        {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                     binding.tvIntroText.text = mIntroItems[position]
                     binding.tvIntroText2.text = mIntroSubItems[position]

                if(position == 1 || position == 0)
                    binding.btnSkip.visibility = View.VISIBLE
                else
                    binding.btnSkip.visibility = View.INVISIBLE

                if(position == 2)
                    binding.btnGetStarted.text = "Build my resume"
                else
                    binding.btnGetStarted.text = "Get started"
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })


    }

    private class ViewPagerAdapter : PagerAdapter() {


        var onboardingResources = intArrayOf(
            R.drawable.onboarding1,
            R.drawable.onboarding2,
            R.drawable.onboarding3
        )

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = LayoutInflater.from(container.context)
                .inflate(R.layout.startup_view_pager_final_item, container, false)
            container.addView(view)
            val imageView = view.findViewById<ImageView>(R.id.iv_startup)

            if(position ==2)
            {
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
                ).apply {
                    gravity = Gravity.END
                }
                imageView.layoutParams = params
            }

            imageView.setImageResource(onboardingResources[position])


            return view

        }

        override fun getCount(): Int {
            return onboardingResources.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }


        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)

        }
    }
}


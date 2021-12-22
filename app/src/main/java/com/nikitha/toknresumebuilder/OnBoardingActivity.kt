package com.nikitha.toknresumebuilder

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.nikitha.toknresumebuilder.databinding.ActivityOnBoardingBinding
import java.util.ArrayList
import kotlin.math.roundToInt

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding
    private var mIntroItems : ArrayList<String> = ArrayList()
    private  var mIntroSubItems : ArrayList<String> = ArrayList()
    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mIntroItems.addAll(this.resources.getStringArray(R.array.onboarding_text))
        mIntroSubItems.addAll(this.resources.getStringArray(R.array.onboarding_subtext))

        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

       binding.btnContinue.setOnClickListener {
           val params1: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
               dpToJava(40, binding.root.context),
               dpToJava(8, binding.root.context))

           params1.setMargins(4, 0, 4, 0)

           val params2: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
               dpToJava(8, binding.root.context),
               dpToJava(8, binding.root.context)
           )
           params2.setMargins(4, 0, 4, 0)

           if(counter == 0)
           {

               binding.onboardingPic.setImageDrawable(
                   ContextCompat.getDrawable(
                       this,
                       R.drawable.onboarding2))

               binding.tvIntroText.text = "Customize to your liking!"
               binding.tvIntroText2.text = "Add sections, modify the order and save the progress "

               binding.view2.layoutParams = params1
               binding.view2.setRotation(180F)
               binding.view1.layoutParams = params2

               binding.view1.setBackgroundResource(R.drawable.gradient_dot)
               binding.view2.setBackgroundResource(R.drawable.gradient_button)
               binding.view3.setBackgroundResource(R.drawable.gradient_dot)

               counter++
           }
           else if(counter == 1)
           {
               binding.onboardingPic.setImageDrawable(
                   ContextCompat.getDrawable(
                       this,
                       R.drawable.onboarding3
                   )
               )
               binding.tvIntroText.text = "Share easily to get the job!"
               binding.tvIntroText2.text = "Build the best version of your resume and apply"

               binding.view3.layoutParams = params1
               binding.view3.setRotation(180F)
               binding.view2.layoutParams = params2

               binding.view1.setBackgroundResource(R.drawable.gradient_dot)
               binding.view2.setBackgroundResource(R.drawable.gradient_dot)
               binding.view3.setBackgroundResource(R.drawable.gradient_button)

               val params = LinearLayout.LayoutParams(
                   LinearLayout.LayoutParams.MATCH_PARENT,
                   LinearLayout.LayoutParams.WRAP_CONTENT
               ).apply {
                   gravity = Gravity.END
               }
               binding.onboardingPic.layoutParams = params

               binding.btnContinue.text = "Build my resume"
               counter++
           }
           else
           {
               val intent = Intent(this, HolderActivity::class.java)
               startActivity(intent)
           }
       }

    }

    private fun dpToJava(dp: Int, context: Context): Int {
        return (dp * context.resources.displayMetrics.density).roundToInt()
    }
}


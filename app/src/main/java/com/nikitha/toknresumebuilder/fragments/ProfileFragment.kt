package com.nikitha.toknresumebuilder.fragments

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.nikitha.toknresumebuilder.BuildConfig
import com.nikitha.toknresumebuilder.R
import com.nikitha.toknresumebuilder.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val colorDrawable = ColorDrawable(Color.TRANSPARENT)
        (activity as? AppCompatActivity)?.supportActionBar?.setBackgroundDrawable(colorDrawable)
        (activity as? AppCompatActivity)?.supportActionBar?.setHomeAsUpIndicator(R.drawable.back_arrow)
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as? AppCompatActivity)?.supportActionBar?.setHomeButtonEnabled(true)

        binding.tvFeedback.setOnClickListener {
            val recipients = arrayOf("support@tokntech.com")

            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:")

            intent.putExtra(Intent.EXTRA_EMAIL, recipients)

            if (intent.resolveActivity(requireActivity().packageManager) != null) startActivity(
                intent
            )

        }

        binding.tvShareApp.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Hey check out my app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID
            )
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }
        binding.tvRateApp.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=PackageName")
                )
            )
        }
    }

}
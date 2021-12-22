package com.nikitha.toknresumebuilder.fragments

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.style.AbsoluteSizeSpan
import android.view.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.text.set
import androidx.core.text.toSpannable
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.nikitha.toknresumebuilder.R
import com.nikitha.toknresumebuilder.commonactivities.MonthYearPickerDialog
import com.nikitha.toknresumebuilder.databinding.FragmentPersonalDetailsBinding
import com.nikitha.toknresumebuilder.model.PersonalDetails
import java.io.File
import kotlin.concurrent.fixedRateTimer


class PersonalDetailsFragment : Fragment() {
    private lateinit var binding: FragmentPersonalDetailsBinding
    private lateinit var resultLauncherCamera: ActivityResultLauncher<Intent>
    private lateinit var resultLauncherGallery: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPersonalDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = "Sections"

        val colorDrawable = ColorDrawable(Color.TRANSPARENT)
        (activity as? AppCompatActivity)?.supportActionBar?.setBackgroundDrawable(colorDrawable)
        (activity as? AppCompatActivity)?.supportActionBar?.setHomeAsUpIndicator(R.drawable.back_arrow)
        //(activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val btntext = ("Save \n& proceed").toSpannable()
        btntext[5..15] = AbsoluteSizeSpan(10 , true)
        binding.btnSave.text = btntext

        binding.ivProfilePic.setOnClickListener{

            showPictureDialog()

        }


        binding.etPerDetLayout.setEndIconOnClickListener {
            Toast.makeText(context, "Blog clicked", Toast.LENGTH_SHORT).show()
        }

        binding.etLinkedInUrlLayout.setEndIconOnClickListener {
            val customDialog = Dialog(requireActivity())
            customDialog.setContentView(R.layout.dialog_linkedin_url)
           // customDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            customDialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            val yesBtn = customDialog.findViewById(R.id.textView) as TextView
            val noBtn = customDialog.findViewById(R.id.tvOk) as TextView
            yesBtn.setOnClickListener {
                //Do something here
                customDialog.dismiss()
            }
            noBtn.setOnClickListener {
                customDialog.dismiss()
            }
            customDialog.show()
            customDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        }

        binding.btnSave.setOnClickListener{
           //NavHostFragment.findNavController(this).navigate(R.id.action_personalDetailsFragment_to_educationDetailsFragment)
        }

        resultLauncherCamera = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {
            if(it.resultCode == Activity.RESULT_OK)
            {
                val takenPhoto = BitmapFactory.decodeFile(filePhoto.absolutePath)
                binding.ivProfilePic.setImageBitmap(takenPhoto)
            }
        }

        resultLauncherGallery = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {
            if(it.resultCode == Activity.RESULT_OK)
            {
                binding.ivProfilePic.setImageURI(it.data?.data)
            }
        }


        binding.tvAddSection.setOnClickListener {

            val customDialog = Dialog(requireActivity())
            customDialog.setContentView(R.layout.dialog_add_section)
            // customDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            customDialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            val TextBtn = customDialog.findViewById(R.id.btnAddText) as Button
            val LinkBtn = customDialog.findViewById(R.id.btnAddLink) as Button
            TextBtn.setOnClickListener {
                //Do something here
                customDialog.dismiss()
            }
            LinkBtn.setOnClickListener {
                customDialog.dismiss()
            }
            customDialog.show()
            customDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        }

        binding.btnPrevious.setOnClickListener {
        }


    }

    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(context)
        pictureDialog.setTitle("Change Profile Photo")
        val pictureDialogItems = arrayOf("Take Photo", "Choose Photo")
        pictureDialog.setItems(pictureDialogItems
        ) { _, which ->
            when (which) {
                1 -> chooseImageGallery()
                0 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }

    private fun takePhotoFromCamera() {
        val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        filePhoto = getPhotoFile(FILE_NAME)
        val providerFile = context?.let { FileProvider.getUriForFile(it,"com.nikitha.toknresumebuilder.fileprovider", filePhoto) }
        takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, providerFile)

       if (activity?.packageManager?.let { it1 -> takePhotoIntent.resolveActivity(it1) } != null){
            resultLauncherCamera.launch(takePhotoIntent)
        }else {
            Toast.makeText(context,"Camera could not be opened", Toast.LENGTH_SHORT).show()
        }
    }

    private fun chooseImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        resultLauncherGallery.launch(intent)
    }

    private fun getPhotoFile(fileName: String): File {
        val directoryStorage = activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        return File.createTempFile(fileName, ".jpg", directoryStorage)
    }
}
private lateinit var filePhoto: File
private const val FILE_NAME = "photo.jpg"
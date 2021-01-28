package com.example.profile_upload_page

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity() {
    var image: CircleImageView? = null
    var edit: EditText? = null
    
    var uri: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        image = findViewById(R.id.Profile_image)
        edit = findViewById(R.id.your_name)
        image!!.setOnClickListener(View.OnClickListener {

            CropImage.startPickImageActivity(this@MainActivity) })
    }

    @SuppressLint("NewApi")

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {


        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE || requestCode == RESULT_OK) {
            val imageuri = CropImage.getPickImageResultUri(this, data)
            if (CropImage.isReadExternalStoragePermissionsRequired(this, imageuri)) {
                uri = imageuri
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE
                ), 0)

            } else {

                startcrop(imageuri)

            }
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            val result = CropImage.getActivityResult(data)
            image!!.setImageURI(result.uri)

        }
    }

    private fun startcrop(imageuri: Uri) {

        CropImage.activity(imageuri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(this)

    }
}
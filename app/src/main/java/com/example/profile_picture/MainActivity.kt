package com.example.profile_picture

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.theartofdev.edmodo.cropper.CropImage
import de.hdodenhof.circleimageview.CircleImageView
import kotlin.jvm.internal.Intrinsics
import kotlin.Any as Any1

class MainActivity : AppCompatActivity() {


        val cropActivityResultContract = object : ActivityResultContract<kotlin.Any?,Uri?>(){

            override fun createIntent(context: Context, input: kotlin.Any?): Intent {
               return CropImage.activity()
                       .setAspectRatio(15,15)
                       .getIntent(this@MainActivity);
            }

            override fun parseResult(resultCode: Int, intent: Intent?): Uri? {

                return CropImage.getActivityResult(intent)?.uri
            }
        }
    private lateinit var cropActivityResultLauncher: ActivityResultLauncher<kotlin.Any?>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       val image = findViewById<CircleImageView>(R.id.image)
        val btnchooseimage = findViewById<Button>(R.id.btn)

        cropActivityResultLauncher = registerForActivityResult(cropActivityResultContract){

            it?.let { uri ->
                image.setImageURI(uri)
            }
            btnchooseimage.setOnClickListener(){
                cropActivityResultLauncher.launch(null)
            }

        }

    }
}
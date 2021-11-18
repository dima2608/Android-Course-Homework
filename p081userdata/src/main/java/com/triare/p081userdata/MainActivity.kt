package com.triare.p081userdata


import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.ACTION_IMAGE_CAPTURE
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.triare.p081userdata.Country.Companion.KEY_COUNTRY
import java.lang.Error

class MainActivity : AppCompatActivity() {

    @SuppressLint("UseCompatLoadingForDrawables")
    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    { result: ActivityResult ->
        when(result.resultCode) {

            REQUEST_USER_PIC -> {
                if (result.data?.data == null) {
                    val imageBitmap = result.data?.extras?.get("data") as? Bitmap
                    findViewById<ImageView>(R.id.prof_pic).setImageBitmap(imageBitmap)
                } else {
                    findViewById<ImageView>(R.id.prof_pic).setImageURI(result.data?.data)
                }
            }

            GetUserName.REQUEST_USER_NAME -> {
                findViewById<TextView>(R.id.user_name).text = result.data?.getStringExtra("UserName")
            }

            Country.REQUEST_GET_COUNTRY -> {
                val country = result.data?.getParcelableExtra<Countries>(KEY_COUNTRY)
                if (country != null) {
                    findViewById<TextView>(R.id.select_country).text = country.name
                    findViewById<ImageView>(R.id.country_flag).setImageResource(country.flag)
                }

            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnChangeProfPic = findViewById<ImageButton>(R.id.btn_change_prof_pic)
        val btnProfPic = findViewById<ImageView>(R.id.prof_pic)
        val btnEnterUserName = findViewById<View>(R.id.btn_enter_user_name)
        val btnChooseCountry = findViewById<View>(R.id.btn_select_country)

        btnChangeProfPic.setOnClickListener {
            popupMenu(btnChangeProfPic)
        }
        btnProfPic.setOnClickListener {
            popupMenu(btnProfPic)
        }
        btnEnterUserName.setOnClickListener {
            try {
                val getUserNameIntent = Intent(this, GetUserName::class.java)
                startForResult.launch(getUserNameIntent)
            }catch (err: Error){
                Toast.makeText(this, "getUserNameIntent Error", Toast.LENGTH_SHORT).show()
            }
        }
        btnChooseCountry.setOnClickListener {
            val getUserCountry = Intent(this, Country::class.java)
            startForResult.launch(getUserCountry)
        }
    }



    private fun popupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.inflate(R.menu.popup_menu)
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.take_a_photo -> {
                    val takePictureIntent = Intent(ACTION_IMAGE_CAPTURE)
                    startForResult.launch(takePictureIntent)
                }
                R.id.open_gallery -> {
                    val picPictureIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startForResult.launch(picPictureIntent)
                }

            }
            false
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            popupMenu.setForceShowIcon(true)
        }
        popupMenu.show()
    }

    companion object  {
        const val REQUEST_PIC_FROM_GALLERY = 2
        const val REQUEST_USER_PIC = -1
    }
}
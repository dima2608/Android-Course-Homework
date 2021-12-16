package com.triare.p081userdata

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
import com.triare.p081userdata.CountryActivity.Companion.KEY_COUNTRY
import com.triare.p081userdata.GetUserNameActivity.Companion.KEY_USER_NAME
import java.lang.Error

class MainActivity : AppCompatActivity() {

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    { result: ActivityResult ->
        when(result.resultCode) {
            REQUEST_USER_PIC -> {
                setImage(result)
            }
            GetUserNameActivity.REQUEST_USER_NAME -> {
                findViewById<TextView>(R.id.user_name).text = result.data?.getStringExtra(KEY_USER_NAME)
            }
            CountryActivity.REQUEST_GET_COUNTRY -> {
                setCountry(result)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
    }

    private fun popupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.inflate(R.menu.popup_menu)
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.take_a_photo -> {
                    takePictureIntent()
                }
                R.id.open_gallery -> {
                    picPictureIntent()
                }
            }
            false
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            popupMenu.setForceShowIcon(true)
        }
        popupMenu.show()
    }

    private fun initUi() {
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
                val getUserNameIntent = Intent(this, GetUserNameActivity::class.java)
                startForResult.launch(getUserNameIntent)
            }catch (err: Error){
                Toast.makeText(this, "getUserNameIntent Error", Toast.LENGTH_SHORT).show()
            }
        }
        btnChooseCountry.setOnClickListener {
            val getUserCountry = Intent(this, CountryActivity::class.java)
            startForResult.launch(getUserCountry)
        }
    }

    private fun setCountry(result: ActivityResult) {
        val country = result.data?.getParcelableExtra<Countries>(KEY_COUNTRY)
        if (country != null) {
            findViewById<TextView>(R.id.select_country).text = country.name
            findViewById<ImageView>(R.id.country_flag).setImageResource(country.flag)
        }
    }

    private fun setImage(result: ActivityResult) {
        if (result.data?.data == null) {
            val imageBitmap = result.data?.extras?.get("data") as? Bitmap
            findViewById<ImageView>(R.id.prof_pic).setImageBitmap(imageBitmap)
        } else {
            findViewById<ImageView>(R.id.prof_pic).setImageURI(result.data?.data)
        }
    }

    private fun picPictureIntent() {
        val takePictureIntent = Intent(ACTION_IMAGE_CAPTURE)
        startForResult.launch(takePictureIntent)
    }

    private fun takePictureIntent() {
        val picPictureIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startForResult.launch(picPictureIntent)
    }


    companion object  {
        const val REQUEST_USER_PIC = -1
    }
}
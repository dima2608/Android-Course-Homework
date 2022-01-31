package com.triare.p151notepadapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.triare.p151notepadapp.R
import com.triare.p151notepadapp.ui.content.ContentFragment
import com.triare.p151notepadapp.ui.models.MainViewModel
import com.triare.p151notepadapp.ui.note.NoteFragment

class MainActivity : AppCompatActivity() {

    //private var bntAddNote: FloatingActionButton? = null
    private var mainViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
    }

    private fun initUi() {
        startContentFragment()
        initViewModel()
        //initAddNoteBtn()
    }

    private fun initViewModel() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun startContentFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, ContentFragment.newInstance())
            .addToBackStack("text")
            .commit()
    }
/*
    private fun initAddNoteBtn() {

        bntAddNote = findViewById(R.id.btn_add_note)
        bntAddNote?.setOnClickListener {
            mainViewModel?.createContent()
            mainViewModel?.getCreatedContentId()
            mainViewModel?.contentIdLiveData?.observe(this, {
                mainViewModel?.addNote(it)
                replaceFragment(NoteFragment.newInstance(it))
            })
        }


    }

 */

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .addToBackStack("test3")
            .commit()
    }
}
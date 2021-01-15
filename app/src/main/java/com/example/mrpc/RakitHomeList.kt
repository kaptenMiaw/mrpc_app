package com.example.mrpc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mrpc.rakitlist.EditActivity
import com.example.mrpc.room.NoteDB
import kotlinx.android.synthetic.main.rakit_homelist.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RakitHomeList: AppCompatActivity(){

    val db by lazy { NoteDB(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rakit_homelist)

        setupListener()
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val note = db.noteDao().getNote()
            Log.d("RakitHomeList","dbResponse: $note")
        }
    }

        fun setupListener() {
            button_create.setOnClickListener {
                startActivity(Intent(this, EditActivity::class.java))
            }
        }

        //for toolbar
        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.rakit_homelist_toolbar, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.search_ic -> Toast.makeText(this, "Search Clicked !", Toast.LENGTH_SHORT)
                    .show()
                R.id.sortlist_ic -> Toast.makeText(this, "Sort List Clicked !", Toast.LENGTH_SHORT)
                    .show()
                R.id.cekupdate_ic -> Toast.makeText(
                    this,
                    "Cek Update Clicked !",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.backup_ic -> Toast.makeText(this, "Backup Data Clicked !", Toast.LENGTH_SHORT)
                    .show()
                R.id.abput_ic -> Toast.makeText(this, "About Clicked !", Toast.LENGTH_SHORT).show()
                R.id.moreapps_ic -> Toast.makeText(this, "More Apps Clicked !", Toast.LENGTH_SHORT)
                    .show()
                R.id.donate_ic -> Toast.makeText(this, "Donate Clicked !", Toast.LENGTH_SHORT)
                    .show()
            }
            return true
        }

}
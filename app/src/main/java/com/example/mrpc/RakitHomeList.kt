package com.example.mrpc

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RakitHomeList: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rakit_homelist)

    }
    //for toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.rakit_homelist_toolbar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.search_ic -> Toast.makeText(this, "Search Clicked !", Toast.LENGTH_SHORT).show()
            R.id.sortlist_ic -> Toast.makeText(this, "Sort List Clicked !", Toast.LENGTH_SHORT).show()
            R.id.cekupdate_ic -> Toast.makeText(this, "Cek Update Clicked !", Toast.LENGTH_SHORT).show()
            R.id.backup_ic -> Toast.makeText(this, "Backup Data Clicked !", Toast.LENGTH_SHORT).show()
            R.id.abput_ic -> Toast.makeText(this, "About Clicked !", Toast.LENGTH_SHORT).show()
            R.id.moreapps_ic -> Toast.makeText(this, "More Apps Clicked !", Toast.LENGTH_SHORT).show()
            R.id.donate_ic -> Toast.makeText(this, "Donate Clicked !", Toast.LENGTH_SHORT).show()
        }
        return true
    }

}
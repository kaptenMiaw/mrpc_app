package com.example.mrpc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mrpc.rakitlist.EditActivity
import com.example.mrpc.rakitlist.NoteAdapter
import com.example.mrpc.room.Constant
import com.example.mrpc.room.Note
import com.example.mrpc.room.NoteDB
import kotlinx.android.synthetic.main.rakit_homelist.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RakitHomeList: AppCompatActivity(){

    private val db by lazy { NoteDB(this) }
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rakit_homelist)

        setupListener()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loadNote()
    }
    private fun loadNote(){
        CoroutineScope(Dispatchers.IO).launch {
            val note = db.noteDao().getNotes()
            Log.d("RakitHomeList","dbResponse: $note")
            withContext(Dispatchers.Main){
                noteAdapter.setData(note)
            }
        }
    }

        private fun setupListener() {
            button_create.setOnClickListener {
                intentEdit(0,Constant.TYPE_CREATE)
            }
        }

        fun intentEdit(note_id: Int,intent_type: Int){
            startActivity(
                Intent(this, EditActivity::class.java)
                    .putExtra("intent_id", note_id)
                    .putExtra("intent_type", intent_type)
            )
        }

        private fun setupRecyclerView() {
            noteAdapter = NoteAdapter(arrayListOf(),object: NoteAdapter.OnAdapterListener{
                override fun onRead(note: Note) {
                    //read note
                    intentEdit(note.id,Constant.TYPE_READ)
                }

                override fun onUpdate(note: Note) {
                    //edit note
                    intentEdit(note.id,Constant.TYPE_UPDATE)
                }

                override fun onDelete(note: Note) {
                    deleteAlert(note)
                }

            })
            list_note.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = noteAdapter
            }
        }

        private fun deleteAlert(note: Note){
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.apply {
                setTitle("Konfirmasi Hapus")
                setMessage("Yakin nih mau hapus ${note.title}?")
                setNegativeButton("Batal") { dialog, which ->
                    dialog.dismiss()
                }
                setPositiveButton("Hapus") { dialog, which ->
                    dialog.dismiss()
                    CoroutineScope(Dispatchers.IO).launch {
                        db.noteDao().deleteNote( note )
                        loadNote()
                    }
                }
            }
            alertDialog.show()
        }

        //for toolbar
        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.rakit_homelist_toolbar, menu)
            return true
        }
        //for toolar item
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
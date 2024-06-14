package com.aristidevscurso.scantext.notasApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.aristidevscurso.scantext.R
import com.aristidevscurso.scantext.databinding.ActivityBlogNotesBinding

class BlogNotesActivity : AppCompatActivity() {

    //declaramos el Binding: esta es una forma para vincular las actividades con los archivos XML es decir las vista
    private lateinit var binding: ActivityBlogNotesBinding

    private lateinit var db: NotesDatabaseHelper
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlogNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDatabaseHelper(this)

        notesAdapter = NotesAdapter(db.getAllNotes(), this)

        binding.notesRecyclerView.layoutManager = LinearLayoutManager(this)
        //iniciamos el recyclerView
        binding.notesRecyclerView.adapter = notesAdapter

        // a traves del binding le asignamos un intent al boton de agregar nuevas notas.
        binding.BtnAddNotes.setOnClickListener {
            val intent = Intent(this, AddNotesActivity::class.java)
            startActivity(intent)

        }

    }
    //esta funcion actualiza las notas en el sistema
    override fun onResume() {
        super.onResume()
        notesAdapter.refreshData(db.getAllNotes())
    }
}
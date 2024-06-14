package com.aristidevscurso.scantext.notasApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.viewmodel.CreationExtras
import com.aristidevscurso.scantext.R
import com.aristidevscurso.scantext.databinding.ActivityAddNotesBinding

class AddNotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNotesBinding
    private lateinit var db: NotesDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var texto = intent.getStringExtra("texto")
        binding.contentEditText.setText(texto)

        db = NotesDatabaseHelper(this)

        binding.SaveBtn.setOnClickListener {
            val tittle = binding.TittleEditText.text.toString()
            val content = binding.contentEditText.text.toString()
            val note = note(0, tittle, content)

            if(tittle == "" || content == ""){
                Toast.makeText(this, "No puedes dejar campos vacíos",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            db.insertNote(note)
            finish()
            Toast.makeText(this, "Nota Guardada!", Toast.LENGTH_SHORT).show()
        }

    }
}
package com.aristidevscurso.scantext.notasApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.widget.Toast
import com.aristidevscurso.scantext.R
import com.aristidevscurso.scantext.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

   private lateinit var binding: ActivityUpdateBinding
   private lateinit var db: NotesDatabaseHelper
   private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)


        db = NotesDatabaseHelper(this)

        noteId = intent.getIntExtra("note_id", -1)
        if (noteId == -1){
            finish()
            return
        }

        val note = db.getNoteById(noteId)
        binding.updateTittleEditText.setText(note.tittle)
        binding.updateContentEditText.setText(note.content)

        binding.UpdateBtn.setOnClickListener {
            val newTittle = binding.updateTittleEditText.text.toString()
            val newContent = binding.updateContentEditText.text.toString()
            val updatenote = note(noteId, newTittle, newContent)

            if(newTittle == "" || newContent == ""){
                Toast.makeText(this, "No puedes dejar campos vacíos",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            db.updateNote(updatenote)
            finish()
            Toast.makeText(this, "los cambios se guardaron!!", Toast.LENGTH_SHORT).show()
        }

    }
}
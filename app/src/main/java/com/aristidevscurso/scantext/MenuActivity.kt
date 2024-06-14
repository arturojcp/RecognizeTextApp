package com.aristidevscurso.scantext

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.aristidevscurso.scantext.RecognizeApp.MainActivity
import com.aristidevscurso.scantext.databinding.ActivityDevelomentBinding
import com.aristidevscurso.scantext.notasApp.BlogNotesActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val btnRecognizeApp = findViewById<Button>(R.id.btnRecognizeApp)
        val btnBlogNotes = findViewById<Button>(R.id.btnBlogNotes)
        val btnDeveloment = findViewById<Button>(R.id.btnDevelomentInfo)

        btnRecognizeApp.setOnClickListener { navigateToRecognizeApp() }
        btnBlogNotes.setOnClickListener { navigateToBlogNotesApp() }
        btnDeveloment.setOnClickListener { navigateToDevelomentInfo() }

    }

   private fun navigateToRecognizeApp(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToBlogNotesApp(){
        val intent = Intent(this, BlogNotesActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToDevelomentInfo(){
        val intent = Intent(this, develomentActivity::class.java)
        startActivity(intent)
    }
}


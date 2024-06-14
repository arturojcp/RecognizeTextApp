package com.aristidevscurso.scantext.RecognizeApp

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.aristidevscurso.scantext.R
import com.aristidevscurso.scantext.notasApp.AddNotesActivity
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.text.FirebaseVisionText
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer

class MainActivity : AppCompatActivity() {

    private var hasFoto: Boolean = false
    lateinit var img: ImageView
    lateinit var btnSelectImage: Button
    lateinit var btnRecognizeText: Button
    lateinit var txtDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //en esta funcion se inician los componentes.
        initComponents()
        //en esta funcion se establecen los eventos que ocurriran
        // al presionar un componente del sistema
        initListeners()


    }


    fun initComponents (){
        img = findViewById(R.id.img)
        btnSelectImage = findViewById(R.id.btnSelectImage)
        btnRecognizeText = findViewById(R.id.btnRecognizeText)
        txtDisplay = findViewById(R.id.txtDisplay)
    }

    private fun initListeners() {

        txtDisplay.setOnClickListener {

            if(hasFoto){
                OpenNotesWithReconizedText()
            }
        }

        btnSelectImage.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Image"), 1)
        }

        btnRecognizeText.setOnClickListener {


            if(!hasFoto){
                txtDisplay?.setText("no se han encontrado Datos")

            }else{
                val bitmap: Bitmap = (img.drawable as BitmapDrawable).bitmap
                val image:FirebaseVisionImage = FirebaseVisionImage.fromBitmap(bitmap)
                val recognize:FirebaseVisionTextRecognizer = FirebaseVision.getInstance().onDeviceTextRecognizer
                txtDisplay?.setText("")


                recognize.processImage(image).addOnSuccessListener {firebaseVisionText ->
                    RecognizeText(firebaseVisionText)

                }.addOnFailureListener {

                }
            }

        }
    }

    private fun RecognizeText(resulttext: FirebaseVisionText){
        if(resulttext.textBlocks.size == 0){
            txtDisplay?.setText("no se han encontrado Datos")
            return
        }
        for (block: FirebaseVisionText.TextBlock in resulttext.textBlocks){
            val text:String = block.text
            txtDisplay?.setText(txtDisplay!!.text.toString() + text)
        }
    }

    private fun OpenNotesWithReconizedText(){
        try {
            var texto =  txtDisplay?.text
            val intent = Intent(this, AddNotesActivity::class.java).apply {
                putExtra("texto", texto)
            }
            startActivity(intent)
        }catch (e: Exception){

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK){
            if(data != null){
                img.setImageURI(data.data)
                hasFoto = true
            }
        }
    }



}
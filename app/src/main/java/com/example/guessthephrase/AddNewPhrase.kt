package com.example.guessthephrase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class AddNewPhrase : AppCompatActivity() {

    lateinit var phrases: TextView
    lateinit var newPhraseInput: EditText
    lateinit var addPhraseButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_phrase)

        val phraseSqliteDBHelper = PhraseSqliteDBHelper(applicationContext)
        val phrasesList = phraseSqliteDBHelper.getAllPhrases()

        phrases = findViewById(R.id.tvPhrases)
        for (phrase in phrasesList){
            phrases.text = "${phrases.text}$phrase\n"
        }

        newPhraseInput = findViewById(R.id.etNewPhrase)
        addPhraseButton = findViewById(R.id.btnAddNewPhrase)

        addPhraseButton.setOnClickListener {
            if(newPhraseInput.text.isNotEmpty()){
                val phrase = newPhraseInput.text.toString()
                phraseSqliteDBHelper.saveNewPhrase(phrase)
                phrases.text = "${phrases.text}$phrase\n"
                newPhraseInput.text.clear()
                Toast.makeText(this,"Added",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"Type a Phrase",Toast.LENGTH_LONG).show()
            }
        }
    }
}
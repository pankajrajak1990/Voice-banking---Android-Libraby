package com.example.simpragma_voice_banking.activity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.example.simpragma_voice_banking.R
import com.example.simpragma_voice_banking.VoiceActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
    }
     fun IntentToVoice(view : View){
         var intent: Intent = Intent(applicationContext, com.example.simpragma_voice_banking.VoiceActivity::class.java)
         startActivity(intent)
         finish()

     }
}

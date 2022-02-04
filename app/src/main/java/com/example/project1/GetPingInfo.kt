package com.example.project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class GetPingInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_ping_info)

        //set button variables
        val back = findViewById<Button>(R.id.back)
        val ping = findViewById<Button>(R.id.submit)

        //functionality for back button
        back?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //functionality for passing IP address to next activity
        ping?.setOnClickListener {
            val intent = Intent(this, PerformPing::class.java)

            //get text from input box
            val text = findViewById<TextView>(R.id.ip_input)
            val ipaddr: String = text.text.toString()

            //pass string to bundle and into intent
            intent.putExtra("ip", ipaddr)
            startActivity(intent)
        }
    }
}
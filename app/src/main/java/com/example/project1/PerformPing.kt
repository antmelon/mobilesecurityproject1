package com.example.project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder

class PerformPing : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perform_ping)

        performPing()

        val again = findViewById<Button>(R.id.again)
        val menu = findViewById<Button>(R.id.menu)

        again?.setOnClickListener{
            val intent = Intent(this, GetPingInfo::class.java)
            startActivity(intent)
        }

        menu?.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun performPing(){
        val host = intent.getStringExtra("ip").toString()
        //val responseListPing= mutableListOf<String>()
        val textView = findViewById<TextView>(R.id.ping_view)

        try{
            val pingCmd = "/system/bin/ping -c 5 $host"
            val r: Runtime = Runtime.getRuntime()
            val p: Process = r.exec(pingCmd)
            p.waitFor()
            val inStream = BufferedReader(InputStreamReader(p.inputStream))
            val logger = StringBuilder()
            var line: String? = ""

            var count = 0
            while(line != null){
                count++
                line = inStream.readLine()
                Log.d("PING", "Got: $line")
                logger.append(line + "\n")

                //skip adding average rtt to final output
                if(count > 6){break}
            }
            //OLD: logging
            Log.d("PING", "Done Pinging")

            val pingResult = logger.toString()

            textView.text = pingResult

        } catch (e: Exception) {
            Toast.makeText(this, "Error ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}

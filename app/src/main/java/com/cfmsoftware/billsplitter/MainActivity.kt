package com.cfmsoftware.billsplitter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.ads.AudienceNetworkAds

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AudienceNetworkAds.initialize(this)

        
    }
}
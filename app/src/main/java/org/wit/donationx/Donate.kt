package org.wit.donationx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.wit.donationx.databinding.ActivityDonateBinding

class Donate : AppCompatActivity() {
    private lateinit var donateLayout: ActivityDonateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        donateLayout = ActivityDonateBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(donateLayout.root)
        donateLayout.progressBar.max = 10000
        donateLayout.amountPicker.minValue = 1
        donateLayout.amountPicker.maxValue = 1000
    }
}

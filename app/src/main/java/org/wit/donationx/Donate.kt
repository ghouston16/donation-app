package org.wit.donationx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.wit.donationx.databinding.ActivityDonateBinding

class Donate : AppCompatActivity() {
    // Enable binding on the layout views
    private lateinit var donateLayout: ActivityDonateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        donateLayout = ActivityDonateBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(donateLayout.root)
        // Progress bar for donation targets
        donateLayout.progressBar.max = 10000
        // Max and Min values for number picker for donation amount
        donateLayout.amountPicker.minValue = 1
        donateLayout.amountPicker.maxValue = 1000
        donateLayout.amountPicker.setOnValueChangedListener { _, _, newVal ->
            //Display the newly selected number to paymentAmount
            donateLayout.paymentAmount.setText("$newVal")
        }
        var totalDonated = 0
        donateLayout.donateButton.setOnClickListener{
            val amount = if (donateLayout.paymentAmount.text.isNotEmpty())
                    donateLayout.paymentAmount.text.toString().toInt() else donateLayout.amountPicker.value
            if (totalDonated >= donateLayout.progressBar.max)
                Toast.makeText(applicationContext ,"Donate Target Reached!",Toast.LENGTH_LONG).show()
                else{
                    totalDonated += amount
                    donateLayout.totalSoFar.text = "$totalDonated"
                    donateLayout.progressBar.progress = totalDonated
            }
        }
    }
}

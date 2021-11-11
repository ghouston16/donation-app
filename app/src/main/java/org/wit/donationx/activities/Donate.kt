package org.wit.donationx.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import org.wit.donationx.R
import org.wit.donationx.databinding.ActivityDonateBinding
import org.wit.donationx.main.DonationXApp
import org.wit.donationx.models.DonationModel
import timber.log.Timber

class Donate : AppCompatActivity() {
    lateinit var app: DonationXApp

    // Enable binding on the layout views
    private lateinit var donateLayout: ActivityDonateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        // Initialize and man app
        app = this.application as DonationXApp
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
        // Donate button listener
        donateLayout.donateButton.setOnClickListener {
            // Vaildation of input
            val amount = if (donateLayout.paymentAmount.text.isNotEmpty())
                donateLayout.paymentAmount.text.toString()
                    .toInt() else donateLayout.amountPicker.value
            if (totalDonated >= donateLayout.progressBar.max)
                Toast.makeText(applicationContext, "Donate Amount Exceeded!", Toast.LENGTH_LONG)
                    .show()
            else {
                val paymentmethod =
                    if (donateLayout.paymentMethod.checkedRadioButtonId == R.id.Direct)
                        "Direct" else "Paypal"
                totalDonated += amount
                donateLayout.totalSoFar.text = "$$totalDonated"
                donateLayout.progressBar.progress = totalDonated
                app.donationStore.create(
                    DonationModel(
                        paymentmethod = paymentmethod,
                        amount = amount
                    )
                )
                Timber.i("Total Donated so far $totalDonated")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //Inflate the menu
        menuInflater.inflate(R.menu.menu_donate, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_report -> {
                startActivity(Intent(this, Report::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}

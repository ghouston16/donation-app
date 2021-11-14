package org.wit.donationx.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.donationx.R
import org.wit.donationx.adapters.DonationAdapter
import org.wit.donationx.databinding.ActivityReportBinding
import org.wit.donationx.main.DonationXApp

class Report : AppCompatActivity() {
    lateinit var app: DonationXApp
    lateinit var reportLayout : ActivityReportBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        reportLayout = ActivityReportBinding.inflate(layoutInflater)
        setContentView(reportLayout.root)

        app = this.application as DonationXApp
        reportLayout.recyclerView.layoutManager = LinearLayoutManager(this)
        reportLayout.recyclerView.adapter = DonationAdapter(app.donationStore.findAll())

        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //Inflate the menu
        menuInflater.inflate(R.menu.menu_report, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_donate -> {
                // Launch Donate Activity
                startActivity(Intent(this, Donate::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
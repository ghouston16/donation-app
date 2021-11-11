package org.wit.donationx.main

import android.app.Application
import org.wit.donationx.models.DonationMemStore
import org.wit.donationx.models.DonationStore
import timber.log.Timber

class DonationXApp : Application() {
    lateinit var donationStore: DonationStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        donationStore = DonationMemStore()
        Timber.i("Starting DonationX Application")

    }
}
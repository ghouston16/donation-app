package org.wit.donationx.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.donationx.R
import org.wit.donationx.adapters.DonationAdapter
import org.wit.donationx.databinding.FragmentReportBinding
import org.wit.donationx.main.DonationXApp

class ReportFragment : Fragment() {
    lateinit var app: DonationXApp
    var totalDonated = 0
    private var _fragBinding: FragmentReportBinding? = null
    private val fragBinding get() = _fragBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        app = activity?.application as DonationXApp
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentReportBinding.inflate(inflater, container, false)
        fragBinding.recyclerView.setLayoutManager(LinearLayoutManager(activity))
        fragBinding.recyclerView.adapter = DonationAdapter(app.donationStore.findAll())
        val root = fragBinding.root
        activity?.title = getString(R.string.action_report)

        return root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ReportFragment().apply {
                arguments = Bundle().apply { }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }
}
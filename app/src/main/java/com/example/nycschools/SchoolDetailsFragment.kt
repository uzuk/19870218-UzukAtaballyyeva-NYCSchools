package com.example.nycschools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_school_details.*

class SchoolDetailsFragment : Fragment() {

    private val viewModel: SchoolDetailsViewModel by viewModels()
    private val args: SchoolDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_school_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val schoolDBN = args.school

        viewModel.getSAT(schoolDBN)

        viewModel.status.observe(viewLifecycleOwner, Observer { status ->
            when(status) {
                LoadingStatus.LOADING -> { pb.visibility = View.VISIBLE}
                LoadingStatus.SUCCESS -> { pb.visibility = View.GONE}
                LoadingStatus.ERROR -> {
                    pb.visibility = View.GONE
                    school_name.text = "Service Error"
                }
            }
        })

        viewModel.sat.observe(viewLifecycleOwner, Observer { sat ->
            if (sat.schoolNotFound == true) {
                school_name.text = "School SAT not found"
            } else {
                school_name.text = "School Name: " + sat.school_name
                sat_critical_reading_avg_score.text = "SAT Critical Reading AVG Score: " + sat.sat_critical_reading_avg_score
                sat_math_avg_score.text = "SAT Math AVG Score: " + sat.sat_math_avg_score
                sat_writing_avg_score.text = "SAT Writing AVG Score: " + sat.sat_writing_avg_score
                num_of_sat_test_takers.text = "Number Of SAT Test Takers: " + sat.num_of_sat_test_takers
            }
        })
    }

}
package com.example.nycschools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_schools_list.*

class SchoolsListFragment : Fragment(), ItemSelector<School> {

    private lateinit var viewModel: SchoolsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schools_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = SchoolsListViewModel()

        viewModel.status.observe(viewLifecycleOwner, Observer { status ->
            when(status) {
                LoadingStatus.LOADING -> { pb.visibility = View.VISIBLE}
                LoadingStatus.SUCCESS -> { pb.visibility = View.GONE}
                LoadingStatus.ERROR -> {
                    pb.visibility = View.GONE
                    error.visibility = View.VISIBLE
                }
            }
        })

        viewModel.schools.observe(viewLifecycleOwner, Observer { schools ->
            let {
                rV.adapter = SchoolsAdapter(schools, this)
            }
        })
    }

    override fun itemSelected(item: School) {
        this.findNavController().navigate(SchoolsListFragmentDirections.actionSchoolsListFragmentToSchoolDetailsFragment(item.dbn.toString()))
    }
}
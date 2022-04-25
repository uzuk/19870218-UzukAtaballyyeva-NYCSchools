package com.example.nycschools

import android.view.View
import junit.framework.Assert.assertEquals
import kotlinx.android.synthetic.main.fragment_schools_list.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SchoolsListFragmentTest: KoinTest {

    private lateinit var subject: SchoolsListFragment
    private val schoolsListViewModel: SchoolsListViewModel by inject()
    private val schools = listOf<School>(School(school_name = "New School 1"), School(school_name = "New School 2"))


    @Before
    fun setup() {
       schoolsListViewModel.schools.value = schools
        schoolsListViewModel._status.value = LoadingStatus.SUCCESS
    }

    @Test
    fun verify_Success(){
        assertEquals(View.GONE, subject.pb)
    }

}
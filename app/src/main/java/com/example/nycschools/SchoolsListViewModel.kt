package com.example.nycschools

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


enum class LoadingStatus { LOADING, ERROR, SUCCESS }
class SchoolsListViewModel : ViewModel() {
    private val _schools = MutableLiveData<List<School>>()
    val schools: MutableLiveData<List<School>> = _schools

    //loading status
    private val _status = MutableLiveData<LoadingStatus>()
    val status: LiveData<LoadingStatus> = _status

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getSchools()
    }

    private fun getSchools() {
        coroutineScope.launch {
            val getSchoolsDeferred = SchoolsApi.retrofitService.getSchools()
            try {
                _status.value = LoadingStatus.LOADING
                // this will run on a thread managed by Retrofit
                val schoolsM = getSchoolsDeferred.await()
                _schools.value = schoolsM
                _status.value = LoadingStatus.SUCCESS
                Log.e("names1", schools.value?.size.toString())
            } catch (e: Exception) {
                _status.value = LoadingStatus.ERROR
                _schools.value = ArrayList()
                Log.e("error", e.message.toString())
            }
        }
    }
}
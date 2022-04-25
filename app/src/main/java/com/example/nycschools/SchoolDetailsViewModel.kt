package com.example.nycschools

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SchoolDetailsViewModel : ViewModel() {
    private val _sat = MutableLiveData<SchoolSatDetails>()
    val sat: MutableLiveData<SchoolSatDetails> = _sat

    //loading status
    private val _status = MutableLiveData<LoadingStatus>()
    val status: LiveData<LoadingStatus> = _status

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun getSAT(dbn: String) {
        coroutineScope.launch {
            val getSatDeferred = SchoolsApi.retrofitService.getSat(dbn)
            try {
                _status.value = LoadingStatus.LOADING
                // this will run on a thread managed by Retrofit
                val satM = getSatDeferred.await()
                if (satM.isEmpty()) {
                    _sat.value!!.schoolNotFound = true
                } else {
                    _sat.value = satM[0]
                }
                _status.value = LoadingStatus.SUCCESS
            } catch (e: Exception) {
                _status.value = LoadingStatus.ERROR
                Log.e("error", e.message.toString())
            }
        }
    }
}
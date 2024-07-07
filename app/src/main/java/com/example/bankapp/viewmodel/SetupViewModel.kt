package com.example.bankapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class SetupViewModel: ViewModel() {
    private val mutableViewState = MutableLiveData<SetupViewState>()
    val viewState: LiveData<SetupViewState> = mutableViewState
    var isPanValid = false
    var isBirthdateValid = false

    val pan = MutableLiveData<String>()
    val day = MutableLiveData<String>()
    val month = MutableLiveData<String>()
    val year = MutableLiveData<String>()

    private val panObserver = Observer<String> { onPanChanged(it) }
    private val dayObserver = Observer<String> {  }
    private val monthObserver = Observer<String> {  }
    private val yearObserver = Observer<String> { onBirthDateChanged(it) }

    init {
        mutableViewState.value = SetupViewState(personalData = null, isNextEnabled = false)
        pan.observeForever(panObserver)
        day.observeForever(dayObserver)
        month.observeForever(monthObserver)
        year.observeForever(yearObserver)
    }

    fun onPanChanged(newPan: String) {
        if (newPan.length > 10 || newPan.length < 10) {
            isPanValid = false
            mutableViewState.value = mutableViewState.value?.copy(
                isNextEnabled = isPanValid && isBirthdateValid
            )
        }
        // validating the PAN only when value entered has 10 characters
        else if (newPan.length == 10) {
            var flag = 0
            for (i in newPan.indices) {
                if (i < 5) {
                    if (!newPan[i].isLetter()) {
                        flag = 1
                        break
                    }
                } else {
                    if (!newPan[i].isDigit()) {
                        flag = 1
                        break
                    }
                }
            }
            if (flag == 0) {
                isPanValid = true
                mutableViewState.value = mutableViewState.value?.copy(
                    isNextEnabled = isPanValid && isBirthdateValid
                )
            }
        }
    }

    fun onBirthDateChanged(newYear: String) {
        if (newYear.length < 4) {
            isBirthdateValid = false
        }
        // validating the date here
        val d = day.value?.toInt()
        val m = month.value?.toInt()
        val y = year.value?.toInt()
        if (newYear.length == 4 && d != null && m != null && y != null) {
            isBirthdateValid = (d in 1..31) && (m in 1..12) && (y in 1900..2024)
        }
        mutableViewState.value = mutableViewState.value?.copy(
            isNextEnabled = isPanValid && isBirthdateValid
        )
    }

    override fun onCleared() {
        pan.removeObserver(panObserver)
        day.removeObserver(dayObserver)
        month.removeObserver(monthObserver)
        year.removeObserver(yearObserver)
    }
}
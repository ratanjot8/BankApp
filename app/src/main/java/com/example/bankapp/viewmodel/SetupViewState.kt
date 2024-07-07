package com.example.bankapp.viewmodel

import com.example.bankapp.data.model.PersonalData

data class SetupViewState(
    val personalData: PersonalData?,
    val isNextEnabled: Boolean
)

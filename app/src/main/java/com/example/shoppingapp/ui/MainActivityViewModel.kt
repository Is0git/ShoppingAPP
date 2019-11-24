package com.example.shoppingapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.di.activity.ActivityScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScope
class MainActivityViewModel @Inject constructor(val repo: MainActivityRepository) : ViewModel() {


}
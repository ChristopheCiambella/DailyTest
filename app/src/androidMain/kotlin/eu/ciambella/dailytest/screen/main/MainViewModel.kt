package eu.ciambella.dailytest.screen.main

import androidx.lifecycle.ViewModel
import eu.ciambella.dailytest.common.screen.home.HOME_ROUTE

class MainViewModel : ViewModel() {

    fun getStartDestination(): String = HOME_ROUTE

}

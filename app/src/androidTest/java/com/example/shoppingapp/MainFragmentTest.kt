package com.example.shoppingapp

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.shoppingapp.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainFragmentTest {

    @get: Rule
    var rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun isListNotEmpty() {

    }


}
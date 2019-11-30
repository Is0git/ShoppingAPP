package com.example.shoppingapp.ui.fragments

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.runner.AndroidJUnitRunner
import com.example.shoppingapp.R
import com.example.shoppingapp.ui.MainActivity
import com.example.shoppingapp.ui.fragments.full_item_fragment.FullItemFragment
import com.example.shoppingapp.ui.fragments.home_fragment.CategoriesAdapter
import com.example.shoppingapp.ui.fragments.home_fragment.HomeFragment
import com.example.shoppingapp.ui.fragments.items_fragment.ItemsFragment
import kotlinx.android.synthetic.main.home_fragment.view.*
import org.hamcrest.Matchers.allOf
import org.hamcrest.core.AllOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class FullFragmentTest {


//
//    @Test
//    fun testVisibility_FullFragment() {
//       onView(withId(R.id.itemRecyclerView))
//           .check(matches(isDisplayed()))
//           .perform(RecyclerViewActions.actionOnItemAtPosition<CategoriesAdapter.MyViewHolder>(0, click()))
//        onView(withId(R.id.itemRecyclerView)).check(matches(isDisplayed()))
//    }

    @Test
    fun fullFragment_isolation() {
//        val fragmentFactory = ShoppingFragmentFactory()
//        val scenario = launchFragmentInContainer<HomeFragment>(null, factory = fragmentFactory)
        val fragmentScenario= launchFragmentInContainer<HomeFragment>()
        onView(withId(R.id.itemRecyclerView)).check(matches(isDisplayed()))
    }
}
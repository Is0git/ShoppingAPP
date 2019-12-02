package com.example.shoppingapp.ui.fragments

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.shoppingapp.R
import com.example.shoppingapp.ui.fragments.home_fragment.HomeFragment
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
        val fragmentScenario = launchFragmentInContainer<HomeFragment>()
        onView(withId(R.id.itemRecyclerView)).check(matches(isDisplayed()))
    }
}
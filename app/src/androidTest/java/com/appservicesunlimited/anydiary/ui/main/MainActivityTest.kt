package com.appservicesunlimited.anydiary.ui.main

import androidx.paging.ExperimentalPagingApi
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.appservicesunlimited.anydiary.R
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalPagingApi
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{
    
    @Test
    fun test_isActivityInView(){
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.main_view)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_recyclerView_fab(){
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.entries_recycler_view)).check(matches(isDisplayed()))
        onView(withId(R.id.add_fab)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

}
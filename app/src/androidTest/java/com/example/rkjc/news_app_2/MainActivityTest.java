package com.example.rkjc.news_app_2;

import android.app.Instrumentation;
import android.content.Intent;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.StringContains.containsString;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    // Convenience helper
    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);

    @Before
    public void setup(){
        onView(withId(R.id.action_search)).perform(click());
    }

    @Test
    public void testRecyclerViewIsDisplayed(){
        onView(withId(R.id.news_recyclerview)).check(matches(isDisplayed()));
    }

    @Test
    public void testRecyclerViewNotEmpty(){
        onView(withRecyclerView(R.id.news_recyclerview).atPosition(0))
                .check(matches(hasDescendant(isDisplayed())));
    }

    @Test
    public void testItemsTextViewsHaveRightText(){
        onView(withRecyclerView(R.id.news_recyclerview).atPosition(0))
                .check(matches(hasDescendant(withText(containsString("Title")))));
    }


    @Test
    public void testRecyclerViewItemClick(){
        onView(withId(R.id.action_search)).perform(click());

        Intents.init();
        Matcher<Intent> expectedIntent = allOf(hasAction(Intent.ACTION_VIEW));
        intending(expectedIntent).respondWith(new Instrumentation.ActivityResult(0, null));

        onView(withId(R.id.news_recyclerview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        intended(expectedIntent);
        Intents.release();
    }
}

package comp3350.team10.acceptance;

import org.junit.*;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

import android.content.Context;

import java.util.Map;

import comp3350.team10.R;
import comp3350.team10.presentation.ActivityMealDiary;
import comp3350.team10.presentation.ActivityDailyProgress;

@RunWith(AndroidJUnit4.class)
public class TestStoryViewWeekTrends {
    @Rule
    public ActivityScenarioRule<ActivityMealDiary> activityRuleMeal = new ActivityScenarioRule<>(ActivityMealDiary.class);

    @Before
    public void setup(){
        onView(ViewMatchers.withId(R.id.dailyNav)).perform(click());

    }
    @Test
    public void test_DailyProgress_screen() {
        onView(ViewMatchers.withId(R.id.progressCircle)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.progressPercentage)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.progressrecyclerview)).check(matches(isDisplayed()));
        //onView(withText("ConsumedCalories")).check(matches(isDisplayed()));
        //onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(0, withText("Test Text"))));
        onView(ViewMatchers.withId(R.id.mealDiaryNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.dailyNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.recipeBookNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.chartsNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.socialNav)).check(matches(isDisplayed()));
    }
    @Test
    public void itemWithText_doesNotExist() {
        // Attempt to scroll to an item that contains the special text.
        onView(ViewMatchers.withId(R.id.progressrecyclerview))
                // scrollTo will fail the test if no item matches.
                .perform(RecyclerViewActions.scrollTo(
                        hasDescendant(withText("%"))
                ));
    }
}

package comp3350.team10.acceptance;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.team10.R;
import comp3350.team10.presentation.ActivityMealDiary;

@RunWith(AndroidJUnit4.class)
public class TestStoryViewLongTrends {

    @Rule
    public ActivityScenarioRule<ActivityMealDiary> activityRule = new ActivityScenarioRule<>(ActivityMealDiary.class);

    @Before
    public void setup() {
        onView(ViewMatchers.withId(R.id.chartsNav)).perform(click());

    }

    @Test
    public void user_can_see_Trends_screen() {
        onView(ViewMatchers.withId(R.id.progressCircle)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.progressPercentage)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.trendsRecyclerView)).check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.mealDiaryNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.dailyNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.recipeBookNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.chartsNav)).check(matches(isDisplayed()));
    }

    @Test
    public void user_can_view_weekly_bar_charts() {
        onView(ViewMatchers.withId(R.id.trendsRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("ConsumedCalories"))));
        onView(ViewMatchers.withId(R.id.trendsRecyclerView)).check(ViewAssertions.matches(ViewMatchers.withText("ConsumedCalories")));

        onView(ViewMatchers.withId(R.id.trendsRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("NetCalories"))));
        onView(ViewMatchers.withId(R.id.trendsRecyclerView)).check(ViewAssertions.matches(ViewMatchers.withText("NetCalories")));

        onView(ViewMatchers.withId(R.id.trendsRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("ExerciseCalories"))));
        onView(ViewMatchers.withId(R.id.trendsRecyclerView)).check(ViewAssertions.matches(ViewMatchers.withText("ExerciseCalories")));

    }
}

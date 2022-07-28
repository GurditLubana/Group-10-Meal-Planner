package comp3350.team10.acceptance;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static comp3350.team10.utils.Utils.atPosition;
import static comp3350.team10.utils.Utils.selectTabAtPosition;

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
public class TestStoryProgressMoreCharts {

    @Rule
    public ActivityScenarioRule<ActivityMealDiary> activityRule = new ActivityScenarioRule<>(ActivityMealDiary.class);

    @Before
    public void setup() {
        onView(ViewMatchers.withId(R.id.chartsNav)).perform(click());

    }

    @Test
    public void user_can_see_Trends_screen() {
        onView(ViewMatchers.withId(R.id.trendsRecyclerView)).check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.mealDiaryNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.dailyNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.recipeBookNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.chartsNav)).check(matches(isDisplayed()));
    }

    @Test
    public void user_can_view_long_term_trend_charts() {
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(0));
        onView(ViewMatchers.withId(R.id.trendsRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("ConsumedCalories"))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("ConsumedCalories")))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("Week")))));

        onView(ViewMatchers.withId(R.id.trendsRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("NetCalories"))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("NetCalories")))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("Week")))));

        onView(ViewMatchers.withId(R.id.trendsRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("ExerciseCalories"))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("ExerciseCalories")))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("Week")))));

        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(1));
        onView(ViewMatchers.withId(R.id.trendsRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("ConsumedCalories"))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("ConsumedCalories")))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("Month")))));

        onView(ViewMatchers.withId(R.id.trendsRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("NetCalories"))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("NetCalories")))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("Month")))));

        onView(ViewMatchers.withId(R.id.trendsRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("ExerciseCalories"))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("ExerciseCalories")))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("Month")))));

        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(2));
        onView(ViewMatchers.withId(R.id.trendsRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("ConsumedCalories"))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("ConsumedCalories")))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("ThreeMonth")))));

        onView(ViewMatchers.withId(R.id.trendsRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("NetCalories"))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("NetCalories")))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("ThreeMonth")))));

        onView(ViewMatchers.withId(R.id.trendsRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("ExerciseCalories"))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("ExerciseCalories")))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("ThreeMonth")))));

        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(3));
        onView(ViewMatchers.withId(R.id.trendsRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("ConsumedCalories"))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("ConsumedCalories")))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("SixMonth")))));

        onView(ViewMatchers.withId(R.id.trendsRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("NetCalories"))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("NetCalories")))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("SixMonth")))));

        onView(ViewMatchers.withId(R.id.trendsRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("ExerciseCalories"))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("ExerciseCalories")))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("SixMonth")))));

        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(4));
        onView(ViewMatchers.withId(R.id.trendsRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("ConsumedCalories"))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("ConsumedCalories")))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("Year")))));

        onView(ViewMatchers.withId(R.id.trendsRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("NetCalories"))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("NetCalories")))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("Year")))));

        onView(ViewMatchers.withId(R.id.trendsRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("ExerciseCalories"))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("ExerciseCalories")))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("Year")))));

        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(5));
        onView(ViewMatchers.withId(R.id.trendsRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("ConsumedCalories"))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("ConsumedCalories")))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("All")))));

        onView(ViewMatchers.withId(R.id.trendsRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("NetCalories"))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("NetCalories")))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("All")))));

        onView(ViewMatchers.withId(R.id.trendsRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("ExerciseCalories"))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("ExerciseCalories")))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("All")))));

        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(2));
        onView(ViewMatchers.withId(R.id.trendsRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("ConsumedCalories"))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("ConsumedCalories")))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("ThreeMonth")))));

        onView(ViewMatchers.withId(R.id.trendsRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("NetCalories"))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("NetCalories")))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("ThreeMonth")))));

        onView(ViewMatchers.withId(R.id.trendsRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("ExerciseCalories"))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("ExerciseCalories")))));
        onView(withId(R.id.trendsRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("ThreeMonth")))));

    }
}

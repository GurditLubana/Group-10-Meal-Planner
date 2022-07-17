package comp3350.team10.acceptance;

import org.junit.*;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
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

import static org.hamcrest.Matchers.not;

import comp3350.team10.R;
import comp3350.team10.presentation.ActivityMealDiary;

@RunWith(AndroidJUnit4.class)
public class TestStoryAddMeals {
    @Rule
    public ActivityScenarioRule<ActivityMealDiary> activityRule = new ActivityScenarioRule<>(ActivityMealDiary.class);
    //public ActivityTestRule<ActivityMealDiary> activityRule = new ActivityTestRule<>(ActivityMealDiary.class);

    @Test
    public void test_ActivityMealDiary_Screen() {
        onView(withText("MealPlanner")).check(matches(isDisplayed()));
        onView(withText("Calories")).check(matches(isDisplayed()));
        onView(withText("Budget")).check(matches(isDisplayed()));
        onView(withText("Food")).check(matches(isDisplayed()));
        onView(withText("Exercise")).check(matches(isDisplayed()));
        onView(withText("Net")).check(matches(isDisplayed()));
        onView(withText("Oct 10")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("274")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("1726")));
        //onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(0, withText("Test Text"))));

        onView(ViewMatchers.withId(R.id.mealDiaryNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.dailyNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.recipeBookNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.chartsNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.socialNav)).check(matches(isDisplayed()));
    }
}

package comp3350.team10.acceptance;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static comp3350.team10.utils.Utils.atPosition;
import static comp3350.team10.utils.Utils.clickChildViewWithId;
import static comp3350.team10.utils.Utils.waitId;

import androidx.test.espresso.Espresso;
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
public class TestStoryViewWeekTrends {
    @Rule
    public ActivityScenarioRule<ActivityMealDiary> activityRuleMeal = new ActivityScenarioRule<>(ActivityMealDiary.class);

    @Before
    public void setup() {
        onView(ViewMatchers.withId(R.id.dailyNav)).perform(click());

    }

    @Test
    public void user_can_see_DailyProgress_screen() {
        onView(ViewMatchers.withId(R.id.progressCircle)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.progressPercentage)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.progressrecyclerview)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.mealDiaryNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.dailyNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.recipeBookNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.chartsNav)).check(matches(isDisplayed()));
    }

    @Test
    public void user_can_view_weekly_bar_charts() {
        onView(ViewMatchers.withId(R.id.progressrecyclerview)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("ConsumedCalories"))));
        onView(withId(R.id.progressrecyclerview)).check(matches(atPosition(0, hasDescendant(withText("54%")))));

        onView(ViewMatchers.withId(R.id.progressrecyclerview)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("NetCalories"))));
        onView(withId(R.id.progressrecyclerview)).check(matches(atPosition(1, hasDescendant(withText("57%")))));

        onView(ViewMatchers.withId(R.id.progressrecyclerview)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("ExerciseCalories"))));
        onView(withId(R.id.progressrecyclerview)).check(matches(atPosition(2, hasDescendant(withText("84%")))));
    }

    @Test
    public void user_can_see_weekly_progress_update_after_log_update() {
        onView(ViewMatchers.withId(R.id.progressrecyclerview)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("ConsumedCalories"))));
        onView(withId(R.id.progressrecyclerview)).check(matches(atPosition(0, hasDescendant(withText("54%")))));
        onView(withId(R.id.progressrecyclerview)).check(matches(atPosition(0, hasDescendant(withText("274")))));
        onView(withId(R.id.progressrecyclerview)).check(matches(atPosition(0, hasDescendant(withText("952"))))); //oct 4

        onView(ViewMatchers.withId(R.id.progressrecyclerview)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("NetCalories"))));
        onView(withId(R.id.progressrecyclerview)).check(matches(atPosition(1, hasDescendant(withText("57%")))));
        onView(withId(R.id.progressrecyclerview)).check(matches(atPosition(1, hasDescendant(withText("1726")))));
        onView(withId(R.id.progressrecyclerview)).check(matches(atPosition(1, hasDescendant(withText("1348")))));

        onView(ViewMatchers.withId(R.id.progressrecyclerview)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("ExerciseCalories"))));
        onView(withId(R.id.progressrecyclerview)).check(matches(atPosition(2, hasDescendant(withText("84%")))));
        onView(withId(R.id.progressrecyclerview)).check(matches(atPosition(2, hasDescendant(withText("0")))));
        onView(withId(R.id.progressrecyclerview)).check(matches(atPosition(2, hasDescendant(withText("200")))));

        onView(ViewMatchers.withId(R.id.mealDiaryNav)).perform(click());

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(2, clickChildViewWithId(R.id.btnModifyMeal)));

        onView(withId(R.id.inputQty)).perform(clearText(), typeText("3000"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.btnOk)).perform(click());

        onView(ViewMatchers.withId(R.id.exerciseProgress)).perform(click());
        onView(withId(R.id.inputQty)).perform(clearText(), typeText("400"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.btnOk)).perform(click());

        onView(ViewMatchers.withId(R.id.dailyNav)).perform(click());
        onView(ViewMatchers.withId(R.id.progressrecyclerview)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("ConsumedCalories"))));
        onView(withId(R.id.progressrecyclerview)).check(matches(atPosition(0, hasDescendant(withText("75%")))));
        onView(withId(R.id.progressrecyclerview)).check(matches(atPosition(0, hasDescendant(withText("3244")))));
        onView(withId(R.id.progressrecyclerview)).check(matches(atPosition(0, hasDescendant(withText("952"))))); //oct 4

        onView(ViewMatchers.withId(R.id.progressrecyclerview)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("NetCalories"))));
        onView(withId(R.id.progressrecyclerview)).check(matches(atPosition(1, hasDescendant(withText("39%")))));
        onView(withId(R.id.progressrecyclerview)).check(matches(atPosition(1, hasDescendant(withText("-844")))));
        onView(withId(R.id.progressrecyclerview)).check(matches(atPosition(1, hasDescendant(withText("1348")))));

        onView(ViewMatchers.withId(R.id.progressrecyclerview)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("ExerciseCalories"))));
        onView(withId(R.id.progressrecyclerview)).check(matches(atPosition(2, hasDescendant(withText("112%")))));
        onView(withId(R.id.progressrecyclerview)).check(matches(atPosition(2, hasDescendant(withText("400")))));
        onView(withId(R.id.progressrecyclerview)).check(matches(atPosition(2, hasDescendant(withText("200")))));

        onView(ViewMatchers.withId(R.id.mealDiaryNav)).perform(click());

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(2, clickChildViewWithId(R.id.btnModifyMeal)));

        onView(withId(R.id.inputQty)).perform(clearText(), typeText("30"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.btnOk)).perform(click());

        onView(ViewMatchers.withId(R.id.exerciseProgress)).perform(click());
        onView(withId(R.id.inputQty)).perform(clearText(), typeText("0"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.btnOk)).perform(click());

        onView(isRoot()).perform(waitId(R.id.nextDateProgress, 5000));
        onView(ViewMatchers.withId(R.id.nextDateProgress)).perform(click());
        onView(isRoot()).perform(waitId(R.id.prevDateProgress, 5000));
        onView(ViewMatchers.withId(R.id.prevDateProgress)).perform(click());
    }
}

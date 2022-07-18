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
import comp3350.team10.presentation.ActivityRecipeBook;

@RunWith(AndroidJUnit4.class)
public class TestStoryViewRecipes {

    @Rule
    public ActivityScenarioRule<ActivityRecipeBook> activityRule = new ActivityScenarioRule<>(ActivityRecipeBook.class);

    @Test
    public void test_RecipeBook_screen() {
        onView(ViewMatchers.withId(R.id.recipeBookNav)).perform(click());
        onView(ViewMatchers.withId(R.id.progressCircle)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.progressPercentage)).check(matches(isDisplayed()));
    }
}

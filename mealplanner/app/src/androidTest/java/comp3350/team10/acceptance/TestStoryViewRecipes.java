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

import static comp3350.team10.utils.Utils.atPosition;

import comp3350.team10.utils.Utils;
import comp3350.team10.R;
import comp3350.team10.presentation.ActivityMealDiary;
import comp3350.team10.presentation.ActivityRecipeBook;

@RunWith(AndroidJUnit4.class)
public class TestStoryViewRecipes {

    @Rule
    public ActivityScenarioRule<ActivityMealDiary> activityRule = new ActivityScenarioRule<>(ActivityMealDiary.class);
    //public ActivityScenarioRule<ActivityRecipeBook> activityRule = new ActivityScenarioRule<>(ActivityRecipeBook.class);

    @Before
    public void setup(){
        onView(ViewMatchers.withId(R.id.recipeBookNav)).perform(click());

    }
    @Test
    public void test_RecipeBook_screen() {
        onView(ViewMatchers.withId(R.id.recipeRecyclerView)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.mealDiaryNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.dailyNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.recipeBookNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.chartsNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.socialNav)).check(matches(isDisplayed()));
    }
    @Test
    public void itemWithText_doesNotExist() {
        onView(withId(R.id.recipeRecyclerView))
                .check(matches(atPosition(0, withText("Apple"))));
        onView(withId(R.id.recipeRecyclerView))
                .check(matches(atPosition(0, hasDescendant(withText("Apple")))));
        // Attempt to scroll to an item that contains the special text.
        onView(ViewMatchers.withId(R.id.recipeRecyclerView))
                // scrollTo will fail the test if no item matches.
                .perform(RecyclerViewActions.scrollTo(
                        hasDescendant(withText("Apple"))
                ));
    }
}

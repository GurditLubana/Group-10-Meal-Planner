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
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.fail;

import static comp3350.team10.utils.Utils.*;

import comp3350.team10.utils.Utils;
import comp3350.team10.R;
import comp3350.team10.presentation.ActivityMealDiary;
import comp3350.team10.presentation.ActivityRecipeBook;

@RunWith(AndroidJUnit4.class)
public class TestStoryViewRecipes {

    @Rule
    public ActivityScenarioRule<ActivityMealDiary> activityRule = new ActivityScenarioRule<>(ActivityMealDiary.class);

    @Before
    public void setup(){
        onView(ViewMatchers.withId(R.id.recipeBookNav)).perform(click());
    }

    @Test
    public void user_can_see_app_navigation() {
        onView(ViewMatchers.withId(R.id.recipeRecyclerView)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.mealDiaryNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.dailyNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.recipeBookNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.chartsNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.socialNav)).check(matches(isDisplayed()));
    }

//    @Test
//    public void method1() {
//        onView(withId(R.id.recipeRecyclerView))
//                .check(matches(atPosition(0, withText("Apple"))));
//    }
//
//    @Test
//    public void method2() {
//        onView(withId(R.id.recipeRecyclerView))
//                .check(matches(atPosition(0, hasDescendant(withText("Apple")))));
//    }
//
//    @Test
//    public void method3() {
//        // Attempt to scroll to an item that contains the special text.
//        onView(ViewMatchers.withId(R.id.recipeRecyclerView))
//                // scrollTo will fail the test if no item matches.
//                .perform(RecyclerViewActions.scrollTo(
//                        hasDescendant(withText("Apple"))
//                ));
//    }

    @Test
    public void user_can_see_food_recipes() {
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(0));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Apple"))));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Cracker"))));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Grain of Rice"))));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Steak"))));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Banana"))));
    }

    @Test
    public void user_can_see_meal_recipes() {
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(1));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 67"))));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 89"))));
    }

    @Test
    public void user_can_see_drink_recipes() {
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(2));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Orange Juice"))));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Vodka"))));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Tonic"))));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("White Rum"))));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Mojito"))));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Vodka OJ"))));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Vodka Tonic"))));
    }

    @Test
    public void user_can_see_food_recipe_details() { //TODO detailed view
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(0));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Apple"))));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Cracker"))));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Grain of Rice"))));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Steak"))), click());
    }

    @Test
    public void user_can_see_meal_recipe_details() {
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(1));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 67"))), click());
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, clickChildViewWithId(R.id.btnBackRecipe)));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 89"))));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, clickChildViewWithId(R.id.cardView2)));
    }

    @Test
    public void user_can_see_drink_recipe_details() {
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(2));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Orange Juice"))));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Vodka"))));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Tonic"))));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("White Rum"))));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Mojito"))));
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(4, clickChildViewWithId(R.id.cardView2)));
    }

    @Test
    public void user_is_warned_invalid_add_food_input() {
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(0));
        onView(ViewMatchers.withId(R.id.openButton)).perform(click());

        onView(isRoot()).perform(waitId(R.id.addButton, 5000));
        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(),click());
        onView(withId(R.id.dialogRecipeNameInput)).check(matches(hasErrorText("Field cannot be empty")));
    }

    @Test
    public void user_can_add_food() {
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(0));
        onView(ViewMatchers.withId(R.id.openButton)).perform(click());

        onView(isRoot()).perform(waitId(R.id.addButton, 5000));
        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("McRib"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("333"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("777"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.dialogRecipeSpinner)).check(matches(withSpinnerText(containsString("tbsp"))));

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(),click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("McRib"))));
    }

    @Test
    public void user_is_warned_invalid_add_meal_input() {
        fail("TODO");
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(1));
        onView(ViewMatchers.withId(R.id.openButton)).perform(click());

        onView(isRoot()).perform(waitId(R.id.addButton, 5000));
        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(),click());
        onView(withId(R.id.dialogRecipeNameInput)).check(matches(hasErrorText("Field cannot be empty")));
    }

    @Test
    public void user_can_add_meal() {
        fail("TODO");
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(1));
        onView(ViewMatchers.withId(R.id.openButton)).perform(click());

        onView(isRoot()).perform(waitId(R.id.addButton, 5000));
        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("McRib"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("333"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("777"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.dialogRecipeSpinner)).check(matches(withSpinnerText(containsString("tbsp"))));

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(),click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("McRib"))));
    }

    @Test
    public void user_is_warned_invalid_add_drinks_input() {
        fail("TODO");
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(0));
        onView(ViewMatchers.withId(R.id.openButton)).perform(click());

        onView(isRoot()).perform(waitId(R.id.addButton, 5000));
        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(),click());
        onView(withId(R.id.dialogRecipeNameInput)).check(matches(hasErrorText("Field cannot be empty")));
    }

    @Test
    public void user_can_add_drinks() {
        fail("TODO");
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(0));
        onView(ViewMatchers.withId(R.id.openButton)).perform(click());

        onView(isRoot()).perform(waitId(R.id.addButton, 5000));
        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("McRib"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("333"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("777"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.dialogRecipeSpinner)).check(matches(withSpinnerText(containsString("tbsp"))));

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(),click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("McRib"))));
    }
}

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

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.fail;

import static comp3350.team10.utils.Utils.clickChildViewWithId;
import static comp3350.team10.utils.Utils.selectTabAtPosition;
import static comp3350.team10.utils.Utils.waitId;

import comp3350.team10.R;
import comp3350.team10.presentation.ActivityMealDiary;

@RunWith(AndroidJUnit4.class)
public class TestStoryAddMeals {
    @Rule
    public ActivityScenarioRule<ActivityMealDiary> activityRule = new ActivityScenarioRule<>(ActivityMealDiary.class);

    @Test
    public void test_ActivityMealDiary_Screen() {
        onView(withText("MealPlanner")).check(matches(isDisplayed()));
        onView(withText("Calories")).check(matches(isDisplayed()));
        onView(withText("Budget")).check(matches(isDisplayed()));
        onView(withText("Food")).check(matches(isDisplayed()));
        onView(withText("Exercise")).check(matches(isDisplayed()));
        onView(withText("Net")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.mealRecyclerView)).check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.mealDiaryNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.dailyNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.recipeBookNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.chartsNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.socialNav)).check(matches(isDisplayed()));
    }

    @Test
    public void user_can_see_logged_food() {
        onView(withText("Oct 10")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("274")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("1726")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Apple"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Cracker"))));
    }

    @Test
    public void user_can_see_a_previous_day_log() {
        onView(ViewMatchers.withId(R.id.prevDateProgress)).perform(click());
        onView(withText("Oct 09")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("600")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("1400")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 2items"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 3items"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 67"))));

        onView(ViewMatchers.withId(R.id.nextDateProgress)).perform(click());
        onView(withText("Oct 10")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("274")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("1726")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Apple"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Cracker"))));
    }

    @Test
    public void user_can_see_logs_further_out_with_date_picker() {
        onView(ViewMatchers.withId(R.id.dateProgress)).perform(click());
        fail("TODO");

        onView(withText("Oct 09")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("600")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("1400")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 2items"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 3items"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 67"))));

        onView(ViewMatchers.withId(R.id.nextDateProgress)).perform(click());
        onView(withText("Oct 10")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("274")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("1726")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Apple"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Cracker"))));
    }

    @Test
    public void user_can_add_meal_entries_to_day_log() {
        for (int i = 0; i < 5; i++) {
            onView(isRoot()).perform(waitId(R.id.nextDateProgress, 5000));
            onView(ViewMatchers.withId(R.id.nextDateProgress)).perform(click());
        }
        onView(withText("Oct 15")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("666")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("666")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.btnAddMeal)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(13, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(13, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Burrito"))));

        onView(withText("Oct 15")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("666")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("200")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("466")));

        onView(ViewMatchers.withId(R.id.prevDateProgress)).perform(click());
        onView(withText("Oct 14")).check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.nextDateProgress)).perform(click());
        onView(withText("Oct 15")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("666")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("200")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("466")));

    }

    @Test
    public void user_can_add_food_meal_drink_to_day_log() {
        for (int i = 0; i < 5; i++) {
            onView(isRoot()).perform(waitId(R.id.nextDateProgress, 5000));
            onView(ViewMatchers.withId(R.id.nextDateProgress)).perform(click());
        }
        onView(withText("Oct 15")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("666")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("666")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.btnAddMeal)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(13, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(13, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Burrito"))));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.btnAddMeal)));
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(1));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(3, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(3, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 89"))));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(2, clickChildViewWithId(R.id.btnAddMeal)));
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(2));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(4, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(4, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Mojito"))));

        onView(withText("Oct 15")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("666")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("600")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("66")));

        onView(ViewMatchers.withId(R.id.prevDateProgress)).perform(click());
        onView(withText("Oct 14")).check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.nextDateProgress)).perform(click());
        onView(withText("Oct 15")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("666")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("600")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("66")));

    }

    private void seed_test_date() {
        for (int i = 0; i < 6; i++) {
            onView(isRoot()).perform(waitId(R.id.nextDateProgress, 5000));
            onView(ViewMatchers.withId(R.id.nextDateProgress)).perform(click());
        }
        onView(withText("Oct 16")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("666")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("666")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.btnAddMeal)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(13, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(13, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Burrito"))));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.btnAddMeal)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(10, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(10, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Burger"))));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(2, clickChildViewWithId(R.id.btnAddMeal)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(19, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(19, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Ham"))));

        onView(withText("Oct 16")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("666")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("400")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("266")));

    }

    @Test
    public void user_can_edit_meal_entries_in_day_log() {
        seed_test_date();

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.btnModifyMeal)));

        onView(withId(R.id.inputQty)).perform(clearText(), typeText("5"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.inputUnit)).perform(click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.inputUnit)).check(matches(withSpinnerText(containsString("tbsp"))));
        onView(ViewMatchers.withId(R.id.btnOk)).perform(click());

        onView(withText("Oct 16")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("666")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("270")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("396")));

        onView(ViewMatchers.withId(R.id.prevDateProgress)).perform(click());
        onView(withText("Oct 15")).check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.nextDateProgress)).perform(click());
        onView(withText("Oct 16")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("666")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("270")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("396")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.btnModifyMeal)));

        onView(withId(R.id.inputQty)).perform(clearText(), typeText("5"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.inputUnit)).perform(click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.inputUnit)).check(matches(withSpinnerText(containsString("tbsp"))));
        onView(ViewMatchers.withId(R.id.btnOk)).perform(click());

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(2, clickChildViewWithId(R.id.btnModifyMeal)));

        onView(withId(R.id.inputQty)).perform(clearText(), typeText("5"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.inputUnit)).perform(click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.inputUnit)).check(matches(withSpinnerText(containsString("tbsp"))));
        onView(ViewMatchers.withId(R.id.btnOk)).perform(click());

        onView(ViewMatchers.withId(R.id.prevDateProgress)).perform(click());
        onView(withText("Oct 15")).check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.nextDateProgress)).perform(click());
        onView(withText("Oct 16")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("666")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("1498")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("832")));
    }

    @Test
    public void user_can_set_new_calorie_goal() {
        seed_test_date();


    }

    @Test
    public void user_can_set_new_exercise_performed() {
        seed_test_date();


    }

    @Test
    public void user_can_remove_previously_logged_food() {
        seed_test_date();


    }
}

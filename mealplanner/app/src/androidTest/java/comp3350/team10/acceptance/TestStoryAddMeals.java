package comp3350.team10.acceptance;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.fail;
import static comp3350.team10.utils.Utils.atPosition;
import static comp3350.team10.utils.Utils.clickChildViewWithId;
import static comp3350.team10.utils.Utils.selectTabAtPosition;
import static comp3350.team10.utils.Utils.waitId;

import android.view.KeyEvent;
import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.team10.R;
import comp3350.team10.presentation.ActivityMealDiary;

@RunWith(AndroidJUnit4.class)
public class TestStoryAddMeals {
    @Rule
    public ActivityScenarioRule<ActivityMealDiary> activityRule = new ActivityScenarioRule<>(ActivityMealDiary.class);

    @Test
    public void user_can_see_main_activity() {
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
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2100")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("1038")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("201")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("1262")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Biscotti"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Bologna"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Walnut"))));

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
        onView(ViewMatchers.withId(R.id.month_navigation_next))
                .perform(click())
                .perform(click())
                .perform(click());
        onView(ViewMatchers.withId(R.id.mtrl_picker_header_toggle)).perform(click());
        onView(ViewMatchers.withId(R.id.mtrl_picker_text_input_date))
                .perform(click())
                .perform(pressKey(KeyEvent.KEYCODE_DEL))
                .perform(pressKey(KeyEvent.KEYCODE_DEL))
                .perform(pressKey(KeyEvent.KEYCODE_DEL))
                .perform(pressKey(KeyEvent.KEYCODE_DEL))
                .perform(pressKey(KeyEvent.KEYCODE_DEL))
                .perform(pressKey(KeyEvent.KEYCODE_DEL))
                .perform(pressKey(KeyEvent.KEYCODE_DEL))
                .perform(pressKey(KeyEvent.KEYCODE_DEL));
        onView(withId(R.id.mtrl_picker_text_input_date))
                .perform(pressKey(KeyEvent.KEYCODE_8))
                .perform(pressKey(KeyEvent.KEYCODE_SLASH))
                .perform(pressKey(KeyEvent.KEYCODE_9))
                .perform(pressKey(KeyEvent.KEYCODE_SLASH))
                .perform(pressKey(KeyEvent.KEYCODE_2))
                .perform(pressKey(KeyEvent.KEYCODE_2));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.confirm_button)).perform(click());

        onView(withText("Aug 09")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2100")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("1721")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("183")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("561")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Potatoes"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Biscotti"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Ham"))));

        onView(ViewMatchers.withId(R.id.dateProgress)).perform(click());
        onView(ViewMatchers.withId(R.id.mtrl_picker_header_toggle)).perform(click());
        onView(ViewMatchers.withId(R.id.mtrl_picker_text_input_date))
                .perform(click())
                .perform(pressKey(KeyEvent.KEYCODE_DEL))
                .perform(pressKey(KeyEvent.KEYCODE_DEL))
                .perform(pressKey(KeyEvent.KEYCODE_DEL))
                .perform(pressKey(KeyEvent.KEYCODE_DEL))
                .perform(pressKey(KeyEvent.KEYCODE_DEL))
                .perform(pressKey(KeyEvent.KEYCODE_DEL))
                .perform(pressKey(KeyEvent.KEYCODE_DEL))
                .perform(pressKey(KeyEvent.KEYCODE_DEL));
        onView(withId(R.id.mtrl_picker_text_input_date))
                .perform(pressKey(KeyEvent.KEYCODE_1))
                .perform(pressKey(KeyEvent.KEYCODE_0))
                .perform(pressKey(KeyEvent.KEYCODE_SLASH))
                .perform(pressKey(KeyEvent.KEYCODE_1))
                .perform(pressKey(KeyEvent.KEYCODE_0))
                .perform(pressKey(KeyEvent.KEYCODE_SLASH))
                .perform(pressKey(KeyEvent.KEYCODE_2))
                .perform(pressKey(KeyEvent.KEYCODE_2));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.confirm_button)).perform(click());

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
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.btnAddMeal)));
        onView(withId(R.id.tabLayout)).perform(selectTabAtPosition(1), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(3, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(3, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 89"))));

        onView(withText("Oct 15")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("300")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("1700")));

        onView(ViewMatchers.withId(R.id.prevDateProgress)).perform(click());
        onView(withText("Oct 14")).check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.nextDateProgress)).perform(click());
        onView(withText("Oct 15")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("300")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("1700")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.btnDeleteMeal)));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
    }

    @Test
    public void user_can_add_food_meal_drink_to_day_log() {
        for (int i = 0; i < 5; i++) {
            onView(isRoot()).perform(waitId(R.id.nextDateProgress, 5000));
            onView(ViewMatchers.withId(R.id.nextDateProgress)).perform(click());
        }
        onView(withText("Oct 15")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.btnAddMeal)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(12, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(12, clickChildViewWithId(R.id.addToPlannerBtn2)));
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
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("500")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("1500")));

        onView(ViewMatchers.withId(R.id.prevDateProgress)).perform(click());
        onView(withText("Oct 14")).check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.nextDateProgress)).perform(click());
        onView(withText("Oct 15")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("500")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("1500")));

        for (int i = 0; i < 3; i++) {
            onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
            onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.btnDeleteMeal)));
        }

    }

    private void seed_test_date() {
        for (int i = 0; i < 6; i++) {
            onView(isRoot()).perform(waitId(R.id.nextDateProgress, 5000));
            onView(ViewMatchers.withId(R.id.nextDateProgress)).perform(click());
        }
        onView(withText("Oct 16")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.btnAddMeal)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(9, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(9, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Burger"))));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.btnAddMeal)));
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(1));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(3, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(3, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 89"))));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(2, clickChildViewWithId(R.id.btnAddMeal)));
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(2));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Vodka"))));

        onView(withText("Oct 16")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("800")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("1200")));

        onView(isRoot()).perform(waitId(R.id.nextDateProgress, 5000));
        onView(ViewMatchers.withId(R.id.nextDateProgress)).perform(click());
        onView(isRoot()).perform(waitId(R.id.prevDateProgress, 5000));
        onView(ViewMatchers.withId(R.id.prevDateProgress)).perform(click());
    }

    private void clear_test_date() {

        for (int i = 0; i < 3; i++) {
            onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
            onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.btnDeleteMeal)));
        }

        onView(ViewMatchers.withId(R.id.goalCalorie)).perform(click());
        onView(withId(R.id.inputQty)).perform(clearText(), typeText("2000"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.btnOk)).perform(click());

        onView(ViewMatchers.withId(R.id.exerciseProgress)).perform(click());
        onView(withId(R.id.inputQty)).perform(clearText(), typeText("0"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.btnOk)).perform(click());

        onView(withText("Oct 16")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));

        onView(isRoot()).perform(waitId(R.id.nextDateProgress, 5000));
        onView(ViewMatchers.withId(R.id.nextDateProgress)).perform(click());
        onView(isRoot()).perform(waitId(R.id.prevDateProgress, 5000));
        onView(ViewMatchers.withId(R.id.prevDateProgress)).perform(click());
    }

    @Test
    public void user_can_edit_meal_entries_in_day_log() {
        seed_test_date();

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.btnModifyMeal)));

        onView(withId(R.id.inputQty)).perform(clearText(), typeText("1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.inputUnit)).perform(click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.inputUnit)).check(matches(withSpinnerText(containsString("tbsp"))));
        onView(ViewMatchers.withId(R.id.btnOk)).perform(click());

        onView(withText("Oct 16")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("1520")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("480")));

        onView(ViewMatchers.withId(R.id.prevDateProgress)).perform(click());
        onView(withText("Oct 15")).check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.nextDateProgress)).perform(click());
        onView(withText("Oct 16")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("1520")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("480")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.btnModifyMeal)));

        onView(withId(R.id.inputQty)).perform(clearText(), typeText("100"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.inputUnit)).perform(click());
        onView(withText("oz")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.inputUnit)).check(matches(withSpinnerText(containsString("oz"))));
        onView(ViewMatchers.withId(R.id.btnOk)).perform(click());
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("1345")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("655")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(2, clickChildViewWithId(R.id.btnModifyMeal)));

        onView(withId(R.id.inputQty)).perform(clearText(), typeText("60"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.inputUnit)).perform(click());
        onView(withText("ml")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.inputUnit)).check(matches(withSpinnerText(containsString("ml"))));
        onView(ViewMatchers.withId(R.id.btnOk)).perform(click());

        onView(ViewMatchers.withId(R.id.prevDateProgress)).perform(click());
        onView(withText("Oct 15")).check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.nextDateProgress)).perform(click());
        onView(withText("Oct 16")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("1845")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("155")));

        clear_test_date();
    }

    @Test
    public void user_can_set_new_calorie_goal() {
        seed_test_date();

        onView(withText("Oct 16")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("800")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("1200")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Burger"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 89"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Vodka"))));

        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("400")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("300")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("100")))));

        onView(ViewMatchers.withId(R.id.goalCalorie)).perform(click());
        onView(withId(R.id.inputQty)).perform(clearText(), typeText("4000"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.btnOk)).perform(click());

        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("4000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("800")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("3200")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Burger"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 89"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Vodka"))));

        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("400")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("300")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("100")))));

        onView(isRoot()).perform(waitId(R.id.nextDateProgress, 5000));
        onView(ViewMatchers.withId(R.id.nextDateProgress)).perform(click());
        onView(isRoot()).perform(waitId(R.id.prevDateProgress, 5000));
        onView(ViewMatchers.withId(R.id.prevDateProgress)).perform(click());

        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("4000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("800")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("3200")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Burger"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 89"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Vodka"))));

        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("400")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("300")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("100")))));

        clear_test_date();
    }

    @Test
    public void user_can_set_new_exercise_performed() {
        seed_test_date();

        onView(withText("Oct 16")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("800")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("1200")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Burger"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 89"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Vodka"))));

        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("400")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("300")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("100")))));

        onView(ViewMatchers.withId(R.id.exerciseProgress)).perform(click());
        onView(withId(R.id.inputQty)).perform(clearText(), typeText("800"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.btnOk)).perform(click());

        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("800")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("800")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Burger"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 89"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Vodka"))));

        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("400")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("300")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("100")))));

        onView(isRoot()).perform(waitId(R.id.nextDateProgress, 5000));
        onView(ViewMatchers.withId(R.id.nextDateProgress)).perform(click());
        onView(isRoot()).perform(waitId(R.id.prevDateProgress, 5000));
        onView(ViewMatchers.withId(R.id.prevDateProgress)).perform(click());

        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("800")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("800")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Burger"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 89"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Vodka"))));

        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("400")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("300")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("100")))));

        clear_test_date();
    }

    @Test
    public void user_can_remove_previously_logged_food() {
        seed_test_date();

        onView(isRoot()).perform(waitId(R.id.nextDateProgress, 5000));
        onView(ViewMatchers.withId(R.id.nextDateProgress)).perform(click());
        onView(isRoot()).perform(waitId(R.id.nextDateProgress, 5000));
        onView(ViewMatchers.withId(R.id.nextDateProgress)).perform(click());
        onView(isRoot()).perform(waitId(R.id.prevDateProgress, 5000));
        onView(ViewMatchers.withId(R.id.prevDateProgress)).perform(click());
        onView(isRoot()).perform(waitId(R.id.prevDateProgress, 5000));
        onView(ViewMatchers.withId(R.id.prevDateProgress)).perform(click());

        for (int i = 0; i < 3; i++) {
            onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
            onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.btnDeleteMeal)));
        }

        onView(withText("Oct 16")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));

        onView(isRoot()).perform(waitId(R.id.nextDateProgress, 5000));
        onView(ViewMatchers.withId(R.id.nextDateProgress)).perform(click());
        onView(isRoot()).perform(waitId(R.id.prevDateProgress, 5000));
        onView(ViewMatchers.withId(R.id.prevDateProgress)).perform(click());
    }

    @Test
    public void user_is_warned_of_invalid_input_calorie() {
        seed_test_date();

        onView(withText("Oct 16")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("800")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("1200")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Burger"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 89"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Vodka"))));

        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("400")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("300")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("100")))));

        onView(ViewMatchers.withId(R.id.goalCalorie)).perform(click());
        onView(withId(R.id.inputQty)).perform(clearText(), typeText("0"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.btnOk)).perform(click());

        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("800")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("800")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Burger"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 89"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Vodka"))));

        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("400")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("300")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("100")))));

        onView(ViewMatchers.withId(R.id.goalCalorie)).perform(click());
        onView(withId(R.id.inputQty)).perform(clearText(), typeText("1"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.btnOk)).perform(click());

        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("1")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("800")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("799")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Burger"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 89"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Vodka"))));

        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("400")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("300")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("100")))));

        onView(ViewMatchers.withId(R.id.goalCalorie)).perform(click());
        onView(withId(R.id.inputQty)).perform(clearText(), typeText("10000"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.btnOk)).perform(click());
        onView(withId(R.id.inputQty)).check(matches(hasErrorText("Invalid input must be between 0 and 9999 inclusive")));
        onView(withId(R.id.inputQty)).perform(clearText(), typeText("9999"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.btnOk)).perform(click());

        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("9999")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("800")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("9199")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Burger"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 89"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Vodka"))));

        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("400")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("300")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("100")))));

        clear_test_date();
    }

    @Test
    public void user_is_warned_of_invalid_input_exercise() {
        seed_test_date();

        onView(withText("Oct 16")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("800")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("1200")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Burger"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 89"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Vodka"))));

        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("400")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("300")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("100")))));

        onView(ViewMatchers.withId(R.id.exerciseProgress)).perform(click());
        onView(withId(R.id.inputQty)).perform(clearText(), typeText("0"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.btnOk)).perform(click());

        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("800")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("1200")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Burger"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 89"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Vodka"))));

        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("400")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("300")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("100")))));

        onView(ViewMatchers.withId(R.id.exerciseProgress)).perform(click());
        onView(withId(R.id.inputQty)).perform(clearText(), typeText("1"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.btnOk)).perform(click());

        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("800")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("1")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("1201")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Burger"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 89"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Vodka"))));

        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("400")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("300")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("100")))));

        onView(ViewMatchers.withId(R.id.exerciseProgress)).perform(click());
        onView(withId(R.id.inputQty)).perform(clearText(), typeText("10000"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.btnOk)).perform(click());
        onView(withId(R.id.inputQty)).check(matches(hasErrorText("Invalid input must be between 0 and 9999 inclusive")));
        onView(withId(R.id.inputQty)).perform(clearText(), typeText("9999"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.btnOk)).perform(click());

        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("800")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("9999")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("11199")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Burger"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 89"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Vodka"))));

        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("400")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("300")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("100")))));

        clear_test_date();
    }

    @Test
    public void user_is_warned_of_invalid_input_quantity() {
        seed_test_date();

        onView(withText("Oct 16")).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("800")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("1200")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Burger"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 89"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Vodka"))));

        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("400")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("300")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("100")))));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.btnModifyMeal)));

        onView(withId(R.id.inputQty)).perform(clearText(), typeText("0"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.inputUnit)).perform(click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.inputUnit)).check(matches(withSpinnerText(containsString("tbsp"))));
        onView(ViewMatchers.withId(R.id.btnOk)).perform(click());
        onView(withId(R.id.inputQty)).check(matches(hasErrorText("Invalid input must be between 0 and 9999 inclusive")));
        onView(withId(R.id.inputQty)).perform(clearText(), typeText("1"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.btnOk)).perform(click());

        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("1520")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("480")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Burger"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 89"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Vodka"))));

        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("1120")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("300")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("100")))));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.btnModifyMeal)));

        onView(withId(R.id.inputQty)).perform(clearText(), typeText("10000"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.inputUnit)).perform(click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.inputUnit)).check(matches(withSpinnerText(containsString("tbsp"))));
        onView(ViewMatchers.withId(R.id.btnOk)).perform(click());
        onView(withId(R.id.inputQty)).check(matches(hasErrorText("Invalid input must be between 0 and 9999 inclusive")));
        onView(withId(R.id.inputQty)).perform(clearText(), typeText("9999"));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.btnOk)).perform(click());

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.btnModifyMeal)));

        onView(withId(R.id.inputQty)).perform(clearText(), typeText("1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.inputUnit)).perform(click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.inputUnit)).check(matches(withSpinnerText(containsString("tbsp"))));
        Espresso.closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.btnOk)).perform(click());

        onView(ViewMatchers.withId(R.id.goalCalorie)).check(ViewAssertions.matches(ViewMatchers.withText("2000")));
        onView(ViewMatchers.withId(R.id.foodConsumed)).check(ViewAssertions.matches(ViewMatchers.withText("1520")));
        onView(ViewMatchers.withId(R.id.exerciseProgress)).check(ViewAssertions.matches(ViewMatchers.withText("0")));
        onView(ViewMatchers.withId(R.id.netCalories)).check(ViewAssertions.matches(ViewMatchers.withText("480")));

        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Burger"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 89"))));
        onView(withId(R.id.mealRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Vodka"))));

        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(0, hasDescendant(withText("1120")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(1, hasDescendant(withText("300")))));
        onView(withId(R.id.mealRecyclerView)).check(matches(atPosition(2, hasDescendant(withText("100")))));

        clear_test_date();
    }
}

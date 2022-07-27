package comp3350.team10.acceptance;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.fail;
import static comp3350.team10.utils.Utils.clickChildViewWithId;
import static comp3350.team10.utils.Utils.selectTabAtPosition;
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
import comp3350.team10.persistence.DBSelector;
import comp3350.team10.persistence.DataAccess;
import comp3350.team10.presentation.ActivityMealDiary;

@RunWith(AndroidJUnit4.class)
public class TestStoryViewRecipes {

    @Rule
    public ActivityScenarioRule<ActivityMealDiary> activityRule = new ActivityScenarioRule<>(ActivityMealDiary.class);

    @Before
    public void setup() {
        onView(ViewMatchers.withId(R.id.recipeBookNav)).perform(click());
    }

    @Test
    public void user_can_see_app_navigation() {
        onView(ViewMatchers.withId(R.id.recipeRecyclerView)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.mealDiaryNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.dailyNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.recipeBookNav)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.chartsNav)).check(matches(isDisplayed()));
    }

    @Test
    public void user_can_browse_recipes() {
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(0));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Apple"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Cracker"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Grain of Rice"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Steak"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Banana"))));

        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(1));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 67"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 89"))));

        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(2));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Orange Juice"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Vodka"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Tonic"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("White Rum"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Mojito"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Vodka OJ"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Vodka Tonic"))));
    }

    @Test
    public void user_can_see_recipe_details() { //TODO detailed view
        fail("TODO");
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(0));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Apple"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Cracker"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(2, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Grain of Rice"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Steak"))), click());

        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(1));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 67"))), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(2, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(2, clickChildViewWithId(R.id.btnBackRecipe)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Meal 89"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(3, clickChildViewWithId(R.id.cardView2)));

        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(2));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Orange Juice"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Vodka"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Tonic"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(2, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("White Rum"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(3, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Mojito"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(4, clickChildViewWithId(R.id.cardView2)));
    }

    @Test
    public void user_is_warned_invalid_add_food_input() {
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(0));
        onView(ViewMatchers.withId(R.id.openButton)).perform(click());
        onView(isRoot()).perform(waitId(R.id.addButton, 5000));
        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeNameInput)).check(matches(hasErrorText("input cannot be null and requires length between 1 and 9999")));
        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("bad food"));
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeCaloriesInput)).check(matches(hasErrorText("input cannot be null and requires length between 1 and 9999")));
        onView(ViewMatchers.withId(R.id.dialogRecipeCaloriesInput)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("10000"));
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeCaloriesInput)).check(matches(hasErrorText("input requires value between 0 and 9999")));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("5"));
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeQuantityInput)).check(matches(hasErrorText("input cannot be null and requires length between 1 and 9999")));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(clearText(), typeText("10000"));
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeQuantityInput)).check(matches(hasErrorText("input requires value between 0 and 9999")));
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnCancel)).perform(scrollTo(), click());

        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(1));
        onView(isRoot()).perform(waitId(R.id.addButton, 5000));
        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeNameInput)).check(matches(hasErrorText("input cannot be null and requires length between 1 and 9999")));
        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("bad meal"));
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeCaloriesInput)).check(matches(hasErrorText("input cannot be null and requires length between 1 and 9999")));
        onView(ViewMatchers.withId(R.id.dialogRecipeCaloriesInput)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("10000"));
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeCaloriesInput)).check(matches(hasErrorText("input requires value between 0 and 9999")));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("5"));
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeQuantityInput)).check(matches(hasErrorText("input cannot be null and requires length between 1 and 9999")));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(clearText(), typeText("10000"));
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeQuantityInput)).check(matches(hasErrorText("input requires value between 0 and 9999")));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(clearText(), typeText("5"));
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.ingredientError)).check(matches(hasErrorText("input list cannot be null and requires length between 0 and 9999")));
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnCancel)).perform(scrollTo(), click());

        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(2));
        onView(isRoot()).perform(waitId(R.id.addButton, 5000));
        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeNameInput)).check(matches(hasErrorText("input cannot be null and requires length between 1 and 9999")));
        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("bad drink"));
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeCaloriesInput)).check(matches(hasErrorText("input cannot be null and requires length between 1 and 9999")));
        onView(ViewMatchers.withId(R.id.dialogRecipeCaloriesInput)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("10000"));
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeCaloriesInput)).check(matches(hasErrorText("input requires value between 0 and 9999")));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("5"));
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeQuantityInput)).check(matches(hasErrorText("input cannot be null and requires length between 1 and 9999")));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(clearText(), typeText("10000"));
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.dialogRecipeQuantityInput)).check(matches(hasErrorText("input requires value between 0 and 9999")));
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnCancel)).perform(scrollTo(), click());
    }

    private void cleanup() {
        DataAccess hsql = DBSelector.getSharedDB();
        hsql.removeTestData();
    }

    @Test
    public void user_can_add_food() {
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(0));
        onView(ViewMatchers.withId(R.id.openButton)).perform(click());
        onView(isRoot()).perform(waitId(R.id.addButton, 5000));
        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Test McRib"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("333"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("777"));
        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.dialogRecipeSpinner)).check(matches(withSpinnerText(containsString("tbsp"))));

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test McRib"))));

        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Celery"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("0"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("1"));
        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("oz")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.dialogRecipeSpinner)).check(matches(withSpinnerText(containsString("oz"))));
        onView(withId(R.id.isAlcoholic)).perform(click());
        onView(withId(R.id.isSpicy)).perform(click());
        onView(withId(R.id.isVegan)).perform(click());
        onView(withId(R.id.isVegetarian)).perform(click());
        onView(withId(R.id.isGluteenFree)).perform(click());

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test McRib"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Celery"))));

        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Something else"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("9999"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("9999"));
        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.dialogRecipeSpinner)).check(matches(withSpinnerText(containsString("tbsp"))));
        onView(withId(R.id.isAlcoholic)).perform(click());
        onView(withId(R.id.isVegan)).perform(click());
        onView(withId(R.id.isGluteenFree)).perform(click());

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test McRib"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Celery"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Something else"))));

        cleanup();
    }

    @Test
    public void user_can_add_meal() {
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(1));
        onView(ViewMatchers.withId(R.id.openButton)).perform(click());

        onView(isRoot()).perform(waitId(R.id.addButton, 5000));
        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Test McRib"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("333"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("777"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());

        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withId(R.id.imageView4))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.recipeRecyclerView)).perform(click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(2, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(2, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test McRib"))));

        //add one where contents are modified

        //add one where contents are removed

        //add one with many

        //add one where adds ingredients from Drink

        //add one where adds ingredients from Meal

        cleanup();
    }

    @Test
    public void user_can_add_simple_drinks() {
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(2));
        onView(ViewMatchers.withId(R.id.openButton)).perform(click());
        onView(isRoot()).perform(waitId(R.id.addButton, 5000));
        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Test Drink1"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("333"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("777"));
        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.dialogRecipeSpinner)).check(matches(withSpinnerText(containsString("tbsp"))));

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test Drink1"))));

        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Test Drink2"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("0"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("1"));
        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("oz")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.dialogRecipeSpinner)).check(matches(withSpinnerText(containsString("oz"))));
        onView(withId(R.id.isAlcoholic)).perform(click());
        onView(withId(R.id.isSpicy)).perform(click());
        onView(withId(R.id.isVegan)).perform(click());
        onView(withId(R.id.isVegetarian)).perform(click());
        onView(withId(R.id.isGluteenFree)).perform(click());

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test Drink1"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test Drink2"))));

        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Test Something else"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("9999"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("9999"));
        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.dialogRecipeSpinner)).check(matches(withSpinnerText(containsString("tbsp"))));
        onView(withId(R.id.isAlcoholic)).perform(click());
        onView(withId(R.id.isVegan)).perform(click());
        onView(withId(R.id.isGluteenFree)).perform(click());

        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test Drink1"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test Drink2"))));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test Something else"))));

        cleanup();
    }

    @Test
    public void user_can_add_complex_drinks() {
        onView(ViewMatchers.withId(R.id.tabLayout)).perform(selectTabAtPosition(2));
        onView(ViewMatchers.withId(R.id.openButton)).perform(click());

        onView(isRoot()).perform(waitId(R.id.addButton, 5000));
        onView(ViewMatchers.withId(R.id.addButton)).perform(click());

        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("Test2 sugar water"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("5"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("777"));
        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());

        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withId(R.id.imageView4))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.recipeRecyclerView)).perform(click());

        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(ViewMatchers.withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test2 sugar water"))));

        //add one where contents are modified
        onView(withId(R.id.dialogRecipeNameInput)).perform(clearText(), typeText("modDrink"));
        onView(withId(R.id.dialogRecipeCaloriesInput)).perform(clearText(), typeText("0"));
        onView(withId(R.id.dialogRecipeQuantityInput)).perform(scrollTo(), clearText(), typeText("1"));
        onView(withId(R.id.dialogRecipeSpinner)).perform(scrollTo(), click());
        onView(withText("tbsp")).inRoot(isPlatformPopup()).perform(click());

        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withId(R.id.imageView4))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.recipeRecyclerView)).perform(click());

        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.cardView2)));
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.addToPlannerBtn2)));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Pear"))));
        onView(withId(R.id.dialogRecipeIngredientsInput)).perform(click());
        //click on edit
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.btnModifyMeal)));

        //dialog pops up and rewrite quantity and
        //onView(withId(R.id.dialogRecipeBtnOk)).perform(scrollTo(withId(R.id.inputQty)), click());
        onView(withId(R.id.recipeRecyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Test2 sugar water"))));



        //add one where contents are removed

        //add one with many

        //add one where adds ingredients from Drink

        //add one where adds ingredients from Meal

        //add one where substitute is checked

        cleanup();
    }
}

//add testing for invalid ingredients

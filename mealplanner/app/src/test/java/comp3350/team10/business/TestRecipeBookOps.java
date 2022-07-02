// package comp3350.team10.business;

// import static org.junit.jupiter.api.Assertions.*;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Nested;
// import org.junit.jupiter.api.Test;

// import java.util.Calendar;
// import java.util.ArrayList;

// import comp3350.team10.business.MealDiaryOps;
// import comp3350.team10.business.RecipeBookOps;
// import comp3350.team10.objects.Constant;
// import comp3350.team10.objects.Drink;
// import comp3350.team10.objects.Edible;
// 
// import comp3350.team10.objects.Meal;
// import comp3350.team10.persistence.*;

// public class TestRecipeBookOps {

//     @Nested
//     @DisplayName("Simple tests")
//     class Test_Simple {
//         DataAccessStub db;
//         RecipeBookOps recipeBookOps;

//         @BeforeEach
//         void setup() {
//            SharedDB.start("test");
//            db = SharedDB.getSharedDB();
//            recipeBookOps = new RecipeBookOps(db);
//         }

//         @Test
//         void testGetRecipes() {
//             assertNotNull(recipeBookOps.getFoodRecipes());
//             assertNotNull(recipeBookOps.getDrinkRecipes());
//             assertNotNull(recipeBookOps.getMealRecipes());
//         }

//         @Test
//         void testFoodRecipesValid() {
//             ArrayList<Edible> foodRecipes = recipeBookOps.getFoodRecipes();

//             for(int i = 0; i < foodRecipes.size(); i++) {
//                 assertNotNull(foodRecipes.get(i));
//                 assertTrue(foodRecipes.get(i) instanceof Food);
//             }
//         }

//         @Test
//         void testDrinkRecipesValid() {
//             ArrayList<Edible> drinkRecipes = recipeBookOps.getDrinkRecipes();

//             for(int i = 0; i < drinkRecipes.size(); i++) {
//                 assertNotNull(drinkRecipes.get(i));
//                 assertTrue(drinkRecipes.get(i) instanceof Drink);
//             }
//         }

//         @Test
//         void testMealRecipesValid() {
//             ArrayList<Edible> mealRecipes = recipeBookOps.getMealRecipes();

//             for(int i = 0; i < mealRecipes.size(); i++) {
//                 assertNotNull(mealRecipes.get(i));
//                 assertTrue(mealRecipes.get(i) instanceof Meal);
//             }
//         }

//         @Test
//         void testAddFood() {
//             ArrayList<Edible> foodRecipes = recipeBookOps.getFoodRecipes();
//             int prevLength = foodRecipes.size();

//             recipeBookOps.addFood("food", 1, 5, Edible.Unit.g, 5);
//             foodRecipes = recipeBookOps.getFoodRecipes();
//             assertNotNull(foodRecipes.get(foodRecipes.size() - 1));
//             assertEquals(prevLength, foodRecipes.size() - 1);
//         }

//         @Test
//         void testAddDrink() {
//             ArrayList<Edible> drinkRecipes = recipeBookOps.getDrinkRecipes();
//             int prevLength = drinkRecipes.size();

//             recipeBookOps.addDrink("drink", 1, 5, "instructions", "ingredients", Edible.Unit.g, 5);
//             drinkRecipes = recipeBookOps.getDrinkRecipes();
//             assertNotNull(drinkRecipes.get(drinkRecipes.size() - 1));
//             assertEquals(prevLength, drinkRecipes.size() - 1);
//         }

//         @Test
//         void testAddMeal() {
//             ArrayList<Edible> mealRecipes = recipeBookOps.getMealRecipes();
//             int prevLength = mealRecipes.size();

//             recipeBookOps.addMeal("meal", 1, 5, "ingredients", "instructions", Edible.Unit.g, 5);
//             mealRecipes = recipeBookOps.getMealRecipes();
//             assertNotNull(mealRecipes.get(mealRecipes.size() - 1));
//             assertEquals(prevLength, mealRecipes.size() - 1);
//         }
//     }

//     @Nested
//     @DisplayName("Complex tests") //only proper one increases
//     class Test_Complex {
//         DataAccessStub db;
//         RecipeBookOps recipeBookOps;

//         @BeforeEach
//         void setup() {
//             SharedDB.start("test");
//             db = SharedDB.getSharedDB();
//             recipeBookOps = new RecipeBookOps(db);
//         }

//         @Test
//         void testDuplicateFoods() {
//             ArrayList<Edible> foodRecipes = recipeBookOps.getFoodRecipes();
//             int prevLength = foodRecipes.size();
//             int iterations = 5;

//             for(int i = 0; i < iterations; i++) {
//                 recipeBookOps.addFood("food", 1, 5, Edible.Unit.g, 5);
//             }

//             foodRecipes = recipeBookOps.getFoodRecipes();

//             for(int i = 0; i < iterations; i++) {
//                 assertNotNull(foodRecipes.get(foodRecipes.size() - 1 - i));
//             }

//             assertEquals(prevLength, foodRecipes.size() - iterations);
//         }

//         @Test
// 		void testMultiAddFood() {
//             ArrayList<Edible> foodRecipes = recipeBookOps.getFoodRecipes();
//             int prevLength = foodRecipes.size();
//             int startPoint = 50;
//             int iterations = 5;

//             for(int i = startPoint; i < startPoint + iterations; i++) {
//                 recipeBookOps.addFood("food", 1, i, Edible.Unit.g, 5);
//             }

//             foodRecipes = recipeBookOps.getFoodRecipes();

//             for(int i = 0; i < iterations; i++) {
//                 assertNotNull(foodRecipes.get(foodRecipes.size() - 1 - i));
//             }

//             assertEquals(prevLength, foodRecipes.size() - iterations);
//         }

//         @Test
// 		void testDuplicateDrinks() {
//             ArrayList<Edible> drinkRecipes = recipeBookOps.getDrinkRecipes();
//             int prevLength = drinkRecipes.size();
//             int iterations = 5;

//             for(int i = 0; i < iterations; i++) {
//                 recipeBookOps.addDrink("drink", 1, 5, "ingredients", "instructions", Edible.Unit.g, 5);
//             }

//             drinkRecipes = recipeBookOps.getDrinkRecipes();

//             for(int i = 0; i < iterations; i++) {
//                 assertNotNull(drinkRecipes.get(drinkRecipes.size() - 1 - i));
//             }

//             assertEquals(prevLength, drinkRecipes.size() - iterations);
//         }

//         @Test
// 		void testMultiAddDrink() {
//             ArrayList<Edible> drinkRecipes = recipeBookOps.getDrinkRecipes();
//             int prevLength = drinkRecipes.size();
//             int startPoint = 100;
//             int iterations = 5;

//             for(int i = startPoint; i < startPoint + iterations; i++) {
//                 recipeBookOps.addDrink("drink", 1, i, "ingredients", "instructions", Edible.Unit.g, 5);
//             }

//             drinkRecipes = recipeBookOps.getDrinkRecipes();

//             for(int i = 0; i < iterations; i++) {
//                 assertNotNull(drinkRecipes.get(drinkRecipes.size() - 1 - i));
//             }

//             assertEquals(prevLength, drinkRecipes.size() - iterations);
//         }

//         @Test
// 		void testDuplicateMeals() {
//             ArrayList<Edible> mealRecipes = recipeBookOps.getMealRecipes();
//             int prevLength = mealRecipes.size();
//             int iterations = 5;

//             for(int i = 0; i < iterations; i++) {
//                 recipeBookOps.addMeal("meal", 1, 5, "ingredients", "instructions", Edible.Unit.g, 5);
//             }

//             mealRecipes = recipeBookOps.getMealRecipes();

//             for(int i = 0; i < iterations; i++) {
//                 assertNotNull(mealRecipes.get(mealRecipes.size() - 1 - i));
//             }

//             assertEquals(prevLength, mealRecipes.size() - iterations);
//         }

//         @Test
// 		void testMultiAddMeal() {
//             ArrayList<Edible> mealRecipes = recipeBookOps.getMealRecipes();
//             int prevLength = mealRecipes.size();
//             int startPoint = 150;
//             int iterations = 5;

//             for(int i = startPoint; i < startPoint + iterations; i++) {
//                 recipeBookOps.addMeal("meal", 1, i, "ingredients", "instructions", Edible.Unit.g, 5);
//             }

//             mealRecipes = recipeBookOps.getMealRecipes();

//             for(int i = 0; i < iterations; i++) {
//                 assertNotNull(mealRecipes.get(mealRecipes.size() - 1 - i));
//             }

//             assertEquals(prevLength, mealRecipes.size() - iterations);
//         }

//         @Test
// 		void testAddRecipesToMultipleGroups() {
//             ArrayList<Edible> foodRecipes = recipeBookOps.getFoodRecipes();
//             int prevFoodLength = foodRecipes.size();
//             int startPoint = 50;
//             int iterations = 5;

//             for(int i = startPoint; i < startPoint + iterations; i++) {
//                 recipeBookOps.addFood("food", i, 5, Edible.Unit.g, 5);
//             }

//             foodRecipes = recipeBookOps.getFoodRecipes();


//             ArrayList<Edible> drinkRecipes = recipeBookOps.getDrinkRecipes();
//             int prevDrinkLength = drinkRecipes.size();

//             for(int i = startPoint; i < startPoint + iterations; i++) {
//                 recipeBookOps.addDrink("drink", i, 5, "ingredients", "instructions", Edible.Unit.g, 5);
//             }

//             drinkRecipes = recipeBookOps.getDrinkRecipes();


//             ArrayList<Edible> mealRecipes = recipeBookOps.getMealRecipes();
//             int prevMealLength = mealRecipes.size();

//             for(int i = startPoint; i < startPoint + iterations; i++) {
//                 recipeBookOps.addMeal("meal", i, 5, "ingredients", "instructions", Edible.Unit.g, 5);
//             }

//             mealRecipes = recipeBookOps.getMealRecipes();

            
//             assertEquals(prevFoodLength, foodRecipes.size() - iterations);
//             assertEquals(prevDrinkLength, drinkRecipes.size() - iterations);
//             assertEquals(prevMealLength, mealRecipes.size() - iterations);
//         }
// 	}

//     @Nested
//     @DisplayName("Empty tests")
//     class Test_Empty {
//         DataAccessStub db;
//         RecipeBookOps recipeBookOps;

//         @BeforeEach
//         void setup() {
//             SharedDB.start("test");
//             db = SharedDB.getSharedDB();
//             recipeBookOps = new RecipeBookOps(db);
//         }

//         @Test
//         void testAddIncompleteFood() {
//             ArrayList<Edible> foodRecipes = recipeBookOps.getFoodRecipes();
//             int prevLength = foodRecipes.size();

//             recipeBookOps.addFood(null, 1, 5, Edible.Unit.g, 5);
//             recipeBookOps.addFood("", -1, 5, Edible.Unit.g, 5);
//             recipeBookOps.addFood("food", 1, -1, Edible.Unit.g, 5);
//             recipeBookOps.addFood("food", 1, 5, null, 5);
//             recipeBookOps.addFood("food", 1, 5, Edible.Unit.g, -1);
//             recipeBookOps.addFood(null, -1, -1, null, -1);

//             foodRecipes = recipeBookOps.getFoodRecipes();

//             assertEquals(prevLength, foodRecipes.size());
//         }

//         @Test
//         void testAddIncompleteDrink() {
//             ArrayList<Edible> drinkRecipes = recipeBookOps.getDrinkRecipes();
//             int prevLength = drinkRecipes.size();

//             recipeBookOps.addDrink(null, 1, 5, "instructions", "ingredients", Edible.Unit.g, 5);
//             recipeBookOps.addDrink("", 1, 5, "instructions", "ingredients", Edible.Unit.g, 5);
//             recipeBookOps.addDrink("drink", -1, 5, "instructions", "ingredients", Edible.Unit.g, 5);
//             recipeBookOps.addDrink("drink", 1, -1, null, "ingredients", Edible.Unit.g, 5);
//             recipeBookOps.addDrink("drink", 1, 5, "instructions", null, Edible.Unit.g, 5);
//             recipeBookOps.addDrink("drink", 1, 5, "instructions", "ingredients", null, 5);
//             recipeBookOps.addDrink("drink", 1, 5, "instructions", "ingredients", Edible.Unit.g, -1);
//             recipeBookOps.addDrink(null, -1, -1, null, null, null, -1);

//             drinkRecipes = recipeBookOps.getDrinkRecipes();

//             assertEquals(prevLength, drinkRecipes.size());
//         }

//         @Test
//         void testIncompleteMeal() {
//             ArrayList<Edible> mealRecipes = recipeBookOps.getMealRecipes();
//             int prevLength = mealRecipes.size();

//             recipeBookOps.addMeal(null, 1, 5, "ingredients", "instructions", Edible.Unit.g, 5);
//             recipeBookOps.addMeal("", 1, 5, "ingredients", "instructions", Edible.Unit.g, 5);
//             recipeBookOps.addMeal("meal", -1, 5, "ingredients", "instructions", Edible.Unit.g, 5);
//             recipeBookOps.addMeal("meal", 1, -1, "ingredients", "instructions", Edible.Unit.g, 5);
//             recipeBookOps.addMeal("meal", 1, 5, null, "instructions", Edible.Unit.g, 5);
//             recipeBookOps.addMeal("meal", 1, 5, "ingredients", null, Edible.Unit.g, 5);
//             recipeBookOps.addMeal("meal", 1, 5, "ingredients", "instructions", null, 5);
//             recipeBookOps.addMeal("meal", 1, 5, "ingredients", "instructions", Edible.Unit.g, -1);
//             recipeBookOps.addMeal(null, -1, -1, null, null, null, -1);
            
//             mealRecipes = recipeBookOps.getMealRecipes();

//             assertEquals(prevLength, mealRecipes.size());
//         }
//     }

//     @Nested
//     @DisplayName("Edge case tests")
//     class Test_EdgeCases {
//         DataAccessStub db;
//         RecipeBookOps recipeBookOps;

//         @BeforeEach
//         void setup() {
//             SharedDB.start("test");
//             db = SharedDB.getSharedDB();
//             recipeBookOps = new RecipeBookOps(db);
//         }

//         void testAddFoods() {
//             ArrayList<Edible> mealRecipes = recipeBookOps.getMealRecipes();
//             int prevLength = mealRecipes.size();

//             recipeBookOps.addFood("\n", 1, 1, Edible.Unit.cups, 5);
//             recipeBookOps.addFood(" ", 1, 1, Edible.Unit.cups, 5);
//             recipeBookOps.addFood("food", Constant.ENTRY_MAX_VALUE, 1, Edible.Unit.cups, 5);
//             recipeBookOps.addFood("food", 1, Constant.ENTRY_MAX_VALUE, Edible.Unit.cups, 5);
//             recipeBookOps.addFood("food", 1, 1, Edible.Unit.cups, Constant.ENTRY_MAX_VALUE);
//             recipeBookOps.addFood("food", Constant.ENTRY_MAX_VALUE, Constant.ENTRY_MAX_VALUE, Edible.Unit.cups, Constant.ENTRY_MAX_VALUE);

//             mealRecipes = recipeBookOps.getMealRecipes();

//             assertEquals(prevLength, mealRecipes.size() - 6);
//         }

//         void testAddDrinks() {
//             ArrayList<Edible> mealRecipes = recipeBookOps.getMealRecipes();
//             int prevLength = mealRecipes.size();

//             recipeBookOps.addDrink("\n", 1, 1, "instructions", "ingredients", Edible.Unit.cups, 5);
//             recipeBookOps.addDrink(" ", 1, 1, "instructions", "ingredients", Edible.Unit.cups, 5);
//             recipeBookOps.addDrink("drink", Constant.ENTRY_MAX_VALUE, 1, "instructions", "ingredients", Edible.Unit.cups, 5);
//             recipeBookOps.addDrink("drink", 1, Constant.ENTRY_MAX_VALUE, "instructions", "ingredients", Edible.Unit.cups, 5);
//             recipeBookOps.addDrink("drink", 1, 1, "", "ingredients", Edible.Unit.cups, 5);
//             recipeBookOps.addDrink("drink", 1, 1, "instructions", "", Edible.Unit.cups, 5);
//             recipeBookOps.addDrink("drink", 1, 1, "\n", "ingredients", Edible.Unit.cups, 5);
//             recipeBookOps.addDrink("drink", 1, 1, "instructions", "\n", Edible.Unit.cups, 5);
//             recipeBookOps.addDrink("drink", 1, 1, "\n", "\n", Edible.Unit.cups, 5);
//             recipeBookOps.addDrink("drink", 1, 1, "\n", "", Edible.Unit.cups, 5);
//             recipeBookOps.addDrink("drink", 1, 1, "", "instructions", Edible.Unit.cups, 5);
//             recipeBookOps.addDrink("drink", 1, 1, "", "", Edible.Unit.cups, 5);
//             recipeBookOps.addDrink("drink", 1, 1, "instructions", "", Edible.Unit.cups, 5);
//             recipeBookOps.addDrink("drink", 1, 1, "instructions", "ingredients", Edible.Unit.cups, Constant.ENTRY_MAX_VALUE);
//             recipeBookOps.addDrink("drink", 1, 1, "instructions", "ingredients", Edible.Unit.cups, 1);
//             recipeBookOps.addDrink("drink", Constant.ENTRY_MAX_VALUE, Constant.ENTRY_MAX_VALUE, "instructions", "ingredients", Edible.Unit.cups, Constant.ENTRY_MAX_VALUE);
            
//             mealRecipes = recipeBookOps.getMealRecipes();

//             assertEquals(prevLength, mealRecipes.size() - 16);
//         }

//         void testAddMeals() {
//             ArrayList<Edible> mealRecipes = recipeBookOps.getMealRecipes();
//             int prevLength = mealRecipes.size();

//             recipeBookOps.addMeal("\n", 1, 1, "instructions", "ingredients", Edible.Unit.cups, 5);
//             recipeBookOps.addMeal(" ", 1, 1, "instructions", "ingredients", Edible.Unit.cups, 5);
//             recipeBookOps.addMeal("meal", Constant.ENTRY_MAX_VALUE, 1, "instructions", "ingredients", Edible.Unit.cups, 5);
//             recipeBookOps.addMeal("meal", 1, Constant.ENTRY_MAX_VALUE, "instructions", "ingredients", Edible.Unit.cups, 5);
//             recipeBookOps.addMeal("meal", 1, 1, "", "ingredients", Edible.Unit.cups, 5);
//             recipeBookOps.addMeal("meal", 1, 1, "instructions", "", Edible.Unit.cups, 5);
//             recipeBookOps.addMeal("meal", 1, 1, "\n", "ingredients", Edible.Unit.cups, 5);
//             recipeBookOps.addMeal("meal", 1, 1, "instructions", "\n", Edible.Unit.cups, 5);
//             recipeBookOps.addMeal("meal", 1, 1, "instructions", "ingredients", Edible.Unit.cups, Constant.ENTRY_MAX_VALUE);
//             recipeBookOps.addMeal("meal", Constant.ENTRY_MAX_VALUE, Constant.ENTRY_MAX_VALUE, "instructions", "ingredients", Edible.Unit.cups, Constant.ENTRY_MAX_VALUE);
//             recipeBookOps.addMeal("meal", 1, 1, "\n", "ingredients", Edible.Unit.cups, 5);
//             recipeBookOps.addMeal("meal", 1, 1, "\n", "\n", Edible.Unit.cups, 5);
//             recipeBookOps.addMeal("meal", 1, 1, "", " ", Edible.Unit.cups, 5);
//             recipeBookOps.addMeal("meal", 1, 1, " ", "", Edible.Unit.cups, 5);
//             recipeBookOps.addMeal("meal", 1, 1, "", "", Edible.Unit.cups, 5);
//             recipeBookOps.addMeal("meal", 1, 1, " ", " ", Edible.Unit.cups, 5);
//             recipeBookOps.addMeal("meal", 1, 1, "", "ingredients", Edible.Unit.cups, 5);
//             recipeBookOps.addMeal("meal", 1, 1, "instructions", "", Edible.Unit.cups, 5);
            
            
//             mealRecipes = recipeBookOps.getMealRecipes();

//             assertEquals(prevLength, mealRecipes.size() - 18);
//         }
//     }
// }
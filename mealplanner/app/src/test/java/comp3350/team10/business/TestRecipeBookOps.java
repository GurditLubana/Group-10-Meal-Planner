package comp3350.team10.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;

import comp3350.team10.application.Main;
import comp3350.team10.objects.DrinkIngredient;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.Ingredient;
import comp3350.team10.persistence.DataAccessStub;
import comp3350.team10.persistence.RecipeDBInterface;
import comp3350.team10.persistence.SharedDB;

public class TestRecipeBookOps {

}
    @Nested
    @DisplayName("Simple Tests")
    class simpleTests {
        private RecipeBookOps ops;


        @BeforeEach
        void setup() {
            Main.startUp();
            ops = new RecipeBookOps();


        }

        @Test
        @DisplayName("Checking if getters are returning something")
        void testGetRecipes()
        {
            assert(ops.getFoodRecipes().size() > 0);
            assert(ops.getDrinkRecipes().size() > 0);
            assert(ops.getMealRecipes().size() > 0);
        }

        @Test
        @DisplayName("Testing getIsCustom() ")
        void testIfCustomRecipes()
        {

            assertFalse(ops.getFoodRecipes().get(0).getIsCustom());
            assertFalse(ops.getFoodRecipes().get(1).getIsCustom());
            assertFalse(ops.getFoodRecipes().get(2).getIsCustom());
            assertFalse(ops.getFoodRecipes().get(3).getIsCustom());
            assertFalse(ops.getDrinkRecipes().get(0).getIsCustom());
            assertFalse(ops.getDrinkRecipes().get(1).getIsCustom());
            assertFalse(ops.getDrinkRecipes().get(2).getIsCustom());
            assertFalse(ops.getMealRecipes().get(0).getIsCustom());
            assertFalse(ops.getMealRecipes().get(1).getIsCustom());
            assertFalse(ops.getMealRecipes().get(2).getIsCustom());

            ops.getMealRecipes().get(0).setCustom(true);
            ops.getMealRecipes().get(1).setCustom(true);
            ops.getDrinkRecipes().get(0).setCustom(true);
            ops.getDrinkRecipes().get(1).setCustom(true);
            ops.getFoodRecipes().get(0).setCustom(true);
            ops.getFoodRecipes().get(1).setCustom(true);

            assertTrue(ops.getFoodRecipes().get(0).getIsCustom());
            assertTrue(ops.getFoodRecipes().get(1).getIsCustom());
            assertTrue(ops.getDrinkRecipes().get(0).getIsCustom());
            assertTrue(ops.getDrinkRecipes().get(1).getIsCustom());
            assertTrue(ops.getMealRecipes().get(0).getIsCustom());
            assertTrue(ops.getMealRecipes().get(1).getIsCustom());

        }

        @Test
        @DisplayName("Test of adding food items")
        void testAddFood()
        {
            int initialSize = ops.getFoodRecipes().size();

            String description = "dfhjdhsgsdjjkds\nidfdshf\ndfh";

            ops.addFood("Pan Cake",description, 2, Edible.Unit.g, 350, 400, 74, 89,
                    false, true,false, true, false, null);

            assertEquals(initialSize + 1,ops.getFoodRecipes().size());

        }

        @Test
        @DisplayName("Test of adding meal items")
        void testAddMeals()
        {
            int initialSize = ops.getMealRecipes().size();

            String description = "dfhjdhsgsdjjkds\nidfdshf\ndfh";
            String instruction = "sdfhosdhgl\ngjkdsjggk\ndfdgdg";
            ArrayList<Ingredient> ingredients = new ArrayList<>();

            ops.addMeal("Pan Cake",description, 2, Edible.Unit.g, null, instruction,ingredients);

            assertEquals(initialSize + 1,ops.getMealRecipes().size());

        }

        @Test
        @DisplayName("Test of adding meal items")
        void testAddPreparedDrinks()
        {
            int initialSize = ops.getDrinkRecipes().size();

            String description = "dfhjdhsgsdjjkds\nidfdshf\ndfh";
            String instruction = "sdfhosdhgl\ngjkdsjggk\ndfdgdg";
            ArrayList<DrinkIngredient> ingredients = new ArrayList<>();

            ops.addPreparedDrink("Banana Smoothy",description, 2, Edible.Unit.ml, null, instruction,ingredients);

            assertEquals(initialSize + 1,ops.getDrinkRecipes().size());

        }


        @Test
        @DisplayName("Test of adding meal items")
        void testAddSimpleDrinks()
        {
            int initialSize = ops.getDrinkRecipes().size();

            String description = "dfhjdhsgsdjjkds\nidfdshf\ndfh";
            ArrayList<DrinkIngredient> ingredients = new ArrayList<>();

//            addSimpleDrink(String name, String desc, int qty, Edible.Unit unit, int calories, int protein, int carbs, int fat,
//            boolean alcoholic, boolean spicy, boolean vegan, boolean vegetarian, boolean glutenFree, byte[] photo)

            ops.addSimpleDrink("Pepsi",description, 2, Edible.Unit.ml, 350, 400, 74, 89,
                    false, true,false, true, false, null);

            assertEquals(initialSize + 1,ops.getDrinkRecipes().size());

        }


        @Test
        @DisplayName("Test of adding Duplicate items")
        void testDuplicateItems()
        {
            int initialSize = 5;
            String description = "dfhjdhsgsdjjkds\nidfdshf\ndfh";
            ops.addFood("Pizza",description, 3, Edible.Unit.g, 250, 500, 70, 89,
            false, true,false, true, false, null);

            ops.addFood("Pizza",description, 3, Edible.Unit.g, 250, 500, 70, 89,
                    false, true,false, true, false, null);

            ops.addFood("Pizza",description, 3, Edible.Unit.g, 250, 500, 70, 89,
                    false, true,false, true, false, null);


            //We want to be able to add duplicate items in our recipe book

            assertEquals(initialSize + 3, ops.getFoodRecipes().size());
        }



    }


    @Nested
    @DisplayName("Some complex tests")
    class complexTests{

        private RecipeBookOps ops;


        @BeforeEach
        void setup() {
            Main.startUp();
            ops = new RecipeBookOps();
        }

        @Test
        @DisplayName("Testing some edge cases for addFood method")
        void testAddFoods()
        {
            int initialSize = ops.getFoodRecipes().size();

            String description = "dfhjdhsgsdjjkds\nidfdshf\ndfh";

            ops.addFood("food",description, 2, Edible.Unit.g, 3, 40, 74, 89,
                    false, true,false, true, false, null);

            ops.addFood("food","\n", 20, Edible.Unit.g, 3, 444, 74, 89,
                    false, true,true, false, false, null);

            ops.addFood("food2","\n\n", 2, Edible.Unit.g, 99, 55, 74, 89,
                    false, true,false, true, false, null);

            ops.addFood("food2"," ", 2, Edible.Unit.g, 0, 45, 74, 89,
                    false, true,false, true, false, null);

            ops.addFood("food2","             hello world", 2, Edible.Unit.ml, 99, 40, 74, 89,
                    false, true,false, true, false, null);

            ops.addFood("food2","\n\n", 2, Edible.Unit.g, 99, 405, 74, 89,
                    false, false,false, false, false, null);

            assertEquals(initialSize + 6,ops.getFoodRecipes().size());
        }

        @Test
        @DisplayName("Adding calories more than the limit")
        void testMaxCalories()
        {
            int initialSize = ops.getDrinkRecipes().size();

            String description = "dfhjdhsgsdjjkds\nidfdshf\ndfh";
            String instruction = "sdfhosdhgl\ngjkdsjggk\ndfdgdg";
            ArrayList<DrinkIngredient> ingredients = new ArrayList<>();

            // calories = 0 (minimum value)
            ops.addSimpleDrink("drink",description, 30, Edible.Unit.ml, 0, 400, 74, 89,
                    false, true,false, true, false, null);

            //calories = 9999 (Maximum value)
            ops.addSimpleDrink("drink",description, 2, Edible.Unit.ml, 350, 400, 74, 89,
                    false, true,false, true, false, null);

            ops.addPreparedDrink("Banana Smoothy",description, 2, Edible.Unit.ml, null, instruction,ingredients);

            assertEquals(initialSize + 3,ops.getDrinkRecipes().size());
        }



    }



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
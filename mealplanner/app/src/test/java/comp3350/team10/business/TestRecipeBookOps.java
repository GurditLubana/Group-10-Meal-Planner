package comp3350.team10.business;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import comp3350.team10.application.Main;
import comp3350.team10.objects.Drink;
import comp3350.team10.objects.DrinkIngredient;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.Ingredient;
import comp3350.team10.objects.Meal;

public class TestRecipeBookOps {

    @Nested
    @DisplayName("Simple Tests")
    class simpleTests {
        private RecipeBookOps ops;
        private String testString;

        @BeforeEach
        void setup() {
            Main.startUp();
            ops = new RecipeBookOps();
            testString = "dfhjdhsgsdjjkds\nidfdshf\ndfh";
        }

        @AfterEach
        void takedown() {
            Main.shutDown();
        }

        @Test
        @DisplayName("Checking if getters are returning something")
        void testGetRecipes() {
            assertTrue(ops.getFoodRecipes().size() > 0);
            assertTrue(ops.getDrinkRecipes().size() > 0);
            assertTrue(ops.getMealRecipes().size() > 0);
        }

        @Test
        @DisplayName("Checking all edibles returned are valid")
        void testFoodRecipesValid() {
            ArrayList<Edible> foodRecipes = ops.getFoodRecipes();

            for (int i = 0; i < foodRecipes.size(); i++) {
                assertTrue(foodRecipes.get(i) instanceof Edible);
            }
        }

        @Test
        @DisplayName("Checking all drinks returned are valid")
        void testDrinkRecipesValid() {
            ArrayList<Edible> drinkRecipes = ops.getDrinkRecipes();

            for (int i = 0; i < drinkRecipes.size(); i++) {
                assertTrue(drinkRecipes.get(i) instanceof Drink);
            }
        }

        @Test
        @DisplayName("Checking all meals returned are valid")
        void testMealRecipesValid() {
            ArrayList<Edible> mealRecipes = ops.getMealRecipes();

            for (int i = 0; i < mealRecipes.size(); i++) {
                assertTrue(mealRecipes.get(i) instanceof Meal);
            }
        }

        @Test
        @DisplayName("Tests adding a food edible in meal diary ops")
        void testAddFood() {
            int initialSize = ops.getFoodRecipes().size();

            ops.addFood("Pan Cake", testString, 2, Edible.Unit.g, 350, 400, 74, 89,
                    false, true, false, true, false, "photo");

            assertEquals(initialSize + 1, ops.getFoodRecipes().size());

        }

        @Test
        @DisplayName("Tests adding a meal recipe ops")
        void testAddMeals() {
            int initialSize = ops.getMealRecipes().size();

            ops.addMeal("Pan Cake", testString, 2, Edible.Unit.g, "photo", testString, new ArrayList<Ingredient>());

            assertEquals(initialSize + 1, ops.getMealRecipes().size());

        }

        @Test
        @DisplayName("Tests adding a drink in recipe ops")
        void testAddPreparedDrinks() {
            int initialSize = ops.getDrinkRecipes().size();

            ops.addPreparedDrink("Banana Smoothie", testString, 2, Edible.Unit.ml, "photo", testString, new ArrayList<DrinkIngredient>());
            assertEquals(initialSize + 1, ops.getDrinkRecipes().size());
        }

        @Test
        @DisplayName("Tests adding a simple drink (without ingredients) in recipe ops")
        void testAddSimpleDrinks() {
            int initialSize = ops.getDrinkRecipes().size();

            ops.addSimpleDrink("Pepsi", testString, 2, Edible.Unit.ml, 350, 400, 74, 89,
                    false, true, false, true, false, "photo");
            assertEquals(initialSize + 1, ops.getDrinkRecipes().size());
        }
    }


    @Nested
    @DisplayName("Complex tests")
    class complexTests {
        private RecipeBookOps ops;

        @BeforeEach
        void setup() {
            Main.startUp();
            ops = new RecipeBookOps();
        }

        @AfterEach
        void takedown() {
            Main.shutDown();
        }

        @Test
        @DisplayName("Testing adding more complex and multiple food")
        void testAddFoods() {
            int initialSize = ops.getFoodRecipes().size();

            ops.addFood("food", "dfhjdhsgsdjjkds\nidfdshf\ndfh", 2, Edible.Unit.g, 3, 9, 74, 89,
                    false, true, false, true, false, "null");
            ops.addFood("food", "\n", 20, Edible.Unit.g, 3, 444, 74, 89,
                    false, true, true, false, false, "null");
            ops.addFood("food2", "\n\n", 2, Edible.Unit.g, 99, 55, 74, 89,
                    false, true, false, true, false, "null");
            ops.addFood("food2", " ", 2, Edible.Unit.g, 0, 45, 74, 89,
                    false, true, false, true, false, "null");
            ops.addFood("food2", "             hello world", 2, Edible.Unit.ml, 99, 40, 74, 89,
                    false, true, false, true, false, "             hello world");
            ops.addFood("food2", "\n\n", 2, Edible.Unit.g, 99, 405, 74, 89,
                    false, false, false, false, false, "\"");
            ops.addFood("food2", "\t\t", 2, Edible.Unit.g, 99, 405, 74, 89,
                    false, false, false, false, false, "photopath");
            ops.addFood("food2", "\t\t", 2, Edible.Unit.g, 9, 9991, 9929, 9299,
                    false, false, false, false, false, "null");
            ops.addFood("food2", "Hello world", 2, Edible.Unit.g, 1, 1, 1, 1,
                    false, false, false, false, false, "jfifjn%");
            ops.addFood(" !!!!!!! * @@@ +++==", " ____", 2, Edible.Unit.g, 1, 1, 1, 1,
                    false, false, false, false, false, "photo");

            assertEquals(initialSize + 10, ops.getFoodRecipes().size());
        }


        @Test
        @DisplayName("Testing adding more complex and multiple drink (both with and without ingredients)")
        void testAddDrinks() {
            int initialSize = ops.getDrinkRecipes().size();

            ArrayList<DrinkIngredient> ingredients = new ArrayList<>();
            String description = "dfhjdhsgsdjjkds\n\n\nidfdshf\ndfh";
            String instruction = "sdfhosdhgl\ngjkdsjggk\ndfdgdg";

            Edible food = new Edible().initDetails(5, "name", "description", 5, Edible.Unit.g)
                    .initNutrition(5, 5, 5, 5)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "photo");

            DrinkIngredient ingredient = new DrinkIngredient();
            ingredient.init(food, 5, Edible.Unit.cups);
            ingredients.add(ingredient);

            ops.addSimpleDrink("drink", " hello world", 30, Edible.Unit.ml, 3, 400, 74, 89,
                    false, true, false, true, false, "null");
            ops.addSimpleDrink("drink", "                          678", 30, Edible.Unit.ml, 0, 400, 74, 89,
                    false, true, false, true, false, "null");
            ops.addSimpleDrink("drink", "12345", 30, Edible.Unit.ml, 0, 400, 74, 89,
                    false, true, false, false, false, "null");
            ops.addSimpleDrink("drink", "12345", 30, Edible.Unit.ml, 0, 400, 74, 89,
                    true, true, true, true, true, "null");

            ops.addPreparedDrink("Banana Smoothy", description, 2, Edible.Unit.ml, "null", instruction, ingredients);
            ops.addPreparedDrink(" ", description, 2, Edible.Unit.ml, "null", instruction, ingredients);
            ops.addPreparedDrink("\t\t\t\nSmoothy", description, 2, Edible.Unit.ml, "null", instruction, ingredients);
            ops.addPreparedDrink("9876!!", description, 1, Edible.Unit.ml, "null", instruction, ingredients);
            ops.addPreparedDrink("%^&%#$@", description, 9, Edible.Unit.ml, "null", instruction, ingredients);
            ops.addPreparedDrink("\n\n\n", description, 97, Edible.Unit.ml, "null", "\t\t\t", ingredients);
            ops.addPreparedDrink("drink", description, 8, Edible.Unit.ml, "null", "\n\n\n", ingredients);
            ops.addPreparedDrink("drink", description, 4, Edible.Unit.ml, "null", " __!1!@@\n", ingredients);
            ops.addPreparedDrink("drink", description, 2, Edible.Unit.ml, "null", "123344", ingredients);

            assertEquals(initialSize + 13, ops.getDrinkRecipes().size());
        }


        @Test
        @DisplayName("Testing adding more complex and multiple meals")
        void testAddMeals() {
            int initialSize = ops.getMealRecipes().size();

            ArrayList<Ingredient> ingredients = new ArrayList<>();
            String instruction = "sdfhosdhgl\ngjkdsjggk\ndfdgdg";

            Edible food = new Edible().initDetails(5, "name", "description", 5, Edible.Unit.g)
                    .initNutrition(5, 5, 5, 5)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "photo");

            DrinkIngredient ingredient = new DrinkIngredient();
            ingredient.init(food, 5, Edible.Unit.cups);
            ingredients.add(ingredient);

            ops.addMeal("A k k d ", "                      ", 2, Edible.Unit.serving, "null", instruction, ingredients);
            ops.addMeal(" . ", "hello world", 2, Edible.Unit.serving, "null", instruction, ingredients);
            ops.addMeal("meal", "\t\t\t\t", 2, Edible.Unit.serving, "null", instruction, ingredients);
            ops.addMeal("!@#$%^&*", "hello world\nfdd\n", 2, Edible.Unit.serving, "null", instruction, ingredients);
            ops.addMeal("meal", ")(**&&^^%%", 2, Edible.Unit.serving, "null", instruction, ingredients);
            ops.addMeal("1234566", "09876554", 2, Edible.Unit.serving, "null", instruction, ingredients);
            ops.addMeal(" . ", "hell0", 9999, Edible.Unit.serving, "null", instruction, ingredients);
            ops.addMeal(" . ", "h", 1, Edible.Unit.oz, "null", "\t\t\t", ingredients);
            ops.addMeal(" . ", "HELLO", 1, Edible.Unit.oz, "null", "\n\n\n", ingredients);
            ops.addMeal(" . ", "1!!!DDD", 1, Edible.Unit.oz, "null", " __!1!@@\n", ingredients);
            ops.addMeal(" . ", "ghkvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", 1, Edible.Unit.oz, "null", "123344", ingredients);

            assertEquals(initialSize + 11, ops.getMealRecipes().size());
        }
    }

//empty

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

    //edge
    //@Test
//         void testDuplicateFoods() {
//             ArrayList<Edible> foodRecipes = recipeBookOps.getFoodRecipes();
//             int prevLength = foodRecipes.size();
//             int iterations = 5;

//             for(int i = 0; i < iterations; i++) {
//                 recipeBookOps.addFood("food", 1, 5, Edible.Unit.g, 5);
//             }

//             foodRecipes = recipeBookOps.getFoodRecipes();

//             for(int i = 0; i < iterations; i++) {
//                 (foodRecipes.get(foodRecipes.size() - 1 - i));
//             }

//             assertEquals(prevLength, foodRecipes.size() - iterations);
//         }

// 		void testDuplicateDrinks() {
//             ArrayList<Edible> drinkRecipes = recipeBookOps.getDrinkRecipes();
//             int prevLength = drinkRecipes.size();
//             int iterations = 5;

//             for(int i = 0; i < iterations; i++) {
//                 recipeBookOps.addDrink("drink", 1, 5, "ingredients", "instructions", Edible.Unit.g, 5);
//             }

//             drinkRecipes = recipeBookOps.getDrinkRecipes();

//             for(int i = 0; i < iterations; i++) {
//                 (drinkRecipes.get(drinkRecipes.size() - 1 - i));
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
//                 (mealRecipes.get(mealRecipes.size() - 1 - i));
//             }

//             assertEquals(prevLength, mealRecipes.size() - iterations);
//         }

    void testAddFoods() {
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

        @Nested
        @DisplayName("Following tests should fail")
        class shouldFailTests {

            private RecipeBookOps ops;


            @BeforeEach
            void setup() {
                Main.startUp();
                ops = new RecipeBookOps();
            }

            @AfterEach
            void takedown() {
                Main.shutDown();
            }

            @Test
            @DisplayName("Testing some edge cases for addFood method")
            void testAddFoods() {


                assertThrows(IllegalArgumentException.class, () -> {
                    ops.addFood("food2", "\n\n", 2, Edible.Unit.g, 99, 40000, 74, 89,
                            false, false, false, false, false, null);

                    ops.addFood("food2", "\n\n", 2, Edible.Unit.g, 99999, 405, 74, 89,
                            false, false, false, false, false, null);

                    ops.addFood("food2", "\n\n", 2, Edible.Unit.g, 99, 405, 74888, 89,
                            false, false, false, false, false, null);

                    ops.addFood("food2", "\n\n", 2, Edible.Unit.g, 99, 405, 74, 89000,
                            false, false, false, false, false, null);

                    ops.addFood("food2", "\n\n", 2, Edible.Unit.g, 99, 405, 7, 89,
                            false, false, false, false, false, null);

                    ops.addFood("food2", "\n\n", 2, Edible.Unit.g, 99, 405, 74, 8,
                            false, false, false, false, false, null);
                });

            }


            @Test
            @DisplayName("Testing some edge cases for addDrinks method")
            void testAddDrinks() {

                String description = "dfhjdhsgsdjjkds\n\n\nidfdshf\ndfh";
                ArrayList<DrinkIngredient> ingredients = new ArrayList<>();

                assertThrows(IllegalArgumentException.class, () -> {

                    ops.addPreparedDrink("drink", description, 200000, Edible.Unit.ml, null, "123344", ingredients);
                    ops.addPreparedDrink("drink", description, 999999, Edible.Unit.ml, null, "123344", ingredients);
                    ops.addPreparedDrink("drink", description, 989898, Edible.Unit.ml, null, "123344", ingredients);
                    ops.addPreparedDrink("drink", description, 76543, Edible.Unit.ml, null, "123344", ingredients);


                });

            }

            @Test
            @DisplayName("Some Tests for addDrinks method that should throw an Exception")
            void testAddMeals() {

                String description = "dfhjdhsgsdjjkds\n\n\nidfdshf\ndfh";
                ArrayList<Ingredient> ingredients = new ArrayList<>();

                assertThrows(IllegalArgumentException.class, () -> {

                    ops.addMeal("failedMeal", "hello world", 20000, Edible.Unit.serving, null, "sfdhosdfh ", ingredients);
                    ops.addMeal("failedMeal", "hello world", 20, Edible.Unit.serving, null, "sfdhosdfh ", ingredients);
                });

            }

        }
    }
}
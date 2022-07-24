package comp3350.team10.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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
import comp3350.team10.persistence.SharedDB;

public class TestRecipeBookOps {

    @Nested
    @DisplayName("Simple Tests")
    class simpleTests {
        private RecipeBookOps ops;
        private String testString;

        @BeforeEach
        void setup() {
            SharedDB.start();
            SharedDB.startStub();
            ops = new RecipeBookOps();
            testString = "dfhjdhsgsdjjkds\nidfdshf\ndfh";
        }

        @AfterEach
        void takedown() {
            SharedDB.close();
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
            Edible currEdible = new Edible().initDetails(5, "name", "description", 5, Edible.Unit.g)
                    .initNutrition(5, 5, 5, 5)
                    .initCategories(true, true, true, true, true)
                    .initMetadata(true, "photo");
            Ingredient currIngredient = new Ingredient().init(currEdible, 5, Edible.Unit.cups);
            ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
            ingredients.add(currIngredient);

            ops.addMeal("Pan Cake", testString, 2, Edible.Unit.g, "photo", testString, ingredients);

            assertEquals(initialSize + 1, ops.getMealRecipes().size());

        }

        @Test
        @DisplayName("Tests adding a drink in recipe ops")
        void testAddPreparedDrinks() {
            int initialSize = ops.getDrinkRecipes().size();
            Edible currEdible = new Edible().initDetails(5, "name", "description", 5, Edible.Unit.g)
                    .initNutrition(5, 5, 5, 5)
                    .initCategories(true, true, true, true, true)
                    .initMetadata(true, "photo");
            DrinkIngredient currIngredient = (DrinkIngredient) new DrinkIngredient().init(currEdible, 5, Edible.Unit.cups);
            ArrayList<DrinkIngredient> ingredients = new ArrayList<DrinkIngredient>();
            ingredients.add(currIngredient);

            ops.addPreparedDrink("Banana Smoothie", testString, 2, Edible.Unit.ml, "photo", testString, ingredients);

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
            SharedDB.start();
            SharedDB.startStub();
            ops = new RecipeBookOps();
        }

        @AfterEach
        void takedown() {
            SharedDB.close();
        }

        @Test
        @DisplayName("Testing adding more complex and multiple food into even and odd lists")
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
        @DisplayName("Testing adding more complex and multiple drink (both with and without ingredients) into even and odd lists")
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
        @DisplayName("Testing adding more complex and multiple meals into even and odd lists")
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

    @Nested
    @DisplayName("Empty Tests")
    class Empty_Tests {
        private RecipeBookOps ops;

        @BeforeEach
        void setup() {
            SharedDB.start();
            SharedDB.startStub();
            ops = new RecipeBookOps();
        }

        @AfterEach
        void takedown() {
            SharedDB.close();
        }

        @Test
        @DisplayName("Testing adding empty or null attributes for edibles")
        void testAddEmptyEdible() {
            ArrayList<Edible> foodRecipes = ops.getFoodRecipes();
            int prevLength = foodRecipes.size();

            try {
                ops.addFood(null, "description", 2, Edible.Unit.g, 1, 1, 1, 1,
                        false, false, false, false, false, "photo");
                fail("name cannot be null, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addFood("", "description", 2, Edible.Unit.g, 1, 1, 1, 1,
                        false, false, false, false, false, "photo");
                fail("name cannot be empty, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            ops.addFood(" ", "description", 2, Edible.Unit.g, 1, 1, 1, 1,
                    false, false, false, false, false, "photo");
            assertEquals(prevLength + 1, ops.getFoodRecipes().size());

            try {
                ops.addFood(" !!!!!!! * @@@ +++==", null, 2, Edible.Unit.g, 1, 1, 1, 1,
                        false, false, false, false, false, "photo");
                fail("description cannot be null, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            ops.addFood(" !!!!!!! * @@@ +++==", "", 2, Edible.Unit.g, 1, 1, 1, 1,
                    false, false, false, false, false, "photo");
            assertEquals(prevLength + 2, ops.getFoodRecipes().size());

            ops.addFood(" !!!!!!! * @@@ +++==", " ", 2, Edible.Unit.g, 1, 1, 1, 1,
                    false, false, false, false, false, "photo");
            assertEquals(prevLength + 3, ops.getFoodRecipes().size());

            try {
                ops.addFood(" !!!!!!! * @@@ +++==", " ____", 2, null, 1, 1, 1, 1,
                        false, false, false, false, false, "photo");
                fail("unit cannot be null, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addFood(" !!!!!!! * @@@ +++==", " ____", 2, Edible.Unit.g, 1, 1, 1, 1,
                        false, false, false, false, false, null);
                fail("photo cannot be null, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addFood(" !!!!!!! * @@@ +++==", " ____", 2, Edible.Unit.g, 1, 1, 1, 1,
                        false, false, false, false, false, "");
                fail("photo cannot be empty, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            ops.addFood(" !!!!!!! * @@@ +++==", " ____", 2, Edible.Unit.g, 1, 1, 1, 1,
                    false, false, false, false, false, " ");
            assertEquals(prevLength + 4, ops.getFoodRecipes().size());
        }

        @Test
        @DisplayName("Testing adding empty or null attributes for meals")
        void testAddEmptyMeal() {
            ArrayList<Edible> mealRecipes = ops.getMealRecipes();
            int prevLength = mealRecipes.size();

            ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

            Edible food = new Edible().initDetails(5, "name", "description", 5, Edible.Unit.g)
                    .initNutrition(5, 5, 5, 5)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "photo");

            Ingredient ingredient = new Ingredient().init(food, 5, Edible.Unit.cups);
            ingredients.add(ingredient);

            try {
                ops.addMeal(null, "description", 2, Edible.Unit.ml, "photo", "instructions", ingredients);
                fail("name cannot be null, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addMeal("", "description", 2, Edible.Unit.ml, "photo", "instructions", ingredients);
                fail("name cannot be empty, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            ops.addMeal(" ", "description", 2, Edible.Unit.ml, "photo", "instructions", ingredients);
            assertEquals(prevLength + 1, ops.getMealRecipes().size());

            try {
                ops.addMeal("drink", null, 2, Edible.Unit.ml, "photo", "instructions", ingredients);
                fail("description cannot be null, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            ops.addMeal("drink", "", 2, Edible.Unit.ml, "photo", "instructions", ingredients);
            assertEquals(prevLength + 2, ops.getMealRecipes().size());

            ops.addMeal("drink", " ", 2, Edible.Unit.ml, "photo", "instructions", ingredients);
            assertEquals(prevLength + 3, ops.getMealRecipes().size());

            try {
                ops.addMeal("drink", "description", 2, null, "photo", "instructions", ingredients);
                fail("unit cannot be null, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addMeal("drink", "description", 2, Edible.Unit.ml, null, "instructions", ingredients);
                fail("photo cannot be null, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addMeal("drink", "description", 2, Edible.Unit.ml, "", "instructions", ingredients);
                fail("photo cannot be empty, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            ops.addMeal("drink", "description", 2, Edible.Unit.ml, " ", "instructions", ingredients);
            assertEquals(prevLength + 4, ops.getMealRecipes().size());

            try {
                ops.addMeal("drink", "description", 2, Edible.Unit.ml, "photo", null, ingredients);
                fail("instructions cannot be null, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            ops.addMeal("drink", "description", 2, Edible.Unit.ml, "photo", "", ingredients);
            assertEquals(prevLength + 5, ops.getMealRecipes().size());

            ops.addMeal("drink", "description", 2, Edible.Unit.ml, "photo", " ", ingredients);
            assertEquals(prevLength + 6, ops.getMealRecipes().size());

            try {
                ops.addMeal("drink", "description", 2, Edible.Unit.ml, "photo", "instructions", null);
                fail("ingredients cannot be null, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addMeal("drink", "description", 2, Edible.Unit.ml, "photo", "instructions", new ArrayList<Ingredient>());
                fail("ingredients cannot be empty, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }

        @Test
        @DisplayName("Testing adding empty or null attributes for drinks without ingredients")
        void testAddSimpleEmptyDrink() {
            ArrayList<Edible> drinkRecipes = ops.getDrinkRecipes();
            int prevLength = drinkRecipes.size();

            try {
                ops.addSimpleDrink(null, "description", 2, Edible.Unit.g, 1, 1, 1, 1,
                        false, false, false, false, false, "photo");
                fail("name cannot be null, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addSimpleDrink("", "description", 2, Edible.Unit.g, 1, 1, 1, 1,
                        false, false, false, false, false, "photo");
                fail("name cannot be empty, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            ops.addSimpleDrink(" ", "description", 2, Edible.Unit.g, 1, 1, 1, 1,
                    false, false, false, false, false, "photo");
            assertEquals(prevLength + 1, ops.getDrinkRecipes().size());

            try {
                ops.addSimpleDrink(" !!!!!!! * @@@ +++==", null, 2, Edible.Unit.g, 1, 1, 1, 1,
                        false, false, false, false, false, "photo");
                fail("description cannot be null, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }


            ops.addSimpleDrink(" !!!!!!! * @@@ +++==", "", 2, Edible.Unit.g, 1, 1, 1, 1,
                    false, false, false, false, false, "photo");
            assertEquals(prevLength + 2, ops.getDrinkRecipes().size());

            ops.addSimpleDrink(" !!!!!!! * @@@ +++==", " ", 2, Edible.Unit.g, 1, 1, 1, 1,
                    false, false, false, false, false, "photo");
            assertEquals(prevLength + 3, ops.getDrinkRecipes().size());

            try {
                ops.addSimpleDrink(" !!!!!!! * @@@ +++==", " ____", 2, null, 1, 1, 1, 1,
                        false, false, false, false, false, "photo");
                fail("unit cannot be null, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addSimpleDrink(" !!!!!!! * @@@ +++==", " ____", 2, Edible.Unit.g, 1, 1, 1, 1,
                        false, false, false, false, false, null);
                fail("photo cannot be null, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addSimpleDrink(" !!!!!!! * @@@ +++==", " ____", 2, Edible.Unit.g, 1, 1, 1, 1,
                        false, false, false, false, false, "");
                fail("photo cannot be empty, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            ops.addSimpleDrink(" !!!!!!! * @@@ +++==", " ____", 2, Edible.Unit.g, 1, 1, 1, 1,
                    false, false, false, false, false, " ");
            assertEquals(prevLength + 4, ops.getDrinkRecipes().size());
        }

        @Test
        @DisplayName("Testing adding empty or null attributes for drinks with ingredients")
        void testAddEmptyDrink() {
            ArrayList<Edible> drinkRecipes = ops.getDrinkRecipes();
            int prevLength = drinkRecipes.size();

            ArrayList<DrinkIngredient> ingredients = new ArrayList<>();

            Edible food = new Edible().initDetails(5, "name", "description", 5, Edible.Unit.g)
                    .initNutrition(5, 5, 5, 5)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "photo");

            DrinkIngredient ingredient = new DrinkIngredient();
            ingredient.init(food, 5, Edible.Unit.cups);
            ingredients.add(ingredient);

            try {
                ops.addPreparedDrink(null, "description", 2, Edible.Unit.ml, "photo", "instructions", ingredients);
                fail("name cannot be null, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addPreparedDrink("", "description", 2, Edible.Unit.ml, "photo", "instructions", ingredients);
                fail("name cannot be empty, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            ops.addPreparedDrink(" ", "description", 2, Edible.Unit.ml, "photo", "instructions", ingredients);
            assertEquals(prevLength + 1, ops.getDrinkRecipes().size());

            try {
                ops.addPreparedDrink("drink", null, 2, Edible.Unit.ml, "photo", "instructions", ingredients);
                fail("description cannot be null, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            ops.addPreparedDrink("drink", "", 2, Edible.Unit.ml, "photo", "instructions", ingredients);
            assertEquals(prevLength + 2, ops.getDrinkRecipes().size());

            ops.addPreparedDrink("drink", " ", 2, Edible.Unit.ml, "photo", "instructions", ingredients);
            assertEquals(prevLength + 3, ops.getDrinkRecipes().size());

            try {
                ops.addPreparedDrink("drink", "description", 2, null, "photo", "instructions", ingredients);
                fail("unit cannot be null, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addPreparedDrink("drink", "description", 2, Edible.Unit.ml, null, "instructions", ingredients);
                fail("photo cannot be null, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addPreparedDrink("drink", "description", 2, Edible.Unit.ml, "", "instructions", ingredients);
                fail("photo cannot be empty, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            ops.addPreparedDrink("drink", "description", 2, Edible.Unit.ml, " ", "instructions", ingredients);
            assertEquals(prevLength + 4, ops.getDrinkRecipes().size());

            try {
                ops.addPreparedDrink("drink", "description", 2, Edible.Unit.ml, "photo", null, ingredients);
                fail("instructions cannot be null, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            ops.addPreparedDrink("drink", "description", 2, Edible.Unit.ml, "photo", "", ingredients);
            assertEquals(prevLength + 5, ops.getDrinkRecipes().size());

            ops.addPreparedDrink("drink", "description", 2, Edible.Unit.ml, "photo", " ", ingredients);
            assertEquals(prevLength + 6, ops.getDrinkRecipes().size());

            try {
                ops.addPreparedDrink("drink", "description", 2, Edible.Unit.ml, "photo", "instructions", null);
                fail("ingredients cannot be null, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addPreparedDrink("drink", "description", 2, Edible.Unit.ml, "photo", "instructions", new ArrayList<DrinkIngredient>());
                fail("ingredients cannot be empty, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }
    }

    @Nested
    @DisplayName("Edge case Tests")
    class EdgeCase_Tests {
        private RecipeBookOps ops;
        String longTestString;

        @BeforeEach
        void setup() {
            SharedDB.start();
            SharedDB.startStub();
            ops = new RecipeBookOps();
            longTestString = "";

            for (int i = 0; i < 9999; i++) {
                longTestString = longTestString + "a";
            }
        }

        @AfterEach
        void takedown() {
            SharedDB.close();
        }

        @Test
        @DisplayName("Tests adding duplicate food items")
        void testDuplicateFoods() {
            ArrayList<Edible> foodRecipes = ops.getFoodRecipes();
            int prevLength = foodRecipes.size();

            for (int i = 0; i < 5; i++) {
                ops.addFood("food", "description", 5, Edible.Unit.g, 5, 5, 5, 5, false, false, false,
                        false, false, "photo");
            }

            foodRecipes = ops.getFoodRecipes();
            assertEquals(foodRecipes.size(), prevLength + 5);
        }

        @Test
        @DisplayName("Tests adding duplicate drinks (with duplicate ingredients and without ingredients)")
        void testDuplicateDrinks() {
            ArrayList<Edible> drinkRecipes = ops.getDrinkRecipes();
            int prevLength = drinkRecipes.size();

            for (int i = 0; i < 5; i++) {
                ops.addSimpleDrink("food", "description", 5, Edible.Unit.g, 5, 5, 5, 5, false, false, false,
                        false, false, "photo");
            }

            drinkRecipes = ops.getDrinkRecipes();
            assertEquals(drinkRecipes.size(), prevLength + 5);

            ArrayList<DrinkIngredient> ingredients = new ArrayList<DrinkIngredient>();
            Edible currEdible = new Edible().initDetails(5, "name", "description", 5, Edible.Unit.g)
                    .initNutrition(5, 5, 5, 5)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "photo");
            DrinkIngredient currIngredient = (DrinkIngredient) new DrinkIngredient().init(currEdible, 5, Edible.Unit.cups);

            ingredients.add(currIngredient);
            ingredients.add(currIngredient);
            ingredients.add(currIngredient);
            prevLength = drinkRecipes.size();

            for (int i = 0; i < 5; i++) {
                ops.addPreparedDrink("name", "description", 5, Edible.Unit.g, "photo", "instructions", ingredients);
            }

            drinkRecipes = ops.getDrinkRecipes();
            assertEquals(drinkRecipes.size(), prevLength + 5);
        }

        @Test
        @DisplayName("Tests adding duplicate meals (with duplicate ingredients)")
        void testDuplicateMeals() {
            ArrayList<Edible> mealRecipes = ops.getMealRecipes();
            int prevLength = mealRecipes.size();

            ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
            Edible currEdible = new Edible().initDetails(5, "name", "description", 5, Edible.Unit.g)
                    .initNutrition(5, 5, 5, 5)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "photo");
            Ingredient currIngredient = new Ingredient().init(currEdible, 5, Edible.Unit.cups);

            ingredients.add(currIngredient);
            ingredients.add(currIngredient);
            ingredients.add(currIngredient);

            for (int i = 0; i < 5; i++) {
                ops.addMeal("name", "description", 5, Edible.Unit.g, "photo", "instructions", ingredients);
            }

            mealRecipes = ops.getMealRecipes();
            assertEquals(mealRecipes.size(), prevLength + 5);
        }

        @Test
        @DisplayName("Tests edge case foods that we can be processed by recipe book ops")
        void testAddFoods() {
            ArrayList<Edible> foodRecipes = ops.getFoodRecipes();
            int prevLength = foodRecipes.size();

            ops.addFood(longTestString, longTestString, 2, Edible.Unit.g, 1, 1, 1, 1,
                    false, false, false, false, false, longTestString);
            ops.addFood(" ", " ", 2, Edible.Unit.g, 1, 1, 1, 1,
                    true, false, false, false, false, " ");
            ops.addFood(longTestString, longTestString, 1, Edible.Unit.g, 0, 0, 0, 0,
                    false, false, false, true, true, longTestString);
            ops.addFood(longTestString, longTestString, 9999, Edible.Unit.g, 9999, 9999, 9999, 9999,
                    true, true, true, true, true, longTestString);
            ops.addFood("12345", "12345", 9999, Edible.Unit.g, 9999, 9999, 9999, 9999,
                    true, false, true, false, true, "12345");
            ops.addFood("&^%#&!^%#LOL", "&^%#&!^%#LOL", 9999, Edible.Unit.g, 9999, 9999, 9999, 9999,
                    true, true, true, true, true, "&^%#&!^%#LOL");

            foodRecipes = ops.getFoodRecipes();

            assertEquals(foodRecipes.size(), prevLength + 6);
        }

        @Test
        @DisplayName("Tests edge case drinks that we can be processed by recipe book ops (with and without ingredients)")
        void testAddDrinks() {
            ArrayList<Edible> drinkRecipes = ops.getDrinkRecipes();
            int prevLength = drinkRecipes.size();

            ops.addSimpleDrink(longTestString, longTestString, 5, Edible.Unit.g, 5, 5, 5, 5, false, false, false,
                    false, false, longTestString);
            ops.addSimpleDrink(" ", " ", 5, Edible.Unit.g, 5, 5, 5, 5, false, false, false,
                    false, false, " ");
            ops.addSimpleDrink("12345", "description", 5, Edible.Unit.g, 0, 0, 0, 0, false, false, false,
                    false, false, "photo");
            ops.addSimpleDrink("food", "description", 5, Edible.Unit.g, 9999, 9999, 9999, 9999, false, false, false,
                    false, false, "photo");

            drinkRecipes = ops.getDrinkRecipes();
            assertEquals(drinkRecipes.size(), prevLength + 4);

            ArrayList<DrinkIngredient> ingredients = new ArrayList<DrinkIngredient>();
            Edible currEdible = new Edible().initDetails(5, longTestString, longTestString, 1, Edible.Unit.g)
                    .initNutrition(9999, 9999, 9999, 9999)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, longTestString);
            DrinkIngredient currIngredient = (DrinkIngredient) new DrinkIngredient().init(currEdible, 5, Edible.Unit.cups);

            ingredients.add(currIngredient);
            prevLength = drinkRecipes.size();

            ops.addPreparedDrink(longTestString, longTestString, 5, Edible.Unit.g, longTestString, longTestString, ingredients);
            ops.addPreparedDrink(" ", " ", 5, Edible.Unit.g, " ", " ", ingredients);
            ops.addPreparedDrink("name", "description", 1, Edible.Unit.g, "photo", "instructions", ingredients);
            ops.addPreparedDrink("name", "description", 9999, Edible.Unit.g, "photo", "instructions", ingredients);

            drinkRecipes = ops.getDrinkRecipes();
            assertEquals(drinkRecipes.size(), prevLength + 4);
        }

        @Test
        @DisplayName("Tests edge case meals that we can be processed by recipe book ops")
        void testAddMeals() {
            ArrayList<Edible> mealRecipes = ops.getMealRecipes();
            int prevLength = mealRecipes.size();

            ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
            Edible currEdible = new Edible().initDetails(5, longTestString, longTestString, 1, Edible.Unit.g)
                    .initNutrition(9999, 9999, 9999, 9999)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, longTestString);
            Ingredient currIngredient = new Ingredient().init(currEdible, 5, Edible.Unit.cups);

            ingredients.add(currIngredient);

            ops.addMeal(longTestString, longTestString, 5, Edible.Unit.g, longTestString, longTestString, ingredients);
            ops.addMeal(" ", " ", 5, Edible.Unit.g, " ", " ", ingredients);
            ops.addMeal("name", "description", 1, Edible.Unit.g, "photo", "instructions", ingredients);
            ops.addMeal("name", "description", 9999, Edible.Unit.g, "photo", "instructions", ingredients);


            mealRecipes = ops.getMealRecipes();

            assertEquals(mealRecipes.size(), prevLength + 4);
        }
    }

    @Nested
    @DisplayName("Invalid Tests")
    class Invalid_Tests {
        private RecipeBookOps ops;
        private String longTestString;

        @BeforeEach
        void setup() {
            SharedDB.start();
            SharedDB.startStub();
            ops = new RecipeBookOps();
            longTestString = "";

            for (int i = 0; i < 10000; i++) {
                longTestString = longTestString + "a";
            }
        }

        @AfterEach
        void takedown() {
            SharedDB.close();
        }

        @Test
        @DisplayName("Testing adding invalid edibles")
        void testAddInvalidEdible() {
            ArrayList<Edible> foodRecipes = ops.getFoodRecipes();
            int prevLength = foodRecipes.size();

            try {
                ops.addFood(longTestString, "description", 0, Edible.Unit.g, 1, 1, 1, 1,
                        false, false, false, false, false, "photo");
                fail("name is too long, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addFood("name", longTestString, 0, Edible.Unit.g, 1, 1, 1, 1,
                        false, false, false, false, false, "photo");
                fail("description is too long, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addFood("name", "description", 1, Edible.Unit.g, 1, 1, 1, 1,
                        false, false, false, false, false, longTestString);
                fail("photo is too long, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addFood("name", "description", 0, Edible.Unit.g, 1, 1, 1, 1,
                        false, false, false, false, false, "photo");
                fail("quantity cannot be 0, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addFood("name", "description", -1, Edible.Unit.g, 1, 1, 1, 1,
                        false, false, false, false, false, "photo");
                fail("quantity cannot be less than 0, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addFood("name", "description", 10000, Edible.Unit.g, 1, 1, 1, 1,
                        false, false, false, false, false, "photo");
                fail("quantity cannot be greater than 9999, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addFood("name", "description", 2, Edible.Unit.g, -1, 1, 1, 1,
                        false, false, false, false, false, "photo");
                fail("calories cannot be less than 0, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addFood("name", "description", 2, Edible.Unit.g, 1, -1, 1, 1,
                        false, false, false, false, false, "photo");
                fail("protein cannot be less than 0, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addFood("name", "description", 2, Edible.Unit.g, 1, 10000, 1, 1,
                        false, false, false, false, false, "photo");
                fail("protein cannot be greater than 9999, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addFood("name", "description", 2, Edible.Unit.g, 1, 2, -1, 1,
                        false, false, false, false, false, "photo");
                fail("carbs cannot be less than 0, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addFood("name", "description", 2, Edible.Unit.g, 1, 2, 10000, 1,
                        false, false, false, false, false, "photo");
                fail("carbs cannot be greater than 9999, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addFood("name", "description", 2, Edible.Unit.g, 1, 1, 1, -1,
                        false, false, false, false, false, "photo");
                fail("fat cannot be less than 0, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addFood("name", "description", 2, Edible.Unit.g, 1, 1, 1, 10000,
                        false, false, false, false, false, "photo");
                fail("fat cannot be greater than 9999, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            assertEquals(prevLength, ops.getFoodRecipes().size());
        }

        @Test
        @DisplayName("Testing adding invalid meals")
        void testAddInvalidMeal() {
            ArrayList<Edible> mealRecipes = ops.getMealRecipes();
            int prevLength = mealRecipes.size();

            ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

            Edible food = new Edible().initDetails(5, "name", "description", 5, Edible.Unit.g)
                    .initNutrition(5, 5, 5, 5)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "photo");

            Ingredient ingredient = new Ingredient().init(food, 5, Edible.Unit.cups);
            ingredients.add(ingredient);

            try {
                ops.addMeal("name", "description", 0, Edible.Unit.ml, "photo", "instructions", ingredients);
                fail("quantity cannot be less than or equal to 0, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addMeal("name", "description", 10000, Edible.Unit.ml, "photo", "instructions", ingredients);
                fail("quantity cannot be greater than 9999, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addMeal(longTestString, "description", 1, Edible.Unit.ml, "photo", "instructions", ingredients);
                fail("name is too long, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addMeal("name", longTestString, 1, Edible.Unit.ml, "photo", "instructions", ingredients);
                fail("description is too long, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addMeal("name", "description", 1, Edible.Unit.ml, longTestString, "instructions", ingredients);
                fail("photo is too long, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addMeal("name", "description", 1, Edible.Unit.ml, "photo", longTestString, ingredients);
                fail("instructions is too long, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            assertEquals(prevLength, ops.getMealRecipes().size());
        }

        @Test
        @DisplayName("Testing adding invalid drinks without ingredients")
        void testAddSimpleInvalidDrink() {
            ArrayList<Edible> drinkRecipes = ops.getDrinkRecipes();
            int prevLength = drinkRecipes.size();

            try {
                ops.addSimpleDrink(longTestString, "description", 0, Edible.Unit.g, 1, 1, 1, 1,
                        false, false, false, false, false, "photo");
                fail("name is too long, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addSimpleDrink("name", longTestString, 0, Edible.Unit.g, 1, 1, 1, 1,
                        false, false, false, false, false, "photo");
                fail("description is too long, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addSimpleDrink("name", "description", 1, Edible.Unit.g, 1, 1, 1, 1,
                        false, false, false, false, false, longTestString);
                fail("photo is too long, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addSimpleDrink("name", "description", 0, Edible.Unit.g, 1, 1, 1, 1,
                        false, false, false, false, false, "photo");
                fail("quantity cannot be 0, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addSimpleDrink("name", "description", 10000, Edible.Unit.g, 1, 1, 1, 1,
                        false, false, false, false, false, "photo");
                fail("quantity cannot be greater than 9999, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addSimpleDrink("name", "description", -1, Edible.Unit.g, 1, 1, 1, 1,
                        false, false, false, false, false, "photo");
                fail("quantity cannot be less than 0, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addSimpleDrink("name", "description", 2, Edible.Unit.g, -1, 1, 1, 1,
                        false, false, false, false, false, "photo");
                fail("calories cannot be less than 0, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addSimpleDrink("name", "description", 2, Edible.Unit.g, 1, -1, 1, 1,
                        false, false, false, false, false, "photo");
                fail("protein cannot be less than 0, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addSimpleDrink("name", "description", 2, Edible.Unit.g, 1, 10000, 1, 1,
                        false, false, false, false, false, "photo");
                fail("protein cannot be greater than 9999, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addSimpleDrink("name", "description", 2, Edible.Unit.g, 1, 2, -1, 1,
                        false, false, false, false, false, "photo");
                fail("carbs cannot be less than 0, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addSimpleDrink("name", "description", 2, Edible.Unit.g, 1, 2, 10000, 1,
                        false, false, false, false, false, "photo");
                fail("carbs cannot be greater than 9999, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addSimpleDrink("name", "description", 2, Edible.Unit.g, 1, 1, 1, -1,
                        false, false, false, false, false, "photo");
                fail("fat cannot be less than 0, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addSimpleDrink("name", "description", 2, Edible.Unit.g, 1, 1, 1, 10000,
                        false, false, false, false, false, "photo");
                fail("fat cannot be greater than 9999, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            assertEquals(prevLength, ops.getDrinkRecipes().size());
        }

        @Test
        @DisplayName("Testing adding invalid drinks with ingredients")
        void testAddInvalidDrink() {
            ArrayList<Edible> drinkRecipes = ops.getDrinkRecipes();
            int prevLength = drinkRecipes.size();

            ArrayList<DrinkIngredient> ingredients = new ArrayList<>();

            Edible food = new Edible().initDetails(5, "name", "description", 5, Edible.Unit.g)
                    .initNutrition(5, 5, 5, 5)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "photo");

            DrinkIngredient ingredient = new DrinkIngredient();
            ingredient.init(food, 5, Edible.Unit.cups);
            ingredients.add(ingredient);

            try {
                ops.addPreparedDrink("name", "description", 0, Edible.Unit.ml, "photo", "instructions", ingredients);
                fail("quantity cannot be less than or equal to 0, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addPreparedDrink("name", "description", 10000, Edible.Unit.ml, "photo", "instructions", ingredients);
                fail("quantity cannot be greater than 9999, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addPreparedDrink(longTestString, "description", 1, Edible.Unit.ml, "photo", "instructions", ingredients);
                fail("name is too long, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addPreparedDrink("name", longTestString, 1, Edible.Unit.ml, "photo", "instructions", ingredients);
                fail("description is too long, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addPreparedDrink("name", "description", 1, Edible.Unit.ml, longTestString, "instructions", ingredients);
                fail("photo is too long, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                ops.addPreparedDrink("name", "description", 1, Edible.Unit.ml, "photo", longTestString, ingredients);
                fail("instructions is too long, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            assertEquals(prevLength, ops.getDrinkRecipes().size());
        }
    }
}
package comp3350.team10.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import comp3350.team10.objects.Drink;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.EdibleLog;
import comp3350.team10.objects.Ingredient;
import comp3350.team10.objects.Meal;

public class TestRecipeDBInterface {

    RecipeDBInterface db;

    int foodRecipeCount;
    int mealRecipeCount;
    int drinkRecipeCount;

    @BeforeEach
    void setup() {
        SharedDB.start();
        SharedDB.startStub();
        //SharedDB.startHsql();
        db = SharedDB.getRecipeDB();
        this.foodRecipeCount = db.getFoodRecipes().size();
        this.mealRecipeCount = db.getMealRecipes().size();
        this.drinkRecipeCount = db.getDrinkRecipes().size();
    }

    @AfterEach
    void shutdown() {
        SharedDB.close();
    }

    @Nested
    @DisplayName("Tests that should fail")
    class caseFail {
        Edible testEdible;
        Meal testMeal;
        Drink testDrink;

        @BeforeEach
        void setup() {
            setupTestObjects();
        }

        void setupTestObjects() {
            ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
            Ingredient ingredient = null;
            ingredient = new Ingredient().init((new Edible()
                    .initDetails(4, "Grain of Rice", "rice desc", 400, Edible.Unit.g)
                    .initNutrition(400, 30, 20, 50)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "rice.jpg")), 1, Edible.Unit.cups);
            ingredients.add(ingredient);

            ingredient = new Ingredient().init((new Edible()
                    .initDetails(12, "Bologna", "Bologna desc", 1, Edible.Unit.tsp)
                    .initNutrition(100, 30, 20, 50)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "bologna.jpg")), 1, Edible.Unit.cups);
            ingredients.add(ingredient);

            this.testEdible = new Edible()
                    .initDetails(2, "Chicken", "desc", 20, Edible.Unit.tsp)
                    .initNutrition(200, 25, 40, 35)
                    .initCategories(false, false, false, false, true)
                    .initMetadata(false, "photo.jpg");

            this.testMeal = (Meal) new Meal()
                    .initDetails(41, "Meal 1011", "desc", 30, Edible.Unit.cups)
                    .initNutrition(300, 40, 50, 10)
                    .initMetadata(false, "ribsmash.jpg");
            this.testMeal.setIngredients(ingredients);

            this.testDrink = (Drink) new Drink()
                    .initDetails(31, "Orange Juice", "OJ", 100, Edible.Unit.ml)
                    .initNutrition(100, 30, 45, 25)
                    .initMetadata(false, "orangejuice.jpg");
        }

        @Test
        @DisplayName("adding an item with dbkey that collides with existing item should fail")
        void testCollision() {

            assertThrows(IllegalArgumentException.class, () -> {
                db.addFoodToRecipeBook(this.testEdible);
            });
            assertEquals(foodRecipeCount, db.getFoodRecipes().size());

            assertThrows(IllegalArgumentException.class, () -> {
                db.addMealToRecipeBook(this.testMeal);
            });
            assertEquals(mealRecipeCount, db.getMealRecipes().size());

            assertThrows(IllegalArgumentException.class, () -> {
                db.addDrinkToRecipeBook(this.testDrink);
            });
            assertEquals(drinkRecipeCount, db.getDrinkRecipes().size());
        }

        @Test
        @DisplayName("passing preparedItems to addFoodToRecipeBook should fail")
        void testWrongObjectType() {

            assertThrows(IllegalArgumentException.class, () -> {
                db.addFoodToRecipeBook(this.testMeal);
            });
            assertEquals(foodRecipeCount, db.getFoodRecipes().size());

            assertThrows(IllegalArgumentException.class, () -> {
                db.addFoodToRecipeBook(this.testDrink);
            });
            assertEquals(foodRecipeCount, db.getFoodRecipes().size());

        }

        @Test
        @DisplayName("Null parameters should fail")
        void testNull() {

            assertThrows(IllegalArgumentException.class, () -> {
                db.addFoodToRecipeBook(null);
            });
            assertEquals(foodRecipeCount, db.getFoodRecipes().size());

            assertThrows(IllegalArgumentException.class, () -> {
                db.addMealToRecipeBook(null);
            });
            assertEquals(mealRecipeCount, db.getMealRecipes().size());

            assertThrows(IllegalArgumentException.class, () -> {
                db.addDrinkToRecipeBook(null);
            });
            assertEquals(drinkRecipeCount, db.getDrinkRecipes().size());
        }

        @Test
        @DisplayName("Uninitialized objects should fail")
        void testUninitialized() {

            assertThrows(IllegalArgumentException.class, () -> {
                db.addFoodToRecipeBook(new Edible());
            });
            assertEquals(foodRecipeCount, db.getFoodRecipes().size());

            assertThrows(IllegalArgumentException.class, () -> {
                db.addMealToRecipeBook(new Meal());
            });
            assertEquals(mealRecipeCount, db.getMealRecipes().size());

            assertThrows(IllegalArgumentException.class, () -> {
                db.addDrinkToRecipeBook(new Drink());
            });
            assertEquals(drinkRecipeCount, db.getDrinkRecipes().size());
        }

        @Test
        @DisplayName("non existent dbkey should fail")
        void testBadDBkey() {
            final int testKey = db.getNextKey();

            assertNull(db.findEdibleByKey(-1, false));
            assertNull(db.findEdibleByKey(-1, true));
            assertNull(db.findEdibleByKey(999999, true));
            assertNull(db.findEdibleByKey(testKey, true));
        }
    }

    @Nested
    @DisplayName("Simple Tests")
    class caseSimple {
        Edible testEdible;
        Meal testMeal;
        Drink testDrink;

        @BeforeEach
        void setup() {
            this.setupTestObjects();
        }

        void setupTestObjects() {
            ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
            Ingredient ingredient = null;
            ingredient = new Ingredient().init((new Edible()
                    .initDetails(4, "Grain of Rice", "rice desc", 400, Edible.Unit.g)
                    .initNutrition(400, 30, 20, 50)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "rice.jpg")), 1, Edible.Unit.cups);
            ingredients.add(ingredient);

            ingredient = new Ingredient().init((new Edible()
                    .initDetails(12, "Bologna", "Bologna desc", 1, Edible.Unit.tsp)
                    .initNutrition(100, 30, 20, 50)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "bologna.jpg")), 1, Edible.Unit.cups);
            ingredients.add(ingredient);

            this.testEdible = new Edible()
                    .initDetails(101, "Chicken", "desc", 20, Edible.Unit.tsp)
                    .initNutrition(200, 25, 40, 35)
                    .initCategories(false, false, false, false, true)
                    .initMetadata(false, "photo.jpg");

            this.testMeal = (Meal) new Meal()
                    .initDetails(102, "Meal 1011", "desc", 30, Edible.Unit.cups)
                    .initNutrition(300, 40, 50, 10)
                    .initMetadata(false, "ribsmash.jpg");
            this.testMeal.setIngredients(ingredients);

            this.testDrink = (Drink) new Drink()
                    .initDetails(103, "Orange Juice", "OJ", 100, Edible.Unit.ml)
                    .initNutrition(100, 30, 45, 25)
                    .initMetadata(false, "orangejuice.jpg");
        }

        @Test
        @DisplayName("typical values should work")
        void typicalValues() {

            assertEquals(25, foodRecipeCount);
            assertEquals(6, mealRecipeCount);
            assertEquals(7, drinkRecipeCount);
            assertEquals(100, db.getNextKey());

            EdibleLog item = db.findEdibleByKey(2, false);
            assertEquals("Pear", item.getName());

            db.addFoodToRecipeBook(this.testEdible);
            assertEquals(foodRecipeCount, db.getFoodRecipes().size(), 1);

            db.addMealToRecipeBook(this.testMeal);
            assertEquals(mealRecipeCount, db.getMealRecipes().size(), 1);

            db.addDrinkToRecipeBook(this.testDrink);
            assertEquals(drinkRecipeCount, db.getDrinkRecipes().size(), 1);

            this.testEdible.setDBKey(104);
            this.testMeal.setDBKey(105);
            this.testDrink.setDBKey(106);

            db.addFoodToRecipeBook(this.testEdible);
            assertEquals(foodRecipeCount, db.getFoodRecipes().size(), 2);

            db.addMealToRecipeBook(this.testMeal);
            assertEquals(mealRecipeCount, db.getMealRecipes().size(), 2);

            db.addDrinkToRecipeBook(this.testDrink);
            assertEquals(drinkRecipeCount, db.getDrinkRecipes().size(), 2);
        }

    }

    @Nested
    @DisplayName("Complex Tests should pass")
    class caseComplex {
        Edible testEdible;
        Meal testMeal;
        Drink testDrink;

        @BeforeEach
        void setup() {
            this.setupTestObjects();
        }

        void setupTestObjects() {
            ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
            Ingredient ingredient = null;
            ingredient = new Ingredient().init((new Edible()
                    .initDetails(4, "Grain of Rice", "rice desc", 400, Edible.Unit.g)
                    .initNutrition(400, 30, 20, 50)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "rice.jpg")), 1, Edible.Unit.cups);
            ingredients.add(ingredient);

            ingredient = new Ingredient().init((new Edible()
                    .initDetails(12, "Bologna", "old description", 1, Edible.Unit.tsp)
                    .initNutrition(100, 30, 20, 50)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "bologna.jpg")), 1, Edible.Unit.cups);
            ingredients.add(ingredient);

            this.testEdible = new Edible()
                    .initDetails(101, "Chicken", "old description", 20, Edible.Unit.tsp)
                    .initNutrition(200, 25, 40, 35)
                    .initCategories(false, false, false, false, true)
                    .initMetadata(false, "photo.jpg");

            this.testMeal = (Meal) new Meal()
                    .initDetails(102, "Meal 1011", "old description", 30, Edible.Unit.cups)
                    .initNutrition(300, 40, 50, 10)
                    .initMetadata(false, "ribsmash.jpg");
            this.testMeal.setIngredients(ingredients);

            this.testDrink = (Drink) new Drink()
                    .initDetails(103, "Orange Juice", "old description", 100, Edible.Unit.ml)
                    .initNutrition(100, 30, 45, 25)
                    .initMetadata(false, "orangejuice.jpg");
        }

        @Test
        @DisplayName("When we add an object to the database we should not be able to modify primitives")
        void testObjectSeparation() {
            db.addFoodToRecipeBook(this.testEdible);
            assertEquals(foodRecipeCount, db.getFoodRecipes().size(), 1);

            db.addMealToRecipeBook(this.testMeal);
            assertEquals(mealRecipeCount, db.getMealRecipes().size(), 1);

            db.addDrinkToRecipeBook(this.testDrink);
            assertEquals(drinkRecipeCount, db.getDrinkRecipes().size(), 1);

            this.testEdible.setDescription("a new description");
            this.testMeal.setDescription("a new description");
            this.testDrink.setDescription("a new description");

            assertEquals("old description", db.findEdibleByKey(101, false).getDescription());
            assertEquals("old description", db.findEdibleByKey(102, false).getDescription());
            assertEquals("old description", db.findEdibleByKey(103, false).getDescription());
        }

        @Test
        @DisplayName("When we add an object to the database we should not be able to contained objects")
        void testEmptyIngredientList() {
            db.addFoodToRecipeBook(this.testEdible);
            assertEquals(foodRecipeCount, db.getFoodRecipes().size(), 1);

            db.addMealToRecipeBook(this.testMeal);
            assertEquals(mealRecipeCount, db.getMealRecipes().size(), 1);

            db.addDrinkToRecipeBook(this.testDrink);
            assertEquals(drinkRecipeCount, db.getDrinkRecipes().size(), 1);

            this.testMeal.getIngredients().clear();
            this.testDrink.getIngredients().clear();

            fail("Info is lost when returning preparedItems as EdibleLog");
            //assertEquals(2, ((Meal) db.findEdibleByKey(102, false)).getIngredients().size());
        }
    }


    @Nested
    @DisplayName("Edge case Tests should pass")
    class caseEdge {

        @Test
        @DisplayName("We should be able to get the first item in the database")
        void getFirstItem() {
            EdibleLog item = db.findEdibleByKey(1, false);
            assertEquals("Apple", item.getName());
        }
    }
}

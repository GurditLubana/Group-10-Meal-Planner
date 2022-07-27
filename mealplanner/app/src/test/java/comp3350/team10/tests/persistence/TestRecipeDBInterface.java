package comp3350.team10.tests.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import comp3350.team10.objects.Drink;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.Ingredient;
import comp3350.team10.objects.Meal;
import comp3350.team10.persistence.DBSelector;
import comp3350.team10.persistence.DataAccess;
import comp3350.team10.persistence.RecipeDBInterface;

public class TestRecipeDBInterface {

    RecipeDBInterface db;
    ArrayList<Edible> foodDB;
    ArrayList<Edible> mealDB;
    ArrayList<Edible> drinkDB;

    int foodRecipeCount = -1;
    int mealRecipeCount = -1;
    int drinkRecipeCount = -1;
    int testEdibleKey = -1;
    int testMealKey = -1;
    int testDrinkKey = -1;

    Edible testEdible;
    Meal testMeal;
    Drink testDrink;

    @BeforeEach
    void setup() {
        DBSelector.start(new DataAccessStub());
        db = DBSelector.getRecipeDB();
        this.foodDB = db.getFoodRecipes();
        this.mealDB = db.getMealRecipes();
        this.drinkDB = db.getDrinkRecipes();
        this.foodRecipeCount = this.foodDB.size();
        this.mealRecipeCount = this.mealDB.size();
        this.drinkRecipeCount = this.drinkDB.size();
    }

    @AfterEach
    void shutdown() {
        DataAccess dataAccess = DBSelector.getSharedDB();
        dataAccess.removeTestData();
        DBSelector.close();
    }

    void setupTestObjects() {
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        Ingredient ingredient = null;
        ingredient = new Ingredient().init((new Edible()
                .initDetails(4, "Test Grain of Rice", "rice desc", 400, Edible.Unit.g)
                .initNutrition(400, 30, 20, 50)
                .initCategories(false, false, false, false, false)
                .initMetadata(false, "rice.jpg")), 1, Edible.Unit.cups);
        ingredients.add(ingredient);

        ingredient = new Ingredient().init((new Edible()
                .initDetails(12, "Test Bologna", "Bologna desc", 1, Edible.Unit.tsp)
                .initNutrition(100, 30, 20, 50)
                .initCategories(false, false, false, false, false)
                .initMetadata(false, "bologna.jpg")), 1, Edible.Unit.cups);
        ingredients.add(ingredient);

        this.testEdible = new Edible()
                .initDetails(101, "Test TestChicken", "desc", 20, Edible.Unit.tsp)
                .initNutrition(200, 25, 40, 35)
                .initCategories(false, false, false, false, true)
                .initMetadata(false, "photo.jpg");

        this.testMeal = (Meal) new Meal()
                .initDetails(102, "Test Meal 1011", "desc", 30, Edible.Unit.cups)
                .initNutrition(300, 40, 50, 10)
                .initMetadata(false, "ribsmash.jpg");
        this.testMeal.setIngredients(ingredients);

        this.testDrink = (Drink) new Drink()
                .initDetails(103, "Test Orange Juice", "desc", 100, Edible.Unit.ml)
                .initNutrition(100, 30, 45, 25)
                .initMetadata(false, "orangejuice.jpg");
    }

    void setupInvalidObjects() {
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        Ingredient ingredient = null;
        ingredient = new Ingredient().init((new Edible()
                .initDetails(4, "Test Grain of Rice", "rice desc", 400, Edible.Unit.g)
                .initNutrition(400, 30, 20, 50)
                .initCategories(false, false, false, false, false)
                .initMetadata(false, "rice.jpg")), 1, Edible.Unit.cups);
        ingredients.add(ingredient);

        ingredient = new Ingredient().init((new Edible()
                .initDetails(12, "Test Bologna", "Bologna desc", 1, Edible.Unit.tsp)
                .initNutrition(100, 30, 20, 50)
                .initCategories(false, false, false, false, false)
                .initMetadata(false, "bologna.jpg")), 1, Edible.Unit.cups);
        ingredients.add(ingredient);

        this.testEdible = new Edible()
                .initDetails(2, "Test Chicken", "desc", 20, Edible.Unit.tsp)
                .initNutrition(200, 25, 40, 35)
                .initCategories(false, false, false, false, true)
                .initMetadata(false, "photo.jpg");

        this.testMeal = (Meal) new Meal()
                .initDetails(26, "Test Meal 1011", "desc", 30, Edible.Unit.cups)
                .initNutrition(300, 40, 50, 10)
                .initMetadata(false, "ribsmash.jpg");
        this.testMeal.setIngredients(ingredients);

        this.testDrink = (Drink) new Drink()
                .initDetails(31, "Test Orange Juice", "OJ", 100, Edible.Unit.ml)
                .initNutrition(100, 30, 45, 25)
                .initMetadata(false, "orangejuice.jpg");
    }

    @Nested
    @DisplayName("Tests that should fail")
    class caseFail {

        @BeforeEach
        void setup() {
            setupInvalidObjects();
        }

        @AfterEach
        void removeTestData() {
            DataAccess hsql = DBSelector.getSharedDB();
            hsql.removeTestData();
        }

//        @Test
//        @DisplayName("adding an item with dbkey that collides with existing item should fail")
//        void testCollision() {
//
//            assertThrows(IllegalArgumentException.class, () -> {
//                db.addFoodToRecipeBook(testEdible);
//            });
//            assertEquals(foodRecipeCount, db.getFoodRecipes().size());
//
//            assertThrows(IllegalArgumentException.class, () -> {
//                db.addMealToRecipeBook(testMeal);
//            });
//            assertEquals(mealRecipeCount, db.getMealRecipes().size());
//
//            assertThrows(IllegalArgumentException.class, () -> {
//                db.addDrinkToRecipeBook(testDrink);
//            });
//            assertEquals(drinkRecipeCount, db.getDrinkRecipes().size());
//        }

        @Test
        @DisplayName("passing preparedItems to addFoodToRecipeBook should fail")
        void testWrongObjectType() {

            assertThrows(IllegalArgumentException.class, () -> {
                db.addFoodToRecipeBook(testMeal);
            });
            assertEquals(foodRecipeCount, db.getFoodRecipes().size());

            assertThrows(IllegalArgumentException.class, () -> {
                db.addFoodToRecipeBook(testDrink);
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

            assertNull(db.findIngredientByKey(-1, false));
            assertNull(db.findIngredientByKey(-1, true));
            assertNull(db.findIngredientByKey(999999, true));
            assertNull(db.findIngredientByKey(testKey, true));
        }
    }

    @Nested
    @DisplayName("Simple Tests")
    class caseSimple {

        @BeforeEach
        void setup() {
            setupTestObjects();
        }

        @AfterEach
        void removeTestData() {
            DataAccess hsql = DBSelector.getSharedDB();
            hsql.removeTestData();
        }

        @Test
        @DisplayName("typical values should work")
        void typicalValues() {
            assertEquals(25, foodRecipeCount);
            assertEquals(6, mealRecipeCount);
            assertEquals(7, drinkRecipeCount);

            Edible item = db.findIngredientByKey(1, false);
            assertEquals("Pear", item.getName());

            db.addFoodToRecipeBook(testEdible);
            assertEquals(foodRecipeCount, db.getFoodRecipes().size(), 1);

            db.addMealToRecipeBook(testMeal);
            assertEquals(mealRecipeCount, db.getMealRecipes().size(), 1);

            db.addDrinkToRecipeBook(testDrink);
            assertEquals(drinkRecipeCount, db.getDrinkRecipes().size(), 1);

            testEdible.setDBKey(104);
            testMeal.setDBKey(105);
            testDrink.setDBKey(106);

            db.addFoodToRecipeBook(testEdible);
            assertEquals(foodRecipeCount, db.getFoodRecipes().size(), 2);

            db.addMealToRecipeBook(testMeal);
            assertEquals(mealRecipeCount, db.getMealRecipes().size(), 2);

            db.addDrinkToRecipeBook(testDrink);
            assertEquals(drinkRecipeCount, db.getDrinkRecipes().size(), 2);
        }

    }

    @Nested
    @DisplayName("Complex Tests should pass")
    class caseComplex {

        @BeforeEach
        void setup() {
            setupTestObjects();
        }

        @AfterEach
        void removeTestData() {
            DataAccess hsql = DBSelector.getSharedDB();
            hsql.removeTestData();
        }

        @Test
        @DisplayName("When we add an object to the database we should not be able to modify primitives")
        void testObjectSeparation() {

            testEdibleKey = db.addFoodToRecipeBook(testEdible);
            assertEquals(foodRecipeCount, db.getFoodRecipes().size(), 1);

            testMealKey = db.addMealToRecipeBook(testMeal);
            assertEquals(mealRecipeCount, db.getMealRecipes().size(), 1);

            testDrinkKey = db.addDrinkToRecipeBook(testDrink);
            assertEquals(drinkRecipeCount, db.getDrinkRecipes().size(), 1);

            testEdible.setDescription("a new description");
            testMeal.setDescription("a new description");
            testDrink.setDescription("a new description");

            assertEquals("desc", db.findIngredientByKey(testEdibleKey, false).getDescription());
            assertEquals("desc", db.findIngredientByKey(testMealKey, false).getDescription());
            assertEquals("desc", db.findIngredientByKey(testDrinkKey, false).getDescription());
        }
    }

    @Nested
    @DisplayName("Edge case Tests should pass")
    class caseEdge {

        @Test
        @DisplayName("We should be able to get the first item in the database")
        void getFirstItem() {
            Edible item = db.findIngredientByKey(0, false);
            assertEquals("Apple", item.getName());
        }
    }
}

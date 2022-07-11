package comp3350.team10.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import comp3350.team10.objects.Drink;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.EdibleLog;
import comp3350.team10.objects.Meal;

public class TestRecipeDBInterface {

    @Nested
    @DisplayName("Simple Tests")
    class caseSimple {
        RecipeDBInterface db;

        @BeforeEach
        void setup() {
            SharedDB.start();
            SharedDB.startStub();
            //SharedDB.startHsql();
            this.db = SharedDB.getRecipeDB();
        }

        @AfterEach
        void shutdown() {
            SharedDB.close();
        }

        @Test
        @DisplayName("typical values should work")
        void typicalValues() {
            int foodRecipeCount = this.db.getFoodRecipes().size();
            int mealRecipeCount = this.db.getMealRecipes().size();
            int drinkRecipeCount = this.db.getDrinkRecipes().size();

            assertEquals(25, foodRecipeCount);
            assertEquals(6, mealRecipeCount);
            assertEquals(7, drinkRecipeCount);
            assertEquals(100, this.db.getNextKey());

            EdibleLog item = db.findEdibleByKey(2, false);
            assertTrue(item.getName().equals("Pear"));

            this.db.addFoodToRecipeBook(new Edible()
                    .initDetails(101, "Chicken", "desc", 20, Edible.Unit.tsp)
                    .initNutrition(200, 25, 40, 35)
                    .initCategories(false, false, false, false, true)
                    .initMetadata(false, "photo.jpg"));
            assertEquals(foodRecipeCount, this.db.getFoodRecipes().size(), 1);

            this.db.addMealToRecipeBook(new Meal());
            assertEquals(mealRecipeCount, this.db.getMealRecipes().size(), 1);

            this.db.addDrinkToRecipeBook(new Drink());
            assertEquals(drinkRecipeCount, this.db.getDrinkRecipes().size(), 1);
        }

    }


    @Nested
    @DisplayName("Complex Tests should pass")
    class caseComplex {
        RecipeDBInterface db;

        @BeforeEach
        void setup() {
            SharedDB.start();
            SharedDB.startStub();
            //SharedDB.startHsql();
            this.db = SharedDB.getRecipeDB();
        }

        @AfterEach
        void shutdown() {
            SharedDB.close();
        }

        @Test
        @DisplayName("")
        void test() {

        }
    }


    @Nested
    @DisplayName("Edge case Tests should pass")
    class caseEdge {
        RecipeDBInterface db;

        @BeforeEach
        void setup() {
            SharedDB.start();
            SharedDB.startStub();
            //SharedDB.startHsql();
            this.db = SharedDB.getRecipeDB();
        }

        @AfterEach
        void shutdown() {
            SharedDB.close();
        }

        @Test
        @DisplayName("")
        void test() {
        }
    }


    @Nested
    @DisplayName("Tests that should fail")
    class caseFail {

        @BeforeEach
        void setup() {
            SharedDB.start();
            SharedDB.startStub();
            //SharedDB.startHsql();
        }

        @AfterEach
        void shutdown() {
            SharedDB.close();
        }

        @Test
        @DisplayName("")
        void test() {
        }
    }
}

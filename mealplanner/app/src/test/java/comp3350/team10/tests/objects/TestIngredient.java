package comp3350.team10.tests.objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import comp3350.team10.objects.Edible;
import comp3350.team10.objects.Ingredient;

public class TestIngredient {

    @Nested
    @DisplayName("Simple tests")
    class Test_Simple {
        private Edible testEdible;
        private Edible secondTestEdible;
        private Ingredient testIngredient;

        @BeforeEach
        void setup() {
            testEdible = new Edible();
            secondTestEdible = new Edible();
            testIngredient = new Ingredient();

            testEdible.initDetails(5, "name", "description", 5, Edible.Unit.g)
                    .initNutrition(5, 5, 5, 5)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "photo");
            secondTestEdible.initDetails(10, "another name", "another description", 10, Edible.Unit.cups)
                    .initNutrition(10, 10, 10, 10)
                    .initCategories(true, true, true, true, true)
                    .initMetadata(true, "another photo");
        }

        @Test
        @DisplayName("Tests default values in an ingredient's creation")
        void testCreateIngrdient() {
            assertNull(testIngredient.getIngredient());
            assertEquals(testIngredient.getQuantity(), -1);
            assertNull(testIngredient.getQuantityUnits());
        }

        @Test
        @DisplayName("Tests setting a simple ingredients edible")
        void testSetIngredient() {
            testIngredient.setIngredient(testEdible);
            assertEquals(testIngredient.getIngredient(), testEdible);
            testIngredient.setIngredient(secondTestEdible);
            assertEquals(testIngredient.getIngredient(), secondTestEdible);

            testIngredient.init(testEdible, 5, Edible.Unit.cups);
            assertEquals(testIngredient.getIngredient(), testEdible);
            testIngredient.init(secondTestEdible, 5, Edible.Unit.cups);
            assertEquals(testIngredient.getIngredient(), secondTestEdible);
        }

        @Test
        @DisplayName("Tests settings a simple ingredients quantity")
        void testSetQuantity() {
            testIngredient.setQuantity(5);
            assertEquals(testIngredient.getQuantity(), 5);

            testIngredient.setQuantity(10);
            assertEquals(testIngredient.getQuantity(), 10);

            testIngredient.init(testEdible, 5, Edible.Unit.g);
            assertEquals(testIngredient.getQuantity(), 5);

            testIngredient.init(testEdible, 10, Edible.Unit.g);
            assertEquals(testIngredient.getQuantity(), 10);
        }

        @Test
        @DisplayName("Tests settings an ingredients unit")
        void testSetUnit() {
            testIngredient.setQuantityUnit(Edible.Unit.cups);
            assertEquals(testIngredient.getQuantityUnits(), Edible.Unit.cups);

            testIngredient.setQuantityUnit(Edible.Unit.g);
            assertEquals(testIngredient.getQuantityUnits(), Edible.Unit.g);

            testIngredient.setQuantityUnit(Edible.Unit.ml);
            assertEquals(testIngredient.getQuantityUnits(), Edible.Unit.ml);

            testIngredient.setQuantityUnit(Edible.Unit.oz);
            assertEquals(testIngredient.getQuantityUnits(), Edible.Unit.oz);

            testIngredient.setQuantityUnit(Edible.Unit.liter);
            assertEquals(testIngredient.getQuantityUnits(), Edible.Unit.liter);

            testIngredient.setQuantityUnit(Edible.Unit.serving);
            assertEquals(testIngredient.getQuantityUnits(), Edible.Unit.serving);

            testIngredient.setQuantityUnit(Edible.Unit.tbsp);
            assertEquals(testIngredient.getQuantityUnits(), Edible.Unit.tbsp);

            testIngredient.setQuantityUnit(Edible.Unit.tsp);
            assertEquals(testIngredient.getQuantityUnits(), Edible.Unit.tsp);

            testIngredient.init(testEdible, 5, Edible.Unit.cups);
            assertEquals(testIngredient.getQuantityUnits(), Edible.Unit.cups);

            testIngredient.init(testEdible, 5, Edible.Unit.g);
            assertEquals(testIngredient.getQuantityUnits(), Edible.Unit.g);

            testIngredient.init(testEdible, 5, Edible.Unit.ml);
            assertEquals(testIngredient.getQuantityUnits(), Edible.Unit.ml);

            testIngredient.init(testEdible, 5, Edible.Unit.oz);
            assertEquals(testIngredient.getQuantityUnits(), Edible.Unit.oz);

            testIngredient.init(testEdible, 5, Edible.Unit.liter);
            assertEquals(testIngredient.getQuantityUnits(), Edible.Unit.liter);

            testIngredient.init(testEdible, 5, Edible.Unit.serving);
            assertEquals(testIngredient.getQuantityUnits(), Edible.Unit.serving);

            testIngredient.init(testEdible, 5, Edible.Unit.tbsp);
            assertEquals(testIngredient.getQuantityUnits(), Edible.Unit.tbsp);

            testIngredient.init(testEdible, 5, Edible.Unit.tsp);
            assertEquals(testIngredient.getQuantityUnits(), Edible.Unit.tsp);
        }
    }

    @Nested
    @DisplayName("Complex tests")
    class Test_Complex {
        private Edible testEdible;
        private Edible secondTestEdible;
        private Ingredient testIngredient;

        @BeforeEach
        void setup() {
            testEdible = new Edible();
            secondTestEdible = new Edible();
            testIngredient = new Ingredient();

            testEdible.initDetails(500, "name\r\r\n\n?$?$?$", "description\r\r\n\n?$?$?$", 500, Edible.Unit.g)
                    .initNutrition(500, 500, 500, 500)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "photo\r\r\n\n\n?$?$?$");
            secondTestEdible.initDetails(1000, "12345", "12345", 1000, Edible.Unit.cups)
                    .initNutrition(1000, 1000, 1000, 1000)
                    .initCategories(true, true, true, true, true)
                    .initMetadata(true, "12345");
        }

        @Test
        @DisplayName("Tests setting a complex ingredients edible")
        void testSetIngredient() {
            testIngredient.setIngredient(testEdible);
            assertEquals(testIngredient.getIngredient(), testEdible);
            testIngredient.setIngredient(secondTestEdible);
            assertEquals(testIngredient.getIngredient(), secondTestEdible);

            testIngredient.init(testEdible, 5, Edible.Unit.cups);
            assertEquals(testIngredient.getIngredient(), testEdible);
            testIngredient.init(secondTestEdible, 5, Edible.Unit.cups);
            assertEquals(testIngredient.getIngredient(), secondTestEdible);
        }

        @Test
        @DisplayName("Tests settings a complex ingredients quantity")
        void testSetQuantity() {
            testIngredient.setQuantity(500);
            assertEquals(testIngredient.getQuantity(), 500);

            testIngredient.setQuantity(1000);
            assertEquals(testIngredient.getQuantity(), 1000);

            testIngredient.init(testEdible, 500, Edible.Unit.g);
            assertEquals(testIngredient.getQuantity(), 500);

            testIngredient.init(testEdible, 1000, Edible.Unit.g);
            assertEquals(testIngredient.getQuantity(), 1000);
        }
    }

    @Nested
    @DisplayName("Empty tests")
    class Test_Empty {
        private Edible testEdible;
        private Ingredient testIngredient;

        @BeforeEach
        void setup() {
            testEdible = new Edible();
            testIngredient = new Ingredient();

            testEdible.initDetails(500, "name\r\r\n\n?$?$?$", "description\r\r\n\n?$?$?$", 500, Edible.Unit.g)
                    .initNutrition(500, 500, 500, 500)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "photo\r\r\n\n\n?$?$?$");
        }

        @Test
        @DisplayName("Tests setting a null edible for an ingredient")
        void testSetIngredient() {
            try {
                testIngredient.setIngredient(null);
                fail("An ingredient's edible cannot be null, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                testIngredient.init(null, 5, Edible.Unit.g);
                fail("An ingredient's edible cannot be null, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }

        @Test
        @DisplayName("Tests setting a null unit for an ingredient")
        void testSetUnit() {
            try {
                testIngredient.setQuantityUnit(null);
                fail("An ingredient's unit cannot be null, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                testIngredient.init(testEdible, 5, null);
                fail("An ingredient's unit cannot be null, should throw an IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }
    }

    @Nested
    @DisplayName("Edge tests")
    class Test_Edge {
        private Edible testEdible;
        private Edible secondTestEdible;
        private Ingredient testIngredient;

        @BeforeEach
        void setup() {
            testEdible = new Edible();
            secondTestEdible = new Edible();
            testIngredient = new Ingredient();

            testEdible.initDetails(0, "name\r\r\n\n?$?$?$", "description\r\r\n\n?$?$?$", 500, Edible.Unit.g)
                    .initNutrition(500, 500, 500, 500)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "photo\r\r\n\n\n?$?$?$");
        }

        @Test
        @DisplayName("Tests quantity edge cases for an ingredient")
        void testSetQuantity() {
            testIngredient.setQuantity(1);
            assertEquals(testIngredient.getQuantity(), 1);
            testIngredient.setQuantity(9999);
            assertEquals(testIngredient.getQuantity(), 9999);

            testIngredient.init(testEdible, 1, Edible.Unit.g);
            assertEquals(testIngredient.getQuantity(), 1);
            testIngredient.init(testEdible, 9999, Edible.Unit.g);
            assertEquals(testIngredient.getQuantity(), 9999);
        }
    }


    @Nested
    @DisplayName("Invalid tests")
    class Test_Invalid {
        private Edible testEdible;
        private Ingredient testIngredient;

        @BeforeEach
        void setup() {
            testEdible = new Edible();
            testIngredient = new Ingredient();
        }

        @Test
        @DisplayName("Tests invalid edibles for an ingredient")
        void testSetIngredient() {
            testEdible.setDBKey(1);

            try {
                testIngredient.setIngredient(testEdible);
                fail("Should throw an exception, this is an incomplete edible");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible = new Edible();
            testEdible.setName("name");

            try {
                testIngredient.setIngredient(testEdible);
                fail("Should throw an exception, this is an incomplete edible");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible = new Edible();
            testEdible.setDescription("description");

            try {
                testIngredient.setIngredient(testEdible);
                fail("Should throw an exception, this is an incomplete edible");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible = new Edible();
            testEdible.setBaseQuantity(5);

            try {
                testIngredient.setIngredient(testEdible);
                fail("Should throw an exception, this is an incomplete edible");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible = new Edible();
            testEdible.setBaseUnit(Edible.Unit.g);

            try {
                testIngredient.setIngredient(testEdible);
                fail("Should throw an exception, this is an incomplete edible");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible = new Edible();
            testEdible.setCalories(5);

            try {
                testIngredient.setIngredient(testEdible);
                fail("Should throw an exception, this is an incomplete edible");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible = new Edible();
            testEdible.setProtein(5);

            try {
                testIngredient.setIngredient(testEdible);
                fail("Should throw an exception, this is an incomplete edible");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible = new Edible();
            testEdible.setCarbs(5);

            try {
                testIngredient.setIngredient(testEdible);
                fail("Should throw an exception, this is an incomplete edible");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible = new Edible();
            testEdible.setFat(5);

            try {
                testIngredient.setIngredient(testEdible);
                fail("Should throw an exception, this is an incomplete edible");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible = new Edible();
            testEdible.setAlcoholic(true);

            try {
                testIngredient.setIngredient(testEdible);
                fail("Should throw an exception, this is an incomplete edible");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible = new Edible();
            testEdible.setSpicy(true);

            try {
                testIngredient.setIngredient(testEdible);
                fail("Should throw an exception, this is an incomplete edible");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible = new Edible();
            testEdible.setVegan(true);

            try {
                testIngredient.setIngredient(testEdible);
                fail("Should throw an exception, this is an incomplete edible");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible = new Edible();
            testEdible.setVegetarian(true);

            try {
                testIngredient.setIngredient(testEdible);
                fail("Should throw an exception, this is an incomplete edible");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible = new Edible();
            testEdible.setVegan(true);

            try {
                testIngredient.setIngredient(testEdible);
                fail("Should throw an exception, this is an incomplete edible");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible = new Edible();
            testEdible.setVegetarian(true);

            try {
                testIngredient.setIngredient(testEdible);
                fail("Should throw an exception, this is an incomplete edible");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible = new Edible();
            testEdible.setGlutenFree(true);

            try {
                testIngredient.setIngredient(testEdible);
                fail("Should throw an exception, this is an incomplete edible");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible = new Edible();
            testEdible.setCustom(true);

            try {
                testIngredient.setIngredient(testEdible);
                fail("Should throw an exception, this is an incomplete edible");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible = new Edible();
            testEdible.setPhoto("photo");

            try {
                testIngredient.setIngredient(testEdible);
                fail("Should throw an exception, this is an incomplete edible");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible = new Edible();
            testEdible.initDetails(1, "name", "description", 5, Edible.Unit.g);

            try {
                testIngredient.setIngredient(testEdible);
                fail("Should throw an exception, this is an incomplete edible");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible = new Edible();
            testEdible.initNutrition(5, 5, 5, 5);

            try {
                testIngredient.setIngredient(testEdible);
                fail("Should throw an exception, this is an incomplete edible");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible = new Edible();
            testEdible.initCategories(false, false, false, false, false);

            try {
                testIngredient.setIngredient(testEdible);
                fail("Should throw an exception, this is an incomplete edible");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible = new Edible();
            testEdible.initMetadata(false, "photo");

            try {
                testIngredient.setIngredient(testEdible);
                fail("Should throw an exception, this is an incomplete edible");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }

        @Test
        @DisplayName("Tests invalid quantities for an ingredient")
        void testSetQuantity() {

            try {
                testIngredient.setQuantity(0);
                fail("This quantity is too small, must be greater than 0, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                testIngredient.setQuantity(10000);
                fail("This quantity is too large, must be smaller than 9999, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }
    }
}

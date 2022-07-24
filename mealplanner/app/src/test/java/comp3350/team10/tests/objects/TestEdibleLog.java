package comp3350.team10.tests.objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import comp3350.team10.objects.Edible;
import comp3350.team10.objects.EdibleLog;

public class TestEdibleLog {

    @Nested
    @DisplayName("Simple tests")
    class Test_Simple {
        private Edible testEdible;
        private EdibleLog testLog;
        private String testString;

        @BeforeEach
        void setUp() {
            testEdible = new Edible().initDetails(5, "name", "description", 5, Edible.Unit.g)
                    .initNutrition(5, 5, 5, 5)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "photo");

            testLog = new EdibleLog(testEdible);
            testString = "testString";
        }

        @Test
        @DisplayName("Tests an Edible's contents upon new creation")
        void testInitialValues() {
            assertEquals(testLog.getDbkey(), 5);
            assertEquals(testLog.getName(), "name");
            assertEquals(testLog.getDescription(), "description");
            assertEquals(testLog.getQuantity(), 5);
            assertEquals(testLog.getUnit(), Edible.Unit.g);

            assertEquals(testLog.getCalories(), 5);
            assertEquals(testLog.getProtein(), 5);
            assertEquals(testLog.getCarbs(), 5);
            assertEquals(testLog.getFat(), 5);

            assertFalse(testLog.getIsAlcoholic());
            assertFalse(testLog.getIsSpicy());
            assertFalse(testLog.getIsVegan());
            assertFalse(testLog.getIsVegetarian());
            assertFalse(testLog.getIsGlutenFree());

            assertFalse(testLog.getIsCustom());
            assertEquals(testLog.getPhoto(), "photo");
        }

        @Test
        @DisplayName("Tests setting a simple DB key for an edible")
        void testSetDBKey() {
            testLog.setDBKey(5);
            assertEquals(testLog.getDbkey(), 5);

            testLog.setDBKey(10);
            assertEquals(testLog.getDbkey(), 10);

            testLog.initDetails(5, "name", "description", 1, Edible.Unit.g);
            assertEquals(testLog.getDbkey(), 5);

            testLog.initDetails(10, "name", "description", 1, Edible.Unit.g);
            assertEquals(testLog.getDbkey(), 10);
        }

        @Test
        @DisplayName("Tests setting a simple name for an edible")
        void testSetName() {
            testLog.setName(testString);
            assertEquals(testLog.getName(), testString);

            testLog.setName("A different name");
            assertEquals(testLog.getName(), "A different name");

            testLog.initDetails(5, testString, "description", 1, Edible.Unit.g);
            assertEquals(testLog.getName(), testString);

            testLog.initDetails(5, "A different name", "description", 1, Edible.Unit.g);
            assertEquals(testLog.getName(), "A different name");
        }

        @Test
        @DisplayName("Tests setting a simple description for an edible")
        void testSetDescription() {
            testLog.setDescription(testString);
            assertEquals(testLog.getDescription(), testString);

            testLog.setDescription("A different description");
            assertEquals(testLog.getDescription(), "A different description");

            testLog.initDetails(5, "name", testString, 1, Edible.Unit.g);
            assertEquals(testLog.getDescription(), testString);

            testLog.initDetails(5, "name", "A different description", 1, Edible.Unit.g);
            assertEquals(testLog.getDescription(), "A different description");
        }

        @Test
        @DisplayName("Tests setting a simple quantity for an edible")
        void testSetQuantity() {
            testLog.setBaseQuantity(5);
            assertEquals(testLog.getQuantity(), 5);

            testLog.setBaseQuantity(10);
            assertEquals(testLog.getQuantity(), 5);

            testLog.initDetails(5, "name", "description", 5, Edible.Unit.g);
            assertEquals(testLog.getQuantity(), 5);

            testLog.initDetails(10, "name", "description", 10, Edible.Unit.g);
            assertEquals(testLog.getQuantity(), 5);
        }

        @Test
        @DisplayName("Tests setting an edibles unit")
        void testSetUnit() {
            testLog.setBaseUnit(Edible.Unit.cups);
            assertEquals(testLog.getUnit(), Edible.Unit.g);

            testLog.setBaseUnit(Edible.Unit.g);
            assertEquals(testLog.getUnit(), Edible.Unit.g);

            testLog.setBaseUnit(Edible.Unit.ml);
            assertEquals(testLog.getUnit(), Edible.Unit.g);

            testLog.setBaseUnit(Edible.Unit.oz);
            assertEquals(testLog.getUnit(), Edible.Unit.g);

            testLog.setBaseUnit(Edible.Unit.liter);
            assertEquals(testLog.getUnit(), Edible.Unit.g);

            testLog.setBaseUnit(Edible.Unit.serving);
            assertEquals(testLog.getUnit(), Edible.Unit.g);

            testLog.setBaseUnit(Edible.Unit.tbsp);
            assertEquals(testLog.getUnit(), Edible.Unit.g);

            testLog.setBaseUnit(Edible.Unit.tsp);
            assertEquals(testLog.getUnit(), Edible.Unit.g);

            testLog.initDetails(5, "name", "description", 10, Edible.Unit.cups);
            assertEquals(testLog.getUnit(), Edible.Unit.g);

            testLog.initDetails(5, "name", "description", 10, Edible.Unit.g);
            assertEquals(testLog.getUnit(), Edible.Unit.g);

            testLog.initDetails(5, "name", "description", 10, Edible.Unit.ml);
            assertEquals(testLog.getUnit(), Edible.Unit.g);

            testLog.initDetails(5, "name", "description", 10, Edible.Unit.oz);
            assertEquals(testLog.getUnit(), Edible.Unit.g);

            testLog.initDetails(5, "name", "description", 10, Edible.Unit.liter);
            assertEquals(testLog.getUnit(), Edible.Unit.g);

            testLog.initDetails(5, "name", "description", 10, Edible.Unit.serving);
            assertEquals(testLog.getUnit(), Edible.Unit.g);

            testLog.initDetails(5, "name", "description", 10, Edible.Unit.tbsp);
            assertEquals(testLog.getUnit(), Edible.Unit.g);

            testLog.initDetails(5, "name", "description", 10, Edible.Unit.tsp);
            assertEquals(testLog.getUnit(), Edible.Unit.g);
        }

        @Test
        @DisplayName("Tests setting a simple calorie count for an edible")
        void testSetCalories() {
            testLog.setCalories(5);
            assertEquals(testLog.getCalories(), 5);

            testLog.setCalories(10);
            assertEquals(testLog.getCalories(), 5);

            testLog.initNutrition(5, 1, 1, 1);
            assertEquals(testLog.getCalories(), 5);

            testLog.initNutrition(10, 1, 1, 1);
            assertEquals(testLog.getCalories(), 5);
        }

        @Test
        @DisplayName("Tests setting a simple protein count for an edible")
        void testSetProtein() {
            testLog.setProtein(5);
            assertEquals(testLog.getProtein(), 5);

            testLog.setProtein(10);
            assertEquals(testLog.getProtein(), 10);

            testLog.initNutrition(1, 5, 1, 1);
            assertEquals(testLog.getProtein(), 5);

            testLog.initNutrition(1, 10, 1, 1);
            assertEquals(testLog.getProtein(), 10);
        }

        @Test
        @DisplayName("Tests setting a simple carb count for an edible")
        void testSetCarbs() {
            testLog.setCarbs(5);
            assertEquals(testLog.getCarbs(), 5);

            testLog.setCarbs(10);
            assertEquals(testLog.getCarbs(), 10);

            testLog.initNutrition(1, 1, 5, 1);
            assertEquals(testLog.getCarbs(), 5);

            testLog.initNutrition(1, 1, 10, 1);
            assertEquals(testLog.getCarbs(), 10);
        }

        @Test
        @DisplayName("Tests setting a simple fat count for an edible")
        void testSetFat() {
            testLog.setFat(5);
            assertEquals(testLog.getFat(), 5);

            testLog.setFat(10);
            assertEquals(testLog.getFat(), 10);

            testLog.initNutrition(1, 1, 1, 5);
            assertEquals(testLog.getFat(), 5);

            testLog.initNutrition(1, 1, 1, 10);
            assertEquals(testLog.getFat(), 10);
        }

        @Test
        @DisplayName("Tests setting an edibles alcoholic flag")
        void testSetIsAlcoholic() {
            testLog.setAlcoholic(false);
            assertFalse(testLog.getIsAlcoholic());

            testLog.setAlcoholic(true);
            assertTrue(testLog.getIsAlcoholic());

            testLog.initCategories(false, false, false, false, false);
            assertFalse(testLog.getIsAlcoholic());

            testLog.initCategories(true, false, false, false, false);
            assertTrue(testLog.getIsAlcoholic());
        }

        @Test
        @DisplayName("Tests setting an edibles spicy flag")
        void testSetIsSpicy() {
            testLog.setSpicy(false);
            assertFalse(testLog.getIsSpicy());

            testLog.setSpicy(true);
            assertTrue(testLog.getIsSpicy());

            testLog.initCategories(false, false, false, false, false);
            assertFalse(testLog.getIsSpicy());

            testLog.initCategories(false, true, false, false, false);
            assertTrue(testLog.getIsSpicy());
        }

        @Test
        @DisplayName("Tests setting an edibles vegan flag")
        void testSetIsVegan() {
            testLog.setVegan(false);
            assertFalse(testLog.getIsVegan());

            testLog.setVegan(true);
            assertTrue(testLog.getIsVegan());

            testLog.initCategories(false, false, false, false, false);
            assertFalse(testLog.getIsVegan());

            testLog.initCategories(false, false, true, false, false);
            assertTrue(testLog.getIsVegan());
        }

        @Test
        @DisplayName("Tests setting an edibles vegetarian flag")
        void testSetVegetarian() {
            testLog.setVegetarian(false);
            assertFalse(testLog.getIsVegetarian());

            testLog.setVegetarian(true);
            assertTrue(testLog.getIsVegetarian());

            testLog.initCategories(false, false, false, false, false);
            assertFalse(testLog.getIsVegetarian());

            testLog.initCategories(false, false, false, true, false);
            assertTrue(testLog.getIsVegetarian());
        }

        @Test
        @DisplayName("Tests setting an edibles gluten free flag")
        void testSetGlutenFree() {
            testLog.setGlutenFree(false);
            assertFalse(testLog.getIsGlutenFree());

            testLog.setGlutenFree(true);
            assertTrue(testLog.getIsGlutenFree());

            testLog.initCategories(false, false, false, false, false);
            assertFalse(testLog.getIsGlutenFree());

            testLog.initCategories(false, false, false, false, true);
            assertTrue(testLog.getIsGlutenFree());
        }

        @Test
        @DisplayName("Tests setting an edibles custom flag")
        void testSetIsCustom() {
            testLog.setCustom(false);
            assertFalse(testLog.getIsCustom());

            testLog.setCustom(true);
            assertTrue(testLog.getIsCustom());

            testLog.initMetadata(false, "photo");
            assertFalse(testLog.getIsCustom());

            testLog.initMetadata(true, "photo");
            assertTrue(testLog.getIsCustom());
        }

        @Test
        @DisplayName("Tests setting a simple photo for an edible")
        void testSetPhoto() {
            testLog.setPhoto(testString);
            assertEquals(testLog.getPhoto(), testString);

            testLog.setPhoto("A different photo");
            assertEquals(testLog.getPhoto(), "A different photo");

            testLog.initMetadata(false, testString);
            assertEquals(testLog.getPhoto(), testString);

            testLog.initMetadata(false, "A different photo");
            assertEquals(testLog.getPhoto(), "A different photo");
        }

        @Test
        @DisplayName("Tests setting a simple log quantity")
        void setLogQuantity() {
            testLog.setQuantity(10);
            assertEquals(testLog.getQuantity(), 10);
            assertEquals(testLog.getCalories(), 5);

            testLog.setQuantity(15);
            assertEquals(testLog.getQuantity(), 15);
            assertEquals(testLog.getCalories(), 5);

            try {
                testLog.init(10, testLog.getUnit());
                assertEquals(testLog.getQuantity(), 10);
                assertEquals(testLog.getCalories(), 10);

                testLog.init(15, testLog.getUnit());
                assertEquals(testLog.getQuantity(), 15);
                assertEquals(testLog.getCalories(), 15);
            } catch (Exception e) {
                fail("no errors should be thrown, these should be legan quantities, units, calories and thus conversions");
            }
        }

        @Test
        @DisplayName("Tests setting a simple log quantity")
        void setLogUnit() {
            testLog.setUnit(Edible.Unit.cups);
            assertEquals(testLog.getUnit(), Edible.Unit.cups);
            assertEquals(testLog.getCalories(), 5);

            testLog.setUnit(Edible.Unit.g);
            assertEquals(testLog.getUnit(), Edible.Unit.g);
            assertEquals(testLog.getCalories(), 5);

            testLog.setUnit(Edible.Unit.ml);
            assertEquals(testLog.getUnit(), Edible.Unit.ml);
            assertEquals(testLog.getCalories(), 5);

            testLog.setUnit(Edible.Unit.oz);
            assertEquals(testLog.getUnit(), Edible.Unit.oz);
            assertEquals(testLog.getCalories(), 5);

            testLog.setUnit(Edible.Unit.liter);
            assertEquals(testLog.getUnit(), Edible.Unit.liter);
            assertEquals(testLog.getCalories(), 5);

            testLog.setUnit(Edible.Unit.serving);
            assertEquals(testLog.getUnit(), Edible.Unit.serving);
            assertEquals(testLog.getCalories(), 5);

            testLog.setUnit(Edible.Unit.tbsp);
            assertEquals(testLog.getUnit(), Edible.Unit.tbsp);
            assertEquals(testLog.getCalories(), 5);

            testLog.setUnit(Edible.Unit.tsp);
            assertEquals(testLog.getUnit(), Edible.Unit.tsp);
            assertEquals(testLog.getCalories(), 5);

            try {
                testLog.init(testLog.getQuantity(), Edible.Unit.cups);
                assertEquals(testLog.getUnit(), Edible.Unit.cups);
                assertEquals(testLog.getCalories(), 1120);

                testLog.init(testLog.getQuantity(), Edible.Unit.g);
                assertEquals(testLog.getUnit(), Edible.Unit.g);
                assertEquals(testLog.getCalories(), 5);

                testLog.init(testLog.getQuantity(), Edible.Unit.ml);
                assertEquals(testLog.getUnit(), Edible.Unit.ml);
                assertEquals(testLog.getCalories(), 5);

                testLog.init(testLog.getQuantity(), Edible.Unit.oz);
                assertEquals(testLog.getUnit(), Edible.Unit.oz);
                assertEquals(testLog.getCalories(), 140);

                testLog.init(testLog.getQuantity(), Edible.Unit.liter);
                assertEquals(testLog.getUnit(), Edible.Unit.liter);
                assertEquals(testLog.getCalories(), 5000);

                testLog.init(testLog.getQuantity(), Edible.Unit.serving);
                assertEquals(testLog.getUnit(), Edible.Unit.serving);
                assertEquals(testLog.getCalories(), 1250);

                testLog.init(testLog.getQuantity(), Edible.Unit.tbsp);
                assertEquals(testLog.getUnit(), Edible.Unit.tbsp);
                assertEquals(testLog.getCalories(), 70);

                testLog.init(testLog.getQuantity(), Edible.Unit.tsp);
                assertEquals(testLog.getUnit(), Edible.Unit.tsp);
                assertEquals(testLog.getCalories(), 25);
            } catch (Exception e) {
                fail("no errors should be thrown, these should be legan quantities, units, calories and thus conversions");
            }
        }

        @Test
        @DisplayName("Tests cloning a simple edible with all false flags")
        void testCloneEdibleLogWithAllFalse() {
            EdibleLog newLog;

            testEdible.initDetails(1, "name", "description", 1, Edible.Unit.g)
                    .initNutrition(1, 1, 1, 1)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, testString);
            testLog = new EdibleLog(testEdible);
            newLog = testLog.clone();

            assertEquals(newLog.getDbkey(), 1);
            assertEquals(newLog.getName(), "name");
            assertEquals(newLog.getDescription(), "description");
            assertEquals(newLog.getQuantity(), 1);
            assertEquals(newLog.getUnit(), Edible.Unit.g);
            assertEquals(newLog.getCalories(), 1);
            assertEquals(newLog.getProtein(), 1);
            assertEquals(newLog.getCarbs(), 1);
            assertEquals(newLog.getFat(), 1);
            assertFalse(newLog.getIsAlcoholic());
            assertFalse(newLog.getIsSpicy());
            assertFalse(newLog.getIsVegan());
            assertFalse(newLog.getIsVegetarian());
            assertFalse(newLog.getIsGlutenFree());
            assertFalse(newLog.getIsCustom());
            assertEquals(newLog.getPhoto(), testString);
        }

        @Test
        @DisplayName("Tests cloning a simple edible with all true flags")
        void testCloneEdibleLogWithAllTrue() {
            EdibleLog newLog;
            testEdible.initDetails(1, "name", "description", 1, Edible.Unit.g)
                    .initNutrition(1, 1, 1, 1)
                    .initCategories(true, true, true, true, true)
                    .initMetadata(true, testString);

            testLog = new EdibleLog(testEdible);
            newLog = testLog.clone();

            assertEquals(newLog.getDbkey(), 1);
            assertEquals(newLog.getName(), "name");
            assertEquals(newLog.getDescription(), "description");
            assertEquals(newLog.getQuantity(), 1);
            assertEquals(newLog.getUnit(), Edible.Unit.g);
            assertEquals(newLog.getCalories(), 1);
            assertEquals(newLog.getProtein(), 1);
            assertEquals(newLog.getCarbs(), 1);
            assertEquals(newLog.getFat(), 1);
            assertTrue(newLog.getIsAlcoholic());
            assertTrue(newLog.getIsSpicy());
            assertTrue(newLog.getIsVegan());
            assertTrue(newLog.getIsVegetarian());
            assertTrue(newLog.getIsGlutenFree());
            assertTrue(newLog.getIsCustom());
            assertEquals(newLog.getPhoto(), testString);
        }
    }

    @Nested
    @DisplayName("Complex tests")
    class Test_Complex {
        private Edible testEdible;
        private EdibleLog testLog;
        private String testString;
        private String numberTestString;

        @BeforeEach
        void setUp() {
            testEdible = new Edible().initDetails(5, "name", "description", 5, Edible.Unit.g)
                    .initNutrition(5, 5, 5, 5)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "photo");
            testLog = new EdibleLog(testEdible);
            numberTestString = "666";
            testString = "String test_instruction=\"very long instructions sdakjlfh&sl@$jfkhldsakjhfiuweasdhyfuiklewahearewrw\" +\n" +
                    "                        \"adsjfkghbewakjdshfljkaewhdflkaewj\\njewifhewl\\r isdfauhjljkewf\\n\\\\wieosuhjrfiol;ewk\" +\n" +
                    "                        \"53465687-/34324o90ukljo&$^#$^@#$%@#^%$*#$#%@@$#@$@!$@#\";";
        }

        @Test
        @DisplayName("Tests setting a complex DB key for an edible")
        void testSetDBKey() {
            testEdible.setDBKey(500);
            assertEquals(testEdible.getDbkey(), 500);

            testEdible.setDBKey(1000);
            assertEquals(testEdible.getDbkey(), 1000);

            testEdible.initDetails(500, "name", "description", 1, Edible.Unit.g);
            assertEquals(testEdible.getDbkey(), 500);

            testEdible.initDetails(1000, "name", "description", 1, Edible.Unit.g);
            assertEquals(testEdible.getDbkey(), 1000);
        }

        @Test
        @DisplayName("Tests setting a complex name for an edible")
        void testSetName() {
            testEdible.setName(testString);
            assertEquals(testEdible.getName(), testString);

            testEdible.setName(numberTestString);
            assertEquals(testEdible.getName(), numberTestString);

            testEdible.initDetails(5, testString, "description", 1, Edible.Unit.g);
            assertEquals(testEdible.getName(), testString);

            testEdible.initDetails(5, numberTestString, "description", 1, Edible.Unit.g);
            assertEquals(testEdible.getName(), numberTestString);
        }

        @Test
        @DisplayName("Tests setting a complex description for an edible")
        void testSetDescription() {
            testEdible.setDescription(testString);
            assertEquals(testEdible.getDescription(), testString);

            testEdible.setDescription(numberTestString);
            assertEquals(testEdible.getDescription(), numberTestString);

            testEdible.initDetails(5, "name", testString, 1, Edible.Unit.g);
            assertEquals(testEdible.getDescription(), testString);

            testEdible.initDetails(5, "name", numberTestString, 1, Edible.Unit.g);
            assertEquals(testEdible.getDescription(), numberTestString);
        }

        @Test
        @DisplayName("Tests setting a complex quantity for an edible")
        void testSetQuantity() {
            testEdible.setBaseQuantity(500);
            assertEquals(testEdible.getQuantity(), 500);

            testEdible.setBaseQuantity(1000);
            assertEquals(testEdible.getQuantity(), 1000);

            testEdible.initDetails(5, "name", "description", 500, Edible.Unit.g);
            assertEquals(testEdible.getQuantity(), 500);

            testEdible.initDetails(10, "name", "description", 1000, Edible.Unit.g);
            assertEquals(testEdible.getQuantity(), 1000);
        }

        @Test
        @DisplayName("Tests setting a complex calorie count for an edible")
        void testSetCalories() {
            testEdible.setCalories(500);
            assertEquals(testEdible.getCalories(), 500);

            testEdible.setCalories(1000);
            assertEquals(testEdible.getCalories(), 1000);

            testEdible.initNutrition(500, 1, 1, 1);
            assertEquals(testEdible.getCalories(), 500);

            testEdible.initNutrition(1000, 1, 1, 1);
            assertEquals(testEdible.getCalories(), 1000);
        }

        @Test
        @DisplayName("Tests setting a complex protein count for an edible")
        void testSetProtein() {
            testEdible.setProtein(500);
            assertEquals(testEdible.getProtein(), 500);

            testEdible.setProtein(1000);
            assertEquals(testEdible.getProtein(), 1000);

            testEdible.initNutrition(1, 500, 1, 1);
            assertEquals(testEdible.getProtein(), 500);

            testEdible.initNutrition(1, 1000, 1, 1);
            assertEquals(testEdible.getProtein(), 1000);
        }

        @Test
        @DisplayName("Tests setting a complex carb count for an edible")
        void testSetCarbs() {
            testEdible.setCarbs(500);
            assertEquals(testEdible.getCarbs(), 500);

            testEdible.setCarbs(1000);
            assertEquals(testEdible.getCarbs(), 1000);

            testEdible.initNutrition(1, 1, 500, 1);
            assertEquals(testEdible.getCarbs(), 500);

            testEdible.initNutrition(1, 1, 1000, 1);
            assertEquals(testEdible.getCarbs(), 1000);
        }

        @Test
        @DisplayName("Tests setting a complex fat count for an edible")
        void testSetFat() {
            testEdible.setFat(500);
            assertEquals(testEdible.getFat(), 500);

            testEdible.setFat(1000);
            assertEquals(testEdible.getFat(), 1000);

            testEdible.initNutrition(1, 1, 1, 500);
            assertEquals(testEdible.getFat(), 500);

            testEdible.initNutrition(1, 1, 1, 1000);
            assertEquals(testEdible.getFat(), 1000);
        }

        @Test
        @DisplayName("Tests setting a complex photo for an edible")
        void testSetPhoto() {
            testEdible.setPhoto(testString);
            assertEquals(testEdible.getPhoto(), testString);

            testEdible.setPhoto(numberTestString);
            assertEquals(testEdible.getPhoto(), numberTestString);

            testEdible.initMetadata(false, testString);
            assertEquals(testEdible.getPhoto(), testString);

            testEdible.initMetadata(false, numberTestString);
            assertEquals(testEdible.getPhoto(), numberTestString);
        }

        @Test
        @DisplayName("Tests setting a complex log quantity")
        void setLogQuantity() {
            testLog.setQuantity(500);
            assertEquals(testLog.getQuantity(), 500);
            assertEquals(testLog.getCalories(), 5);

            testLog.setQuantity(1000);
            assertEquals(testLog.getQuantity(), 1000);
            assertEquals(testLog.getCalories(), 5);

            try {
                testLog.init(500, testLog.getUnit());
                assertEquals(testLog.getQuantity(), 500);
                assertEquals(testLog.getCalories(), 500);

                testLog.init(1000, testLog.getUnit());
                assertEquals(testLog.getQuantity(), 1000);
                assertEquals(testLog.getCalories(), 1000);
            } catch (Exception e) {
                fail("no errors should be thrown, these should be legan quantities, units, calories and thus conversions");
            }
        }

        @Test
        @DisplayName("Tests cloning a complex edible with all true or false flags, where its log details have been modified (not calories though)")
        void testCloneEdibleLog() {
            EdibleLog newLog;
            testEdible.initDetails(0, testString, testString, 1, Edible.Unit.g)
                    .initNutrition(0, 0, 0, 0)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, testString);

            testLog = new EdibleLog(testEdible);
            testLog.setQuantity(5);
            testLog.setUnit(Edible.Unit.serving);
            newLog = testLog.clone();

            assertEquals(newLog.getDbkey(), 0);
            assertEquals(newLog.getName(), testString);
            assertEquals(newLog.getDescription(), testString);
            assertEquals(newLog.getQuantity(), 5);
            assertEquals(newLog.getUnit(), Edible.Unit.serving);
            assertEquals(newLog.getCalories(), 0);
            assertEquals(newLog.getProtein(), 0);
            assertEquals(newLog.getCarbs(), 0);
            assertEquals(newLog.getFat(), 0);
            assertFalse(newLog.getIsAlcoholic());
            assertFalse(newLog.getIsSpicy());
            assertFalse(newLog.getIsVegan());
            assertFalse(newLog.getIsVegetarian());
            assertFalse(newLog.getIsGlutenFree());
            assertFalse(newLog.getIsCustom());
            assertEquals(newLog.getPhoto(), testString);

            testEdible.initDetails(500, numberTestString, numberTestString, 500, Edible.Unit.g)
                    .initNutrition(500, 500, 500, 500)
                    .initCategories(true, true, true, true, true)
                    .initMetadata(true, numberTestString);

            testLog = new EdibleLog(testEdible);
            testLog.setQuantity(5);
            testLog.setUnit(Edible.Unit.serving);
            newLog = testLog.clone();

            assertEquals(newLog.getDbkey(), 500);
            assertEquals(newLog.getName(), numberTestString);
            assertEquals(newLog.getDescription(), numberTestString);
            assertEquals(newLog.getQuantity(), 5);
            assertEquals(newLog.getUnit(), Edible.Unit.serving);
            assertEquals(newLog.getCalories(), 500);
            assertEquals(newLog.getProtein(), 500);
            assertEquals(newLog.getCarbs(), 500);
            assertEquals(newLog.getFat(), 500);
            assertTrue(newLog.getIsAlcoholic());
            assertTrue(newLog.getIsSpicy());
            assertTrue(newLog.getIsVegan());
            assertTrue(newLog.getIsVegetarian());
            assertTrue(newLog.getIsGlutenFree());
            assertTrue(newLog.getIsCustom());
            assertEquals(newLog.getPhoto(), numberTestString);
        }

        @Test
        @DisplayName("Tests cloning a complex edible with all true or false flags, where its log details have been modified including calories")
        void testCloneEdibleLogAndUpdateCalories() {
            EdibleLog newLog;
            testEdible.initDetails(0, testString, testString, 1, Edible.Unit.g)
                    .initNutrition(0, 0, 0, 0)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, testString);

            testLog = new EdibleLog(testEdible);

            try {
                testLog.init(5, Edible.Unit.serving);
            } catch (Exception e) {
                fail("this is a perfectly valid calculation, no error should be thrown");
            }

            newLog = testLog.clone();

            assertEquals(newLog.getDbkey(), 0);
            assertEquals(newLog.getName(), testString);
            assertEquals(newLog.getDescription(), testString);
            assertEquals(newLog.getQuantity(), 5);
            assertEquals(newLog.getUnit(), Edible.Unit.serving);
            assertEquals(newLog.getCalories(), 0);
            assertEquals(newLog.getProtein(), 0);
            assertEquals(newLog.getCarbs(), 0);
            assertEquals(newLog.getFat(), 0);
            assertFalse(newLog.getIsAlcoholic());
            assertFalse(newLog.getIsSpicy());
            assertFalse(newLog.getIsVegan());
            assertFalse(newLog.getIsVegetarian());
            assertFalse(newLog.getIsGlutenFree());
            assertFalse(newLog.getIsCustom());
            assertEquals(newLog.getPhoto(), testString);

            testEdible.initDetails(500, numberTestString, numberTestString, 500, Edible.Unit.g)
                    .initNutrition(500, 500, 500, 500)
                    .initCategories(true, true, true, true, true)
                    .initMetadata(true, numberTestString);

            testLog = new EdibleLog(testEdible);

            try {
                testLog.init(5, Edible.Unit.g);
            } catch (Exception e) {
                fail("this is a perfectly valid calculation, no error should be thrown");
            }

            newLog = testLog.clone();

            assertEquals(newLog.getDbkey(), 500);
            assertEquals(newLog.getName(), numberTestString);
            assertEquals(newLog.getDescription(), numberTestString);
            assertEquals(newLog.getQuantity(), 5);
            assertEquals(newLog.getUnit(), Edible.Unit.g);
            assertEquals(newLog.getCalories(), 5);
            assertEquals(newLog.getProtein(), 500);
            assertEquals(newLog.getCarbs(), 500);
            assertEquals(newLog.getFat(), 500);
            assertTrue(newLog.getIsAlcoholic());
            assertTrue(newLog.getIsSpicy());
            assertTrue(newLog.getIsVegan());
            assertTrue(newLog.getIsVegetarian());
            assertTrue(newLog.getIsGlutenFree());
            assertTrue(newLog.getIsCustom());
            assertEquals(newLog.getPhoto(), numberTestString);
        }
    }

    @Nested
    @DisplayName("Empty tests")
    class Test_Empty {
        private Edible testEdible;
        private EdibleLog testLog;
        private String emptyTestString;

        @BeforeEach
        void setUp() {
            testEdible = new Edible().initDetails(5, "name", "description", 5, Edible.Unit.g)
                    .initNutrition(5, 5, 5, 5)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "photo");
            testLog = new EdibleLog(testEdible);
            emptyTestString = "";
        }

        @Test
        @DisplayName("Tests setting a empty or null name for an edible")
        void testSetName() {
            try {
                testEdible.setName(null);
                fail("Name should not be null, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                testEdible.setName(emptyTestString);
                fail("Name should not be empty,should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible.setName(" ");
            assertEquals(testEdible.getName(), " ");

            try {
                testEdible.initDetails(1, null, "description", 5, Edible.Unit.cups);
                fail("Name should not be null, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                testEdible.initDetails(1, emptyTestString, "description", 5, Edible.Unit.cups);
                fail("Name should not be empty, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible.initDetails(1, " ", "description", 5, Edible.Unit.cups);
            assertEquals(testEdible.getName(), " ");
        }

        @Test
        @DisplayName("Tests setting a empty or null description for an edible")
        void testSetDescription() {
            try {
                testEdible.setDescription(null);
                fail("Description should not be null, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible.setDescription(emptyTestString);
            assertEquals(testEdible.getDescription(), emptyTestString);

            testEdible.setDescription(" ");
            assertEquals(testEdible.getDescription(), " ");

            try {
                testEdible.initDetails(1, "name", null, 5, Edible.Unit.cups);
                fail("Description should not be null, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible.initDetails(1, "name", emptyTestString, 5, Edible.Unit.cups);
            assertEquals(testEdible.getDescription(), emptyTestString);

            testEdible.initDetails(1, "name", " ", 5, Edible.Unit.cups);
            assertEquals(testEdible.getDescription(), " ");
        }

        @Test
        @DisplayName("Tests setting a null unit for an edible")
        void testSetUnit() {
            try {
                testEdible.setBaseUnit(null);
                fail("Unit should not be null, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                testEdible.initDetails(1, "name", "description", 5, null);
                fail("Unit should not be null, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }

        @Test
        @DisplayName("Tests setting a empty or null photo for an edible")
        void testSetPhoto() {
            try {
                testEdible.setPhoto(null);
                fail("Photo should not be null, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                testEdible.setPhoto(emptyTestString);
                fail("Photo should not be empty, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible.setPhoto(" ");
            assertEquals(testEdible.getPhoto(), " ");

            testEdible.initMetadata(false, " ");
            assertEquals(testEdible.getPhoto(), " ");

            try {
                testEdible.initMetadata(false, null);
                fail("Photo should not be null, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                testEdible.initMetadata(false, emptyTestString);
                fail("Photo should not be empty, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }

        @Test
        @DisplayName("Tests setting a null log unit")
        void testSetLogUnit() {
            try {
                testLog.setUnit(null);
            } catch (Exception e) {
                assertTrue(true);
            }

            try {
                testLog.init(testLog.getQuantity(), null);
            } catch (Exception e) {
                assertTrue(true);
            }
        }
    }

    @Nested
    @DisplayName("Edge case tests")
    class Test_EdgeCases {
        private Edible testEdible;
        private EdibleLog testLog;
        private String testString;
        private String largeTestString;

        @BeforeEach
        void setUp() {
            testEdible = new Edible().initDetails(5, "name", "description", 5, Edible.Unit.g)
                    .initNutrition(5, 5, 5, 5)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "photo");
            testLog = new EdibleLog(testEdible);
            testString = "testString";
            largeTestString = "";

            for (int i = 0; i < 9999; i++) {
                largeTestString = largeTestString + "a";
            }
        }

        @Test
        @DisplayName("Tests setting the smallest DB key for an edible")
        void testSetDBKey() {
            testEdible.setDBKey(0);
            assertEquals(testEdible.getDbkey(), 0);

            testEdible.initDetails(0, "name", "description", 1, Edible.Unit.g);
            assertEquals(testEdible.getDbkey(), 0);
        }

        @Test
        @DisplayName("Tests setting the same DB key that an edible already has")
        void testSetDuplicateDBKey() {
            testEdible.setDBKey(5);
            testEdible.setDBKey(5);
            assertEquals(testEdible.getDbkey(), 5);

            testEdible.initDetails(5, "name", "description", 1, Edible.Unit.g);
            testEdible.initDetails(5, "name", "description", 1, Edible.Unit.g);
            assertEquals(testEdible.getDbkey(), 5);
        }

        @Test
        @DisplayName("Tests setting the longest name for an edible")
        void testSetName() {
            testEdible.setName(largeTestString);
            assertEquals(testEdible.getName(), largeTestString);

            testEdible.initDetails(5, largeTestString, "description", 1, Edible.Unit.g);
            assertEquals(testEdible.getName(), largeTestString);
        }

        @Test
        @DisplayName("Tests setting the same name an edible already has")
        void testSetDuplicateName() {
            testEdible.setName(testString);
            testEdible.setName(testString);
            assertEquals(testEdible.getName(), testString);

            testEdible.initDetails(5, testString, "description", 1, Edible.Unit.g);
            testEdible.initDetails(5, testString, "description", 1, Edible.Unit.g);
            assertEquals(testEdible.getName(), testString);
        }

        @Test
        @DisplayName("Tests setting the longest description for an edible")
        void testSetDescription() {
            testEdible.setDescription(largeTestString);
            assertEquals(testEdible.getDescription(), largeTestString);

            testEdible.initDetails(5, "name", largeTestString, 1, Edible.Unit.g);
            assertEquals(testEdible.getDescription(), largeTestString);
        }

        @Test
        @DisplayName("Tests setting the same description an edible already has")
        void testSetDuplicateDescription() {
            testEdible.setDescription(testString);
            testEdible.setDescription(testString);
            assertEquals(testEdible.getDescription(), testString);

            testEdible.initDetails(5, "name", testString, 1, Edible.Unit.g);
            testEdible.initDetails(5, "name", testString, 1, Edible.Unit.g);
            assertEquals(testEdible.getDescription(), testString);
        }

        @Test
        @DisplayName("Tests setting the largest and smallest quantities for an edible")
        void testSetQuantity() {
            testEdible.setBaseQuantity(1);
            assertEquals(testEdible.getQuantity(), 1);

            testEdible.setBaseQuantity(9999);
            assertEquals(testEdible.getQuantity(), 9999);

            testEdible.initDetails(1, "name", "description", 1, Edible.Unit.g);
            assertEquals(testEdible.getQuantity(), 1);

            testEdible.initDetails(1, "name", "description", 9999, Edible.Unit.g);
            assertEquals(testEdible.getQuantity(), 9999);
        }

        @Test
        @DisplayName("Tests setting the same quantity an edible already has")
        void testSetDuplicateQuantity() {
            testEdible.setBaseQuantity(5);
            testEdible.setBaseQuantity(5);
            assertEquals(testEdible.getQuantity(), 5);

            testEdible.initDetails(5, "name", "description", 5, Edible.Unit.g);
            testEdible.initDetails(5, "name", "description", 5, Edible.Unit.g);
            assertEquals(testEdible.getQuantity(), 5);
        }

        @Test
        @DisplayName("Tests setting the same unit an edibles already has")
        void testSetUnit() {
            testEdible.setBaseUnit(Edible.Unit.cups);
            testEdible.setBaseUnit(Edible.Unit.cups);
            assertEquals(testEdible.getUnit(), Edible.Unit.cups);

            testEdible.initDetails(5, "name", "description", 10, Edible.Unit.cups);
            testEdible.initDetails(5, "name", "description", 10, Edible.Unit.cups);
            assertEquals(testEdible.getUnit(), Edible.Unit.cups);
        }

        @Test
        @DisplayName("Tests setting the largest and smallest calorie counts for an edible")
        void testSetCalories() {
            testEdible.setCalories(0);
            assertEquals(testEdible.getCalories(), 0);

            testEdible.setCalories(9999);
            assertEquals(testEdible.getCalories(), 9999);

            testEdible.initNutrition(0, 1, 1, 1);
            assertEquals(testEdible.getCalories(), 0);

            testEdible.initNutrition(9999, 1, 1, 1);
            assertEquals(testEdible.getCalories(), 9999);
        }

        @Test
        @DisplayName("Tests setting the same calorie count an edible already has")
        void testSetDuplicateCalories() {
            testEdible.setCalories(5);
            testEdible.setCalories(5);
            assertEquals(testEdible.getCalories(), 5);

            testEdible.initNutrition(5, 1, 1, 1);
            testEdible.initNutrition(5, 1, 1, 1);
            assertEquals(testEdible.getCalories(), 5);
        }

        @Test
        @DisplayName("Tests setting the largest and smallest protein counts for an edible")
        void testSetProtein() {
            testEdible.setProtein(0);
            assertEquals(testEdible.getProtein(), 0);

            testEdible.setProtein(9999);
            assertEquals(testEdible.getProtein(), 9999);

            testEdible.initNutrition(1, 0, 1, 1);
            assertEquals(testEdible.getProtein(), 0);

            testEdible.initNutrition(1, 9999, 1, 1);
            assertEquals(testEdible.getProtein(), 9999);
        }

        @Test
        @DisplayName("Tests setting the same protein count an edible already has")
        void testSetDuplicateProtein() {
            testEdible.setProtein(5);
            testEdible.setProtein(5);
            assertEquals(testEdible.getProtein(), 5);

            testEdible.initNutrition(1, 5, 1, 1);
            testEdible.initNutrition(1, 5, 1, 1);
            assertEquals(testEdible.getProtein(), 5);
        }

        @Test
        @DisplayName("Tests setting the largest and smallest carb counts for an edible")
        void testSetCarbs() {
            testEdible.setCarbs(0);
            assertEquals(testEdible.getCarbs(), 0);

            testEdible.setCarbs(9999);
            assertEquals(testEdible.getCarbs(), 9999);

            testEdible.initNutrition(1, 1, 0, 1);
            assertEquals(testEdible.getCarbs(), 0);

            testEdible.initNutrition(1, 1, 9999, 1);
            assertEquals(testEdible.getCarbs(), 9999);
        }

        @Test
        @DisplayName("Tests setting the same carbs count an edible already has")
        void testSetDuplicateCarbs() {
            testEdible.setCarbs(5);
            testEdible.setCarbs(5);
            assertEquals(testEdible.getCarbs(), 5);

            testEdible.initNutrition(1, 1, 5, 1);
            testEdible.initNutrition(1, 1, 5, 1);
            assertEquals(testEdible.getCarbs(), 5);
        }

        @Test
        @DisplayName("Tests setting the largest and smallest fat count for an edible")
        void testSetFat() {
            testEdible.setFat(0);
            assertEquals(testEdible.getFat(), 0);

            testEdible.setFat(9999);
            assertEquals(testEdible.getFat(), 9999);

            testEdible.initNutrition(1, 1, 1, 0);
            assertEquals(testEdible.getFat(), 0);

            testEdible.initNutrition(1, 1, 1, 9999);
            assertEquals(testEdible.getFat(), 9999);
        }

        @Test
        @DisplayName("Tests setting the same fat count an edible already has")
        void testSetDuplicateFat() {
            testEdible.setFat(5);
            testEdible.setFat(5);
            assertEquals(testEdible.getFat(), 5);

            testEdible.initNutrition(1, 1, 1, 5);
            testEdible.initNutrition(1, 1, 1, 5);
            assertEquals(testEdible.getFat(), 5);
        }

        @Test
        @DisplayName("Tests setting the same alcoholic flag an edible already has")
        void testSetIsAlcoholic() {
            testEdible.setAlcoholic(false);
            testEdible.setAlcoholic(false);
            assertFalse(testEdible.getIsAlcoholic());

            testEdible.setAlcoholic(true);
            testEdible.setAlcoholic(true);
            assertTrue(testEdible.getIsAlcoholic());

            testEdible.initCategories(false, false, false, false, false);
            testEdible.initCategories(false, false, false, false, false);
            assertFalse(testEdible.getIsAlcoholic());

            testEdible.initCategories(true, false, false, false, false);
            testEdible.initCategories(true, false, false, false, false);
            assertTrue(testEdible.getIsAlcoholic());
        }

        @Test
        @DisplayName("Tests setting the same spicy flag an edible already has")
        void testSetIsSpicy() {
            testEdible.setSpicy(false);
            testEdible.setSpicy(false);
            assertFalse(testEdible.getIsSpicy());

            testEdible.setSpicy(true);
            testEdible.setSpicy(true);
            assertTrue(testEdible.getIsSpicy());

            testEdible.initCategories(false, false, false, false, false);
            testEdible.initCategories(false, false, false, false, false);
            assertFalse(testEdible.getIsSpicy());

            testEdible.initCategories(false, true, false, false, false);
            testEdible.initCategories(false, true, false, false, false);
            assertTrue(testEdible.getIsSpicy());
        }

        @Test
        @DisplayName("Tests setting the same vegan flag an edible already has")
        void testSetIsVegan() {
            testEdible.setVegan(false);
            testEdible.setVegan(false);
            assertFalse(testEdible.getIsVegan());

            testEdible.setVegan(true);
            testEdible.setVegan(true);
            assertTrue(testEdible.getIsVegan());

            testEdible.initCategories(false, false, false, false, false);
            testEdible.initCategories(false, false, false, false, false);
            assertFalse(testEdible.getIsVegan());

            testEdible.initCategories(false, false, true, false, false);
            testEdible.initCategories(false, false, true, false, false);
            assertTrue(testEdible.getIsVegan());
        }

        @Test
        @DisplayName("Tests setting the same vegetarian flag an edible already has")
        void testSetVegetarian() {
            testEdible.setVegetarian(false);
            testEdible.setVegetarian(false);
            assertFalse(testEdible.getIsVegetarian());

            testEdible.setVegetarian(true);
            testEdible.setVegetarian(true);
            assertTrue(testEdible.getIsVegetarian());

            testEdible.initCategories(false, false, false, false, false);
            testEdible.initCategories(false, false, false, false, false);
            assertFalse(testEdible.getIsVegetarian());

            testEdible.initCategories(false, false, false, true, false);
            testEdible.initCategories(false, false, false, true, false);
            assertTrue(testEdible.getIsVegetarian());
        }

        @Test
        @DisplayName("Tests setting the same gluten free flag an edible already has")
        void testSetGlutenFree() {
            testEdible.setGlutenFree(false);
            testEdible.setGlutenFree(false);
            assertFalse(testEdible.getIsGlutenFree());

            testEdible.setGlutenFree(true);
            testEdible.setGlutenFree(true);
            assertTrue(testEdible.getIsGlutenFree());

            testEdible.initCategories(false, false, false, false, false);
            testEdible.initCategories(false, false, false, false, false);
            assertFalse(testEdible.getIsGlutenFree());

            testEdible.initCategories(false, false, false, false, true);
            testEdible.initCategories(false, false, false, false, true);
            assertTrue(testEdible.getIsGlutenFree());
        }

        @Test
        @DisplayName("Tests setting the same is custom flag an edible already has")
        void testSetIsCustom() {
            testEdible.setCustom(false);
            testEdible.setCustom(false);
            assertFalse(testEdible.getIsCustom());

            testEdible.setCustom(true);
            testEdible.setCustom(true);
            assertTrue(testEdible.getIsCustom());

            testEdible.initMetadata(false, "photo");
            testEdible.initMetadata(false, "photo");
            assertFalse(testEdible.getIsCustom());

            testEdible.initMetadata(true, "photo");
            testEdible.initMetadata(true, "photo");
            assertTrue(testEdible.getIsCustom());
        }

        @Test
        @DisplayName("Tests setting the longest photo for an edible")
        void testSetPhoto() {
            testEdible.setPhoto(largeTestString);
            assertEquals(testEdible.getPhoto(), largeTestString);

            testEdible.initMetadata(false, largeTestString);
            assertEquals(testEdible.getPhoto(), largeTestString);
        }

        @Test
        @DisplayName("Tests setting the same photo an edible already has")
        void testSetDuplicatePhoto() {
            testEdible.setPhoto(testString);
            testEdible.setPhoto(testString);
            assertEquals(testEdible.getPhoto(), testString);

            testEdible.initMetadata(false, testString);
            testEdible.initMetadata(false, testString);
            assertEquals(testEdible.getPhoto(), testString);
        }

        @Test
        @DisplayName("Tests log quantity edge cases")
        void testSetLogQuantity() {
            testLog.setQuantity(1);
            assertEquals(testLog.getQuantity(), 1);
            assertEquals(testLog.getCalories(), 5);

            testLog.setQuantity(9999);
            assertEquals(testLog.getQuantity(), 9999);
            assertEquals(testLog.getCalories(), 5);

            try {
                testLog.init(1, testLog.getUnit());
                assertEquals(testLog.getQuantity(), 1);
                assertEquals(testLog.getCalories(), 1);

                testLog.init(9999, testLog.getUnit());
                assertEquals(testLog.getQuantity(), 9999);
                assertEquals(testLog.getCalories(), 9999);
            } catch (Exception e) {
                fail("Quantities, calories and units are all valid, thus should also be the conversion");
            }
        }

        @Test
        @DisplayName("Tests log quantity edge cases where its assigned to what it currently is")
        void testSetLogDuplicateQuantity() {
            testLog.setQuantity(1);
            testLog.setQuantity(1);
            assertEquals(testLog.getQuantity(), 1);
            assertEquals(testLog.getCalories(), 5);

            testLog.setQuantity(9999);
            testLog.setQuantity(9999);
            assertEquals(testLog.getQuantity(), 9999);
            assertEquals(testLog.getCalories(), 5);

            try {
                testLog.init(1, testLog.getUnit());
                testLog.init(1, testLog.getUnit());
                assertEquals(testLog.getQuantity(), 1);
                assertEquals(testLog.getCalories(), 1);

                testLog.init(9999, testLog.getUnit());
                testLog.init(9999, testLog.getUnit());
                assertEquals(testLog.getQuantity(), 9999);
                assertEquals(testLog.getCalories(), 9999);
            } catch (Exception e) {
                fail("Quantities, calories and units are all valid, thus should also be the conversion");
            }
        }

        @Test
        @DisplayName("Adjusting log calories when nothing gets changed")
        void testSetLogCalories() {
            try {
                testLog.setCalories();
            } catch (Exception e) {
                fail("no error should be thrown, these are valid calories, quantities and units");
            }

            assertEquals(testLog.getCalories(), 5);
        }

        @Test
        @DisplayName("Tests cloning a complex edible with all true or false flags")
        void testCloneEdibleLog() {
            Edible newEdible;

            testEdible.initDetails(0, largeTestString, largeTestString, 1, Edible.Unit.g)
                    .initNutrition(0, 0, 0, 0)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, largeTestString);

            newEdible = testEdible.clone();

            assertEquals(newEdible.getDbkey(), 0);
            assertEquals(newEdible.getName(), largeTestString);
            assertEquals(newEdible.getDescription(), largeTestString);
            assertEquals(newEdible.getQuantity(), 1);
            assertEquals(newEdible.getUnit(), Edible.Unit.g);
            assertEquals(newEdible.getCalories(), 0);
            assertEquals(newEdible.getProtein(), 0);
            assertEquals(newEdible.getCarbs(), 0);
            assertEquals(newEdible.getFat(), 0);
            assertFalse(newEdible.getIsAlcoholic());
            assertFalse(newEdible.getIsSpicy());
            assertFalse(newEdible.getIsVegan());
            assertFalse(newEdible.getIsVegetarian());
            assertFalse(newEdible.getIsGlutenFree());
            assertFalse(newEdible.getIsCustom());
            assertEquals(newEdible.getPhoto(), largeTestString);

            testEdible.initDetails(1, largeTestString, largeTestString, 9999, Edible.Unit.g)
                    .initNutrition(9999, 9999, 9999, 9999)
                    .initCategories(true, true, true, true, true)
                    .initMetadata(true, largeTestString);

            newEdible = testEdible.clone();

            assertEquals(newEdible.getDbkey(), 1);
            assertEquals(newEdible.getName(), largeTestString);
            assertEquals(newEdible.getDescription(), largeTestString);
            assertEquals(newEdible.getQuantity(), 9999);
            assertEquals(newEdible.getUnit(), Edible.Unit.g);
            assertEquals(newEdible.getCalories(), 9999);
            assertEquals(newEdible.getProtein(), 9999);
            assertEquals(newEdible.getCarbs(), 9999);
            assertEquals(newEdible.getFat(), 9999);
            assertTrue(newEdible.getIsAlcoholic());
            assertTrue(newEdible.getIsSpicy());
            assertTrue(newEdible.getIsVegan());
            assertTrue(newEdible.getIsVegetarian());
            assertTrue(newEdible.getIsGlutenFree());
            assertTrue(newEdible.getIsCustom());
            assertEquals(newEdible.getPhoto(), largeTestString);
        }
    }

    @Nested
    @DisplayName("Invalid tests")
    class Test_Invalid {
        private Edible testEdible;
        private EdibleLog testLog;
        private String longTestString;

        @BeforeEach
        void setUp() {
            testEdible = new Edible();
            longTestString = "";

            for (int i = 0; i < 10000; i++) {
                longTestString = longTestString + "a";
            }

            testEdible = new Edible().initDetails(5, "name", "description", 5, Edible.Unit.g)
                    .initNutrition(5, 5, 5, 5)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "photo");
        }

        @Test
        @DisplayName("Tests setting an invalid db key for an edible")
        void testSetDBkey() {
            try {
                testEdible.setDBKey(-1);
                testLog = new EdibleLog(testEdible);
                fail("Should throw an exception, db key is less than 0");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                testEdible.initDetails(-1, "name", "description", 5, Edible.Unit.cups);
                testLog = new EdibleLog(testEdible);
                fail("Should throw an exception, db key is less than 0");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }

        @Test
        @DisplayName("Tests setting an invalid name for an edible")
        void testSetName() {
            try {
                testEdible.setName(longTestString);
                testLog = new EdibleLog(testEdible);
                fail("Should throw an exception, this name is too long");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                testEdible.initDetails(1, longTestString, "description", 5, Edible.Unit.cups);
                testLog = new EdibleLog(testEdible);
                fail("Should throw an exception, this name is too long");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }

        @Test
        @DisplayName("Tests setting an invalid description for an edible")
        void testSetDescription() {
            try {
                testEdible.setDescription(longTestString);
                testLog = new EdibleLog(testEdible);
                fail("Should throw an exception, this description is too long");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                testEdible.initDetails(1, "name", longTestString, 5, Edible.Unit.cups);
                testLog = new EdibleLog(testEdible);
                fail("Should throw an exception, this description is too long");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }

        @Test
        @DisplayName("Tests setting an invalid quantity for an edible")
        void testSetQuantity() {
            try {
                testEdible.setBaseQuantity(0);
                testLog = new EdibleLog(testEdible);
                fail("Should throw an exception, this quantity is too low");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                testEdible.setBaseQuantity(10000);
                testLog = new EdibleLog(testEdible);
                fail("Should throw an exception, this quantity is too big");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                testEdible.initDetails(1, "name", "description", 0, Edible.Unit.cups);
                testLog = new EdibleLog(testEdible);
                fail("Should throw an exception, this quantity is too low");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                testEdible.initDetails(1, "name", "description", 10000, Edible.Unit.cups);
                testLog = new EdibleLog(testEdible);
                fail("Should throw an exception, this quantity is too big");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }

        @Test
        @DisplayName("Tests setting an invalid photo for an edible")
        void testSetPhoto() {
            try {
                testEdible.setPhoto(longTestString);
                testLog = new EdibleLog(testEdible);
                fail("Should throw an exception, this photo is too long");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                testEdible.initMetadata(false, longTestString);
                testLog = new EdibleLog(testEdible);
                fail("Should throw an exception, this photo is too long");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }

        @Test
        @DisplayName("Tests log quantity cases where the quantity is invalid")
        void testSetLogQuantity() {
            testLog = new EdibleLog(testEdible);

            try {
                testLog.setQuantity(0);
                fail("Logs should not have a quantity of 0, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                testLog.setQuantity(10000);
                fail("Logs should not have a quantity of 10000, too big, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                testLog.init(0, testLog.getUnit());
                fail("Logs should not have a quantity of 0, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                testLog.init(10000, testLog.getUnit());
                fail("Logs should not have a quantity of 10000, too big, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }
    }
}

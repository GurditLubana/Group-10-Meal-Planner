package comp3350.team10.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;

import comp3350.team10.application.Main;
import comp3350.team10.objects.DailyLog;
import comp3350.team10.objects.DataFrame;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.EdibleLog;

public class TestLogDBInterface {

    @Nested
    @DisplayName("Simple Tests typical cases should pass")
    class caseSimple {
        LogDBInterface db;
        Calendar currDate;
        Calendar testDate;
        DailyLog currLog;
        DailyLog testLog;
        Edible testEdible;
        ArrayList<Edible> edibleList;

        @BeforeEach
        void setup() {
            Main.startUp();
            this.db = SharedDB.getLogDB();
            currDate = Calendar.getInstance();
        }

        @AfterEach
        void shutdown() {
            Main.shutDown();
        }

        void setupTestLog() {
            edibleList = new ArrayList<Edible>();
            testLog = new DailyLog();
            try {
                edibleList.add( new EdibleLog(
                        new Edible()
                                .initDetails(7, "Rabbit", "desc", 40, Edible.Unit.tbsp)
                                .initNutrition(400, 30, 20, 50)
                                .initCategories(false, false, false, false, false)
                                .initMetadata(false, "photo.jpg")
                ).init(40, Edible.Unit.tbsp));
                edibleList.add( new EdibleLog(
                        new Edible()
                                .initDetails(6, "Carrots", "desc", 30, Edible.Unit.g)
                                .initNutrition(300, 40, 50, 10)
                                .initCategories(false, false, true, true, false)
                                .initMetadata(false, "photo.jpg")
                ).init(30, Edible.Unit.g));
                edibleList.add( new EdibleLog(
                        new Edible()
                                .initDetails(5, "Chicken", "desc", 20, Edible.Unit.tsp)
                                .initNutrition(200, 25, 40, 35)
                                .initCategories(false, false, false, false, true)
                                .initMetadata(false, "photo.jpg")
                ).init(20, Edible.Unit.tsp));
                testLog.init(testDate, edibleList, 1400, 600, 200);
            }
            catch(Exception e) {
                fail(e);
            }
        }

        @Test
        @DisplayName("there should be a valid log at db start")
        void dbConstructionLogEntry(){
            currLog = this.db.searchFoodLogByDate(0, currDate);
            testDate = currLog.getDate();
            assertEquals(currDate.get(Calendar.DAY_OF_YEAR), testDate.get(Calendar.DAY_OF_YEAR));
            assertEquals(3, currLog.getEdibleList().size());
            assertEquals(0, currLog.getExerciseActual());
            assertEquals(600, currLog.getExerciseGoal());
            assertEquals(2000, currLog.getCalorieGoal());
            assertEquals(1726.0, currLog.getCalorieNet());
            assertEquals(274.0, currLog.getEdibleCalories());
            assertEquals(13.7, currLog.getProgressBar());
            assertEquals(0, currLog.getProgressExcess());
        }

        @Test
        @DisplayName("there should be recipes at db start")
        void dbConstructionRecipes(){
            testEdible = this.db.findEdibleByKey(5, false);
            assertTrue(testEdible instanceof EdibleLog);
            assertTrue(testEdible.getName().equals("Walnut"));
            assertTrue(testEdible.getDescription().equals("not a floor nut"));
            assertEquals(Edible.Unit.tbsp, testEdible.getUnit());
            assertEquals(100, testEdible.getCalories());
            assertEquals(30, testEdible.getProtein());
            assertEquals(20, testEdible.getCarbs());
            assertEquals(50, testEdible.getFat());
            assertFalse(testEdible.getIsAlcoholic());
            assertFalse(testEdible.getIsSpicy());
            assertFalse(testEdible.getIsVegan());
            assertFalse(testEdible.getIsVegetarian());
            assertFalse(testEdible.getIsGlutenFree());
            assertFalse(testEdible.getIsCustom());
            assertTrue(testEdible.getPhoto().equals("walnut.jpg"));
        }

        @Test
        @DisplayName("there should be trend data at db start")
        void dbConstructionTrend(){
            ArrayList<Double> result = this.db.getDataFrame(DataFrame.DataType.ConsumedCalories, 7);
            assertEquals(7, result.size());
        }

        @Test
        @DisplayName("we should be able to add a day log to the database")
        void dbAddALog(){

            DailyLog newLog = new DailyLog();
            testDate = Calendar.getInstance();
            testDate.add(Calendar.DAY_OF_YEAR, 10);
            this.setupTestLog();

            testDate.set(2021, 12, 1);
            try {
                newLog.init(testDate, edibleList, 1400, 600, 200);
            }
            catch(Exception e) {
                System.out.println(e);
            }

            this.db.replaceLog(0, newLog);

            testLog = this.db.searchFoodLogByDate(0, testDate);

            assertEquals(testDate.get(Calendar.DAY_OF_YEAR),testLog.getDate().get(Calendar.DAY_OF_YEAR));
            assertEquals(testDate.get(Calendar.YEAR),testLog.getDate().get(Calendar.YEAR));
            assertEquals(testDate.get(Calendar.MONTH),testLog.getDate().get(Calendar.MONTH));
            assertEquals(edibleList, testLog.getEdibleList());
            assertEquals(1400, testLog.getCalorieGoal());
            assertEquals(600, testLog.getExerciseGoal());
            assertEquals(200, testLog.getExerciseActual());
            assertEquals(900, testLog.getEdibleCalories());
            assertEquals(0, testLog.getProgressExcess());
            assertEquals(50, testLog.getProgressBar());
            assertEquals(700, testLog.getCalorieNet());
        }

        @Test
        @DisplayName("we should be able to set a new calorie goal for specific logs")
        void dbSetANewCalorieGoal() {
            Calendar newDate = (Calendar) currDate.clone();
            this.db.setLogCalorieGoal(0, 5000, currDate);
            this.testLog = this.db.searchFoodLogByDate(0, currDate);
            assertEquals(5000, this.testLog.getCalorieGoal());

            newDate.add(Calendar.DAY_OF_YEAR, 3);
            this.db.setLogCalorieGoal(0, 1234, newDate);
            this.testLog = this.db.searchFoodLogByDate(0, newDate);
            assertEquals(1234, this.testLog.getCalorieGoal());

        }

        @Test
        @DisplayName("we should be able to set a new exercise goal for specific logs")
        void dbSetANewExerciseGoal() {
            Calendar newDate = (Calendar) currDate.clone();
            this.db.setLogExerciseGoal(0, 5000, currDate);
            this.testLog = this.db.searchFoodLogByDate(0, currDate);
            assertEquals(5000, this.testLog.getExerciseGoal());

            newDate.add(Calendar.DAY_OF_YEAR, 3);
            this.db.setLogExerciseGoal(0, 1234, newDate);
            this.testLog = this.db.searchFoodLogByDate(0, newDate);
            assertEquals(1234, this.testLog.getExerciseGoal());

        }

        @Test
        @DisplayName("we should be able to set new exercise calories burned for specific logs")
        void dbSetANewExerciseCaloriesBurned() {
            Calendar newDate = (Calendar) currDate.clone();
            this.db.setExerciseActual(0, 5000, currDate);
            this.testLog = this.db.searchFoodLogByDate(0, currDate);
            assertEquals(5000, this.testLog.getExerciseActual());

            newDate.add(Calendar.DAY_OF_YEAR, 3);
            this.db.setExerciseActual(0, 1234, newDate);
            this.testLog = this.db.searchFoodLogByDate(0, newDate);
            assertEquals(1234, this.testLog.getExerciseActual());

        }

    }


    @Nested
    @DisplayName("Complex Tests should pass")
    class caseComplex {
        LogDBInterface db;

        @BeforeEach
        void setup() {
            Main.startUp();
            this.db = SharedDB.getLogDB();
        }

        @AfterEach
        void shutdown() {
            Main.shutDown();
        }

        @Test
        @DisplayName("")
        void test() {

        }
    }


    @Nested
    @DisplayName("Edge case Tests should pass")
    class caseEdge {
        LogDBInterface db;

        @BeforeEach
        void setup() {
            Main.startUp();
            this.db = SharedDB.getLogDB();
        }

        @AfterEach
        void shutdown() {
            Main.shutDown();
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
            Main.startUp();
        }

        @AfterEach
        void shutdown() {
            Main.shutDown();
        }

        @Test
        @DisplayName("")
        void test(){
        }
    }
}

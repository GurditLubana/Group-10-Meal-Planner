package comp3350.team10.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
import java.util.NoSuchElementException;

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
            SharedDB.start();
            SharedDB.startStub();
            //SharedDB.startHsql();
            this.db = SharedDB.getLogDB();
            this.currDate = Calendar.getInstance();
            this.currDate.set(Calendar.MONTH, 9);
            this.currDate.set(Calendar.DAY_OF_MONTH, 10);
            this.testDate = (Calendar) this.currDate.clone();
            this.testDate.add(Calendar.DAY_OF_YEAR, 20);
        }

        @AfterEach
        void shutdown() {
            SharedDB.close();
        }

        void setupTestLog() {
            this.edibleList = new ArrayList<Edible>();
            this.testLog = new DailyLog();
            try {
                this.edibleList.add( new EdibleLog(
                        new Edible()
                                .initDetails(7, "Rabbit", "desc", 40, Edible.Unit.tbsp)
                                .initNutrition(400, 30, 20, 50)
                                .initCategories(false, false, false, false, false)
                                .initMetadata(false, "photo.jpg")
                ).init(40, Edible.Unit.tbsp));
                this.edibleList.add( new EdibleLog(
                        new Edible()
                                .initDetails(6, "Carrots", "desc", 30, Edible.Unit.g)
                                .initNutrition(300, 40, 50, 10)
                                .initCategories(false, false, true, true, false)
                                .initMetadata(false, "photo.jpg")
                ).init(30, Edible.Unit.g));
                this.edibleList.add( new EdibleLog(
                        new Edible()
                                .initDetails(5, "Chicken", "desc", 20, Edible.Unit.tsp)
                                .initNutrition(200, 25, 40, 35)
                                .initCategories(false, false, false, false, true)
                                .initMetadata(false, "photo.jpg")
                ).init(20, Edible.Unit.tsp));
                this.testLog.init(this.testDate, this.edibleList, 1400, 600, 200);
                this.db.replaceLog(0, testLog);
            }
            catch(Exception e) {
                fail(e);
            }
        }

        @Test
        @DisplayName("there should be a valid log at db start")
        void dbConstructionLogEntry(){
            this.currLog = this.db.searchFoodLogByDate(0, this.currDate);
            this.testDate = this.currLog.getDate();
            assertEquals(this.currDate.get(Calendar.DAY_OF_YEAR), this.testDate.get(Calendar.DAY_OF_YEAR));
            assertEquals(3, this.currLog.getEdibleList().size());
            assertEquals(0, this.currLog.getExerciseActual());
            assertEquals(600, this.currLog.getExerciseGoal());
            assertEquals(2000, this.currLog.getCalorieGoal());
            assertEquals(1726.0, this.currLog.getCalorieNet());
            assertEquals(274.0, this.currLog.getEdibleCalories());
            assertEquals(13.7, this.currLog.getProgressBar());
            assertEquals(0, this.currLog.getProgressExcess());
        }

        @Test
        @DisplayName("there should be recipes at db start")
        void dbConstructionRecipes(){
            this.testEdible = this.db.findEdibleByKey(5, false);
            assertTrue(this.testEdible instanceof EdibleLog);
            assertTrue(this.testEdible.getName().equals("Walnut"));
            assertTrue(this.testEdible.getDescription().equals("not a floor nut"));
            assertEquals(Edible.Unit.tbsp, this.testEdible.getUnit());
            assertEquals(100, this.testEdible.getCalories());
            assertEquals(30, this.testEdible.getProtein());
            assertEquals(20, this.testEdible.getCarbs());
            assertEquals(50, this.testEdible.getFat());
            assertFalse(this.testEdible.getIsAlcoholic());
            assertFalse(this.testEdible.getIsSpicy());
            assertFalse(this.testEdible.getIsVegan());
            assertFalse(this.testEdible.getIsVegetarian());
            assertFalse(this.testEdible.getIsGlutenFree());
            assertFalse(this.testEdible.getIsCustom());
            assertTrue(this.testEdible.getPhoto().equals("walnut.jpg"));
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
            Calendar newDate = (Calendar) this.testDate.clone();
            newDate.add(Calendar.DAY_OF_YEAR, 1);
            this.setupTestLog();

            try {
                newLog.init(newDate, this.edibleList, 1400, 600, 200);
            }
            catch(Exception e) {
                System.out.println(e);
            }

            this.db.replaceLog(0, newLog);

            this.testLog = this.db.searchFoodLogByDate(0, newDate);

            assertEquals(testDate.get(Calendar.DAY_OF_YEAR) + 1,testLog.getDate().get(Calendar.DAY_OF_YEAR));
            assertEquals(testDate.get(Calendar.YEAR),testLog.getDate().get(Calendar.YEAR));
            assertEquals(testDate.get(Calendar.MONTH),testLog.getDate().get(Calendar.MONTH));
            assertEquals(edibleList.size(), testLog.getEdibleList().size(), 1);
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

            this.setupTestLog();

            this.db.setLogCalorieGoal(0, 5000, testDate);
            this.testLog = this.db.searchFoodLogByDate(0, testDate);
            assertEquals(5000, this.testLog.getCalorieGoal());

            this.db.setLogCalorieGoal(0, 1234, testDate);
            this.testLog = this.db.searchFoodLogByDate(0, testDate);
            assertEquals(1234, this.testLog.getCalorieGoal());

        }

        @Test
        @DisplayName("we should be able to set a new exercise goal for specific logs")
        void dbSetANewExerciseGoal() {

            this.setupTestLog();
            this.db.setLogExerciseGoal(0, 5000, testDate);
            this.testLog = this.db.searchFoodLogByDate(0, testDate);
            assertEquals(5000, this.testLog.getExerciseGoal());

            this.db.setLogExerciseGoal(0, 1234, testDate);
            this.testLog = this.db.searchFoodLogByDate(0, testDate);
            assertEquals(1234, this.testLog.getExerciseGoal());

        }

        @Test
        @DisplayName("we should be able to set new exercise calories burned for specific logs")
        void dbSetANewExerciseCaloriesBurned() {

            this.setupTestLog();
            this.db.setExerciseActual(0, 5000, testDate);
            this.testLog = this.db.searchFoodLogByDate(0, testDate);
            assertEquals(5000, this.testLog.getExerciseActual());

            this.db.setExerciseActual(0, 1234, testDate);
            this.testLog = this.db.searchFoodLogByDate(0, testDate);
            assertEquals(1234, this.testLog.getExerciseActual());

        }
    }


    @Nested
    @DisplayName("Complex Tests should pass")
    class caseComplex {
        LogDBInterface db;
        Calendar currDate;
        Calendar testDate;
        DailyLog currLog;
        DailyLog testLog;
        Edible testEdible;
        ArrayList<Edible> edibleList;

        @BeforeEach
        void setup() {
            SharedDB.start();
            SharedDB.startStub();
            //SharedDB.startHsql();
            this.db = SharedDB.getLogDB();
            this.currDate = Calendar.getInstance();
            this.currDate.set(Calendar.MONTH, 9);
            this.currDate.set(Calendar.DAY_OF_MONTH, 10);
            this.testDate = (Calendar) this.currDate.clone();
            this.testDate.add(Calendar.DAY_OF_YEAR, 20);
        }

        @AfterEach
        void shutdown() {
            SharedDB.close();
        }

        void setupTestLog() {
            this.edibleList = new ArrayList<Edible>();
            this.testLog = new DailyLog();
            try {
                this.edibleList.add( new EdibleLog(
                        new Edible()
                                .initDetails(7, "Rabbit", "desc", 40, Edible.Unit.tbsp)
                                .initNutrition(400, 30, 20, 50)
                                .initCategories(false, false, false, false, false)
                                .initMetadata(false, "photo.jpg")
                ).init(40, Edible.Unit.tbsp));
                this.edibleList.add( new EdibleLog(
                        new Edible()
                                .initDetails(6, "Carrots", "desc", 30, Edible.Unit.g)
                                .initNutrition(300, 40, 50, 10)
                                .initCategories(false, false, true, true, false)
                                .initMetadata(false, "photo.jpg")
                ).init(30, Edible.Unit.g));
                this.edibleList.add( new EdibleLog(
                        new Edible()
                                .initDetails(5, "Chicken", "desc", 20, Edible.Unit.tsp)
                                .initNutrition(200, 25, 40, 35)
                                .initCategories(false, false, false, false, true)
                                .initMetadata(false, "photo.jpg")
                ).init(20, Edible.Unit.tsp));
                this.testLog.init(this.testDate, this.edibleList, 1400, 600, 200);
                this.db.replaceLog(0, testLog);
            }
            catch(Exception e) {
                fail(e);
            }
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
        Calendar currDate;
        Calendar testDate;
        DailyLog currLog;
        DailyLog testLog;
        Edible testEdible;
        ArrayList<Edible> edibleList;

        @BeforeEach
        void setup() {
            SharedDB.start();
            SharedDB.startStub();
            //SharedDB.startHsql();
            this.db = SharedDB.getLogDB();
            this.currDate = Calendar.getInstance();
            this.currDate.set(Calendar.MONTH, 9);
            this.currDate.set(Calendar.DAY_OF_MONTH, 10);
            this.testDate = (Calendar) this.currDate.clone();
            this.testDate.add(Calendar.DAY_OF_YEAR, 20);
        }

        @AfterEach
        void shutdown() {
            SharedDB.close();
        }

        void setupTestLog() {
            this.edibleList = new ArrayList<Edible>();
            this.testLog = new DailyLog();
            try {
                this.edibleList.add( new EdibleLog(
                        new Edible()
                                .initDetails(7, "Rabbit", "desc", 40, Edible.Unit.tbsp)
                                .initNutrition(400, 30, 20, 50)
                                .initCategories(false, false, false, false, false)
                                .initMetadata(false, "photo.jpg")
                ).init(40, Edible.Unit.tbsp));
                this.edibleList.add( new EdibleLog(
                        new Edible()
                                .initDetails(6, "Carrots", "desc", 30, Edible.Unit.g)
                                .initNutrition(300, 40, 50, 10)
                                .initCategories(false, false, true, true, false)
                                .initMetadata(false, "photo.jpg")
                ).init(30, Edible.Unit.g));
                this.edibleList.add( new EdibleLog(
                        new Edible()
                                .initDetails(5, "Chicken", "desc", 20, Edible.Unit.tsp)
                                .initNutrition(200, 25, 40, 35)
                                .initCategories(false, false, false, false, true)
                                .initMetadata(false, "photo.jpg")
                ).init(20, Edible.Unit.tsp));
                this.testLog.init(this.testDate, this.edibleList, 1400, 600, 200);
                this.db.replaceLog(0, testLog);
            }
            catch(Exception e) {
                fail(e);
            }
        }

        @Test
        @DisplayName("")
        void test() {
        }
    }


    @Nested
    @DisplayName("Tests that should fail")
    class caseFail {
        LogDBInterface db;
        Calendar currDate;
        Calendar testDate;
        DailyLog currLog;
        DailyLog testLog;
        Edible testEdible;
        ArrayList<Edible> edibleList;

        @BeforeEach
        void setup() {
            SharedDB.start();
            SharedDB.startStub();
            //SharedDB.startHsql();
            this.db = SharedDB.getLogDB();
            this.currDate = Calendar.getInstance();
            this.currDate.set(Calendar.MONTH, 9);
            this.currDate.set(Calendar.DAY_OF_MONTH, 10);
            this.testDate = (Calendar) this.currDate.clone();
            this.testDate.add(Calendar.DAY_OF_YEAR, 20);
        }

        @AfterEach
        void shutdown() {
            SharedDB.close();
        }

        void setupTestLog() {
            this.edibleList = new ArrayList<Edible>();
            this.testLog = new DailyLog();
            try {
                this.edibleList.add( new EdibleLog(
                        new Edible()
                                .initDetails(7, "Rabbit", "desc", 40, Edible.Unit.tbsp)
                                .initNutrition(400, 30, 20, 50)
                                .initCategories(false, false, false, false, false)
                                .initMetadata(false, "photo.jpg")
                ).init(40, Edible.Unit.tbsp));
                this.edibleList.add( new EdibleLog(
                        new Edible()
                                .initDetails(6, "Carrots", "desc", 30, Edible.Unit.g)
                                .initNutrition(300, 40, 50, 10)
                                .initCategories(false, false, true, true, false)
                                .initMetadata(false, "photo.jpg")
                ).init(30, Edible.Unit.g));
                this.edibleList.add( new EdibleLog(
                        new Edible()
                                .initDetails(5, "Chicken", "desc", 20, Edible.Unit.tsp)
                                .initNutrition(200, 25, 40, 35)
                                .initCategories(false, false, false, false, true)
                                .initMetadata(false, "photo.jpg")
                ).init(20, Edible.Unit.tsp));
                this.testLog.init(this.testDate, this.edibleList, 1400, 600, 200);
                this.db.replaceLog(0, testLog);
            }
            catch(Exception e) {
                fail(e);
            }
        }

        @Test
        @DisplayName("negative user id should fail")
        void testNegativeuserID(){
            this.setupTestLog();

            assertThrows(NoSuchElementException.class, () -> {
                DailyLog result = this.db.searchFoodLogByDate(-1, this.testDate);
            });

            assertThrows(NoSuchElementException.class, () -> {
                this.db.replaceLog(-1, testLog);
            });

            assertThrows(NoSuchElementException.class, () -> {
                this.db.setExerciseActual(-1, 1111, this.testDate);
            });

            assertThrows(NoSuchElementException.class, () -> {
                this.db.setLogCalorieGoal(-1, 1111, this.testDate);
            });

            assertThrows(NoSuchElementException.class, () -> {
                this.db.setLogExerciseGoal(-1, 1111, this.testDate);
            });
        }

        @Test
        @DisplayName("invalid user id should fail")
        void testInvaliduserID(){
            this.setupTestLog();
        }
    }
}

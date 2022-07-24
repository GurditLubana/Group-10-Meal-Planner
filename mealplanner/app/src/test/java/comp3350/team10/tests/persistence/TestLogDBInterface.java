package comp3350.team10.tests.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
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

import comp3350.team10.objects.Constant;
import comp3350.team10.objects.DailyLog;
import comp3350.team10.objects.DataFrame;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.EdibleLog;
import comp3350.team10.persistence.DBSelector;
import comp3350.team10.persistence.LogDBInterface;
import comp3350.team10.tests.persistence.DataAccessStub;

public class TestLogDBInterface {
    LogDBInterface db;
    Calendar currDate;
    Calendar testDate;
    DailyLog currLog;
    DailyLog testLog;
    Edible testEdible;
    ArrayList<Edible> edibleList;

    @BeforeEach
    void setup() {
        DBSelector.start(new DataAccessStub());
        this.db = DBSelector.getLogDB();
        this.currDate = Calendar.getInstance();
        this.currDate.set(Calendar.MONTH, 9);
        this.currDate.set(Calendar.DAY_OF_MONTH, 10);
        this.testDate = (Calendar) this.currDate.clone();
        this.testDate.add(Calendar.DAY_OF_YEAR, 20);
    }

    @AfterEach
    void shutdown() {
        DBSelector.close();
    }

    void setupTestLog() {
        edibleList = new ArrayList<Edible>();
        testLog = new DailyLog();
        try {
            edibleList.add(new EdibleLog(
                    new Edible()
                            .initDetails(3, "Grain of Rice", "rice desc", 400, Edible.Unit.g)
                            .initNutrition(400, 30, 20, 50)
                            .initCategories(false, false, false, false, false)
                            .initMetadata(false, "rice.jpg")
            ).init(400, Edible.Unit.g));
            edibleList.add(new EdibleLog(
                    new Edible()
                            .initDetails(2, "Cracker", "crack desc", 300, Edible.Unit.g)
                            .initNutrition(300, 40, 50, 10)
                            .initCategories(false, false, true, true, false)
                            .initMetadata(false, "cracker.jpg")
            ).init(300, Edible.Unit.g));
            edibleList.add(new EdibleLog(
                    new Edible()
                            .initDetails(1, "Pear", "This shape bad", 200, Edible.Unit.g)
                            .initNutrition(200, 25, 40, 35)
                            .initCategories(false, false, false, false, true)
                            .initMetadata(false, "pear.jpg")
            ).init(200, Edible.Unit.g));
            testLog.init(testDate, edibleList, 1400, 600, 200);
            db.replaceLog(0, testLog);
        } catch (Exception e) {
            fail(e);
        }
    }

    @Nested
    @DisplayName("Simple Tests typical cases should pass")
    class caseSimple {

        @Test
        @DisplayName("there should be a valid log at db start")
        void dbConstructionLogEntry() {
            currLog = db.searchFoodLogByDate(0, currDate);
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
        void dbConstructionRecipes() {
            testEdible = db.findEdibleByKey(4, false);
            assertTrue(testEdible instanceof EdibleLog);
            assertEquals("Walnut", testEdible.getName());
            assertEquals("not a floor nut", testEdible.getDescription());
            assertEquals(Edible.Unit.tbsp, testEdible.getUnit());
            assertEquals(100, testEdible.getCalories());
            assertEquals(30, testEdible.getProtein());
            assertEquals(45, testEdible.getCarbs());
            assertEquals(25, testEdible.getFat());
            assertTrue(testEdible.getIsAlcoholic());
            assertFalse(testEdible.getIsSpicy());
            assertFalse(testEdible.getIsVegan());
            assertFalse(testEdible.getIsVegetarian());
            assertFalse(testEdible.getIsGlutenFree());
            assertFalse(testEdible.getIsCustom());
            assertEquals("walnut.jpg", testEdible.getPhoto());
        }

        @Test
        @DisplayName("there should be trend data at db start")
        void dbConstructionTrend() {
            ArrayList<Double> result = db.getDataFrame(DataFrame.DataType.ConsumedCalories, 7);
            assertEquals(7, result.size());
        }

        @Test
        @DisplayName("we should be able to add a day log to the database")
        void dbAddALog() {

            DailyLog newLog = new DailyLog();
            Calendar newDate = (Calendar) testDate.clone();
            newDate.add(Calendar.DAY_OF_YEAR, 1);
            setupTestLog();

            try {
                newLog.init(newDate, edibleList, 1400, 600, 200);
            } catch (Exception e) {
                System.out.println(e);
            }

            db.replaceLog(0, newLog);

            testLog = db.searchFoodLogByDate(0, newDate);

            assertEquals(testDate.get(Calendar.DAY_OF_YEAR) + 1, testLog.getDate().get(Calendar.DAY_OF_YEAR));
            assertEquals(testDate.get(Calendar.YEAR), testLog.getDate().get(Calendar.YEAR));
            assertEquals(testDate.get(Calendar.MONTH), testLog.getDate().get(Calendar.MONTH));
            assertEquals(edibleList.size(), testLog.getEdibleList().size(), 1);
            assertEquals(1400, testLog.getCalorieGoal());
            assertEquals(600, testLog.getExerciseGoal());
            assertEquals(200, testLog.getExerciseActual());
            assertEquals(900, testLog.getEdibleCalories());
            assertEquals(0, testLog.getProgressExcess());
            assertEquals(50, testLog.getProgressBar());
            assertEquals(700, testLog.getCalorieNet());
            db.replaceLog(0, new DailyLog().init(newDate, new ArrayList<>(),0,0,0));
        }

        @Test
        @DisplayName("we should be able to set a new calorie goal for specific logs")
        void dbSetANewCalorieGoal() {

            setupTestLog();

            db.setLogCalorieGoal(0, 5000, testDate);
            testLog = db.searchFoodLogByDate(0, testDate);
            assertEquals(5000, testLog.getCalorieGoal());

            db.setLogCalorieGoal(0, 1234, testDate);
            testLog = db.searchFoodLogByDate(0, testDate);
            assertEquals(1234, testLog.getCalorieGoal());

            db.replaceLog(0, new DailyLog().init(testDate, new ArrayList<>(),0,0,0));
        }

//        @Test
//        @DisplayName("we should be able to set a new exercise goal for specific logs")
//        void dbSetANewExerciseGoal() {
//
//            setupTestLog();
//            db.setLogExerciseGoal(0, 5000, testDate);
//            testLog = db.searchFoodLogByDate(0, testDate);
//            assertEquals(5000, testLog.getExerciseGoal());
//
//            db.setLogExerciseGoal(0, 1234, testDate);
//            testLog = db.searchFoodLogByDate(0, testDate);
//            assertEquals(1234, testLog.getExerciseGoal());
//
//            db.replaceLog(0, new DailyLog().init(testDate, new ArrayList<>(),0,0,0));
//        }

        @Test
        @DisplayName("we should be able to set new exercise calories burned for specific logs")
        void dbSetANewExerciseCaloriesBurned() {

            setupTestLog();
            db.setExerciseActual(0, 5000, testDate);
            testLog = db.searchFoodLogByDate(0, testDate);
            assertEquals(5000, testLog.getExerciseActual());

            db.setExerciseActual(0, 1234, testDate);
            testLog = db.searchFoodLogByDate(0, testDate);
            assertEquals(1234, testLog.getExerciseActual());

            db.replaceLog(0, new DailyLog().init(testDate, new ArrayList<>(),0,0,0));
        }
    }

    @Nested
    @DisplayName("Edge case Tests should pass")
    class caseEdge {
        @Test
        @DisplayName("We should be able to remove all entries in a log and have it persist")
        void removeAllentries() {
            setupTestLog();
            DailyLog empty = testLog.clone();
            empty.removeItem(2);
            empty.removeItem(1);
            empty.removeItem(0);

            db.replaceLog(0, empty);
            assertEquals(0, db.searchFoodLogByDate(0, testDate).getEdibleList().size());

            db.replaceLog(0, new DailyLog().init(testDate, new ArrayList<>(),0,0,0));
        }

        @Test
        @DisplayName("We should be able to add an entry to an empty log and have it persist")
        void addToAnEmptyLog() {
            Calendar newDate = (Calendar) testDate.clone();
            newDate.add(Calendar.DAY_OF_YEAR, 1);
            db.replaceLog(0, new DailyLog().init(newDate, new ArrayList<>(),0,0,0));
            DailyLog empty = db.searchFoodLogByDate(0, newDate);
            assertEquals(0, empty.getEdibleList().size());
            try {
                empty.addEdibleToLog(-1, new EdibleLog(
                        new Edible()
                                .initDetails(5, "Chicken", "desc", 20, Edible.Unit.tsp)
                                .initNutrition(200, 25, 40, 35)
                                .initCategories(false, false, false, false, true)
                                .initMetadata(false, "photo.jpg")
                ).init(20, Edible.Unit.tsp));
            } catch (Exception e) {
                fail(e);
            }
            db.replaceLog(0, empty);
            assertEquals(1, db.searchFoodLogByDate(0, newDate).getEdibleList().size());

            db.replaceLog(0, new DailyLog().init(newDate, new ArrayList<>(),0,0,0));
        }

        @Test
        @DisplayName("We should be able to get the first item in the database")
        void getFirstItem() {
            EdibleLog item = db.findEdibleByKey(0, false);
            assertEquals("Apple", item.getName());
        }

        @Test
        @DisplayName("We should be able to set zero")
        void setZero() {

            db.setExerciseActual(0, Constant.ENTRY_MIN_VALUE, testDate);
            assertEquals(Constant.ENTRY_MIN_VALUE, db.searchFoodLogByDate(0, testDate).getExerciseActual());

            db.setLogCalorieGoal(0, Constant.ENTRY_MIN_VALUE, testDate);
            assertEquals(Constant.ENTRY_MIN_VALUE, db.searchFoodLogByDate(0, testDate).getCalorieGoal());

            //db.setLogExerciseGoal(0, Constant.ENTRY_MIN_VALUE, testDate);
            //assertEquals(Constant.ENTRY_MIN_VALUE, db.searchFoodLogByDate(0, testDate).getExerciseGoal());
        }

        @Test
        @DisplayName("We should be able to set MaxValue")
        void setMaxValue() {

            db.setExerciseActual(0, Constant.ENTRY_MAX_VALUE, testDate);
            assertEquals(Constant.ENTRY_MAX_VALUE, db.searchFoodLogByDate(0, testDate).getExerciseActual());

            db.setLogCalorieGoal(0, Constant.ENTRY_MAX_VALUE, testDate);
            assertEquals(Constant.ENTRY_MAX_VALUE, db.searchFoodLogByDate(0, testDate).getCalorieGoal());

            //db.setLogExerciseGoal(0, Constant.ENTRY_MAX_VALUE, testDate);
            //assertEquals(Constant.ENTRY_MAX_VALUE, db.searchFoodLogByDate(0, testDate).getExerciseGoal());

        }

    }


    @Nested
    @DisplayName("Tests that should fail")
    class caseFail {

        @Test
        @DisplayName("non existent user id or dbkey should fail")
        void testNegativeuserID() {
            setupTestLog();

            assertThrows(IllegalArgumentException.class, () -> {
                DailyLog result = db.searchFoodLogByDate(-1, testDate);
            });

            assertEquals(3, testLog.getEdibleList().size());
            testLog.removeItem(0);
            assertThrows(IllegalArgumentException.class, () -> {
                db.replaceLog(-1, testLog);
            });
            assertEquals(3, db.searchFoodLogByDate(0, testDate).getEdibleList().size());

            assertThrows(IllegalArgumentException.class, () -> {
                db.setExerciseActual(-1, 1111, testDate);
            });
            assertEquals(200, db.searchFoodLogByDate(0, testDate).getExerciseActual());

            assertThrows(IllegalArgumentException.class, () -> {
                db.setLogCalorieGoal(-1, 1111, testDate);
            });
            assertEquals(1400, db.searchFoodLogByDate(0, testDate).getCalorieGoal());

            assertThrows(IllegalArgumentException.class, () -> {
                db.setLogExerciseGoal(-1, 1111, testDate);
            });
            assertEquals(600, db.searchFoodLogByDate(0, testDate).getExerciseGoal());

            assertThrows(IllegalArgumentException.class, () -> {
                DailyLog result = db.searchFoodLogByDate(5, testDate);
            });

            assertThrows(IllegalArgumentException.class, () -> {
                db.replaceLog(5, testLog);
            });
            assertEquals(3, db.searchFoodLogByDate(0, testDate).getEdibleList().size());

            assertThrows(IllegalArgumentException.class, () -> {
                db.setExerciseActual(5, 1111, testDate);
            });
            assertEquals(200, db.searchFoodLogByDate(0, testDate).getExerciseActual());

            assertThrows(IllegalArgumentException.class, () -> {
                db.setLogCalorieGoal(5, 1111, testDate);
            });
            assertEquals(1400, db.searchFoodLogByDate(0, testDate).getCalorieGoal());

            assertThrows(IllegalArgumentException.class, () -> {
                db.setLogExerciseGoal(5, 1111, testDate);
            });
            assertEquals(600, db.searchFoodLogByDate(0, testDate).getExerciseGoal());

            assertNull(db.findEdibleByKey(-1, false));
            assertNull(db.findEdibleByKey(-1, true));
            assertNull(db.findEdibleByKey(999999, true));

            db.replaceLog(0, new DailyLog().init(testDate, new ArrayList<>(),0,0,0));
        }

        @Test
        @DisplayName("null input should fail")
        void testNullInput() {
            setupTestLog();

            assertThrows(IllegalArgumentException.class, () -> {
                DailyLog result = db.searchFoodLogByDate(0, null);
            });

            assertThrows(IllegalArgumentException.class, () -> {
                db.replaceLog(0, null);
            });

            assertThrows(IllegalArgumentException.class, () -> {
                db.setExerciseActual(0, 1111, null);
            });

            assertThrows(IllegalArgumentException.class, () -> {
                db.setLogCalorieGoal(0, 1111, null);
            });

            assertThrows(IllegalArgumentException.class, () -> {
                db.setLogExerciseGoal(0, 1111, null);
            });

            assertThrows(IllegalArgumentException.class, () -> {
                ArrayList<Double> result = db.getDataFrame(null, 7);
            });

            db.replaceLog(0, new DailyLog().init(testDate, new ArrayList<>(),0,0,0));
        }

        @Test
        @DisplayName("negative input should fail")
        void testNegInput() {
            setupTestLog();

            assertThrows(IllegalArgumentException.class, () -> {
                db.setExerciseActual(-1, -1111, testDate);
            });
            assertEquals(200, db.searchFoodLogByDate(0, testDate).getExerciseActual());

            assertThrows(IllegalArgumentException.class, () -> {
                db.setLogCalorieGoal(-1, -1111, testDate);
            });
            assertEquals(1400, db.searchFoodLogByDate(0, testDate).getCalorieGoal());

            assertThrows(IllegalArgumentException.class, () -> {
                db.setLogExerciseGoal(-1, -1111, testDate);
            });
            assertEquals(600, db.searchFoodLogByDate(0, testDate).getExerciseGoal());

            assertThrows(IllegalArgumentException.class, () -> {
                ArrayList<Double> result = db.getDataFrame(DataFrame.DataType.ConsumedCalories, -7);
            });

            db.replaceLog(0, new DailyLog().init(testDate, new ArrayList<>(),0,0,0));
        }
    }
}

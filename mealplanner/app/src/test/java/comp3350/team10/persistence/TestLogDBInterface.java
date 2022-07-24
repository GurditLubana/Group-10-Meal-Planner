package comp3350.team10.persistence;

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

public class TestLogDBInterface {
    LogDBInterface db;
    Calendar currDate;
    Calendar testDate;

    @BeforeEach
    void setup() {
        SharedDB.start();
        SharedDB.startStub();
        //SharedDB.startHsql();
        this.db = SharedDB.getLogDB();
        this.currDate = Calendar.getInstance();
        this.currDate.set(Calendar.MONTH, 9);
        this.currDate.set(Calendar.DAY_OF_MONTH, 10);
        testDate = (Calendar) this.currDate.clone();
        testDate.add(Calendar.DAY_OF_YEAR, 20);
    }

    @AfterEach
    void shutdown() {
        SharedDB.close();
    }

    public static void TestLogDBInterface() {

    }

    @Nested
    @DisplayName("Simple Tests typical cases should pass")
    class caseSimple {
        DailyLog currLog;
        DailyLog testLog;
        Edible testEdible;
        ArrayList<Edible> edibleList;

        @BeforeEach
        void setup() {
        }

        void setupTestLog() {
            this.edibleList = new ArrayList<Edible>();
            this.testLog = new DailyLog();
            try {
                this.edibleList.add(new EdibleLog(
                        new Edible()
                                .initDetails(7, "Rabbit", "desc", 40, Edible.Unit.tbsp)
                                .initNutrition(400, 30, 20, 50)
                                .initCategories(false, false, false, false, false)
                                .initMetadata(false, "photo.jpg")
                ).init(40, Edible.Unit.tbsp));
                this.edibleList.add(new EdibleLog(
                        new Edible()
                                .initDetails(6, "Carrots", "desc", 30, Edible.Unit.g)
                                .initNutrition(300, 40, 50, 10)
                                .initCategories(false, false, true, true, false)
                                .initMetadata(false, "photo.jpg")
                ).init(30, Edible.Unit.g));
                this.edibleList.add(new EdibleLog(
                        new Edible()
                                .initDetails(5, "Chicken", "desc", 20, Edible.Unit.tsp)
                                .initNutrition(200, 25, 40, 35)
                                .initCategories(false, false, false, false, true)
                                .initMetadata(false, "photo.jpg")
                ).init(20, Edible.Unit.tsp));
                this.testLog.init(testDate, this.edibleList, 1400, 600, 200);
                db.replaceLog(0, testLog);
            } catch (Exception e) {
                fail(e);
            }
        }

        @Test
        @DisplayName("there should be a valid log at db start")
        void dbConstructionLogEntry() {
            this.currLog = db.searchFoodLogByDate(0, currDate);
            testDate = this.currLog.getDate();
            assertEquals(currDate.get(Calendar.DAY_OF_YEAR), testDate.get(Calendar.DAY_OF_YEAR));
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
        void dbConstructionRecipes() {
            this.testEdible = db.findEdibleByKey(4, false);
            assertTrue(this.testEdible instanceof EdibleLog);
            assertEquals("Walnut", this.testEdible.getName());
            assertEquals("not a floor nut", this.testEdible.getDescription());
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
            assertEquals("walnut.jpg", this.testEdible.getPhoto());
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
            this.setupTestLog();

            try {
                newLog.init(newDate, this.edibleList, 1400, 600, 200);
            } catch (Exception e) {
                System.out.println(e);
            }

            db.replaceLog(0, newLog);

            this.testLog = db.searchFoodLogByDate(0, newDate);

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
        }

        @Test
        @DisplayName("we should be able to set a new calorie goal for specific logs")
        void dbSetANewCalorieGoal() {

            this.setupTestLog();

            db.setLogCalorieGoal(0, 5000, testDate);
            this.testLog = db.searchFoodLogByDate(0, testDate);
            assertEquals(5000, this.testLog.getCalorieGoal());

            db.setLogCalorieGoal(0, 1234, testDate);
            this.testLog = db.searchFoodLogByDate(0, testDate);
            assertEquals(1234, this.testLog.getCalorieGoal());

        }

        @Test
        @DisplayName("we should be able to set a new exercise goal for specific logs")
        void dbSetANewExerciseGoal() {

            this.setupTestLog();
            db.setLogExerciseGoal(0, 5000, testDate);
            this.testLog = db.searchFoodLogByDate(0, testDate);
            assertEquals(5000, this.testLog.getExerciseGoal());

            db.setLogExerciseGoal(0, 1234, testDate);
            this.testLog = db.searchFoodLogByDate(0, testDate);
            assertEquals(1234, this.testLog.getExerciseGoal());

        }

        @Test
        @DisplayName("we should be able to set new exercise calories burned for specific logs")
        void dbSetANewExerciseCaloriesBurned() {

            this.setupTestLog();
            db.setExerciseActual(0, 5000, testDate);
            this.testLog = db.searchFoodLogByDate(0, testDate);
            assertEquals(5000, this.testLog.getExerciseActual());

            db.setExerciseActual(0, 1234, testDate);
            this.testLog = db.searchFoodLogByDate(0, testDate);
            assertEquals(1234, this.testLog.getExerciseActual());

        }
    }

    @Nested
    @DisplayName("Edge case Tests should pass")
    class caseEdge {
        DailyLog currLog;
        DailyLog testLog;
        Edible testEdible;
        ArrayList<Edible> edibleList;

        @BeforeEach
        void setup() {
        }

        void setupTestLog() {
            this.edibleList = new ArrayList<Edible>();
            this.testLog = new DailyLog();
            try {
                this.edibleList.add(new EdibleLog(
                        new Edible()
                                .initDetails(7, "Rabbit", "desc", 40, Edible.Unit.tbsp)
                                .initNutrition(400, 30, 20, 50)
                                .initCategories(false, false, false, false, false)
                                .initMetadata(false, "photo.jpg")
                ).init(40, Edible.Unit.tbsp));
                this.edibleList.add(new EdibleLog(
                        new Edible()
                                .initDetails(6, "Carrots", "desc", 30, Edible.Unit.g)
                                .initNutrition(300, 40, 50, 10)
                                .initCategories(false, false, true, true, false)
                                .initMetadata(false, "photo.jpg")
                ).init(30, Edible.Unit.g));
                this.edibleList.add(new EdibleLog(
                        new Edible()
                                .initDetails(5, "Chicken", "desc", 20, Edible.Unit.tsp)
                                .initNutrition(200, 25, 40, 35)
                                .initCategories(false, false, false, false, true)
                                .initMetadata(false, "photo.jpg")
                ).init(20, Edible.Unit.tsp));
                this.testLog.init(testDate, this.edibleList, 1400, 600, 200);
                db.replaceLog(0, testLog);
            } catch (Exception e) {
                fail(e);
            }
        }

        @Test
        @DisplayName("We should be able to remove all entries in a log and have it persist")
        void removeAllentries() {
            this.setupTestLog();
            DailyLog empty = testLog.clone();
            empty.removeItem(2);
            empty.removeItem(1);
            empty.removeItem(0);

            db.replaceLog(0, empty);
            assertEquals(0, db.searchFoodLogByDate(0, testDate).getEdibleList().size());
        }

        @Test
        @DisplayName("We should be able to add an entry to an empty log and have it persist")
        void addToAnEmptyLog() {
            Calendar newDate = (Calendar) testDate.clone();
            newDate.add(Calendar.DAY_OF_YEAR, 1);
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

            db.setLogExerciseGoal(0, Constant.ENTRY_MIN_VALUE, testDate);
            assertEquals(Constant.ENTRY_MIN_VALUE, db.searchFoodLogByDate(0, testDate).getExerciseGoal());
        }

        @Test
        @DisplayName("We should be able to set MaxValue")
        void setMaxValue() {

            db.setExerciseActual(0, Constant.ENTRY_MAX_VALUE, testDate);
            assertEquals(Constant.ENTRY_MAX_VALUE, db.searchFoodLogByDate(0, testDate).getExerciseActual());

            db.setLogCalorieGoal(0, Constant.ENTRY_MAX_VALUE, testDate);
            assertEquals(Constant.ENTRY_MAX_VALUE, db.searchFoodLogByDate(0, testDate).getCalorieGoal());

            db.setLogExerciseGoal(0, Constant.ENTRY_MAX_VALUE, testDate);
            assertEquals(Constant.ENTRY_MAX_VALUE, db.searchFoodLogByDate(0, testDate).getExerciseGoal());

        }

    }


    @Nested
    @DisplayName("Tests that should fail")
    class caseFail {
        DailyLog currLog;
        DailyLog testLog;
        Edible testEdible;
        ArrayList<Edible> edibleList;

        @BeforeEach
        void setup() {
        }

        void setupTestLog() {
            this.edibleList = new ArrayList<Edible>();
            this.testLog = new DailyLog();
            try {
                this.edibleList.add(new EdibleLog(
                        new Edible()
                                .initDetails(7, "Rabbit", "desc", 40, Edible.Unit.tbsp)
                                .initNutrition(400, 30, 20, 50)
                                .initCategories(false, false, false, false, false)
                                .initMetadata(false, "photo.jpg")
                ).init(40, Edible.Unit.tbsp));
                this.edibleList.add(new EdibleLog(
                        new Edible()
                                .initDetails(6, "Carrots", "desc", 30, Edible.Unit.g)
                                .initNutrition(300, 40, 50, 10)
                                .initCategories(false, false, true, true, false)
                                .initMetadata(false, "photo.jpg")
                ).init(30, Edible.Unit.g));
                this.edibleList.add(new EdibleLog(
                        new Edible()
                                .initDetails(5, "Chicken", "desc", 20, Edible.Unit.tsp)
                                .initNutrition(200, 25, 40, 35)
                                .initCategories(false, false, false, false, true)
                                .initMetadata(false, "photo.jpg")
                ).init(20, Edible.Unit.tsp));
                this.testLog.init(testDate, this.edibleList, 1400, 600, 200);
                db.replaceLog(0, testLog);
            } catch (Exception e) {
                fail(e);
            }
        }

        @Test
        @DisplayName("non existent user id or dbkey should fail")
        void testNegativeuserID() {
            this.setupTestLog();

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
        }

        @Test
        @DisplayName("null input should fail")
        void testNullInput() {
            this.setupTestLog();

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
        }

        @Test
        @DisplayName("negative input should fail")
        void testNegInput() {
            this.setupTestLog();

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
        }
    }
}

package comp3350.team10.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        Edible testEdible;

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

        @Test
        @DisplayName("there should be a valid log at db start")
        void dbConstructionLogEntry(){
            currLog = this.db.searchFoodLogByDate(currDate, 0);
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
        }


//        public DailyLog searchFoodLogByDate(Calendar date, int userID);
//        public EdibleLog findEdibleByKey(int dbkey, boolean isCustom);
//        public void addLog(DailyLog newLog, int userID);
//        public void deleteLog(DailyLog delLog, int userID);
//        public void setExerciseActual(double newExercise, DailyLog currLog, int userID);
//        public void setLogCalorieGoal(int userID, double goal, Calendar date);
//        public void setLogExerciseGoal(int userID, double goal, Calendar date);
//        public ArrayList<Double> getDataFrame(DataFrame.DataType type, int days);
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

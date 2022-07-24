package comp3350.team10.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.NoSuchElementException;

import comp3350.team10.application.Main;
import comp3350.team10.objects.DailyLog;
import comp3350.team10.persistence.SharedDB;

public class TestMealDiaryOps {

    @Nested
    @DisplayName("Simple Tests")
    class mealDiarySimple {
        private MealDiaryOps ops;
        private Calendar currDate;
        private Calendar testDate;

        @BeforeEach
        void setup() {
            SharedDB.start();
            SharedDB.startStub();
            //SharedDB.startHsql();
            this.ops = new MealDiaryOps();
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

        @Test
        @DisplayName("object state at construction")
        void objectStateAtConstruction() {
            DailyLog currLog = ops.getCurrLog();

            assertEquals(2000, currLog.getCalorieGoal());
            assertEquals(274, (int) currLog.getEdibleCalories());
            assertEquals(0, (int) currLog.getExerciseActual());
            assertEquals(1726, (int) currLog.getCalorieNet());
            assertEquals(600, (int) currLog.getExerciseGoal());
            assertEquals(3, currLog.getEdibleList().size());
            assertEquals(currDate.get(Calendar.YEAR), currLog.getDate().get(Calendar.YEAR));
            assertEquals(currDate.get(Calendar.MONTH), currLog.getDate().get(Calendar.MONTH));
            assertEquals(currDate.get(Calendar.DATE), currLog.getDate().get(Calendar.DATE));
        }

        @Test
        @DisplayName("prevDate Should Decrement Date by 1 day")
        void prev() {
            ops.prevDate();
            testDate = ops.getCurrLog().getDate();
            assertEquals(currDate.get(Calendar.DAY_OF_YEAR) - 1, testDate.get(Calendar.DAY_OF_YEAR));
        }

        @Test
        @DisplayName("nextDate Should Increment Date by 1 day")
        void next() {
            ops.nextDate();
            testDate = ops.getCurrLog().getDate();
            assertEquals(currDate.get(Calendar.DAY_OF_YEAR) + 1, testDate.get(Calendar.DAY_OF_YEAR));
        }

        @Test
        @DisplayName("prevDate nextDate can return to a previous date")
        void prevnext() {
            ops.prevDate();
            ops.prevDate();
            ops.nextDate();
            ops.nextDate();
            testDate = ops.getCurrLog().getDate();
            assertEquals(currDate.get(Calendar.DAY_OF_YEAR), testDate.get(Calendar.DAY_OF_YEAR));
        }

        @Test
        @DisplayName("prevDate prevDate can return to a previous date")
        void nextprev() {
            ops.nextDate();
            ops.nextDate();
            ops.prevDate();
            ops.prevDate();
            testDate = ops.getCurrLog().getDate();
            assertEquals(currDate.get(Calendar.DAY_OF_YEAR), testDate.get(Calendar.DAY_OF_YEAR));
        }

        @Test
        @DisplayName("we should be able to set a date within 2 years")
        void anyDate() {
            Calendar newDate = Calendar.getInstance();
            newDate.set(2021, 12, 1);

            ops.setListDate(newDate);
            testDate = ops.getCurrLog().getDate();
            assertEquals(newDate.get(Calendar.YEAR), testDate.get(Calendar.YEAR));
            assertEquals(newDate.get(Calendar.MONTH), testDate.get(Calendar.MONTH));
            assertEquals(newDate.get(Calendar.DATE), testDate.get(Calendar.DATE));

            newDate.set(2023, 4, 1);
            ops.setListDate(newDate);
            testDate = ops.getCurrLog().getDate();
            assertEquals(newDate.get(Calendar.YEAR), testDate.get(Calendar.YEAR));
            assertEquals(newDate.get(Calendar.MONTH), testDate.get(Calendar.MONTH));
            assertEquals(newDate.get(Calendar.DATE), testDate.get(Calendar.DATE));
        }

        @Test
        @DisplayName("Switching dates results in updated food log")
        void newDateNewLog() {
            DailyLog currLog = ops.getCurrLog();
            DailyLog newLog = null;

            ops.prevDate();
            newLog = ops.getCurrLog();

            assertNotEquals(currLog, newLog);
        }

        @Test
        @DisplayName("We should be able to add a food item from the db to the DailyLog")
        void addFoodToLog() {
            DailyLog currLog = ops.getCurrLog();

            int prevLogSize = currLog.getEdibleList().size();
            ops.addByKey(2, false);
            assertEquals(prevLogSize + 1, currLog.getEdibleList().size());
        }
    }


    @Nested
    @DisplayName("Complex Tests should pass")
    class mealDiaryComplex {
        private MealDiaryOps ops;
        private Calendar currDate;
        private Calendar testDate;

        @BeforeEach
        void setup() {

            SharedDB.start();
            SharedDB.startStub();
            ops = new MealDiaryOps();
            currDate = Calendar.getInstance();
            testDate = Calendar.getInstance();
        }

        @AfterEach
        void shutdown() {
            SharedDB.close();
        }

        @Test
        @DisplayName("We should be able to commit Logs with a single modification to persistent storage")
        void commitMinorModdedLogToDB() {
            DailyLog currLog = ops.getCurrLog();

            currLog.setCalorieGoal(3000);
            ops.logChangedUpdateDB();
            ops.prevDate();
            ops.nextDate();
            currLog = ops.getCurrLog();

            assertEquals(currLog.getCalorieGoal(), 3000);
            assertEquals((int) currLog.getEdibleCalories(), 274);
            assertEquals((int) currLog.getExerciseActual(), 0);
            assertEquals((int) currLog.getCalorieNet(), 2726);
            assertEquals((int) currLog.getExerciseGoal(), 600);
            assertEquals(currLog.getEdibleList().size(), 3);

            currLog.setExerciseActual(5);
            ops.logChangedUpdateDB();
            ops.prevDate();
            ops.nextDate();
            currLog = ops.getCurrLog();

            assertEquals(currLog.getCalorieGoal(), 3000);
            assertEquals((int) currLog.getEdibleCalories(), 274);
            assertEquals((int) currLog.getExerciseActual(), 5);
            assertEquals((int) currLog.getCalorieNet(), 2731);
            assertEquals((int) currLog.getExerciseGoal(), 600);
            assertEquals(currLog.getEdibleList().size(), 3);

            currLog.setExerciseGoal(15);
            ops.logChangedUpdateDB();
            ops.prevDate();
            ops.nextDate();
            currLog = ops.getCurrLog();

            assertEquals(currLog.getCalorieGoal(), 3000);
            assertEquals((int) currLog.getEdibleCalories(), 274);
            assertEquals((int) currLog.getExerciseActual(), 5);
            assertEquals((int) currLog.getCalorieNet(), 2731);
            assertEquals((int) currLog.getExerciseGoal(), 15);
            assertEquals(currLog.getEdibleList().size(), 3);
        }

        @Test
        @DisplayName("We should be able to commit Logs with a multiple modification to persistent storage")
        void commitHeavilyModdedLogToDB() {
            DailyLog currLog = ops.getCurrLog();

            currLog.setCalorieGoal(3000);
            currLog.setExerciseActual(5);
            ops.logChangedUpdateDB();
            ops.prevDate();
            ops.nextDate();
            currLog = ops.getCurrLog();

            assertEquals(currLog.getCalorieGoal(), 3000);
            assertEquals((int) currLog.getEdibleCalories(), 274);
            assertEquals((int) currLog.getExerciseActual(), 5);
            assertEquals((int) currLog.getCalorieNet(), 2731);
            assertEquals((int) currLog.getExerciseGoal(), 600);
            assertEquals(currLog.getEdibleList().size(), 3);
        }

        @Test
        @DisplayName("We should be able to commit Logs where all immediate data has been modified to persistent storage")
        void commitTotallyChangedLogToDB() {
            DailyLog currLog = ops.getCurrLog();

            currLog.setCalorieGoal(3000);
            currLog.setExerciseActual(5);
            currLog.setExerciseGoal(15);
            ops.logChangedUpdateDB();
            ops.prevDate();
            ops.nextDate();
            currLog = ops.getCurrLog();

            assertEquals(currLog.getCalorieGoal(), 3000);
            assertEquals((int) currLog.getEdibleCalories(), 274);
            assertEquals((int) currLog.getExerciseActual(), 5);
            assertEquals((int) currLog.getCalorieNet(), 2731);
            assertEquals((int) currLog.getExerciseGoal(), 15);
            assertEquals(currLog.getEdibleList().size(), 3);
        }
    }


    @Nested
    @DisplayName("Edge case Tests should pass")
    class mealDiaryEdge {
        private MealDiaryOps ops;
        private Calendar currDate;
        private Calendar testDate;

        @BeforeEach
        void setup() {

            SharedDB.start();
            SharedDB.startStub();
            ops = new MealDiaryOps();
            currDate = Calendar.getInstance();
            testDate = Calendar.getInstance();
        }

        @AfterEach
        void shutdown() {
            SharedDB.close();
        }

        @Test
        @DisplayName("we should recieve the same data if nothing is chagned and the log gets updated in the database")
        void updateDBWithNoChanges() {
            DailyLog currLog;

            ops.logChangedUpdateDB();
            ops.prevDate();
            ops.nextDate();
            currLog = ops.getCurrLog();

            assertEquals(currLog.getCalorieGoal(), 2000);
            assertEquals((int) currLog.getEdibleCalories(), 274);
            assertEquals((int) currLog.getExerciseActual(), 0);
            assertEquals((int) currLog.getCalorieNet(), 1726);
            assertEquals((int) currLog.getExerciseGoal(), 600);
            assertEquals(currLog.getEdibleList().size(), 3);
        }

        @Test
        @DisplayName("we should be able to set a date within 2 years inclusive")
        void anyDate() {
            Calendar newDate = Calendar.getInstance();
            newDate.set(currDate.get(Calendar.YEAR) - 2, currDate.get(Calendar.MONTH), currDate.get(Calendar.DATE));

            ops.setListDate(newDate);
            testDate = ops.getCurrLog().getDate();
            assertEquals(newDate.get(Calendar.YEAR), testDate.get(Calendar.YEAR));
            assertEquals(newDate.get(Calendar.MONTH), testDate.get(Calendar.MONTH));
            assertEquals(newDate.get(Calendar.DATE), testDate.get(Calendar.DATE));

            newDate.set(currDate.get(Calendar.YEAR) + 2, currDate.get(Calendar.MONTH), currDate.get(Calendar.DATE));
            ops.setListDate(newDate);
            testDate = ops.getCurrLog().getDate();
            assertEquals(newDate.get(Calendar.YEAR), testDate.get(Calendar.YEAR));
            assertEquals(newDate.get(Calendar.MONTH), testDate.get(Calendar.MONTH));
            assertEquals(newDate.get(Calendar.DATE), testDate.get(Calendar.DATE));
        }

        @Test
        @DisplayName("We should be able to add the first food item in the db to a DailyLog with an odd number of entries")
        void addFoodToOddNumberedLog() {
            DailyLog currLog = ops.getCurrLog();

            int prevLogSize = currLog.getEdibleList().size();
            ops.addByKey(0, false);
            assertEquals(prevLogSize + 1, currLog.getEdibleList().size());
            assertEquals(currLog.getEdibleList().get(0).getName(), "Apple");
            assertEquals(currLog.getEdibleList().get(1).getName(), "Pear");
            assertEquals(currLog.getEdibleList().get(2).getName(), "Cracker");
            assertEquals(currLog.getEdibleList().get(3).getName(), "Apple");
            assertEquals((int) currLog.getCalorieNet(), 1626);
            assertEquals((int) currLog.getEdibleCalories(), 374);
        }

        @Test
        @DisplayName("We should be able to add the first food item in the db to a DailyLog with an even number of entries")
        void addFoodToEvenNumberedLog() {
            DailyLog currLog = ops.getCurrLog();

            int prevLogSize = currLog.getEdibleList().size();
            ops.addByKey(0, false);
            assertEquals(prevLogSize + 1, currLog.getEdibleList().size());
            assertEquals(currLog.getEdibleList().get(0).getName(), "Apple");
            assertEquals(currLog.getEdibleList().get(1).getName(), "Pear");
            assertEquals(currLog.getEdibleList().get(2).getName(), "Cracker");
            assertEquals(currLog.getEdibleList().get(3).getName(), "Apple");
            assertEquals((int) currLog.getCalorieNet(), 1626);
            assertEquals((int) currLog.getEdibleCalories(), 374);

            ops.addByKey(0, false);
            assertEquals(prevLogSize + 2, currLog.getEdibleList().size());
            assertEquals(currLog.getEdibleList().get(0).getName(), "Apple");
            assertEquals(currLog.getEdibleList().get(1).getName(), "Pear");
            assertEquals(currLog.getEdibleList().get(2).getName(), "Cracker");
            assertEquals(currLog.getEdibleList().get(3).getName(), "Apple");
            assertEquals(currLog.getEdibleList().get(4).getName(), "Apple");
            assertEquals((int) currLog.getCalorieNet(), 1526);
            assertEquals((int) currLog.getEdibleCalories(), 474);
        }
    }


    @Nested
    @DisplayName("Tests that should fail")
    class mealDiaryFail {
        private MealDiaryOps ops;
        private Calendar currDate;
        private Calendar testDate;

        @BeforeEach
        void setup() {

            SharedDB.start();
            SharedDB.startStub();
            ops = new MealDiaryOps();
            currDate = Calendar.getInstance();
            testDate = Calendar.getInstance();
        }

        @AfterEach
        void shutdown() {
            SharedDB.close();
        }

        @Test
        @DisplayName("any date request beyond 2 years of current date")
        void anyDate() {
            Calendar newDate = Calendar.getInstance();
            newDate.set(currDate.get(Calendar.YEAR) - 3, currDate.get(Calendar.MONTH), currDate.get(Calendar.DATE));
            assertThrows(IllegalArgumentException.class, () -> {
                ops.setListDate(newDate);
            });

            newDate.set(currDate.get(Calendar.YEAR) + 3, currDate.get(Calendar.MONTH), currDate.get(Calendar.DATE));
            assertThrows(IllegalArgumentException.class, () -> {
                ops.setListDate(newDate);
            });
        }

        @Test
        @DisplayName("requests for dbkeys that do not exist")
        void dbKey() {

            assertThrows(NoSuchElementException.class, () -> {
                ops.addByKey(-5, false);
            });


            assertThrows(NoSuchElementException.class, () -> {
                ops.addByKey(999999999, false);
            });
        }

        @Test
        @DisplayName("object construction should fail if the db isn't started")
        void noDB() {
            SharedDB.close();
            assertThrows(NullPointerException.class, () -> {
                ops = new MealDiaryOps();
            });
        }
    }
}

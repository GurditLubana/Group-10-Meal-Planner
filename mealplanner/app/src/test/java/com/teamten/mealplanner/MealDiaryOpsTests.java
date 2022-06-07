package com.teamten.mealplanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import comp3350.team10.business.MealDiaryOps;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MealDiaryOpsTests {

    @Nested
    @DisplayName("Initial Object state")
    class constructor {
        MealDiaryOps ops;

        @BeforeEach
        void setup() {
            ops = new MealDiaryOps();
        }

        @Test
        void calories_consumed(){
            assertTrue(ops.getCalorieConsumed() >= 0);
            assertTrue(ops.getCalorieConsumed() <= 9999);
        }

        @Test
        void calories_exercise(){
            assertTrue(ops.getCalorieExercise() >= 0);
            assertTrue(ops.getCalorieExercise() <= 9999);
        }

        @Test
        void list_date(){
            assertTrue(ops.getListDate().YEAR == Calendar.getInstance().YEAR);
            assertTrue(ops.getListDate().DAY_OF_YEAR == Calendar.getInstance().DAY_OF_YEAR);
        }

        @Test
        void calorie_goal(){
            assertTrue(ops.getCalorieGoal() >= 0 );
            assertTrue(ops.getCalorieGoal() <= 9999 );
        }

        @Test
        void calorie_net(){
            assertTrue(ops.getCalorieNet() > -19999);
            assertTrue(ops.getCalorieNet() < 19999);
        }

        @Test
        void progress_bar(){
            assertTrue(ops.getProgressBar() >= 0 );
            assertTrue(ops.getProgressBar() <= 100 );
        }

        @Test
        void progress_exercise(){
            assertTrue(ops.getProgressExcess() >= 0 );
            assertTrue(ops.getProgressExcess() <= 25 );
        }

        @Test
        void initial_list(){
            assertTrue(ops.getList() != null);
        }
    }

    @Nested
    @DisplayName("Date traversal")
    class date {
        MealDiaryOps ops;
        Calendar currDate;

        @BeforeEach
        void setup() {
            ops = new MealDiaryOps();
            currDate = (Calendar) ops.getListDate().clone();
        }

        @Test
        @DisplayName("prevDate Should Decrement Date by 1")
        void prev(){
            long diff = 0;
            for(int i = 1; i < 6; i++) {
                ops.prevDate();
                diff = ChronoUnit.DAYS.between(currDate.toInstant(), ops.getListDate().toInstant());
                assertEquals(diff, -i);
            }
        }

        @Test
        @DisplayName("nextDate Should Increment Date by 1")
        void next(){
            long diff = 0;
            for(int i = 1; i < 6; i++) {
                ops.nextDate();
                diff = ChronoUnit.DAYS.between(currDate.toInstant(), ops.getListDate().toInstant());
                assertEquals(diff, i);
            }
        }

        @Test
        @DisplayName("prevDate nextDate can return to a previous date")
        void prevnext(){
            long diff = 0;
            for(int i = 1; i < 6; i++) {
                ops.prevDate();
                diff = ChronoUnit.DAYS.between(currDate.toInstant(), ops.getListDate().toInstant());
                assertEquals(diff, -i);
            }
            for(int i = 4; i > 0; i--) {
                ops.nextDate();
                diff = ChronoUnit.DAYS.between(currDate.toInstant(), ops.getListDate().toInstant());
                assertEquals(diff, -i);
            }

        }
        @Test
        @DisplayName("we should be able to set a date within 2 years")
        void anydate(){
            long diff = 0;
            Calendar newDate = Calendar.getInstance();
            Calendar testDate = Calendar.getInstance();
            newDate.set(2020, 12, 1);
            testDate.set(2020, 12, 1);
            diff = ChronoUnit.DAYS.between(currDate.toInstant(), ops.getListDate().toInstant());
            assertEquals(diff, 0);

            ops.setListDate(newDate);
            diff = ChronoUnit.DAYS.between(testDate.toInstant(), ops.getListDate().toInstant());
            assertEquals(diff, 0);

            newDate.set(2024, 4, 1);
            testDate.set(2024, 4, 1);
            diff = ChronoUnit.DAYS.between(testDate.toInstant(), ops.getListDate().toInstant());
            assertEquals(diff, 0);
        }
    }


}
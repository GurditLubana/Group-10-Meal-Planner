package com.teamten.mealplanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

import comp3350.team10.business.MealDiaryOps;




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


}
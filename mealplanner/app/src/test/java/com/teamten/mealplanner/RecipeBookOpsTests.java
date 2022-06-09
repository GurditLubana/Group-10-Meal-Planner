package com.teamten.mealplanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

import comp3350.team10.business.MealDiaryOps;
import comp3350.team10.persistence.DataAccessStub;
import comp3350.team10.persistence.SharedDB;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RecipeBookOpsTests {

    @Nested
    @DisplayName("Database is shared between recipebook and mealdiary")
    class someClass{
        DataAccessStub db;
        MealDiaryOps ops;
        Calendar currDate;

        @BeforeEach
        void setup() {
            SharedDB.start("test");
            //db = new DataAccessStub();
            db =SharedDB.getSharedDB();
            ops = new MealDiaryOps(db);
            currDate = (Calendar) ops.getListDate().clone();
        }

        @Test
        @DisplayName("s")
        void someTest(){
        }
    }


}
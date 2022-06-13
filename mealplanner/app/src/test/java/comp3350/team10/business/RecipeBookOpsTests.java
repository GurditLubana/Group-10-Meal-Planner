package comp3350.team10.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import comp3350.team10.business.MealDiaryOps;
import comp3350.team10.business.RecipeBookOps;
import comp3350.team10.persistence.*;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RecipeBookOpsTests {

    @Nested
    @DisplayName("Tests that should pass")
    class dbInstanceSharing{
        DataAccessStub db;
        MealDiaryOps mealDiaryOps;
        RecipeBookOps recipeBookOps;
        Calendar currDate;

        @BeforeEach
        void setup() {
            SharedDB.start("test");
            //db = new DataAccessStub();
            db = SharedDB.getSharedDB();
            mealDiaryOps = new MealDiaryOps(db);
            recipeBookOps = new RecipeBookOps(db);
            currDate = (Calendar) mealDiaryOps.getListDate().clone();
        }

        @Test
        @DisplayName("Item added in recipebook ops shows up in mealdiary")
        void test1(){
            
        }
    }

    @Nested
    @DisplayName("Tests that should fail")
    class testShouldfail{
        DataAccessStub db;
        MealDiaryOps ops;
        Calendar currDate;

        @BeforeEach
        void setup() {
            SharedDB.start("test");
            db =SharedDB.getSharedDB();
            ops = new MealDiaryOps(db);
            currDate = (Calendar) ops.getListDate().clone();
        }

        @Test
        @DisplayName("Dates that more than 2 years older than current date")
        void someTest(){
            Calendar badDate = Calendar.getInstance();
            badDate.set(Calendar.YEAR, badDate.get(Calendar.YEAR) -3);
            ops.setListDate(badDate);
        }
    }
}
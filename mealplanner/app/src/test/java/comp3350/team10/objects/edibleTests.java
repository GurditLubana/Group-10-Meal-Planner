package comp3350.team10.objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import comp3350.team10.business.MealDiaryOps;
import comp3350.team10.business.RecipeBookOps;
import comp3350.team10.persistence.DataAccessStub;
import comp3350.team10.persistence.SharedDB;

public class edibleTests {

    @Nested
    @DisplayName("Database is shared between recipebook and mealdiary")
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
}

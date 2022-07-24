package comp3350.team10.persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.Calendar;

public class TestRecipeDBInterfaceHsql extends TestRecipeDBInterface {

    @Override
    @BeforeEach
    void setup() {
        SharedDB.start();
        SharedDB.startHsql();
        super.db = SharedDB.getRecipeDB();
        super.foodRecipeCount = db.getFoodRecipes().size();
        super.mealRecipeCount = db.getMealRecipes().size();
        super.drinkRecipeCount = db.getDrinkRecipes().size();
    }

    @Override
    @AfterEach
    void shutdown() {
        SharedDB.close();
    }
}

package comp3350.team10.tests.persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import comp3350.team10.persistence.DBSelector;
import comp3350.team10.persistence.HSqlDB;

public class TestRecipeDBInterfaceHsql extends TestRecipeDBInterface {

    @Override
    @BeforeEach
    void setup() {
        DBSelector.start(new HSqlDB());

        super.db = DBSelector.getRecipeDB();
        super.foodDB = db.getFoodRecipes();
        super.mealDB = db.getMealRecipes();
        super.drinkDB = db.getDrinkRecipes();
        super.foodRecipeCount = super.foodDB.size();
        super.mealRecipeCount = super.mealDB.size();
        super.drinkRecipeCount = super.drinkDB.size();
    }

    @Override
    @AfterEach
    void shutdown() {
        DBSelector.close();
    }
}

package comp3350.team10.tests.persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import comp3350.team10.persistence.DBSelector;
import comp3350.team10.persistence.HSqlDB;
import comp3350.team10.tests.persistence.DataAccessStub;

public class TestRecipeDBInterfaceHsql extends TestRecipeDBInterface {

    @Override
    @BeforeEach
    void setup() {
        DBSelector.start(new HSqlDB());;
        super.db = DBSelector.getRecipeDB();
        super.foodRecipeCount = db.getFoodRecipes().size();
        super.mealRecipeCount = db.getMealRecipes().size();
        super.drinkRecipeCount = db.getDrinkRecipes().size();
    }

    @Override
    @AfterEach
    void shutdown() {
        DBSelector.close();
    }
}

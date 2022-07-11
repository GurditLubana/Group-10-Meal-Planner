package comp3350.team10;

import comp3350.team10.business.TestMealDiaryOps;
import comp3350.team10.business.TestRecipeBookOps;
import comp3350.team10.business.TestTrendsOps;
import comp3350.team10.business.TestUserDataOps;
import comp3350.team10.objects.TestDailyLog;
import comp3350.team10.objects.TestDataFrame;
import comp3350.team10.objects.TestDrink;
import comp3350.team10.objects.TestDrinkIngredients;
import comp3350.team10.objects.TestEdible;
import comp3350.team10.objects.TestEdibleLog;
import comp3350.team10.objects.TestIngredient;
import comp3350.team10.objects.TestMeal;
import comp3350.team10.objects.TestUnitConverter;
import comp3350.team10.objects.TestUser;
import comp3350.team10.persistence.TestLogDBInterface;
import comp3350.team10.persistence.TestRecipeDBInterface;
import comp3350.team10.persistence.TestUserDBInterface;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("JUnit5 mealplanner test suite")
@SelectPackages("comp3350.team10.*")
@SelectClasses({
        TestMealDiaryOps.class,
        TestRecipeBookOps.class,
        TestTrendsOps.class,
        TestUserDataOps.class,
        TestUnitConverter.class,
        TestDailyLog.class,
        TestEdibleLog.class,
        TestDataFrame.class,
        TestDrink.class,
        TestEdible.class,
        TestIngredient.class,
        TestDrinkIngredients.class,
        TestMeal.class,
        TestUser.class,
        TestLogDBInterface.class,
        TestUserDBInterface.class,
        TestRecipeDBInterface.class
})

public class AllTests {

}

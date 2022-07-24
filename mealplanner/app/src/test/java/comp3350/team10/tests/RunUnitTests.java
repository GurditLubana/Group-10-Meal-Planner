package comp3350.team10.tests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

import comp3350.team10.tests.business.TestMealDiaryOps;
import comp3350.team10.tests.business.TestRecipeBookOps;
import comp3350.team10.tests.business.TestTrendsOps;
import comp3350.team10.tests.business.TestUserDataOps;
import comp3350.team10.tests.objects.TestDailyLog;
import comp3350.team10.tests.objects.TestDataFrame;
import comp3350.team10.tests.objects.TestDrink;
import comp3350.team10.tests.objects.TestDrinkIngredients;
import comp3350.team10.tests.objects.TestEdible;
import comp3350.team10.tests.objects.TestEdibleLog;
import comp3350.team10.tests.objects.TestIngredient;
import comp3350.team10.tests.objects.TestMeal;
import comp3350.team10.tests.objects.TestUnitConverter;
import comp3350.team10.tests.objects.TestUser;
import comp3350.team10.tests.persistence.TestLogDBInterface;
import comp3350.team10.tests.persistence.TestRecipeDBInterface;
import comp3350.team10.tests.persistence.TestUserDBInterface;

@Suite
@SuiteDisplayName("JUnit5 mealplanner test suite")
@SelectPackages("comp3350.team10.tests.*")
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

public class RunUnitTests {

}

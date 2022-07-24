package comp3350.team10.tests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;


@Suite
@SuiteDisplayName("JUnit5 mealplanner test suite")
@SelectPackages("comp3350.team10.*")
@SelectClasses({
//        TestMealDiaryOps.class,
//        TestRecipeBookOps.class,
//        TestTrendsOps.class,
//        TestUserDataOps.class,
//        TestUnitConverter.class,
//        TestDailyLog.class,
//        TestEdibleLog.class,
//        TestRecipeDBInterface.class
})

public class RunIntegrationTests {

}

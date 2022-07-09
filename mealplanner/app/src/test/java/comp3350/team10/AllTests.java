 package comp3350.team10;

 import static org.junit.jupiter.api.Assertions.*;
 import org.junit.jupiter.api.DisplayName;
 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.Nested;
 import org.junit.jupiter.api.Test;

 import comp3350.team10.business.*;
 import comp3350.team10.objects.*;
 import comp3350.team10.persistence.TestLogDBInterface;
 import comp3350.team10.persistence.TestRecipeDBInterface;
 import comp3350.team10.persistence.TestUserDBInterface;

 import org.junit.jupiter.api.extension.ExtendWith;
 import org.junit.platform.suite.api.SelectClasses;
 import org.junit.platform.suite.api.SelectPackages;
 import org.junit.platform.suite.api.Suite;
 import org.junit.platform.suite.api.SuiteDisplayName;

 @Suite
 @SuiteDisplayName("JUnit5 mealplanner test suite")
 @SelectPackages("comp3350.team10.*")
 @SelectClasses({
         TestMealDiaryOps.class,
         //TestRecipeBookOps.class,
         TestTrendsOps.class,
         TestUnitConverter.class,
         TestDailyLog.class,
         TestDataFrame.class,
         TestDrink.class,
         //TestFoodUnit.class,
         //TestEdibleUnit.class,
         TestIngredient.class,
         //TestMealUnit.class,
         TestLogDBInterface.class,
         TestUserDBInterface.class,
         TestRecipeDBInterface.class
 })
 public class AllTests {

 }


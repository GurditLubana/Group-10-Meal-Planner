 package comp3350.team10;

 import static org.junit.jupiter.api.Assertions.*;
 import org.junit.jupiter.api.DisplayName;
 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.Nested;
 import org.junit.jupiter.api.Test;

 import comp3350.team10.business.*;
 import comp3350.team10.objects.*;

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
         TestDrinkUnit.class,
         TestFoodUnit.class,
         TestEdibleUnit.class,
         TestIngredientUnit.class,
         //TestMealUnit.class
 })
 public class AllTests {

 }


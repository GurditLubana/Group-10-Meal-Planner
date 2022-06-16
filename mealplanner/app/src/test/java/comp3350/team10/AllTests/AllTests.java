package comp3350.team10.business;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import comp3350.team10.business.*;
import comp3350.team10.objects.*;

public class AllTests {
    public static TestSuite suite;

    public static Test suite() {
        System.out.println("Launching all tests...");
        suite = new TestSuite();
        testObjects();
        testBuisnessLogic();

        return suite;
    }

    private static void testObjects() {
        suite.addTestSuite(DailyLogUnitTest.class);
        suite.addTestSuite(DrinkUnitTest.class);
        suite.addTestSuite(FoodUnitTest.class);
        suite.addTestSuite(MealUnitTest.class);
    }

    private static void testBuisnessLogic() {
        suite.addTestSuite(MealDiaryOpsTests.class);
        suite.addTestSuite(RecipeBookOpsTests.class);
        suite.addTestSuite(UnitConverterTests.class);
    }
}

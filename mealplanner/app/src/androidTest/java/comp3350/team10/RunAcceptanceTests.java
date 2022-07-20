package comp3350.team10;


import org.junit.runners.Suite;
import org.junit.runner.RunWith;

import comp3350.team10.acceptance.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestStoryAddMeals.class,
        TestStoryViewLongTrends.class,
        TestStoryViewRecipes.class,
        TestStoryViewWeekTrends.class
})

public class RunAcceptanceTests {

}

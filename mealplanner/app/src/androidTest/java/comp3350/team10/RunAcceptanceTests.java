package comp3350.team10;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.team10.acceptance.TestStoryAddMeals;
import comp3350.team10.acceptance.TestStoryViewLongTrends;
import comp3350.team10.acceptance.TestStoryViewRecipes;
import comp3350.team10.acceptance.TestStoryViewWeekTrends;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestStoryAddMeals.class,
        TestStoryViewLongTrends.class,
        TestStoryViewRecipes.class,
        TestStoryViewWeekTrends.class
})

public class RunAcceptanceTests {

}

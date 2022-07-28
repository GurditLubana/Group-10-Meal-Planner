package comp3350.team10;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.team10.acceptance.TestStoryLogEditPlanMeals;
import comp3350.team10.acceptance.TestStoryProgressMoreCharts;
import comp3350.team10.acceptance.TestStoryAddRecipes;
import comp3350.team10.acceptance.TestStoryProgressTracking;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestStoryLogEditPlanMeals.class,
        TestStoryProgressMoreCharts.class,
        TestStoryAddRecipes.class,
        TestStoryProgressTracking.class
})

public class RunAcceptanceTests {

}

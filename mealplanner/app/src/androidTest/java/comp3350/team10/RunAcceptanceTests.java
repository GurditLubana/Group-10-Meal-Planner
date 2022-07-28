package comp3350.team10;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.team10.acceptance.TestStoryLogEditPlanMeals;
import comp3350.team10.acceptance.TestStoryProgressMoreCharts;
import comp3350.team10.acceptance.TestStoryAddRecipes;
import comp3350.team10.acceptance.TestStoryProgressTracking;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestStoryAddRecipes.class,
        TestStoryLogEditPlanMeals.class,
        TestStoryProgressMoreCharts.class,
        TestStoryProgressTracking.class
})

public class RunAcceptanceTests {

}

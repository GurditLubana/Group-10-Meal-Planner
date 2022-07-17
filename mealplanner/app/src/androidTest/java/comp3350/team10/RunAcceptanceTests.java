package comp3350.team10;


import org.junit.runners.Suite;
import org.junit.runner.RunWith;

import comp3350.team10.acceptance.TestStoryAddMeals;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestStoryAddMeals.class})

public class RunAcceptanceTests {

}

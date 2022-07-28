package comp3350.team10.tests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

import comp3350.team10.tests.integration.TestBusinessPersistenceHsql;
import comp3350.team10.tests.integration.TestBusinessPersistenceSeam;
import comp3350.team10.tests.persistence.TestLogDBInterfaceHsql;
import comp3350.team10.tests.persistence.TestRecipeDBInterfaceHsql;
import comp3350.team10.tests.persistence.TestUserDBInterfaceHsql;


@Suite
@SuiteDisplayName("JUnit5 mealplanner test suite")
@SelectPackages("comp3350.team10.tests.*")
@SelectClasses({
        TestLogDBInterfaceHsql.class,
        TestRecipeDBInterfaceHsql.class,
        TestUserDBInterfaceHsql.class,
        TestBusinessPersistenceHsql.class,
        TestBusinessPersistenceSeam.class,
})

public class RunIntegrationTests {

}

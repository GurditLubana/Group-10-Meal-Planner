package comp3350.team10.tests.persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import comp3350.team10.persistence.DBSelector;
import comp3350.team10.persistence.HSqlDB;

public class TestUserDBInterfaceHsql extends TestUserDBInterface {

    @Override
    @BeforeEach
    void setup() {
        DBSelector.start(new HSqlDB());

        super.db = DBSelector.getUserDB();
    }

    @Override
    @AfterEach
    void shutdown() {
        DBSelector.close();
    }
}

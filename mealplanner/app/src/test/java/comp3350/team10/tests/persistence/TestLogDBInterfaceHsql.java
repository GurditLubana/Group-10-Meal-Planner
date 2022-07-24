package comp3350.team10.tests.persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.Calendar;

import comp3350.team10.persistence.DBSelector;
import comp3350.team10.persistence.HSqlDB;
import comp3350.team10.tests.persistence.DataAccessStub;

public class TestLogDBInterfaceHsql extends TestLogDBInterface {

    @Override
    @BeforeEach
    void setup() {
        DBSelector.start(new HSqlDB());;
        super.db = DBSelector.getLogDB();
        super.currDate = Calendar.getInstance();
        super.currDate.set(Calendar.MONTH, 9);
        super.currDate.set(Calendar.DAY_OF_MONTH, 10);
        super.testDate = (Calendar) this.currDate.clone();
        super.testDate.add(Calendar.DAY_OF_YEAR, 20);
    }

    @Override
    @AfterEach
    void shutdown() {
        DBSelector.close();
    }
}

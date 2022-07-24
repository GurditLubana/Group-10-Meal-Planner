package comp3350.team10.persistence;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Calendar;

import comp3350.team10.objects.DailyLog;

public class TestLogDBInterfaceHsql extends TestLogDBInterface {

    @Override
    @BeforeEach
    void setup() {
        SharedDB.start();
        SharedDB.startHsql();
        super.db = SharedDB.getLogDB();
        super.currDate = Calendar.getInstance();
        super.currDate.set(Calendar.MONTH, 9);
        super.currDate.set(Calendar.DAY_OF_MONTH, 10);
        super.testDate = (Calendar) this.currDate.clone();
        super.testDate.add(Calendar.DAY_OF_YEAR, 20);
    }

    @Override
    @AfterEach
    void shutdown() {
        SharedDB.close();
    }
}

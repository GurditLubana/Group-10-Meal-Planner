package comp3350.team10.persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.Calendar;

public class TestUserDBInterfaceHsql extends TestUserDBInterface {

    @Override
    @BeforeEach
    void setup() {
        SharedDB.start();
        SharedDB.startHsql();
        super.db = SharedDB.getUserDB();
    }

    @Override
    @AfterEach
    void shutdown() {
        SharedDB.close();
    }
}

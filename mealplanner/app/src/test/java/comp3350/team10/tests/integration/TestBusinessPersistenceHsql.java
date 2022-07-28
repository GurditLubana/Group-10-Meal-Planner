package comp3350.team10.tests.integration;

import org.junit.jupiter.api.BeforeEach;

import comp3350.team10.persistence.DBSelector;
import comp3350.team10.persistence.HSqlDB;

public class TestBusinessPersistenceHsql extends TestBusinessPersistenceSeam {

    @BeforeEach
    void setup() {

        try {
            DBSelector.start(new HSqlDB());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

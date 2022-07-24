package comp3350.team10.tests.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.util.Calendar;

import comp3350.team10.business.MealDiaryOps;
import comp3350.team10.persistence.DBSelector;
import comp3350.team10.tests.persistence.DataAccessStub;

public class TestUserOpsDBInterface {

    @Nested
    @DisplayName("Simple Tests")
    class mealDiarySimple {
        private MealDiaryOps ops;
        private Calendar currDate;
        private Calendar testDate;

        @BeforeEach
        void setup() {
            DBSelector.start(new DataAccessStub());;
            
            //DBSelector.startHsql();
            this.ops = new MealDiaryOps();
            this.currDate = Calendar.getInstance();
            this.currDate.set(Calendar.MONTH, 9);
            this.currDate.set(Calendar.DAY_OF_MONTH, 10);
            this.testDate = (Calendar) this.currDate.clone();
            this.testDate.add(Calendar.DAY_OF_YEAR, 20);
        }

        @AfterEach
        void shutdown() {
            DBSelector.close();
        }

    }


    @Nested
    @DisplayName("Complex Tests should pass")
    class mealDiaryComplex {
        private MealDiaryOps ops;
        private Calendar currDate;
        private Calendar testDate;

        @BeforeEach
        void setup() {
            DBSelector.start(new DataAccessStub());;
            
            ops = new MealDiaryOps();
            currDate = Calendar.getInstance();
            testDate = Calendar.getInstance();
        }

        @AfterEach
        void shutdown() {
            DBSelector.close();
        }


    }


    @Nested
    @DisplayName("Edge case Tests should pass")
    class mealDiaryEdge {
        private MealDiaryOps ops;
        private Calendar currDate;
        private Calendar testDate;

        @BeforeEach
        void setup() {
            DBSelector.start(new DataAccessStub());;
            
            ops = new MealDiaryOps();
            currDate = Calendar.getInstance();
            testDate = Calendar.getInstance();
        }

        @AfterEach
        void shutdown() {
            DBSelector.close();
        }


    }


    @Nested
    @DisplayName("Tests that should fail")
    class mealDiaryFail {
        private MealDiaryOps ops;
        private Calendar currDate;
        private Calendar testDate;

        @BeforeEach
        void setup() {
            DBSelector.start(new DataAccessStub());;
            
            ops = new MealDiaryOps();
            currDate = Calendar.getInstance();
            testDate = Calendar.getInstance();
        }

        @AfterEach
        void shutdown() {
            DBSelector.close();
        }


    }
}

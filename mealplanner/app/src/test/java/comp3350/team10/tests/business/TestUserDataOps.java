package comp3350.team10.tests.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import comp3350.team10.business.UserDataOps;
import comp3350.team10.objects.Constant;
import comp3350.team10.objects.User;
import comp3350.team10.persistence.DBSelector;
import comp3350.team10.persistence.UserDBInterface;
import comp3350.team10.tests.persistence.DataAccessStub;

public class TestUserDataOps {
    private UserDataOps userOps;
    UserDBInterface db;
    User userFromDB;
    User userFromOPS;

    @BeforeEach
    void setup() {
        DBSelector.start(new DataAccessStub());;
        db = DBSelector.getUserDB();
        userFromDB = db.getUser(1);
        userOps = new UserDataOps();
        userFromOPS = userOps.getUser(1);
    }

    @AfterEach
    void shutdown() {
        restoreDefault();
        DBSelector.close();
    }

    void restoreDefault(){

    }

    @Nested
    @DisplayName("Typical test cases that should pass")
    class Test_Simple {

        @Nested
        @DisplayName("Simple Tests")
        class UserDataSimple {

            @Test
            public void testUserGetter() {
                assertEquals(100, userFromOPS.getHeight());
                assertEquals(200, userFromOPS.getWeight());
                assertEquals(2000, userFromOPS.getCalorieGoal());
                assertEquals(600, userFromOPS.getExerciseGoal());
                assertEquals("Test", userFromOPS.getName());
                assertEquals(1, userFromOPS.getUserID());
            }
        }

        }

        @Nested
        @DisplayName("Edge case tests")
        class EdgeTestCases {
            private String largeTestString;

            @Test
            @DisplayName("Tests setting an edge case for user's ID")
            void testSetID() {
            }


        }

        @Nested
        @DisplayName("Complex test cases")
        class ComplexTestCases {

            @Test
            @DisplayName("Complex Test cases for user's ID")
            void testSetID() {
            }

        }

        @Nested
        @DisplayName("Empty tests")
        class EmptyTests {


            @Test
            @DisplayName("Tests setting an empty or null name")
            void testName() {
            }
        }


    @Nested
    @DisplayName("Invalid tests")
    class InvalidTestCases {
        private UserDataOps userOps;
        private String longTestString;
        UserDBInterface db;
        User user;

        @BeforeEach
        void setup() {
            DBSelector.start(new DataAccessStub());;
            db = DBSelector.getUserDB();
            user = db.getUser(1);
            longTestString = "";
            userOps = new UserDataOps();

            for (int i = 0; i < 10000; i++) {
                longTestString = longTestString + "a";
            }
        }

        @AfterEach
        void shutdown() {
            DBSelector.close();
        }
    }
}

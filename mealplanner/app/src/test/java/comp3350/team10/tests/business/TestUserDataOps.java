package comp3350.team10.tests.business;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import comp3350.team10.business.UserDataOps;
import comp3350.team10.objects.User;
import comp3350.team10.persistence.DBSelector;
import comp3350.team10.tests.persistence.DataAccessStub;

public class TestUserDataOps {
    private UserDataOps userOps;
    User userFromOPS;

    @BeforeEach
    void setup() {
        DBSelector.start(new DataAccessStub());;
        userOps = new UserDataOps();
        userFromOPS = userOps.getUser(1);
    }

    @AfterEach
    void shutdown() {
        this.restoreDefault();
        DBSelector.close();
    }

    private void restoreDefault() {
        userFromOPS.setName("Test");
        userFromOPS.setHeight(100);
        userFromOPS.setWeight(200);
        userFromOPS.setCalorieGoal(2000);
        userFromOPS.setExerciseGoal(600);
        userOps.updateUser();
    }

    @Nested
    @DisplayName("Testing simple cases")
    class Test_Simple {

        @Test
        @DisplayName("Testing default values")
        public void testUserGetter() {
            assertEquals(100, userFromOPS.getHeight());
            assertEquals(200, userFromOPS.getWeight());
            assertEquals(2000, userFromOPS.getCalorieGoal());
            assertEquals(600, userFromOPS.getExerciseGoal());
            assertEquals("Test", userFromOPS.getName());
            assertEquals(1, userFromOPS.getUserID());
            assertEquals(1, userOps.getUserID());
        }

        @Test
        @DisplayName("Tests setting a users name")
        void setName() {
            userFromOPS.setName("testString");
            assertEquals(userFromOPS.getName(), "testString");

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getName(), "testString");

            userFromOPS.setName("A different name");
            assertEquals(userFromOPS.getName(), "A different name");

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getName(), "A different name");
        }

        @Test
        @DisplayName("Tests setting a users height")
        void setHeight() {
            userFromOPS.setHeight(5);
            assertEquals(userFromOPS.getHeight(), 5);

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getHeight(), 5);

            userFromOPS.setHeight(10);
            assertEquals(userFromOPS.getHeight(), 10);

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getHeight(), 10);
        }

        @Test
        @DisplayName("Tests setting a users weight")
        void setWeight() {
            userFromOPS.setWeight(5);
            assertEquals(userFromOPS.getWeight(), 5);

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getWeight(), 5);

            userFromOPS.setWeight(10);
            assertEquals(userFromOPS.getWeight(), 10);

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getWeight(), 10);
        }

        @Test
        @DisplayName("Tests setting a users calorie goal")
        void setCalorieGoal() {
            userFromOPS.setCalorieGoal(5);
            assertEquals(userFromOPS.getCalorieGoal(), 5);

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getCalorieGoal(), 5);

            userFromOPS.setCalorieGoal(10);
            assertEquals(userFromOPS.getCalorieGoal(), 10);

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getCalorieGoal(), 10);
        }

        @Test
        @DisplayName("Tests setting a users exercise goal")
        void setExerciseGoal() {
            userFromOPS.setExerciseGoal(5);
            assertEquals(userFromOPS.getExerciseGoal(), 5);

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getExerciseGoal(), 5);

            userFromOPS.setExerciseGoal(10);
            assertEquals(userFromOPS.getExerciseGoal(), 10);

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getExerciseGoal(), 10);
        }
    }


    @Nested
    @DisplayName("Complex test cases")
    class ComplexTestCases {

        @Test
        @DisplayName("Complex Test cases for userFromOPS's name")
        void testSetName() {
            userFromOPS.setName("\t\t\t\t\tRomeo");
            assertEquals("\t\t\t\t\tRomeo", userFromOPS.getName());

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getName(), "\t\t\t\t\tRomeo");

            userFromOPS.setName("\n\n\n\n Gurdit");
            assertEquals("\n\n\n\n Gurdit", userFromOPS.getName());

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getName(), "\n\n\n\n Gurdit");

            userFromOPS.setName("!!!!@#$$$%%%%%^");
            assertEquals("!!!!@#$$$%%%%%^", userFromOPS.getName());

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getName(), "!!!!@#$$$%%%%%^");

            userFromOPS.setName("12344");
            assertEquals(userFromOPS.getName(), "12344");

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getName(), "12344");

            userFromOPS.setName("               .         ");
            assertEquals(userFromOPS.getName(), "               .         ");

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getName(), "               .         ");

            userFromOPS.setName("Dane         ");
            assertEquals(userFromOPS.getName(), "Dane         ");

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getName(), "Dane         ");

            userFromOPS.setName("null");
            assertEquals(userFromOPS.getName(), "null");

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getName(), "null");

            userFromOPS.setName(",,,,,,    ");
            assertEquals(userFromOPS.getName(), ",,,,,,    ");

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getName(), ",,,,,,    ");

            userFromOPS.setName("\\\\\\");
            assertEquals(userFromOPS.getName(), "\\\\\\");

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getName(), "\\\\\\");
        }

        @Test
        @DisplayName("Tests setting a complex userFromOPS's height")
        void testSetHeight() {
            userFromOPS.setHeight(500);
            assertEquals(userFromOPS.getHeight(), 500);

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getHeight(), 500);

            userFromOPS.setHeight(1000);
            assertEquals(userFromOPS.getHeight(), 1000);

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getHeight(), 1000);
        }

        @Test
        @DisplayName("Tests setting a complex userFromOPS's weight")
        void testSetWeight() {
            userFromOPS.setWeight(500);
            assertEquals(userFromOPS.getWeight(), 500);

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getWeight(), 500);

            userFromOPS.setWeight(1000);
            assertEquals(userFromOPS.getWeight(), 1000);

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getWeight(), 1000);
        }

        @Test
        @DisplayName("Tests setting a complex userFromOPS's calorie goal")
        void testSetCalorieGoal() {
            userFromOPS.setCalorieGoal(500);
            assertEquals(userFromOPS.getCalorieGoal(), 500);

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getCalorieGoal(), 500);

            userFromOPS.setCalorieGoal(1000);
            assertEquals(userFromOPS.getCalorieGoal(), 1000);

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getCalorieGoal(), 1000);
        }

        @Test
        @DisplayName("Tests setting a complex userFromOPS's exercise goal")
        void testSetExerciseGoal() {
            userFromOPS.setExerciseGoal(500);
            assertEquals(userFromOPS.getExerciseGoal(), 500);

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getExerciseGoal(), 500);

            userFromOPS.setExerciseGoal(1000);
            assertEquals(userFromOPS.getExerciseGoal(), 1000);

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getExerciseGoal(), 1000);
        }
    }

    @Nested
    @DisplayName("Edge case tests")
    class EdgeTestCases {
        private StringBuilder longTestString;

        @BeforeEach
        void setup() {
            userFromOPS = new User();
            userFromOPS = userOps.getUser(1);

            longTestString = new StringBuilder();
            longTestString.setLength(9999);
            longTestString.setCharAt(0, 'a');
            longTestString.setCharAt(9998, 'y');
        }

        @Test
        @DisplayName("Tests setting an edge case for userFromOPS's name")
        void testSetName() {
            userFromOPS.setName(longTestString.toString());
            assertEquals(userFromOPS.getName(), longTestString.toString());

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getName(), longTestString.toString());
        }

        @Test
        @DisplayName("Tests setting the same name a userFromOPS already has")
        void testSetDuplicateName() {
            userFromOPS.setName(longTestString.toString());
            userFromOPS.setName(longTestString.toString());
            assertEquals(userFromOPS.getName(), longTestString.toString());

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getName(), longTestString.toString());
        }

        @Test
        @DisplayName("Tests setting an edge case for userFromOPS's height")
        void testSetHeight() {
            userFromOPS.setHeight(1);
            assertEquals(userFromOPS.getHeight(), 1);

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getHeight(), 1);

            userFromOPS.setHeight(9999);
            assertEquals(userFromOPS.getHeight(), 9999);

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getHeight(), 9999);
        }

        @Test
        @DisplayName("Tests setting the same height a userFromOPS already has")
        void testSetDuplicateHeight() {
            userFromOPS.setHeight(5);
            userFromOPS.setHeight(5);
            assertEquals(userFromOPS.getHeight(), 5);

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getHeight(), 5);
        }

        @Test
        @DisplayName("Tests setting an edge case for userFromOPS's weight")
        void testSetWeight() {
            userFromOPS.setWeight(1);
            assertEquals(userFromOPS.getWeight(), 1);

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getWeight(), 1);

            userFromOPS.setWeight(9999);
            assertEquals(userFromOPS.getWeight(), 9999);

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getWeight(), 9999);
        }

        @Test
        @DisplayName("Tests setting the same weight a userFromOPS already has")
        void testSetDuplicateWeight() {
            userFromOPS.setWeight(5);
            userFromOPS.setWeight(5);
            assertEquals(userFromOPS.getWeight(), 5);

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getWeight(), 5);
        }

        @Test
        @DisplayName("Tests setting an edge case for userFromOPS's calorie goal")
        void testSetCalorieGoal() {
            userFromOPS.setCalorieGoal(0);
            assertEquals(userFromOPS.getCalorieGoal(), 0);

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getCalorieGoal(), 0);

            userFromOPS.setCalorieGoal(9999);
            assertEquals(userFromOPS.getCalorieGoal(), 9999);

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getCalorieGoal(), 9999);
        }

        @Test
        @DisplayName("Tests setting the same calorie goal a userFromOPS already has")
        void testSetDuplicateCalorieGoal() {
            userFromOPS.setCalorieGoal(5);
            userFromOPS.setCalorieGoal(5);
            assertEquals(userFromOPS.getCalorieGoal(), 5);

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getCalorieGoal(), 5);
        }

        @Test
        @DisplayName("Tests setting an edge case for userFromOPS's exercise goal")
        void testSetExerciseGoal() {
            userFromOPS.setExerciseGoal(0);
            assertEquals(userFromOPS.getExerciseGoal(), 0);

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getExerciseGoal(), 0);

            userFromOPS.setExerciseGoal(9999);
            assertEquals(userFromOPS.getExerciseGoal(), 9999);

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getExerciseGoal(), 9999);
        }

        @Test
        @DisplayName("Tests setting the same calorie goal a userFromOPS already has")
        void testSetDuplicateExerciseGoal() {
            userFromOPS.setExerciseGoal(5);
            userFromOPS.setExerciseGoal(5);
            assertEquals(userFromOPS.getExerciseGoal(), 5);

            userOps.updateUser();
            userFromOPS = userOps.getUser(1);
            assertEquals(userFromOPS.getExerciseGoal(), 5);
        }
    }
}
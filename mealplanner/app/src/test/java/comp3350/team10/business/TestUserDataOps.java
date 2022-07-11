package comp3350.team10.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import comp3350.team10.objects.Constant;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.User;
import comp3350.team10.persistence.SharedDB;

public class TestUserDataOps {

    @Nested
    @DisplayName("Typical test cases that should pass")
    class Test_Simple {
        private UserDataOps userOps;

        @BeforeEach
        void setup() {
            SharedDB.start();
            SharedDB.startStub();
            //SharedDB.startHsql();
            userOps = new UserDataOps();
        }

        @AfterEach
        void shutdown() {
            SharedDB.close();
        }

        @Test
        @DisplayName("object state at construction")
        void testDefaultValues() {
            assertEquals(userOps.getUser().getHeight(), SharedDB.getUserDB().getUser().getHeight());
            assertEquals(userOps.getUser().getWeight(), SharedDB.getUserDB().getUser().getWeight());
            assertEquals(userOps.getUser().getCalorieGoal(), SharedDB.getUserDB().getUser().getCalorieGoal());
            assertEquals(userOps.getUser().getExerciseGoal(), SharedDB.getUserDB().getUser().getCalorieGoal());
            assertEquals(userOps.getUser().getName(), SharedDB.getUserDB().getUser().getName());
            assertEquals(userOps.getUser().getUserID(), SharedDB.getUserDB().getUser().getUserID());
        }

        @Nested
        @DisplayName("Simple Tests")
        class UserDataSimple {

            @Test
            public void testUserGetter() {
                assertEquals(userOps.getUser(), SharedDB.getUserDB().getUser());
            }

            @Test
            public void testHeightUpdater() {
                userOps.updateHeight(200);
                assertEquals(200, userOps.getUser().getHeight());
            }

            @Test
            public void testWeightUpdater() {
                userOps.updateWeight(55);
                assertEquals(55, userOps.getUser().getWeight());
            }

            @Test
            public void testCalUpdater() {
                userOps.updateCalorieGoal(260.05);
                assertEquals(260.05, userOps.getUser().getCalorieGoal());
            }

            @Test
            public void testExeGoalUpdater() {
                userOps.updateExerciseGoal(500.99);
                assertEquals(500.99, userOps.getUser().getExerciseGoal());
            }
        }

        @Nested
        @DisplayName("Edge case tests set upperLimit")
        class UserDataOpsEdgeUpperLimit {

            @Test
            @DisplayName("we can set height to upper limit")
            void testHeightUpperLimit() {
                userOps.getUser().setHeight(Constant.ENTRY_MAX_VALUE);
                assertEquals(Constant.ENTRY_MAX_VALUE, userOps.getUser().getHeight());
                assertEquals(userOps.getUser().getWeight(), SharedDB.getUserDB().getUser().getWeight());
                assertEquals(userOps.getUser().getCalorieGoal(), SharedDB.getUserDB().getUser().getCalorieGoal());
                assertEquals(userOps.getUser().getExerciseGoal(), SharedDB.getUserDB().getUser().getExerciseGoal());
                assertEquals(userOps.getUser().getName(), SharedDB.getUserDB().getUser().getName());
                assertEquals(userOps.getUser().getUserID(), SharedDB.getUserDB().getUser().getUserID());
            }

            @Test
            @DisplayName("we can set CalorieGoal to upper limit")
            void testCalorieGoalUpperLimit() {
                userOps.getUser().setCalorieGoal(Constant.ENTRY_MAX_VALUE);
                assertEquals(Constant.ENTRY_MAX_VALUE, userOps.getUser().getCalorieGoal());
                assertEquals(userOps.getUser().getWeight(), SharedDB.getUserDB().getUser().getWeight());
                assertEquals(userOps.getUser().getHeight(), SharedDB.getUserDB().getUser().getHeight());
                assertEquals(userOps.getUser().getExerciseGoal(), SharedDB.getUserDB().getUser().getExerciseGoal());
                assertEquals(userOps.getUser().getName(), SharedDB.getUserDB().getUser().getName());
                assertEquals(userOps.getUser().getUserID(), SharedDB.getUserDB().getUser().getUserID());
            }

            @Test
            @DisplayName("we can set Weight to upper limit")
            void testWeightUpperLimit() {
                userOps.getUser().setWeight(Constant.ENTRY_MAX_VALUE);
                assertEquals(Constant.ENTRY_MAX_VALUE, userOps.getUser().getWeight());
                assertEquals(userOps.getUser().getHeight(), SharedDB.getUserDB().getUser().getHeight());
                assertEquals(userOps.getUser().getCalorieGoal(), SharedDB.getUserDB().getUser().getCalorieGoal());
                assertEquals(userOps.getUser().getExerciseGoal(), SharedDB.getUserDB().getUser().getExerciseGoal());
                assertEquals(userOps.getUser().getName(), SharedDB.getUserDB().getUser().getName());
                assertEquals(userOps.getUser().getUserID(), SharedDB.getUserDB().getUser().getUserID());
            }

            @Test
            @DisplayName("we can set Exercise Goal to upper limit")
            void testExeGoalUpperLimit() {
                userOps.getUser().setExerciseGoal(Constant.ENTRY_MAX_VALUE);
                assertEquals(Constant.ENTRY_MAX_VALUE, userOps.getUser().getExerciseGoal());
                assertEquals(userOps.getUser().getWeight(), SharedDB.getUserDB().getUser().getWeight());
                assertEquals(userOps.getUser().getCalorieGoal(), SharedDB.getUserDB().getUser().getCalorieGoal());
                assertEquals(userOps.getUser().getHeight(), SharedDB.getUserDB().getUser().getHeight());
                assertEquals(userOps.getUser().getName(), SharedDB.getUserDB().getUser().getName());
                assertEquals(userOps.getUser().getUserID(), SharedDB.getUserDB().getUser().getUserID());
            }
        }

        @Nested
        @DisplayName("Edge case tests")
        class EdgeTestCases {
            private String largeTestString;

            @BeforeEach
            void setup() {
                largeTestString = "";

                for (int i = 0; i < 9999; i++) {
                    largeTestString = largeTestString + "a";
                }
            }

            @Test
            @DisplayName("Tests setting an edge case for user's ID")
            void testSetID() {
                userOps.getUser().init(0, "name", 5, 5, 5, 5);
                assertEquals(0, userOps.getUser().getUserID());
            }

            @Test
            @DisplayName("Tests setting the same ID a user already has")
            void testSetDuplicateID() {
                userOps.getUser().init(10, "name", 5, 5, 5, 5);
                userOps.getUser().init(10, "name", 5, 5, 5, 5);
                assertEquals(10, userOps.getUser().getUserID());
            }

            @Test
            @DisplayName("Tests setting an edge case for user's name")
            void testSetName() {
                userOps.getUser().setName(largeTestString);
                assertEquals(userOps.getUser().getName(), largeTestString);

                userOps.getUser().init(5, largeTestString, 5, 5, 5, 5);
                assertEquals(userOps.getUser().getName(), largeTestString);
            }

            @Test
            @DisplayName("Tests setting the same name a user already has")
            void testSetDuplicateName() {
                userOps.getUser().setName(largeTestString);
                userOps.getUser().setName(largeTestString);
                assertEquals(userOps.getUser().getName(), largeTestString);

                userOps.getUser().init(5, largeTestString, 5, 5, 5, 5);
                userOps.getUser().init(5, largeTestString, 5, 5, 5, 5);
                assertEquals(userOps.getUser().getName(), largeTestString);
            }

            @Test
            @DisplayName("Tests setting an edge case for user's height")
            void testSetHeight() {
                userOps.getUser().setHeight(1);
                assertEquals(userOps.getUser().getHeight(), 1);

                userOps.getUser().setHeight(9999);
                assertEquals(userOps.getUser().getHeight(), 9999);

                userOps.getUser().init(5, "name", 1, 5, 5, 5);
                assertEquals(userOps.getUser().getHeight(), 1);

                userOps.getUser().init(5, "name", 9999, 5, 5, 5);
                assertEquals(userOps.getUser().getHeight(), 9999);
            }

            @Test
            @DisplayName("Tests setting the same height a user already has")
            void testSetDuplicateHeight() {
                userOps.getUser().setHeight(5);
                userOps.getUser().setHeight(5);
                assertEquals(userOps.getUser().getHeight(), 5);

                userOps.getUser().init(5, "name", 10, 5, 5, 5);
                userOps.getUser().init(5, "name", 10, 5, 5, 5);
                assertEquals(userOps.getUser().getHeight(), 10);
            }

            @Test
            @DisplayName("Tests setting an edge case for user's weight")
            void testSetWeight() {
                userOps.getUser().setWeight(1);
                assertEquals(userOps.getUser().getWeight(), 1);

                userOps.getUser().setWeight(9999);
                assertEquals(userOps.getUser().getWeight(), 9999);

                userOps.getUser().init(5, "name", 5, 1, 5, 5);
                assertEquals(userOps.getUser().getWeight(), 1);

                userOps.getUser().init(5, "name", 5, 9999, 5, 5);
                assertEquals(userOps.getUser().getWeight(), 9999);
            }

            @Test
            @DisplayName("Tests setting the same weight a user already has")
            void testSetDuplicateWeight() {
                userOps.getUser().setWeight(5);
                userOps.getUser().setWeight(5);
                assertEquals(userOps.getUser().getWeight(), 5);

                userOps.getUser().init(5, "name", 5, 10, 5, 5);
                userOps.getUser().init(5, "name", 5, 10, 5, 5);
                assertEquals(userOps.getUser().getWeight(), 10);
            }

            @Test
            @DisplayName("Tests setting an edge case for user's calorie goal")
            void testSetCalorieGoal() {
                userOps.getUser().setCalorieGoal(0);
                assertEquals(userOps.getUser().getCalorieGoal(), 0);

                userOps.getUser().setCalorieGoal(9999);
                assertEquals(userOps.getUser().getCalorieGoal(), 9999);

                userOps.getUser().init(5, "name", 5, 5, 0, 5);
                assertEquals(userOps.getUser().getCalorieGoal(), 0);

                userOps.getUser().init(5, "name", 5, 5, 9999, 5);
                assertEquals(userOps.getUser().getCalorieGoal(), 9999);
            }

            @Test
            @DisplayName("Tests setting the same calorie goal a user already has")
            void testSetDuplicateCalorieGoal() {
                userOps.getUser().setCalorieGoal(5);
                userOps.getUser().setCalorieGoal(5);
                assertEquals(userOps.getUser().getCalorieGoal(), 5);

                userOps.getUser().init(5, "name", 5, 5, 10, 5);
                userOps.getUser().init(5, "name", 5, 5, 10, 5);
                assertEquals(userOps.getUser().getCalorieGoal(), 10);
            }

            @Test
            @DisplayName("Tests setting an edge case for user's exercise goal")
            void testSetExerciseGoal() {
                userOps.getUser().setExerciseGoal(0);
                assertEquals(userOps.getUser().getExerciseGoal(), 0);

                userOps.getUser().setExerciseGoal(9999);
                assertEquals(userOps.getUser().getExerciseGoal(), 9999);

                userOps.getUser().init(5, "name", 5, 5, 5, 0);
                assertEquals(userOps.getUser().getExerciseGoal(), 0);

                userOps.getUser().init(5, "name", 5, 5, 5, 9999);
                assertEquals(userOps.getUser().getExerciseGoal(), 9999);
            }

            @Test
            @DisplayName("Tests setting the same calorie goal a user already has")
            void testSetDuplicateExerciseGoal() {
                userOps.getUser().setExerciseGoal(5);
                userOps.getUser().setExerciseGoal(5);
                assertEquals(userOps.getUser().getExerciseGoal(), 5);

                userOps.getUser().init(5, "name", 5, 5, 5, 10);
                userOps.getUser().init(5, "name", 5, 5, 5, 10);
                assertEquals(userOps.getUser().getExerciseGoal(), 10);
            }

        }

        @Nested
        @DisplayName("Complex test cases")
        class ComplexTestCases {

            @Test
            @DisplayName("Complex Test cases for user's ID")
            void testSetID() {
                userOps.getUser().init(500, "name", 5, 5, 5, 5);
                assertEquals(userOps.getUser().getUserID(), 500);

                userOps.getUser().init(1000, "name", 5, 5, 5, 5);
                assertEquals(userOps.getUser().getUserID(), 1000);
            }

            @Test
            @DisplayName("Complex Test cases for user's name")
            void testSetName() {
                userOps.getUser().setName("\t\t\t\t\tRomeo");
                assertEquals("\t\t\t\t\tRomeo", userOps.getUser().getName());

                userOps.getUser().setName("\n\n\n\n Gurdit");
                assertEquals("\n\n\n\n Gurdit", userOps.getUser().getName());

                userOps.getUser().setName("!!!!@#$$$%%%%%^");
                assertEquals("!!!!@#$$$%%%%%^", userOps.getUser().getName());

                userOps.getUser().setName("12344");
                assertEquals(userOps.getUser().getName(), "12344");

                userOps.getUser().setName("               .         ");
                assertEquals(userOps.getUser().getName(), "               .         ");

                userOps.getUser().setName("Dane         ");
                assertEquals(userOps.getUser().getName(), "Dane         ");

                userOps.getUser().setName("null");
                assertEquals(userOps.getUser().getName(), "null");

                userOps.getUser().setName(",,,,,,    ");
                assertEquals(userOps.getUser().getName(), ",,,,,,    ");

                userOps.getUser().setName("\\\\\\");
                assertEquals(userOps.getUser().getName(), "\\\\\\");

                userOps.getUser().init(5, "\t\t\t\t\tRomeo", 5, 5, 5, 5);
                assertEquals(userOps.getUser().getName(), "\t\t\t\t\tRomeo");

                userOps.getUser().init(5, "\n\n\n\n Gurdit", 5, 5, 5, 5);
                assertEquals(userOps.getUser().getName(), "\n\n\n\n Gurdit");

                userOps.getUser().init(5, "!!!!@#$$$%%%%%^", 5, 5, 5, 5);
                assertEquals(userOps.getUser().getName(), "!!!!@#$$$%%%%%^");

                userOps.getUser().init(5, "12344", 5, 5, 5, 5);
                assertEquals(userOps.getUser().getName(), "12344");

                userOps.getUser().init(5, "               .         ", 5, 5, 5, 5);
                assertEquals(userOps.getUser().getName(), "               .         ");

                userOps.getUser().init(5, "Dane         ", 5, 5, 5, 5);
                assertEquals(userOps.getUser().getName(), "Dane         ");

                userOps.getUser().init(5, "null", 5, 5, 5, 5);
                assertEquals(userOps.getUser().getName(), "null");

                userOps.getUser().init(5, ",,,,,,    ", 5, 5, 5, 5);
                assertEquals(userOps.getUser().getName(), ",,,,,,    ");

                userOps.getUser().init(5, "\\\\\\", 5, 5, 5, 5);
                assertEquals(userOps.getUser().getName(), "\\\\\\");
            }

            @Test
            @DisplayName("Tests setting a complex user's height")
            void testSetHeight() {
                userOps.getUser().setHeight(500);
                assertEquals(userOps.getUser().getHeight(), 500);

                userOps.getUser().setHeight(1000);
                assertEquals(userOps.getUser().getHeight(), 1000);

                userOps.getUser().init(5, "name", 500, 5, 5, 5);
                assertEquals(userOps.getUser().getHeight(), 500);

                userOps.getUser().init(5, "name", 1000, 5, 5, 5);
                assertEquals(userOps.getUser().getHeight(), 1000);
            }

            @Test
            @DisplayName("Tests setting a complex user's weight")
            void testSetWeight() {
                userOps.getUser().setWeight(500);
                assertEquals(userOps.getUser().getWeight(), 500);

                userOps.getUser().setWeight(1000);
                assertEquals(userOps.getUser().getWeight(), 1000);

                userOps.getUser().init(5, "name", 5, 500, 5, 5);
                assertEquals(userOps.getUser().getWeight(), 500);

                userOps.getUser().init(5, "name", 5, 1000, 5, 5);
                assertEquals(userOps.getUser().getWeight(), 1000);
            }

            @Test
            @DisplayName("Tests setting a complex user's calorie goal")
            void testSetCalorieGoal() {
                userOps.getUser().setCalorieGoal(500);
                assertEquals(userOps.getUser().getCalorieGoal(), 500);

                userOps.getUser().setCalorieGoal(1000);
                assertEquals(userOps.getUser().getCalorieGoal(), 1000);

                userOps.getUser().init(5, "name", 5, 5, 500, 5);
                assertEquals(userOps.getUser().getCalorieGoal(), 500);

                userOps.getUser().init(5, "name", 5, 5, 1000, 5);
                assertEquals(userOps.getUser().getCalorieGoal(), 1000);
            }

            @Test
            @DisplayName("Tests setting a complex user's exercise goal")
            void testSetExerciseGoal() {
                userOps.getUser().setExerciseGoal(500);
                assertEquals(userOps.getUser().getExerciseGoal(), 500);

                userOps.getUser().setExerciseGoal(1000);
                assertEquals(userOps.getUser().getExerciseGoal(), 1000);

                userOps.getUser().init(5, "name", 5, 5, 5, 500);
                assertEquals(userOps.getUser().getExerciseGoal(), 500);

                userOps.getUser().init(5, "name", 5, 5, 5, 1000);
                assertEquals(userOps.getUser().getExerciseGoal(), 1000);
            }
        }

        @Nested
        @DisplayName("Empty tests")
        class EmptyTests {


            @Test
            @DisplayName("Tests setting an empty or null name")
            void testName() {
                try {
                    userOps.getUser().setName(null);
                    fail("Name cannot be null, should throw IllegalArgumentException");
                }
                catch (Exception e) {
                    assertTrue(e instanceof IllegalArgumentException);
                }

                try {
                    userOps.getUser().setName("");
                    fail("Name cannot be empty, should throw IllegalArgumentException");
                }
                catch (Exception e) {
                    assertTrue(e instanceof IllegalArgumentException);
                }

                userOps.getUser().setName(" ");
                assertEquals(userOps.getUser().getName(), " ");

                try {
                    userOps.getUser().init(5, null, 5, 5, 5, 5);
                    fail("Name cannot be null, should throw IllegalArgumentException");
                }
                catch (Exception e) {
                    assertTrue(e instanceof IllegalArgumentException);
                }

                try {
                    userOps.getUser().init(5, "", 5, 5, 5, 5);
                    fail("Name cannot be empty, should throw IllegalArgumentException");
                }
                catch (Exception e) {
                    assertTrue(e instanceof IllegalArgumentException);
                }

                userOps.getUser().init(5, " ", 5, 5, 5, 5);
                assertEquals(userOps.getUser().getName(), " ");
            }
        }
    }

    @Nested
    @DisplayName("Invalid tests")
    class InvalidTestCases {
        private UserDataOps userOps;
        private String longTestString;

        @BeforeEach
        void setup() {

            SharedDB.start();
            SharedDB.startStub();
            //SharedDB.startHsql();
            longTestString = "";
            userOps = new UserDataOps();

            for (int i = 0; i < 10000; i++) {
                longTestString = longTestString + "a";
            }
        }

        @AfterEach
        void shutdown() {
            SharedDB.close();
        }

        @Test
        @DisplayName("Tests setting an invalid user ID")
        void testSetUserID() {
            try {
                userOps.getUser().init(-1, "name", 5, 5, 5, 5);
                fail("User ID should be >= 0. should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }

        @Test
        @DisplayName("Tests setting an invalid user's name")
        void testSetName() {
            try {
                userOps.getUser().setName(longTestString);
                fail("User name is too long. should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                userOps.getUser().init(5, longTestString, 5, 5, 5, 5);
                fail("User name is too long. should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

        }

        @Test
        @DisplayName("Tests setting an invalid user height")
        void testSetHeight() {
            try {
                userOps.getUser().setHeight(0);
                fail("Should throw an exception, this height is to small");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                userOps.getUser().setHeight(-1);
                fail("Should throw an exception, this height is invalid");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                userOps.getUser().setHeight(10000);
                fail("Should throw an exception, this height is too big");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                userOps.getUser().init(5, "name", 0, 5, 5, 5);
                fail("Should throw an exception, this height is too small");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                userOps.getUser().init(5, "name", 10000, 5, 5, 5);
                fail("Should throw an exception, this height is too big");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }

        @Test
        @DisplayName("Tests setting an invalid user's weight")
        void testSetWeight() {
            try {
                userOps.getUser().setWeight(0);
                fail("Should throw an exception, this weight is to small");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                userOps.getUser().setWeight(-1);
                fail("Should throw an exception, this weight is invalid");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                userOps.getUser().setWeight(10000);
                fail("Should throw an exception, this weight is too big");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                userOps.getUser().init(5, "name", 5, 0, 5, 5);
                fail("Should throw an exception, this weight is too small");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                userOps.getUser().init(5, "name", 5, 10000, 5, 5);
                fail("Should throw an exception, this weight is too big");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }


        @Test
        @DisplayName("Tests setting an invalid user's calorie goal")
        void testSetCalorieGoal() {
            try {
                userOps.getUser().setCalorieGoal(-1);
                fail("Should throw an exception, this calorie goal is to small");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                userOps.getUser().setCalorieGoal(10000);
                fail("Should throw an exception, this calorie goal is too big");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                userOps.getUser().init(5, "name", 5, 5, -1, 5);
                fail("Should throw an exception, this calorie goal is too small");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                userOps.getUser().init(5, "name", 5, 5, 10000, 5);
                fail("Should throw an exception, this calorie goal is too big");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }


        @Test
        @DisplayName("Tests setting an invalid user's exercise goal")
        void testSetExerciseGoal() {
            try {
                userOps.getUser().setExerciseGoal(-1);
                fail("Should throw an exception, this exercise goal is to small");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                userOps.getUser().setExerciseGoal(10000);
                fail("Should throw an exception, this exercise goal is too big");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                userOps.getUser().init(5, "name", 5, 5, 5, -1);
                fail("Should throw an exception, this exercise goal is too small");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                userOps.getUser().init(5, "name", 5, 5, 5, 10000);
                fail("Should throw an exception, this exercise goal is too big");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }
    }
}

package comp3350.team10.tests.objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import comp3350.team10.objects.User;

public class TestUser {

    @Nested
    @DisplayName("Simple Tests")
    class simpleTests {
        private User user;
        private String testString;

        @BeforeEach
        void setup() {
            user = new User();
            testString = "testString";
        }

        @Test
        @DisplayName("Tests a User's state at construction")
        void objectStateAtConstruction() {
            assertEquals(user.getUserID(), -1);
            assertNull(user.getName());
            assertEquals(user.getHeight(), -1);
            assertEquals(user.getWeight(), -1);
            assertEquals(user.getCalorieGoal(), -1);
            assertEquals(user.getExerciseGoal(), -1);
        }

        @Test
        @DisplayName("Tests setting a users ID")
        void setID() {
            user.init(5, "name", 5, 5, 5, 5);
            assertEquals(user.getUserID(), 5);

            user.init(10, "name", 5, 5, 5, 5);
            assertEquals(user.getUserID(), 10);
        }

        @Test
        @DisplayName("Tests setting a users name")
        void setName() {
            user.setName(testString);
            assertEquals(user.getName(), testString);

            user.setName("A different name");
            assertEquals(user.getName(), "A different name");

            user.init(5, testString, 5, 5, 5, 5);
            assertEquals(user.getName(), testString);

            user.init(5, "A different name", 5, 5, 5, 5);
            assertEquals(user.getName(), "A different name");
        }

        @Test
        @DisplayName("Tests setting a users height")
        void setHeight() {
            user.setHeight(5);
            assertEquals(user.getHeight(), 5);

            user.setHeight(10);
            assertEquals(user.getHeight(), 10);

            user.init(5, "name", 5, 5, 5, 5);
            assertEquals(user.getHeight(), 5);

            user.init(5, "name", 10, 5, 5, 5);
            assertEquals(user.getHeight(), 10);
        }

        @Test
        @DisplayName("Tests setting a users weight")
        void setWeight() {
            user.setWeight(5);
            assertEquals(user.getWeight(), 5);

            user.setWeight(10);
            assertEquals(user.getWeight(), 10);

            user.init(5, "name", 5, 5, 5, 5);
            assertEquals(user.getWeight(), 5);

            user.init(5, "name", 5, 10, 5, 5);
            assertEquals(user.getWeight(), 10);
        }

        @Test
        @DisplayName("Tests setting a users calorie goal")
        void setCalorieGoal() {
            user.setCalorieGoal(5);
            assertEquals(user.getCalorieGoal(), 5);

            user.setCalorieGoal(10);
            assertEquals(user.getCalorieGoal(), 10);

            user.init(5, "name", 5, 5, 5, 5);
            assertEquals(user.getCalorieGoal(), 5);

            user.init(5, "name", 5, 5, 10, 5);
            assertEquals(user.getCalorieGoal(), 10);
        }

        @Test
        @DisplayName("Tests setting a users exercise goal")
        void setExerciseGoal() {
            user.setExerciseGoal(5);
            assertEquals(user.getExerciseGoal(), 5);

            user.setExerciseGoal(10);
            assertEquals(user.getExerciseGoal(), 10);

            user.init(5, "name", 5, 5, 5, 5);
            assertEquals(user.getExerciseGoal(), 5);

            user.init(5, "name", 5, 5, 5, 10);
            assertEquals(user.getExerciseGoal(), 10);
        }
    }


    @Nested
    @DisplayName("Complex test cases")
    class ComplexTestCases {
        private User user;


        @BeforeEach
        void setup() {
            user = new User();
        }

        @Test
        @DisplayName("Complex Test cases for user's ID")
        void testSetID() {
            user.init(500, "name", 5, 5, 5, 5);
            assertEquals(user.getUserID(), 500);

            user.init(1000, "name", 5, 5, 5, 5);
            assertEquals(user.getUserID(), 1000);
        }

        @Test
        @DisplayName("Complex Test cases for user's name")
        void testSetName() {
            user.setName("\t\t\t\t\tRomeo");
            assertEquals("\t\t\t\t\tRomeo", user.getName());

            user.setName("\n\n\n\n Gurdit");
            assertEquals("\n\n\n\n Gurdit", user.getName());

            user.setName("!!!!@#$$$%%%%%^");
            assertEquals("!!!!@#$$$%%%%%^", user.getName());

            user.setName("12344");
            assertEquals(user.getName(), "12344");

            user.setName("               .         ");
            assertEquals(user.getName(), "               .         ");

            user.setName("Dane         ");
            assertEquals(user.getName(), "Dane         ");

            user.setName("null");
            assertEquals(user.getName(), "null");

            user.setName(",,,,,,    ");
            assertEquals(user.getName(), ",,,,,,    ");

            user.setName("\\\\\\");
            assertEquals(user.getName(), "\\\\\\");

            user.init(5, "\t\t\t\t\tRomeo", 5, 5, 5, 5);
            assertEquals(user.getName(), "\t\t\t\t\tRomeo");

            user.init(5, "\n\n\n\n Gurdit", 5, 5, 5, 5);
            assertEquals(user.getName(), "\n\n\n\n Gurdit");

            user.init(5, "!!!!@#$$$%%%%%^", 5, 5, 5, 5);
            assertEquals(user.getName(), "!!!!@#$$$%%%%%^");

            user.init(5, "12344", 5, 5, 5, 5);
            assertEquals(user.getName(), "12344");

            user.init(5, "               .         ", 5, 5, 5, 5);
            assertEquals(user.getName(), "               .         ");

            user.init(5, "Dane         ", 5, 5, 5, 5);
            assertEquals(user.getName(), "Dane         ");

            user.init(5, "null", 5, 5, 5, 5);
            assertEquals(user.getName(), "null");

            user.init(5, ",,,,,,    ", 5, 5, 5, 5);
            assertEquals(user.getName(), ",,,,,,    ");

            user.init(5, "\\\\\\", 5, 5, 5, 5);
            assertEquals(user.getName(), "\\\\\\");
        }

        @Test
        @DisplayName("Tests setting a complex user's height")
        void testSetHeight() {
            user.setHeight(500);
            assertEquals(user.getHeight(), 500);

            user.setHeight(1000);
            assertEquals(user.getHeight(), 1000);

            user.init(5, "name", 500, 5, 5, 5);
            assertEquals(user.getHeight(), 500);

            user.init(5, "name", 1000, 5, 5, 5);
            assertEquals(user.getHeight(), 1000);
        }

        @Test
        @DisplayName("Tests setting a complex user's weight")
        void testSetWeight() {
            user.setWeight(500);
            assertEquals(user.getWeight(), 500);

            user.setWeight(1000);
            assertEquals(user.getWeight(), 1000);

            user.init(5, "name", 5, 500, 5, 5);
            assertEquals(user.getWeight(), 500);

            user.init(5, "name", 5, 1000, 5, 5);
            assertEquals(user.getWeight(), 1000);
        }

        @Test
        @DisplayName("Tests setting a complex user's calorie goal")
        void testSetCalorieGoal() {
            user.setCalorieGoal(500);
            assertEquals(user.getCalorieGoal(), 500);

            user.setCalorieGoal(1000);
            assertEquals(user.getCalorieGoal(), 1000);

            user.init(5, "name", 5, 5, 500, 5);
            assertEquals(user.getCalorieGoal(), 500);

            user.init(5, "name", 5, 5, 1000, 5);
            assertEquals(user.getCalorieGoal(), 1000);
        }

        @Test
        @DisplayName("Tests setting a complex user's exercise goal")
        void testSetExerciseGoal() {
            user.setExerciseGoal(500);
            assertEquals(user.getExerciseGoal(), 500);

            user.setExerciseGoal(1000);
            assertEquals(user.getExerciseGoal(), 1000);

            user.init(5, "name", 5, 5, 5, 500);
            assertEquals(user.getExerciseGoal(), 500);

            user.init(5, "name", 5, 5, 5, 1000);
            assertEquals(user.getExerciseGoal(), 1000);
        }
    }

    @Nested
    @DisplayName("Empty tests")
    class EmptyTests {
        private User user;

        @BeforeEach
        void setup() {
            user = new User();
        }

        @Test
        @DisplayName("Tests setting an empty or null name")
        void testName() {
            try {
                user.setName(null);
                fail("Name cannot be null, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                user.setName("");
                fail("Name cannot be empty, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            user.setName(" ");
            assertEquals(user.getName(), " ");

            try {
                user.init(5, null, 5, 5, 5, 5);
                fail("Name cannot be null, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                user.init(5, "", 5, 5, 5, 5);
                fail("Name cannot be empty, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            user.init(5, " ", 5, 5, 5, 5);
            assertEquals(user.getName(), " ");
        }
    }


    @Nested
    @DisplayName("Edge case tests")
    class EdgeTestCases {
        private User user;
        private String largeTestString;

        @BeforeEach
        void setup() {
            user = new User();
            largeTestString = "";

            for (int i = 0; i < 9999; i++) {
                largeTestString = largeTestString + "a";
            }
        }

        @Test
        @DisplayName("Tests setting an edge case for user's ID")
        void testSetID() {
            user.init(0, "name", 5, 5, 5, 5);
            assertEquals(0, user.getUserID());
        }

        @Test
        @DisplayName("Tests setting the same ID a user already has")
        void testSetDuplicateID() {
            user.init(10, "name", 5, 5, 5, 5);
            user.init(10, "name", 5, 5, 5, 5);
            assertEquals(10, user.getUserID());
        }

        @Test
        @DisplayName("Tests setting an edge case for user's name")
        void testSetName() {
            user.setName(largeTestString);
            assertEquals(user.getName(), largeTestString);

            user.init(5, largeTestString, 5, 5, 5, 5);
            assertEquals(user.getName(), largeTestString);
        }

        @Test
        @DisplayName("Tests setting the same name a user already has")
        void testSetDuplicateName() {
            user.setName(largeTestString);
            user.setName(largeTestString);
            assertEquals(user.getName(), largeTestString);

            user.init(5, largeTestString, 5, 5, 5, 5);
            user.init(5, largeTestString, 5, 5, 5, 5);
            assertEquals(user.getName(), largeTestString);
        }

        @Test
        @DisplayName("Tests setting an edge case for user's height")
        void testSetHeight() {
            user.setHeight(1);
            assertEquals(user.getHeight(), 1);

            user.setHeight(9999);
            assertEquals(user.getHeight(), 9999);

            user.init(5, "name", 1, 5, 5, 5);
            assertEquals(user.getHeight(), 1);

            user.init(5, "name", 9999, 5, 5, 5);
            assertEquals(user.getHeight(), 9999);
        }

        @Test
        @DisplayName("Tests setting the same height a user already has")
        void testSetDuplicateHeight() {
            user.setHeight(5);
            user.setHeight(5);
            assertEquals(user.getHeight(), 5);

            user.init(5, "name", 10, 5, 5, 5);
            user.init(5, "name", 10, 5, 5, 5);
            assertEquals(user.getHeight(), 10);
        }

        @Test
        @DisplayName("Tests setting an edge case for user's weight")
        void testSetWeight() {
            user.setWeight(1);
            assertEquals(user.getWeight(), 1);

            user.setWeight(9999);
            assertEquals(user.getWeight(), 9999);

            user.init(5, "name", 5, 1, 5, 5);
            assertEquals(user.getWeight(), 1);

            user.init(5, "name", 5, 9999, 5, 5);
            assertEquals(user.getWeight(), 9999);
        }

        @Test
        @DisplayName("Tests setting the same weight a user already has")
        void testSetDuplicateWeight() {
            user.setWeight(5);
            user.setWeight(5);
            assertEquals(user.getWeight(), 5);

            user.init(5, "name", 5, 10, 5, 5);
            user.init(5, "name", 5, 10, 5, 5);
            assertEquals(user.getWeight(), 10);
        }

        @Test
        @DisplayName("Tests setting an edge case for user's calorie goal")
        void testSetCalorieGoal() {
            user.setCalorieGoal(0);
            assertEquals(user.getCalorieGoal(), 0);

            user.setCalorieGoal(9999);
            assertEquals(user.getCalorieGoal(), 9999);

            user.init(5, "name", 5, 5, 0, 5);
            assertEquals(user.getCalorieGoal(), 0);

            user.init(5, "name", 5, 5, 9999, 5);
            assertEquals(user.getCalorieGoal(), 9999);
        }

        @Test
        @DisplayName("Tests setting the same calorie goal a user already has")
        void testSetDuplicateCalorieGoal() {
            user.setCalorieGoal(5);
            user.setCalorieGoal(5);
            assertEquals(user.getCalorieGoal(), 5);

            user.init(5, "name", 5, 5, 10, 5);
            user.init(5, "name", 5, 5, 10, 5);
            assertEquals(user.getCalorieGoal(), 10);
        }

        @Test
        @DisplayName("Tests setting an edge case for user's exercise goal")
        void testSetExerciseGoal() {
            user.setExerciseGoal(0);
            assertEquals(user.getExerciseGoal(), 0);

            user.setExerciseGoal(9999);
            assertEquals(user.getExerciseGoal(), 9999);

            user.init(5, "name", 5, 5, 5, 0);
            assertEquals(user.getExerciseGoal(), 0);

            user.init(5, "name", 5, 5, 5, 9999);
            assertEquals(user.getExerciseGoal(), 9999);
        }

        @Test
        @DisplayName("Tests setting the same calorie goal a user already has")
        void testSetDuplicateExerciseGoal() {
            user.setExerciseGoal(5);
            user.setExerciseGoal(5);
            assertEquals(user.getExerciseGoal(), 5);

            user.init(5, "name", 5, 5, 5, 10);
            user.init(5, "name", 5, 5, 5, 10);
            assertEquals(user.getExerciseGoal(), 10);
        }
    }


    @Nested
    @DisplayName("Invalid tests")
    class InvalidTestCases {
        private User user;
        private String longTestString;

        @BeforeEach
        void setup() {
            user = new User();
            longTestString = "";

            for (int i = 0; i < 10000; i++) {
                longTestString = longTestString + "a";
            }
        }

        @Test
        @DisplayName("Tests setting an invalid user ID")
        void testSetUserID() {
            try {
                user.init(-1, "name", 5, 5, 5, 5);
                fail("User ID should be >= 0. should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }

        @Test
        @DisplayName("Tests setting an invalid user's name")
        void testSetName() {
            try {
                user.setName(longTestString);
                fail("User name is too long. should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                user.init(5, longTestString, 5, 5, 5, 5);
                fail("User name is too long. should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

        }

        @Test
        @DisplayName("Tests setting an invalid user height")
        void testSetHeight() {
            try {
                user.setHeight(0);
                fail("Should throw an exception, this height is to small");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                user.setHeight(10000);
                fail("Should throw an exception, this height is too big");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                user.setHeight(-1);
                fail("Should throw an exception, this height is invalid");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                user.init(5, "name", 0, 5, 5, 5);
                fail("Should throw an exception, this height is too small");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                user.init(5, "name", -1, 5, 5, 5);
                fail("Should throw an exception, this height is invalid");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                user.init(5, "name", 10000, 5, 5, 5);
                fail("Should throw an exception, this height is too big");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }

        @Test
        @DisplayName("Tests setting an invalid user's weight")
        void testSetWeight() {
            try {
                user.setWeight(0);
                fail("Should throw an exception, this weight is to small");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                user.setWeight(10000);
                fail("Should throw an exception, this weight is too big");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                user.setWeight(-1);
                fail("Should throw an exception, this weight is invalid");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                user.init(5, "name", 5, 0, 5, 5);
                fail("Should throw an exception, this weight is too small");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                user.init(5, "name", 5, -1, 5, 5);
                fail("Should throw an exception, this weight is invalid");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                user.init(5, "name", 5, 10000, 5, 5);
                fail("Should throw an exception, this weight is too big");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }


        @Test
        @DisplayName("Tests setting an invalid user's calorie goal")
        void testSetCalorieGoal() {
            try {
                user.setCalorieGoal(-1);
                fail("Should throw an exception, this calorie goal is invalid");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                user.setCalorieGoal(10000);
                fail("Should throw an exception, this calorie goal is too big");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                user.init(5, "name", 5, 5, -1, 5);
                fail("Should throw an exception, this calorie goal is invalid");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                user.init(5, "name", 5, 5, 10000, 5);
                fail("Should throw an exception, this calorie goal is too big");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }


        @Test
        @DisplayName("Tests setting an invalid user's exercise goal")
        void testSetExerciseGoal() {
            try {
                user.setExerciseGoal(-1);
                fail("Should throw an exception, this exercise goal is to small");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                user.setExerciseGoal(10000);
                fail("Should throw an exception, this exercise goal is too big");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                user.init(5, "name", 5, 5, 5, -1);
                fail("Should throw an exception, this exercise goal is invalid");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                user.init(5, "name", 5, 5, 5, 10000);
                fail("Should throw an exception, this exercise goal is too big");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }
    }
}

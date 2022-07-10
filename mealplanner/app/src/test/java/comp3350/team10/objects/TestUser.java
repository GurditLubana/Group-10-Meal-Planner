package comp3350.team10.objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import comp3350.team10.application.Main;


public class TestUser {

    @Nested
    @DisplayName("Simple Tests")
    class simpleTests {
        private User user;


        @BeforeEach
        void setup() {
            Main.startUp();
            user = new User(1, "George", 180, 157, 2000, 300);
        }

        @Test
        @DisplayName("Value of each field at construction of the user object")
        void objectStateAtConstruction() {

            assertEquals(1, user.getUserID());
            assertEquals("George", user.getName());
            assertEquals(157, user.getWeight());
            assertEquals(180, user.getHeight());
            assertEquals(2000, user.getCalorieGoal());
            assertEquals(300, user.getExerciseGoal());

        }

        @Test
        @DisplayName("Trying to set differnt value to different fields of the user")
        void changeUsersFieldTests() {

            user.setName("Alex");
            user.setCalorieGoal(8000);
            user.setWeight(200);
            user.setHeight(160);
            user.setExerciseGoal(400);

            assertEquals(1, user.getUserID());
            assertEquals("Alex", user.getName());
            assertEquals(200, user.getWeight());
            assertEquals(160, user.getHeight());
            assertEquals(8000, user.getCalorieGoal());
            assertEquals(400, user.getExerciseGoal());


        }
    }

    @Nested
    @DisplayName("Complex Test cases, that are valid but complex")
    class ComplexTestCases {

        private User user;


        @BeforeEach
        void setup() {
            Main.startUp();
            user = new User(1, "George", 180, 157, 2000, 300);
        }

        @Test
        @DisplayName("Testing some complex cases which are still valid but not expected input.")
        void ComplexCases() {


            user.setName(" ");
            assertEquals(" ", user.getName());

            user.setName("\t\t\t\t\tRomeo");
            assertEquals("\t\t\t\t\tRomeo", user.getName());

            user.setName("\n\n\n\n Gurdit");
            assertEquals("\n\n\n\n Gurdit", user.getName());

            user.setName("!!!!@#$$$%%%%%^");
            assertEquals("!!!!@#$$$%%%%%^", user.getName());

            user.setName("12344");
            assertEquals("12344", user.getName());

            user.setName("               .         ");
            assertEquals("               .         ", user.getName());

            user.setName("Dane         ");
            assertEquals("Dane         ", user.getName());

            user.setName("null");
            assertEquals("null", user.getName());

            user.setName(",,,,,,    ");
            assertEquals(",,,,,,    ", user.getName());

            user.setName("\\\\\\");
            assertEquals("\\\\\\", user.getName());


        }
    }



    @Nested
    @DisplayName("These are the edge test cases")
    class EdgeTestCases {

        private User user;

        @BeforeEach
        void setup() {
            Main.startUp();
            user = new User(1, "George", 180, 157, 2000, 300);
        }

        @Test
        @DisplayName("All these tests are the edge cases")

        void EdgeCasesOfSetters() {

            user.setHeight(10);                               // minimum height
            assertEquals(10, user.getHeight());

            user.setHeight(280);                              //maximum height
            assertEquals(280, user.getHeight());

            user.setWeight(100);
            assertEquals(100, user.getWeight());      //minimum weight

            user.setWeight(350);                              //maximum weight
            assertEquals(350, user.getWeight());

            user.setExerciseGoal(0);
            assertEquals(0, user.getExerciseGoal());  //minimum Exercise Goal value

            user.setExerciseGoal(9999);
            assertEquals(9999, user.getExerciseGoal());//maximum Exercise Goal value

            user.setCalorieGoal(0);
            assertEquals(0, user.getCalorieGoal());   //minimum Calorie Goal value

            user.setCalorieGoal(9999);
            assertEquals(9999, user.getCalorieGoal()); //maximum Calorie Goal value


        }
    }



    @Nested
    @DisplayName("These test cases should throw exception")
    class InvalidTestCases {

        private User user;

        @BeforeEach
        void setup() {

            Main.startUp();
            user = new User(1, "George", 180, 157, 2000, 300);

        }

        @Test
        @DisplayName("All these tests should fail and throw an exception")

        void shouldThrowExceptionTest() {

            assertThrows(IllegalArgumentException.class, () -> {

                user.setName(null);
                assertNull(user.getName());

                user.setName("");
                assertEquals("", user.getName());

                user.setHeight(100000);
                assertEquals(100000, user.getHeight());

                user.setHeight(-989);
                assertEquals(-989, user.getHeight());

                user.setHeight(0);
                assertEquals(0,user.getHeight());

                user.setWeight(999999);
                assertEquals(9999,user.getWeight());

                user.setWeight(-222222);
                assertEquals(-22222,user.getWeight());

                user.setWeight(0);
                assertEquals(0, user.getWeight());

                user.setExerciseGoal(-9988);
                assertEquals(-9988, user.getExerciseGoal());

                user.setExerciseGoal(3333333);
                assertEquals(3333333, user.getExerciseGoal());

                user.setExerciseGoal(0);
                assertEquals(0, user.getExerciseGoal());

                user.setCalorieGoal(-100);
                assertEquals(-100,user.getCalorieGoal());

                user.setCalorieGoal(100000);
                assertEquals(100000,user.getCalorieGoal());

                user.setCalorieGoal(0);
                assertEquals(0,user.getCalorieGoal());

            });

        }
    }
}

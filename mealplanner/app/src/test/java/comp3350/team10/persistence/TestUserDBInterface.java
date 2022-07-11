package comp3350.team10.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import comp3350.team10.objects.User;

public class TestUserDBInterface {
    UserDBInterface db;

    @BeforeEach
    void setup() {
        SharedDB.start();
        SharedDB.startStub();
        //SharedDB.startHsql();
        this.db = SharedDB.getUserDB();
    }

    @AfterEach
    void shutdown() {
        SharedDB.close();
    }

    @Nested
    @DisplayName("Simple Tests should pass")
    class caseSimple {

        @Test
        @DisplayName("typical values should work")
        void typicalValues() {
            User currUser = db.getUser();

            db.setHeight(currUser.getUserID(), 160);
            assertEquals(160, currUser.getHeight());

            db.setWeight(currUser.getUserID(), 160);
            assertEquals(160, currUser.getWeight());

            db.setCalorieGoal(currUser.getUserID(), 160);
            assertEquals(160, currUser.getCalorieGoal());

            db.setExerciseGoal(currUser.getUserID(), 160);
            assertEquals(160, currUser.getExerciseGoal());
        }

    }


    @Nested
    @DisplayName("Complex Tests should pass")
    class caseComplex {

        @Test
        @DisplayName("we can set values multiple times")
        void typicalValues() {
            User currUser = db.getUser();

            db.setHeight(currUser.getUserID(), 160);
            assertEquals(160, currUser.getHeight());

            db.setWeight(currUser.getUserID(), 160);
            assertEquals(160, currUser.getWeight());

            db.setCalorieGoal(currUser.getUserID(), 160);
            assertEquals(160, currUser.getCalorieGoal());

            db.setExerciseGoal(currUser.getUserID(), 160);
            assertEquals(160, currUser.getExerciseGoal());

            db.setHeight(currUser.getUserID(), 200);
            assertEquals(200, currUser.getHeight());

            db.setWeight(currUser.getUserID(), 200);
            assertEquals(200, currUser.getWeight());

            db.setCalorieGoal(currUser.getUserID(), 200);
            assertEquals(200, currUser.getCalorieGoal());

            db.setExerciseGoal(currUser.getUserID(), 200);
            assertEquals(200, currUser.getExerciseGoal());

            db.setHeight(currUser.getUserID(), 500);
            assertEquals(500, currUser.getHeight());

            db.setWeight(currUser.getUserID(), 500);
            assertEquals(500, currUser.getWeight());

            db.setCalorieGoal(currUser.getUserID(), 500);
            assertEquals(500, currUser.getCalorieGoal());

            db.setExerciseGoal(currUser.getUserID(), 500);
            assertEquals(500, currUser.getExerciseGoal());
        }
    }


    @Nested
    @DisplayName("Edge case Tests should pass")
    class caseEdge {

        @Test
        @DisplayName("1 and 0 should pass")
        void testOneZero() {
            User currUser = db.getUser();

            db.setHeight(currUser.getUserID(), 1);
            assertEquals(1, currUser.getHeight());

            db.setWeight(currUser.getUserID(), 1);
            assertEquals(1, currUser.getWeight());

            db.setCalorieGoal(currUser.getUserID(), 1);
            assertEquals(1, currUser.getCalorieGoal());

            db.setExerciseGoal(currUser.getUserID(), 1);
            assertEquals(1, currUser.getExerciseGoal());

            db.setCalorieGoal(currUser.getUserID(), 0);
            assertEquals(0, currUser.getCalorieGoal());

            db.setExerciseGoal(currUser.getUserID(), 0);
            assertEquals(0, currUser.getCalorieGoal());
        }
    }


    @Nested
    @DisplayName("Tests that should fail")
    class caseFail {

        @Test
        @DisplayName("Negative values should fail")
        void negativeValues() {
            User currUser = db.getUser();

            assertThrows(IllegalArgumentException.class, () -> {
                db.setHeight(currUser.getUserID(), -1);
            });
            assertEquals(666, currUser.getHeight());

            assertThrows(IllegalArgumentException.class, () -> {
                db.setWeight(currUser.getUserID(), -1);
            });
            assertEquals(666, currUser.getWeight());

            assertThrows(IllegalArgumentException.class, () -> {
                db.setCalorieGoal(currUser.getUserID(), -1);
            });
            assertEquals(666, currUser.getCalorieGoal());

            assertThrows(IllegalArgumentException.class, () -> {
                db.setExerciseGoal(currUser.getUserID(), -1);
            });
            assertEquals(666, currUser.getCalorieGoal());

            assertThrows(IllegalArgumentException.class, () -> {
                db.setHeight(-1, 160);
            });
            assertEquals(666, currUser.getHeight());

            assertThrows(IllegalArgumentException.class, () -> {
                db.setWeight(-1, 160);
            });
            assertEquals(666, currUser.getWeight());

            assertThrows(IllegalArgumentException.class, () -> {
                db.setCalorieGoal(-1, 160);
            });
            assertEquals(666, currUser.getCalorieGoal());

            assertThrows(IllegalArgumentException.class, () -> {
                db.setExerciseGoal(-1, 160);
            });
            assertEquals(666, currUser.getCalorieGoal());
        }

        @Test
        @DisplayName(" 0 should fail")
        void testOneZero() {
            User currUser = db.getUser();

            assertThrows(IllegalArgumentException.class, () -> {
                db.setHeight(currUser.getUserID(), 0);
            });

            assertEquals(666, currUser.getHeight());

            assertThrows(IllegalArgumentException.class, () -> {
                db.setWeight(currUser.getUserID(), 0);
            });
            assertEquals(666, currUser.getWeight());
        }
    }
}

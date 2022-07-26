package comp3350.team10.tests.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import comp3350.team10.objects.User;
import comp3350.team10.persistence.DBSelector;
import comp3350.team10.persistence.UserDBInterface;

public class TestUserDBInterface {
    UserDBInterface db;

    @BeforeEach
    void setup() {
        DBSelector.start(new DataAccessStub());

        this.db = DBSelector.getUserDB();
    }

    @AfterEach
    void shutdown() {
        restoreDefault();
        DBSelector.close();
    }

    void restoreDefault() {
        User currUser = db.getUser(1);
        currUser.setHeight(100);
        currUser.setWeight(200);
        currUser.setCalorieGoal(2000);
        currUser.setExerciseGoal(600);
        db.updateUser(currUser);
    }

    @Nested
    @DisplayName("Simple Tests should pass")
    class caseSimple {

        @AfterEach
        void restore() {
            restoreDefault();
        }

        @Test
        @DisplayName("typical values should work")
        void typicalValues() {
            User currUser = db.getUser(1);
            User testUser = currUser.clone();

            assertEquals(100, currUser.getHeight());
            assertEquals(200, currUser.getWeight());
            assertEquals(2000, currUser.getCalorieGoal());
            assertEquals(600, currUser.getExerciseGoal());

            currUser.setHeight(160);
            db.updateUser(currUser);
            testUser = db.getUser(1);
            assertEquals(160, testUser.getHeight());

            currUser.setWeight(161);
            db.updateUser(currUser);
            testUser = db.getUser(1);
            assertEquals(161, testUser.getWeight());

            currUser.setCalorieGoal(162);
            db.updateUser(currUser);
            testUser = db.getUser(1);
            assertEquals(162, testUser.getCalorieGoal());

            currUser.setExerciseGoal(163);
            db.updateUser(currUser);
            testUser = db.getUser(1);
            assertEquals(163, testUser.getExerciseGoal());
        }

    }


    @Nested
    @DisplayName("Complex Tests should pass")
    class caseComplex {

        @AfterEach
        void restore() {
            restoreDefault();
        }

        @Test
        @DisplayName("we can set values multiple times")
        void typicalValues() {
            User currUser = db.getUser(1);
            User testUser = currUser.clone();

            currUser.setHeight(160);
            db.updateUser(currUser);
            testUser = db.getUser(1);
            assertEquals(160, testUser.getHeight());
            assertEquals(200, testUser.getWeight());
            assertEquals(2000, testUser.getCalorieGoal());
            assertEquals(600, testUser.getExerciseGoal());

            currUser.setWeight(161);
            db.updateUser(currUser);
            testUser = db.getUser(1);
            assertEquals(160, testUser.getHeight());
            assertEquals(161, testUser.getWeight());
            assertEquals(2000, testUser.getCalorieGoal());
            assertEquals(600, testUser.getExerciseGoal());

            currUser.setCalorieGoal(162);
            db.updateUser(currUser);
            testUser = db.getUser(1);
            assertEquals(160, testUser.getHeight());
            assertEquals(161, testUser.getWeight());
            assertEquals(162, testUser.getCalorieGoal());
            assertEquals(600, testUser.getExerciseGoal());

            currUser.setExerciseGoal(163);
            db.updateUser(currUser);
            testUser = db.getUser(1);
            assertEquals(160, testUser.getHeight());
            assertEquals(161, testUser.getWeight());
            assertEquals(162, testUser.getCalorieGoal());
            assertEquals(163, testUser.getExerciseGoal());

            currUser.setHeight(200);
            db.updateUser(currUser);
            testUser = db.getUser(1);
            assertEquals(200, testUser.getHeight());
            assertEquals(161, testUser.getWeight());
            assertEquals(162, testUser.getCalorieGoal());
            assertEquals(163, testUser.getExerciseGoal());

            currUser.setWeight(201);
            db.updateUser(currUser);
            testUser = db.getUser(1);
            assertEquals(200, testUser.getHeight());
            assertEquals(201, testUser.getWeight());
            assertEquals(162, testUser.getCalorieGoal());
            assertEquals(163, testUser.getExerciseGoal());

            currUser.setCalorieGoal(202);
            db.updateUser(currUser);
            testUser = db.getUser(1);
            assertEquals(200, testUser.getHeight());
            assertEquals(201, testUser.getWeight());
            assertEquals(202, testUser.getCalorieGoal());
            assertEquals(163, testUser.getExerciseGoal());

            currUser.setExerciseGoal(203);
            db.updateUser(currUser);
            testUser = db.getUser(1);
            assertEquals(200, testUser.getHeight());
            assertEquals(201, testUser.getWeight());
            assertEquals(202, testUser.getCalorieGoal());
            assertEquals(203, testUser.getExerciseGoal());

            currUser.setHeight(500);
            db.updateUser(currUser);
            testUser = db.getUser(1);
            assertEquals(500, testUser.getHeight());
            assertEquals(201, testUser.getWeight());
            assertEquals(202, testUser.getCalorieGoal());
            assertEquals(203, testUser.getExerciseGoal());

            currUser.setWeight(501);
            db.updateUser(currUser);
            testUser = db.getUser(1);
            assertEquals(500, testUser.getHeight());
            assertEquals(501, testUser.getWeight());
            assertEquals(202, testUser.getCalorieGoal());
            assertEquals(203, testUser.getExerciseGoal());

            currUser.setCalorieGoal(502);
            db.updateUser(currUser);
            testUser = db.getUser(1);
            assertEquals(500, testUser.getHeight());
            assertEquals(501, testUser.getWeight());
            assertEquals(502, testUser.getCalorieGoal());
            assertEquals(203, testUser.getExerciseGoal());

            currUser.setExerciseGoal(503);
            db.updateUser(currUser);
            testUser = db.getUser(1);
            assertEquals(500, testUser.getHeight());
            assertEquals(501, testUser.getWeight());
            assertEquals(502, testUser.getCalorieGoal());
            assertEquals(503, testUser.getExerciseGoal());
        }
    }


    @Nested
    @DisplayName("Edge case Tests should pass")
    class caseEdge {

        @AfterEach
        void restore() {
            restoreDefault();
        }

        @Test
        @DisplayName("1 and 0 should pass")
        void testOneZero() {
            User testUser;
            User currUser = db.getUser(1);

            currUser.setHeight(1);
            db.updateUser(currUser);
            testUser = db.getUser(1);
            assertEquals(1, testUser.getHeight());

            currUser.setWeight(1);
            db.updateUser(currUser);
            testUser = db.getUser(1);
            assertEquals(1, testUser.getWeight());

            currUser.setCalorieGoal(1);
            db.updateUser(currUser);
            testUser = db.getUser(1);
            assertEquals(1, testUser.getCalorieGoal());

            currUser.setExerciseGoal(1);
            db.updateUser(currUser);
            testUser = db.getUser(1);
            assertEquals(1, testUser.getExerciseGoal());

            currUser.setCalorieGoal(0);
            db.updateUser(currUser);
            testUser = db.getUser(1);
            assertEquals(0, testUser.getCalorieGoal());

            currUser.setExerciseGoal(0);
            db.updateUser(currUser);
            testUser = db.getUser(1);
            assertEquals(0, testUser.getExerciseGoal());
        }
    }


    @Nested
    @DisplayName("Tests that should fail")
    class caseFail {

        @AfterEach
        void restore() {
            restoreDefault();
        }

        @Test
        @DisplayName("Negative values should fail")
        void negativeValues() {
            User currUser = db.getUser(1);

            assertThrows(IllegalArgumentException.class, () -> {
                db.getUser(-1);
            });
            assertEquals(100, currUser.getHeight());
            assertEquals(200, currUser.getWeight());
            assertEquals(2000, currUser.getCalorieGoal());
            assertEquals(600, currUser.getExerciseGoal());
        }

        @Test
        @DisplayName(" uninitialized user should fail")
        void testNonInit() {
            User testUser = new User();
            User currUser;

            assertThrows(IllegalArgumentException.class, () -> {
                db.updateUser(testUser);
            });
            currUser = db.getUser(1);
            assertEquals(100, currUser.getHeight());
            assertEquals(200, currUser.getWeight());
            assertEquals(2000, currUser.getCalorieGoal());
            assertEquals(600, currUser.getExerciseGoal());
        }

        @Test
        @DisplayName("null should fail")
        void testNull() {
            User currUser;

            assertThrows(IllegalArgumentException.class, () -> {
                db.updateUser(null);
            });
            currUser = db.getUser(1);
            assertEquals(100, currUser.getHeight());
            assertEquals(200, currUser.getWeight());
            assertEquals(2000, currUser.getCalorieGoal());
            assertEquals(600, currUser.getExerciseGoal());
        }
    }
}

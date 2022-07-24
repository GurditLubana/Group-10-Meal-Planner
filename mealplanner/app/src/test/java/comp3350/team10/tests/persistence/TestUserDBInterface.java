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
import comp3350.team10.tests.persistence.DataAccessStub;

public class TestUserDBInterface {
    UserDBInterface db;

    @BeforeEach
    void setup() {
        DBSelector.start(new DataAccessStub());;
        
        this.db = DBSelector.getUserDB();
    }

    @AfterEach
    void shutdown() {
        restoreDefault();
        DBSelector.close();
    }

    void restoreDefault(){
        User currUser = db.getUser();
        db.setHeight(currUser.getUserID(), 100);
        db.setWeight(currUser.getUserID(), 200);
        db.setCalorieGoal(currUser.getUserID(), 2000);
        db.setExerciseGoal(currUser.getUserID(), 600);
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
            User currUser = db.getUser();

            assertEquals(100, currUser.getHeight());
            assertEquals(200, currUser.getWeight());
            assertEquals(2000, currUser.getCalorieGoal());
            assertEquals(600, currUser.getExerciseGoal());

            db.setHeight(currUser.getUserID(), 160);
            currUser = db.getUser();
            assertEquals(160, currUser.getHeight());

            db.setWeight(currUser.getUserID(), 161);
            currUser = db.getUser();
            assertEquals(161, currUser.getWeight());

            db.setCalorieGoal(currUser.getUserID(), 162);
            currUser = db.getUser();
            assertEquals(162, currUser.getCalorieGoal());

            db.setExerciseGoal(currUser.getUserID(), 163);
            currUser = db.getUser();
            assertEquals(163, currUser.getExerciseGoal());
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
            User currUser = db.getUser();

            db.setHeight(currUser.getUserID(), 160);
            currUser = db.getUser();
            assertEquals(160, currUser.getHeight());
            assertEquals(200, currUser.getWeight());
            assertEquals(2000, currUser.getCalorieGoal());
            assertEquals(600, currUser.getExerciseGoal());

            db.setWeight(currUser.getUserID(), 161);
            currUser = db.getUser();
            assertEquals(160, currUser.getHeight());
            assertEquals(161, currUser.getWeight());
            assertEquals(2000, currUser.getCalorieGoal());
            assertEquals(600, currUser.getExerciseGoal());

            db.setCalorieGoal(currUser.getUserID(), 162);
            currUser = db.getUser();
            assertEquals(160, currUser.getHeight());
            assertEquals(161, currUser.getWeight());
            assertEquals(162, currUser.getCalorieGoal());
            assertEquals(600, currUser.getExerciseGoal());

            db.setExerciseGoal(currUser.getUserID(), 163);
            currUser = db.getUser();
            assertEquals(160, currUser.getHeight());
            assertEquals(161, currUser.getWeight());
            assertEquals(162, currUser.getCalorieGoal());
            assertEquals(163, currUser.getExerciseGoal());

            db.setHeight(currUser.getUserID(), 200);
            currUser = db.getUser();
            assertEquals(200, currUser.getHeight());
            assertEquals(161, currUser.getWeight());
            assertEquals(162, currUser.getCalorieGoal());
            assertEquals(163, currUser.getExerciseGoal());

            db.setWeight(currUser.getUserID(), 201);
            currUser = db.getUser();
            assertEquals(200, currUser.getHeight());
            assertEquals(201, currUser.getWeight());
            assertEquals(162, currUser.getCalorieGoal());
            assertEquals(163, currUser.getExerciseGoal());

            db.setCalorieGoal(currUser.getUserID(), 202);
            currUser = db.getUser();
            assertEquals(200, currUser.getHeight());
            assertEquals(201, currUser.getWeight());
            assertEquals(202, currUser.getCalorieGoal());
            assertEquals(163, currUser.getExerciseGoal());

            db.setExerciseGoal(currUser.getUserID(), 203);
            currUser = db.getUser();
            assertEquals(200, currUser.getHeight());
            assertEquals(201, currUser.getWeight());
            assertEquals(202, currUser.getCalorieGoal());
            assertEquals(203, currUser.getExerciseGoal());

            db.setHeight(currUser.getUserID(), 500);
            currUser = db.getUser();
            assertEquals(500, currUser.getHeight());
            assertEquals(201, currUser.getWeight());
            assertEquals(202, currUser.getCalorieGoal());
            assertEquals(203, currUser.getExerciseGoal());

            db.setWeight(currUser.getUserID(), 501);
            currUser = db.getUser();
            assertEquals(500, currUser.getHeight());
            assertEquals(501, currUser.getWeight());
            assertEquals(202, currUser.getCalorieGoal());
            assertEquals(203, currUser.getExerciseGoal());

            db.setCalorieGoal(currUser.getUserID(), 502);
            currUser = db.getUser();
            assertEquals(500, currUser.getHeight());
            assertEquals(501, currUser.getWeight());
            assertEquals(502, currUser.getCalorieGoal());
            assertEquals(203, currUser.getExerciseGoal());

            db.setExerciseGoal(currUser.getUserID(), 503);
            currUser = db.getUser();
            assertEquals(500, currUser.getHeight());
            assertEquals(501, currUser.getWeight());
            assertEquals(502, currUser.getCalorieGoal());
            assertEquals(503, currUser.getExerciseGoal());
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
            User currUser = db.getUser();

            db.setHeight(currUser.getUserID(), 1);
            currUser = db.getUser();
            assertEquals(1, currUser.getHeight());

            db.setWeight(currUser.getUserID(), 1);
            currUser = db.getUser();
            assertEquals(1, currUser.getWeight());

            db.setCalorieGoal(currUser.getUserID(), 1);
            currUser = db.getUser();
            assertEquals(1, currUser.getCalorieGoal());

            db.setExerciseGoal(currUser.getUserID(), 1);
            currUser = db.getUser();
            assertEquals(1, currUser.getExerciseGoal());

            db.setCalorieGoal(currUser.getUserID(), 0);
            currUser = db.getUser();
            assertEquals(0, currUser.getCalorieGoal());

            db.setExerciseGoal(currUser.getUserID(), 0);
            currUser = db.getUser();
            assertEquals(0, currUser.getCalorieGoal());
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
            User currUser = db.getUser();

            assertThrows(IllegalArgumentException.class, () -> {
                db.setHeight(currUser.getUserID(), -1);
            });
            assertEquals(100, currUser.getHeight());

            assertThrows(IllegalArgumentException.class, () -> {
                db.setWeight(currUser.getUserID(), -1);
            });
            assertEquals(200, currUser.getWeight());

            assertThrows(IllegalArgumentException.class, () -> {
                db.setCalorieGoal(currUser.getUserID(), -1);
            });
            assertEquals(2000, currUser.getCalorieGoal());

            assertThrows(IllegalArgumentException.class, () -> {
                db.setExerciseGoal(currUser.getUserID(), -1);
            });
            assertEquals(600, currUser.getExerciseGoal());

            assertThrows(IllegalArgumentException.class, () -> {
                db.setHeight(-1, 160);
            });
            assertEquals(100, currUser.getHeight());

            assertThrows(IllegalArgumentException.class, () -> {
                db.setWeight(-1, 160);
            });
            assertEquals(200, currUser.getWeight());

            assertThrows(IllegalArgumentException.class, () -> {
                db.setCalorieGoal(-1, 160);
            });
            assertEquals(2000, currUser.getCalorieGoal());

            assertThrows(IllegalArgumentException.class, () -> {
                db.setExerciseGoal(-1, 160);
            });
            assertEquals(600, currUser.getExerciseGoal());
        }

        @Test
        @DisplayName(" 0 should fail")
        void testOneZero() {
            User currUser = db.getUser();

            assertThrows(IllegalArgumentException.class, () -> {
                db.setHeight(currUser.getUserID(), 0);
            });

            assertEquals(100, currUser.getHeight());

            assertThrows(IllegalArgumentException.class, () -> {
                db.setWeight(currUser.getUserID(), 0);
            });
            assertEquals(200, currUser.getWeight());
        }
    }
}

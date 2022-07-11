package comp3350.team10.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import comp3350.team10.objects.User;

public class TestUserDBInterface {

    @Nested
    @DisplayName("Simple Tests should pass")
    class caseSimple {
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

        @Test
        @DisplayName("typical values should work")
        void typicalValues() {
            User currUser = this.db.getUser();

            this.db.setHeight(currUser.getUserID(), 160);
            assertEquals(160, currUser.getHeight());

            this.db.setWeight(currUser.getUserID(), 160);
            assertEquals(160, currUser.getWeight());

            this.db.setCalorieGoal(currUser.getUserID(), 160);
            assertEquals(160, currUser.getCalorieGoal());

            this.db.setExerciseGoal(currUser.getUserID(), 160);
            assertEquals(160, currUser.getExerciseGoal());
        }

    }


    @Nested
    @DisplayName("Complex Tests should pass")
    class caseComplex {
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

        @Test
        @DisplayName("we can set values multiple times")
        void typicalValues() {
            User currUser = this.db.getUser();

            this.db.setHeight(currUser.getUserID(), 160);
            assertEquals(160, currUser.getHeight());

            this.db.setWeight(currUser.getUserID(), 160);
            assertEquals(160, currUser.getWeight());

            this.db.setCalorieGoal(currUser.getUserID(), 160);
            assertEquals(160, currUser.getCalorieGoal());

            this.db.setExerciseGoal(currUser.getUserID(), 160);
            assertEquals(160, currUser.getExerciseGoal());

            this.db.setHeight(currUser.getUserID(), 200);
            assertEquals(200, currUser.getHeight());

            this.db.setWeight(currUser.getUserID(), 200);
            assertEquals(200, currUser.getWeight());

            this.db.setCalorieGoal(currUser.getUserID(), 200);
            assertEquals(200, currUser.getCalorieGoal());

            this.db.setExerciseGoal(currUser.getUserID(), 200);
            assertEquals(200, currUser.getExerciseGoal());

            this.db.setHeight(currUser.getUserID(), 500);
            assertEquals(500, currUser.getHeight());

            this.db.setWeight(currUser.getUserID(), 500);
            assertEquals(500, currUser.getWeight());

            this.db.setCalorieGoal(currUser.getUserID(), 500);
            assertEquals(500, currUser.getCalorieGoal());

            this.db.setExerciseGoal(currUser.getUserID(), 500);
            assertEquals(500, currUser.getExerciseGoal());
        }
    }


    @Nested
    @DisplayName("Edge case Tests should pass")
    class caseEdge {
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

        @Test
        @DisplayName("1 and 0 should pass")
        void testOneZero() {
            User currUser = this.db.getUser();

            this.db.setHeight(currUser.getUserID(), 1);
            assertEquals(1, currUser.getHeight());

            this.db.setWeight(currUser.getUserID(), 1);
            assertEquals(1, currUser.getWeight());

            this.db.setCalorieGoal(currUser.getUserID(), 1);
            assertEquals(1, currUser.getCalorieGoal());

            this.db.setExerciseGoal(currUser.getUserID(), 1);
            assertEquals(1, currUser.getExerciseGoal());

            this.db.setCalorieGoal(currUser.getUserID(), 0);
            assertEquals(0, currUser.getCalorieGoal());

            this.db.setExerciseGoal(currUser.getUserID(), 0);
            assertEquals(0, currUser.getCalorieGoal());
        }
    }


    @Nested
    @DisplayName("Tests that should fail")
    class caseFail {
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

        @Test
        @DisplayName("Negative values should fail")
        void negativeValues() {
            User currUser = this.db.getUser();

            assertThrows(IllegalArgumentException.class, () -> {
                this.db.setHeight(currUser.getUserID(), -1);
            });
            assertEquals(666, currUser.getHeight());

            assertThrows(IllegalArgumentException.class, () -> {
                this.db.setWeight(currUser.getUserID(), -1);
            });
            assertEquals(666, currUser.getWeight());

            assertThrows(IllegalArgumentException.class, () -> {
                this.db.setCalorieGoal(currUser.getUserID(), -1);
            });
            assertEquals(666, currUser.getCalorieGoal());

            assertThrows(IllegalArgumentException.class, () -> {
                this.db.setExerciseGoal(currUser.getUserID(), -1);
            });
            assertEquals(666, currUser.getCalorieGoal());

            assertThrows(IllegalArgumentException.class, () -> {
                this.db.setHeight(-1, 160);
            });
            assertEquals(666, currUser.getHeight());

            assertThrows(IllegalArgumentException.class, () -> {
                this.db.setWeight(-1, 160);
            });
            assertEquals(666, currUser.getWeight());

            assertThrows(IllegalArgumentException.class, () -> {
                this.db.setCalorieGoal(-1, 160);
            });
            assertEquals(666, currUser.getCalorieGoal());

            assertThrows(IllegalArgumentException.class, () -> {
                this.db.setExerciseGoal(-1, 160);
            });
            assertEquals(666, currUser.getCalorieGoal());
        }

        @Test
        @DisplayName(" 0 should fail")
        void testOneZero() {
            User currUser = this.db.getUser();

            assertThrows(IllegalArgumentException.class, () -> {
                this.db.setHeight(currUser.getUserID(), 0);
            });

            assertEquals(666, currUser.getHeight());

            assertThrows(IllegalArgumentException.class, () -> {
                this.db.setWeight(currUser.getUserID(), 0);
            });
            assertEquals(666, currUser.getWeight());
        }
    }
}

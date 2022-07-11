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

import comp3350.team10.persistence.SharedDB;

public class TestUserDataOps {

    @Nested
    @DisplayName("Simple tests")
    class Test_Simple {
        private UserDataOps userOps;

        @BeforeEach
        void setup(){
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



        @Test
        public void testUserGetter(){
            assertEquals(userOps.getUser(),SharedDB.getUserDB().getUser());
        }

        @Test
        public void testHeightUpdater(){
            userOps.updateHeight(200);
            assertEquals(200,userOps.getUser().getHeight());
        }

        @Test
        public void testWeightUpdater(){
            userOps.updateWeight(55);
            assertEquals(55,userOps.getUser().getWeight());
        }

        @Test
        public void testCalUpdater(){
            userOps.updateCalorieGoal(260.05);
            assertEquals(260.05, userOps.getUser().getCalorieGoal());
        }

        @Test
        public void testExeGoalUpdater(){
            userOps.updateExerciseGoal(500.99);
            assertEquals(500.99, userOps.getUser().getExerciseGoal());
        }
    }
}

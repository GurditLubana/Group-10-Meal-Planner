package comp3350.team10.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import comp3350.team10.persistence.SharedDB;

public class TestUserDataOps {

    @Nested
    @DisplayName("Simple tests")
    class Test_Simple {
        private UserDataOps userOps;

        @BeforeEach
        void setup(){
            try{
                SharedDB.start();

                SharedDB.startStub();
                //SharedDB.startHsql();
                this.db = SharedDB.getLogDB();
                //currDate = Calendar.getInstance();
                userOps = new UserDataOps();
            }catch (Exception e){

            }

        }
    }
}

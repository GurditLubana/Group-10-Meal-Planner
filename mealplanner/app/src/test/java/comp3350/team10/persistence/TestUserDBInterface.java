package comp3350.team10.persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import comp3350.team10.application.Main;

public class TestUserDBInterface {

    @Nested
    @DisplayName("Simple Tests")
    class caseSimple {
        UserDBInterface db;

        @BeforeEach
        void setup() {
            Main.startUp();
            this.db = SharedDB.getUserDB();
        }

        @AfterEach
        void shutdown() {
            Main.shutDown();
        }

        @Test
        @DisplayName("object state at construction")
        void objectStateAtConstruction(){

        }

    }


    @Nested
    @DisplayName("Complex Tests should pass")
    class caseComplex {
        UserDBInterface db;

        @BeforeEach
        void setup() {
            Main.startUp();
            this.db = SharedDB.getUserDB();
        }

        @AfterEach
        void shutdown() {
            Main.shutDown();
        }

        @Test
        @DisplayName("")
        void test() {

        }
    }


    @Nested
    @DisplayName("Edge case Tests should pass")
    class caseEdge {
        UserDBInterface db;

        @BeforeEach
        void setup() {
            Main.startUp();
            this.db = SharedDB.getUserDB();
        }

        @AfterEach
        void shutdown() {
            Main.shutDown();
        }

        @Test
        @DisplayName("")
        void test() {
        }
    }


    @Nested
    @DisplayName("Tests that should fail")
    class caseFail {

        @BeforeEach
        void setup() {
            Main.startUp();
        }

        @AfterEach
        void shutdown() {
            Main.shutDown();
        }

        @Test
        @DisplayName("")
        void test(){
        }
    }
}

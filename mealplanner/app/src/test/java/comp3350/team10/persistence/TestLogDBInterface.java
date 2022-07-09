package comp3350.team10.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.NoSuchElementException;

import comp3350.team10.application.Main;

public class TestLogDBInterface {

    @Nested
    @DisplayName("Simple Tests")
    class testSimple {

        @BeforeEach
        void setup() {
            Main.startUp();
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
    class testComplex {

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
        void test() {

        }
    }


    @Nested
    @DisplayName("Edge case Tests should pass")
    class testEdge {

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
        void test() {
        }
    }


    @Nested
    @DisplayName("Tests that should fail")
    class testFail {

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

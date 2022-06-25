package comp3350.team10.objects;

import comp3350.team10.objects.*;
import comp3350.team10.objects.ListItem.FragmentType;
import comp3350.team10.objects.Edible.Unit;
import comp3350.team10.persistence.SharedDB;

import static org.junit.jupiter.api.Assertions.*;

import android.icu.util.Freezable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;

public class TestDailyLogUnit {

    @Nested
    @DisplayName("Simple tests")
    class Test_Simple {
        private DailyLog testLog;

        @BeforeEach
        void setup(){
            testLog = new DailyLog();
        }

        @Test
        void testDefaultValues() {
            assertNull(testLog.getFoodList());
            assertNull(testLog.getDate());
            assertNull(testLog.getCalGoal());
            assertNull(testLog.getExcGoal());
            assertNull(testLog.getExcActual());
        }

        @Test
        void testBasicLogCreation() {
            ArrayList<Edible> logs = new ArrayList<Edible>();
            logs.add(new Drink());
            logs.add(new Drink());
        
            assertTrue(testLog.init(5, 100, 50, 1, logs));
            assertEquals(testLog.getCalGoal(), 100);
            assertEquals(testLog.getExcGoal(), 50);
            assertEquals(testLog.getExcActual(), 1);
            assertEquals(testLog.getFoodList().size(), 2);
            assertNotNull(testLog.getDate());

            for(int i = 0; i < testLog.getFoodList().size(); i++) {
                assertNotNull(testLog.getFoodList().get(i));
            }
        }

        @Test
        void testSetFoodList() {
            ArrayList<Edible> logs = new ArrayList<Edible>();
            logs.add(new Drink());
            logs.add(new Drink());

            assertTrue(testLog.setFoodList(logs));
        }

        @Test
        void testSetExcerciseActual() {
            assertTrue(testLog.setExcActual(500));
            assertTrue(testLog.setExcActual(100));
            assertTrue(testLog.setExcActual(900));
        }

        @Test
        void testSetCalGoal() {
            assertTrue(testLog.setCalGoal(500));
            assertTrue(testLog.setCalGoal(100));
            assertTrue(testLog.setCalGoal(900));
        }

        @Test
        void testSetExcerciseGoal() {
            assertTrue(testLog.setExcGoal(500));
            assertTrue(testLog.setExcGoal(100));
            assertTrue(testLog.setExcGoal(900));
        }
    }

    @Nested
    @DisplayName("Complex tests")
    class Test_Complex {
        private DailyLog testLog;

        @BeforeEach
        void setup(){
            testLog = new DailyLog();
        }

        @Test
        void testLogOverriding() {
            ArrayList<Edible> logs = new ArrayList<Edible>();
            logs.add(new Drink());
            logs.add(new Drink());

            assertTrue(testLog.init(5, 100, 50, 1, logs));

            logs = new ArrayList<Edible>();
            logs.add(new Drink());
            logs.add(new Drink());
            logs.add(new Drink());

            assertEquals(testLog.getFoodList().size(), 2);

            assertTrue(testLog.setFoodList(logs));
            for(int i = 0; i < testLog.getFoodList().size(); i++) {
                assertNotNull(testLog.getFoodList().get(i));
            }

            assertEquals(testLog.getFoodList().size(), 3);
        }

        @Test
        void testSetAVarietyOfEdiblesLog() {
            ArrayList<Edible> logs = new ArrayList<Edible>();
            logs.add(new Drink());
            logs.add(new Meal());
            logs.add(new Food());

            assertTrue(testLog.setFoodList(logs));
            for(int i = 0; i < testLog.getFoodList().size(); i++) {
                assertNotNull(testLog.getFoodList().get(i));
            }

            assertEquals(testLog.getFoodList().size(), 3);
        }

        @Test
        void testLongFoodLog() {
            ArrayList<Edible> logs = new ArrayList<Edible>();
            logs.add(new Meal());
            logs.add(new Meal());
            logs.add(new Meal());
            logs.add(new Meal());
            logs.add(new Meal());
            logs.add(new Meal());
            logs.add(new Meal());
            logs.add(new Meal());
            logs.add(new Meal());

            assertTrue(testLog.setFoodList(logs));
            for(int i = 0; i < testLog.getFoodList().size(); i++) {
                assertNotNull(testLog.getFoodList().get(i));
            }

            assertEquals(testLog.getFoodList().size(), 9);
        }

        @Test
        void testSetExcerciseActual() {
            assertTrue(testLog.setExcActual(500));
            assertTrue(testLog.setExcActual(50));
            assertTrue(testLog.setExcActual(900));
        }

        @Test
        void testSetCalGoal() {
            assertTrue(testLog.setCalGoal(500));
            assertTrue(testLog.setCalGoal(50));
            assertTrue(testLog.setCalGoal(900));
        }

        @Test
        void testSetExcerciseGoal() {
            assertTrue(testLog.setExcGoal(500));
            assertTrue(testLog.setExcGoal(50));
            assertTrue(testLog.setExcGoal(900));
        }        
    }
    @Nested
    @DisplayName("Empty tests")
    class Test_Empty {
        private DailyLog testLog;

        @BeforeEach
        void setup() {
            testLog = new DailyLog();
        }

        @Test
        void testLogCreation() {
            assertFalse(testLog.init(null, 0, 0, 0, null));
            assertFalse(testLog.init(null, 0, 0, 0, new ArrayList<Edible>()));
        }

        @Test
        void testSetFoodList() {
            assertFalse(testLog.setFoodList(null));
            assertFalse(testLog.setFoodList(new ArrayList<Edible>()));
        }

        @Test
        void testNullInsideFoodList() {
            ArrayList<Edible> newLog = new ArrayList<Edible>();
            newLog.add(new Meal());
            newLog.add(null);
            newLog.add(new Meal());

            assertFalse(testLog.setFoodList(newLog));
        }

        @Test
        void testSetExcerciseActual() {
            assertTrue(testLog.setExcActual(0));
        }

        @Test
        void testSetCalGoal() {
            assertTrue(testLog.setCalGoal(0));
        }

        @Test
        void testSetExcerciseGoal() {
            assertTrue(testLog.setExcGoal(0));
        }
        
    }
    @Nested
    @DisplayName("Edge case tests")
    class Test_EdgeCases {
        private DailyLog testLog;

        @BeforeEach
        void setup(){
            testLog = new DailyLog();
        }

        @Test
        void testBasicLogCreation() {
            ArrayList<Edible> newLog = new ArrayList<Edible>();
            newLog.add(new Food());

            assertTrue(testLog.init(0, 0, 0, 0, newLog));
            assertTrue(testLog.init(0, Constant.ENTRY_MAX_VALUE, Constant.ENTRY_MAX_VALUE, Constant.ENTRY_MAX_VALUE, newLog));
        }

        @Test
        void testSetExcerciseActual() {
            assertTrue(testLog.setExcActual(0));
            assertTrue(testLog.setExcActual(Constant.ENTRY_MAX_VALUE));
        }

        @Test
        void testSetCalGoal() {
            assertTrue(testLog.setCalGoal(0));
            assertTrue(testLog.setCalGoal(Constant.ENTRY_MAX_VALUE));
        }

        @Test
        void testSetExcerciseGoal() {
            assertTrue(testLog.setExcGoal(0));
            assertTrue(testLog.setExcGoal(Constant.ENTRY_MAX_VALUE));
        }
    }

    @Nested
    @DisplayName("Invalid tests")
    class Test_Invalid {
        private DailyLog testLog;

        @BeforeEach
        void setup(){
            testLog = new DailyLog();
        }

        @Test
        void testBasicLogCreation() {
            assertFalse(testLog.init(-1, 20, 20, 20, new ArrayList<Edible>()));
            assertFalse(testLog.init(null, 20, 20, 20, new ArrayList<Edible>()));
            assertFalse(testLog.init(20, -1, 20, 20, new ArrayList<Edible>()));
            assertFalse(testLog.init(20, Constant.ENTRY_MAX_VALUE + 1, 20, 20, new ArrayList<Edible>()));
            assertFalse(testLog.init(20, 20, -1, 20, new ArrayList<Edible>()));
            assertFalse(testLog.init(20, 20, Constant.ENTRY_MAX_VALUE + 1, 20, new ArrayList<Edible>()));
            assertFalse(testLog.init(20, 20, 5, -1, new ArrayList<Edible>()));
            assertFalse(testLog.init(20, 20, 20, Constant.ENTRY_MAX_VALUE + 1, new ArrayList<Edible>()));
            assertFalse(testLog.init(-1, Constant.ENTRY_MAX_VALUE + 1, Constant.ENTRY_MAX_VALUE + 1, Constant.ENTRY_MAX_VALUE + 1, null));
            assertFalse(testLog.init(-1, -1, -1, -1, null));
        }

        @Test
        void testSetExcerciseActual() {
            assertFalse(testLog.setExcActual(-1));
            assertFalse(testLog.setExcActual(Constant.ENTRY_MAX_VALUE + 1));
        }

        @Test
        void testSetCalGoal() {
            assertFalse(testLog.setCalGoal(-1));
            assertFalse(testLog.setCalGoal(Constant.ENTRY_MAX_VALUE + 1));
        }

        @Test
        void testSetExcerciseGoal() {
            assertFalse(testLog.setExcGoal(-1));
            assertFalse(testLog.setExcGoal(Constant.ENTRY_MAX_VALUE + 1));
        }
    }
}
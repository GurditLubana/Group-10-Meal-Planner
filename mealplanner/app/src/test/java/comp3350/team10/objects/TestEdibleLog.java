package comp3350.team10.objects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;

public class TestEdibleLog {

    @Nested
    @DisplayName("Typical cases should pass")
    class EdibleLogSimpleConstruct {
        private EdibleLog testLog;
        private Edible testEdible;
        //private Calendar currDate;
        //private ArrayList<Edible> edibleList;

        @BeforeEach
        void setup() throws Exception {
            testEdible = new Edible();
            //currDate = Calendar.getInstance();
            //edibleList = new ArrayList<Edible>();
            try {
                testEdible.initDetails(2,"banana", "Good", 0.02, Edible.Unit.serving);
                testEdible.initDetails(7, "Rabbit", "desc", 40, Edible.Unit.tbsp);
                testEdible.initNutrition(400, 30, 20, 50);
                testEdible.initCategories(false, false, false, false, false);
                testEdible.initMetadata(false, "photo.jpg");
                testLog = new EdibleLog(testEdible);
                testLog.init(35, Edible.Unit.cups);
                //seedEdibleList();
            }
            catch(Exception e) {
                System.out.println(e);
                System.exit(1);
            }
        }

        @Test
        @DisplayName("object state at construction")
        void testDefaultValues() {
            assertEquals(testLog.getCalories(), testEdible.getCalories());
            assertEquals(35, testEdible.getQuantity());
            assertEquals(testLog.getUnit(), testEdible.getUnit());
        }

        @Test
        @DisplayName("init should take typical values")
        void testInit() {
            assertEquals(400, testLog.getCalories());
            assertEquals(Edible.Unit.cups, testLog.getUnit());
            assertEquals(35, testLog.getQuantity());
        }


        @Nested
        @DisplayName("Simple tests")
        class EdibleLogSimple {

            @BeforeEach
            void setup() {
                try {
                    testLog.init(1400, Edible.Unit.cups);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Test
            @DisplayName("Calories can be set typical values")
            void testCalories() {
                testLog.setCalories(2000);
                assertEquals(2000, testLog.getCalories());
            }

            @Test
            @DisplayName("Calories can be set typical values")
            void testQuantity() {
                testLog.setQuantity(2000);
                assertEquals(2000, testLog.getQuantity());
            }

            @Test
            @DisplayName("Calories can be set typical values")
            void testUnit() {
                testLog.setUnit(Edible.Unit.tbsp);
                assertEquals(Edible.Unit.tbsp, testLog.getUnit());
            }
        }

    }
        @Nested
        @DisplayName("Edge case tests set upperlimit")
        class DailyLogEdgeGoalsUpperLimit {
            private EdibleLog testLog;
            private Edible testEdible;
            void setup() {
                try {
                    testEdible = new Edible();
                    testEdible.initDetails(7, "Rabbit", "desc", 40, Edible.Unit.tbsp);
                    testEdible.initNutrition(400, 30, 20, 50);
                    testEdible.initCategories(false, false, false, false, false);
                    testEdible.initMetadata(false, "photo.jpg");
                    testLog = new EdibleLog(testEdible);
                    testLog.init(90, Edible.Unit.cups);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Test
            @DisplayName("we can set calories to upper limit and get correct progress")
            void testCalorieGoalUpperLimit() {
                testLog.setCalories(Constant.ENTRY_MAX_VALUE);
                assertEquals(Constant.ENTRY_MAX_VALUE, testLog.getCalories());
                assertEquals(90, testLog.getQuantity());
                assertEquals(Edible.Unit.cups, testLog.getUnit());
            }

            @Test
            @DisplayName("we can set Quantity to upper limit and get correct progress")
            void testExerciseGoalUpperLimit() {
                testLog.setQuantity(Constant.ENTRY_MAX_VALUE);
                assertEquals(Constant.ENTRY_MAX_VALUE, testLog.getQuantity());
                assertEquals(400, testLog.getCalories());
                assertEquals(Edible.Unit.cups, testLog.getUnit());
            }
        }
    }

    @Nested
    @DisplayName("Invalid case tests should fail")
    class DailyLogFail {
        private EdibleLog testLog;
        private Edible testedible;
        @BeforeEach
        void setup() throws Exception {
            testedible = new Edible();
            testedible.initDetails(7, "Rabbit", "desc", 40, Edible.Unit.tbsp);
            testedible.initNutrition(400, 30, 20, 50);
            testedible.initCategories(false, false, false, false, false);
            testedible.initMetadata(false, "photo.jpg");

        }

        private void setTestLog(){
            try {
                testLog = new EdibleLog(testedible);
                testLog.init(90,Edible.Unit.g);
            }
            catch(Exception e) {
                System.out.println(e);
            }
        }

        @Nested
        @DisplayName("Null should fail")
        class DailyLogFailNull {

            @Test
            @DisplayName("init null Quantity")
            void testInitNullCalories() {
                assertThrows(IllegalArgumentException.class, () -> {
                    testLog.setQuantity(0.00);
                });
            }

            @Test
            @DisplayName("set null units")
            void testSetNullList() {
                assertThrows(NullPointerException.class, () -> {
                    testLog.setUnit(null);
                });
            }
        }

        @Nested
        @DisplayName("Negative should fail")
        class DailyLogFailNegative {
            @Test
            @DisplayName("init negative Quantity")
            void testSetNegativeExerciseGoal() {
                setTestLog();
                assertThrows(IllegalArgumentException.class, () -> {
                    testLog.setQuantity(-1);
                });
            }
            @Test
            @DisplayName("init negative Calories")
            void testSetNegativeExerciseActual() {
                setTestLog();
                assertThrows(IllegalArgumentException.class, () -> {
                    testLog.setCalories(-1);
                });
            }

        }

        @Nested
        @DisplayName("Beyond Upper Limit should fail")
        class DailyLogFailPastUpper {
            @Test
            @DisplayName("init too high Calories")
            void testSetTooHighExerciseGoal() {
                setTestLog();
                assertThrows(IllegalArgumentException.class, () -> {
                    testLog.setCalories(Constant.ENTRY_MAX_VALUE + 1);
                });
            }
            @Test
            @DisplayName("init too high Quantity")
            void testSetTooHighExerciseActual() {
                setTestLog();
                assertThrows(IllegalArgumentException.class, () -> {
                    testLog.setQuantity(Constant.ENTRY_MAX_VALUE + 1);
                });
            }

        }
    }
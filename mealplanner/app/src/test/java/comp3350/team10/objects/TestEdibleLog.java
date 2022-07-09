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
            assertEquals(testLog.getQuantity(), testEdible.getQuantity());
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
    @DisplayName("Edge case tests should pass")
    class DailyLogEdge {
        private DailyLog testLog;
        private Calendar currDate;
        private ArrayList<Edible> edibleList;

        @BeforeEach
        void setup() {
            testLog = new DailyLog();
            seedEdibleList();
        }

        void seedEdibleList() {
            try {
                edibleList.add(new EdibleLog(
                        new Edible()
                                .initDetails(7, "Rabbit", "desc", 40, Edible.Unit.tbsp)
                                .initNutrition(400, 30, 20, 50)
                                .initCategories(false, false, false, false, false)
                                .initMetadata(false, "photo.jpg")
                ).init(40, Edible.Unit.tbsp));
                edibleList.add(new EdibleLog(
                        new Edible()
                                .initDetails(6, "Carrots", "desc", 30, Edible.Unit.g)
                                .initNutrition(300, 40, 50, 10)
                                .initCategories(false, false, true, true, false)
                                .initMetadata(false, "photo.jpg")
                ).init(30, Edible.Unit.g));
                edibleList.add(new EdibleLog(
                        new Edible()
                                .initDetails(5, "Chicken", "desc", 20, Edible.Unit.tsp)
                                .initNutrition(200, 25, 40, 35)
                                .initCategories(false, false, false, false, true)
                                .initMetadata(false, "photo.jpg")
                ).init(20, Edible.Unit.tsp));

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    @Nested
    @DisplayName("Edge case tests set upperlimit")
    class DailyLogEdgeGoalsUpperLimit {
            private DailyLog testLog;
            private Calendar currDate;
            private ArrayList<Edible> edibleList;

            @BeforeEach
            void setup() {
                testLog = new DailyLog();
                currDate = Calendar.getInstance();
                edibleList = new ArrayList<Edible>();
                seedEdibleList();
                try {
                    testLog.init(currDate, edibleList, 1400, 600, 200);
                }
                catch(Exception e) {
                    System.out.println(e);
                }
            }

            void seedEdibleList() {
                try {
                    edibleList.add(new EdibleLog(
                            new Edible()
                                    .initDetails(7, "Rabbit", "desc", 40, Edible.Unit.tbsp)
                                    .initNutrition(400, 30, 20, 50)
                                    .initCategories(false, false, false, false, false)
                                    .initMetadata(false, "photo.jpg")
                    ).init(40, Edible.Unit.tbsp));
                    edibleList.add(new EdibleLog(
                            new Edible()
                                    .initDetails(6, "Carrots", "desc", 30, Edible.Unit.g)
                                    .initNutrition(300, 40, 50, 10)
                                    .initCategories(false, false, true, true, false)
                                    .initMetadata(false, "photo.jpg")
                    ).init(30, Edible.Unit.g));
                    edibleList.add(new EdibleLog(
                            new Edible()
                                    .initDetails(5, "Chicken", "desc", 20, Edible.Unit.tsp)
                                    .initNutrition(200, 25, 40, 35)
                                    .initCategories(false, false, false, false, true)
                                    .initMetadata(false, "photo.jpg")
                    ).init(20, Edible.Unit.tsp));

                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            @Test
            @DisplayName("we can set calorie goal to upper limit and get correct progress")
            void testCalorieGoalUpperLimit() {
                testLog.setCalorieGoal(Constant.ENTRY_MAX_VALUE);
                assertEquals(currDate.get(Calendar.DAY_OF_YEAR),testLog.getDate().get(Calendar.DAY_OF_YEAR));
                assertEquals(currDate.get(Calendar.YEAR),testLog.getDate().get(Calendar.YEAR));
                assertEquals(currDate.get(Calendar.MONTH),testLog.getDate().get(Calendar.MONTH));
                assertEquals(Constant.ENTRY_MAX_VALUE, testLog.getCalorieGoal());
                assertEquals(600, testLog.getExerciseGoal());
                assertEquals(200, testLog.getExerciseActual());
                assertEquals(900, testLog.getEdibleCalories());
                assertEquals(0, testLog.getProgressExcess());
                assertEquals(7, (int) testLog.getProgressBar());
                assertEquals(9299, testLog.getCalorieNet());

            }

            @Test
            @DisplayName("we can set exercise goal to upper limit and get correct progress")
            void testExerciseGoalUpperLimit() {
                testLog.setExerciseGoal(Constant.ENTRY_MAX_VALUE);
                assertEquals(currDate.get(Calendar.DAY_OF_YEAR),testLog.getDate().get(Calendar.DAY_OF_YEAR));
                assertEquals(currDate.get(Calendar.YEAR),testLog.getDate().get(Calendar.YEAR));
                assertEquals(currDate.get(Calendar.MONTH),testLog.getDate().get(Calendar.MONTH));
                assertEquals(1400, testLog.getCalorieGoal());
                assertEquals(Constant.ENTRY_MAX_VALUE, testLog.getExerciseGoal());
                assertEquals(200, testLog.getExerciseActual());
                assertEquals(900, testLog.getEdibleCalories());
                assertEquals(0, testLog.getProgressExcess());
                assertEquals(50, testLog.getProgressBar());
                assertEquals(700, testLog.getCalorieNet());

            }

            @Test
            @DisplayName("we can set exercise actual to upper limit and get correct progress")
            void testExerciseActualUpperLimit() {
                testLog.setExerciseActual(Constant.ENTRY_MAX_VALUE);
                assertEquals(currDate.get(Calendar.DAY_OF_YEAR),testLog.getDate().get(Calendar.DAY_OF_YEAR));
                assertEquals(currDate.get(Calendar.YEAR),testLog.getDate().get(Calendar.YEAR));
                assertEquals(currDate.get(Calendar.MONTH),testLog.getDate().get(Calendar.MONTH));
                assertEquals(1400, testLog.getCalorieGoal());
                assertEquals(600, testLog.getExerciseGoal());
                assertEquals(Constant.ENTRY_MAX_VALUE, testLog.getExerciseActual());
                assertEquals(900, testLog.getEdibleCalories());
                assertEquals(0, testLog.getProgressExcess());
                assertEquals(0, testLog.getProgressBar());
                assertEquals(10499, testLog.getCalorieNet());

            }
        }
    }

    @Nested
    @DisplayName("Invalid case tests should fail")
    class DailyLogFail {
        private DailyLog testLog;
        private Calendar currDate;
        private ArrayList<Edible> edibleList;

        @BeforeEach
        void setup() {
            testLog = new DailyLog();
            currDate = Calendar.getInstance();
            edibleList = new ArrayList<Edible>();
            seedEdibleList();
        }

        void seedEdibleList() {
            try {
                edibleList.add(new EdibleLog(
                        new Edible()
                                .initDetails(7, "Rabbit", "desc", 40, Edible.Unit.tbsp)
                                .initNutrition(400, 30, 20, 50)
                                .initCategories(false, false, false, false, false)
                                .initMetadata(false, "photo.jpg")
                ).init(40, Edible.Unit.tbsp));
                edibleList.add(new EdibleLog(
                        new Edible()
                                .initDetails(6, "Carrots", "desc", 30, Edible.Unit.g)
                                .initNutrition(300, 40, 50, 10)
                                .initCategories(false, false, true, true, false)
                                .initMetadata(false, "photo.jpg")
                ).init(30, Edible.Unit.g));
                edibleList.add(new EdibleLog(
                        new Edible()
                                .initDetails(5, "Chicken", "desc", 20, Edible.Unit.tsp)
                                .initNutrition(200, 25, 40, 35)
                                .initCategories(false, false, false, false, true)
                                .initMetadata(false, "photo.jpg")
                ).init(20, Edible.Unit.tsp));

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        private void setTestLog(){
            try {
                testLog.init(currDate, edibleList, 1400, 600, 200);
            }
            catch(Exception e) {
                System.out.println(e);
            }
        }

        @Nested
        @DisplayName("Null should fail")
        class DailyLogFailNull {
            @Test
            @DisplayName("init null date")
            void testInitNullDate() {
                assertThrows(NullPointerException.class, () -> {
                    testLog.init(null, edibleList, 1400, 600, 200);
                });
            }

            @Test
            @DisplayName("init null list")
            void testInitNullList() {
                assertThrows(NullPointerException.class, () -> {
                    testLog.init(currDate, null, 1400, 600, 200);
                });
            }

            @Test
            @DisplayName("set null list")
            void testSetNullList() {
                setTestLog();
                assertThrows(NullPointerException.class, () -> {
                    testLog.setEdibleList(null);
                });
            }
        }

        @Nested
        @DisplayName("Negative should fail")
        class DailyLogFailNegative {
            @Test
            @DisplayName("init negative calorie goal")
            void testInitNegativeCalorieGoal() {
                assertThrows(IllegalArgumentException.class, () -> {
                    testLog.init(currDate, edibleList, -1400, 600, 200);
                });
            }
            @Test
            @DisplayName("init negative exercise goal")
            void testInitNegativeExerciseGoal() {
                assertThrows(IllegalArgumentException.class, () -> {
                    testLog.init(currDate, edibleList, 1400, -600, 200);
                });
            }
            @Test
            @DisplayName("init negative exercise actual")
            void testInitNegativeExerciseActual() {
                assertThrows(IllegalArgumentException.class, () -> {
                    testLog.init(currDate, edibleList, 1400, 600, -200);
                });
            }
            @Test
            @DisplayName("Set negative calorie goal")
            void testSetNegativeCalorieGoal() {
                setTestLog();
                assertThrows(IllegalArgumentException.class, () -> {
                    testLog.setCalorieGoal(-1);
                });
            }
            @Test
            @DisplayName("init negative exercise goal")
            void testSetNegativeExerciseGoal() {
                setTestLog();
                assertThrows(IllegalArgumentException.class, () -> {
                    testLog.setExerciseGoal(-1);
                });
            }
            @Test
            @DisplayName("init negative exercise actual")
            void testSetNegativeExerciseActual() {
                setTestLog();
                assertThrows(IllegalArgumentException.class, () -> {
                    testLog.setExerciseActual(-1);
                });
            }

        }

        @Nested
        @DisplayName("Beyond Upper Limit should fail")
        class DailyLogFailPastUpper {
            @Test
            @DisplayName("init too high calorie goal")
            void testInitTooHighCalorieGoal() {
                assertThrows(IllegalArgumentException.class, () -> {
                    testLog.init(currDate, edibleList, Constant.ENTRY_MAX_VALUE + 1, 600, 200);
                });
            }
            @Test
            @DisplayName("init too high exercise goal")
            void testInitTooHighExerciseGoal() {
                assertThrows(IllegalArgumentException.class, () -> {
                    testLog.init(currDate, edibleList, 1400, Constant.ENTRY_MAX_VALUE + 1, 200);
                });
            }
            @Test
            @DisplayName("init too high exercise actual")
            void testInitTooHighExerciseActual() {
                assertThrows(IllegalArgumentException.class, () -> {
                    testLog.init(currDate, edibleList, 1400, 600, Constant.ENTRY_MAX_VALUE + 1);
                });
            }
            @Test
            @DisplayName("Set too high calorie goal")
            void testSetTooHighCalorieGoal() {
                setTestLog();
                assertThrows(IllegalArgumentException.class, () -> {
                    testLog.setCalorieGoal(Constant.ENTRY_MAX_VALUE + 1);
                });
            }
            @Test
            @DisplayName("init too high exercise goal")
            void testSetTooHighExerciseGoal() {
                setTestLog();
                assertThrows(IllegalArgumentException.class, () -> {
                    testLog.setExerciseGoal(Constant.ENTRY_MAX_VALUE + 1);
                });
            }
            @Test
            @DisplayName("init too high exercise actual")
            void testSetTooHighExerciseActual() {
                setTestLog();
                assertThrows(IllegalArgumentException.class, () -> {
                    testLog.setExerciseActual(Constant.ENTRY_MAX_VALUE + 1);
                });
            }

        }
    }
}
 package comp3350.team10.objects;

 import comp3350.team10.objects.*;
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
     @DisplayName("Typical cases should pass")
     class DailyLogSimpleConstruct {
         private DailyLog testLog;
         private Calendar currDate;
         private ArrayList<Edible> edibleList;

         @BeforeEach
         void setup(){
             testLog = new DailyLog();
             currDate = Calendar.getInstance();
             edibleList = new ArrayList<Edible>();
             try {
                 seedEdibleList();
             }
             catch(Exception e) {
                 System.out.println(e);
                 System.exit(1);
             }
         }

         void seedEdibleList() throws Exception{
             try {
                 edibleList.add( new EdibleLog(
                         new Edible()
                                 .initDetails(7, "Rabbit", "desc", 40, Edible.Unit.tbsp)
                                 .initNutrition(400, 30, 20, 50)
                                 .initCategories(false, false, false, false, false)
                                 .initMetadata(false, null)
                 ).init(40, Edible.Unit.tbsp));
                 edibleList.add( new EdibleLog(
                         new Edible()
                                 .initDetails(6, "Carrots", "desc", 30, Edible.Unit.g)
                                 .initNutrition(300, 40, 50, 10)
                                 .initCategories(false, false, true, true, false)
                                 .initMetadata(false, null)
                 ).init(30, Edible.Unit.g));
                 edibleList.add( new EdibleLog(
                         new Edible()
                                 .initDetails(5, "Chicken", "desc", 20, Edible.Unit.tsp)
                                 .initNutrition(200, 25, 40, 35)
                                 .initCategories(false, false, false, false, true)
                                 .initMetadata(false, null)
                 ).init(20, Edible.Unit.tsp));

             }
             catch(Exception e) {
                 throw e;
             }
         }

         @Test
         @DisplayName("object state at construction")
         void testDefaultValues() {
             assertEquals(currDate.get(Calendar.DAY_OF_YEAR),testLog.getDate().get(Calendar.DAY_OF_YEAR));
             assertEquals(currDate.get(Calendar.YEAR),testLog.getDate().get(Calendar.YEAR));
             assertEquals(currDate.get(Calendar.MONTH),testLog.getDate().get(Calendar.MONTH));
             assertNull(testLog.getEdibleList());
             assertEquals(-1, testLog.getCalorieGoal());
             assertEquals(-1, testLog.getExerciseGoal());
             assertEquals(0, testLog.getExerciseActual());
             assertEquals(0, testLog.getEdibleCalories());
             assertEquals(-1, testLog.getProgressExcess());
             assertEquals(-1, testLog.getProgressBar());
             assertEquals(-1, testLog.getCalorieNet());
         }

         @Test
         @DisplayName("init should take typical values")
         void testInit() {
             Calendar newDate = Calendar.getInstance();
             newDate.set(2021, 12, 1);
             try {
                testLog.init(newDate, edibleList, 1400, 600, 200);
             }
             catch(Exception e) {
                 System.out.println(e);
             }
             assertEquals(newDate.get(Calendar.DAY_OF_YEAR),testLog.getDate().get(Calendar.DAY_OF_YEAR));
             assertEquals(newDate.get(Calendar.YEAR),testLog.getDate().get(Calendar.YEAR));
             assertEquals(newDate.get(Calendar.MONTH),testLog.getDate().get(Calendar.MONTH));
             assertEquals(edibleList, testLog.getEdibleList());
             assertEquals(1400, testLog.getCalorieGoal());
             assertEquals(600, testLog.getExerciseGoal());
             assertEquals(200, testLog.getExerciseActual());
             assertEquals(900, testLog.getEdibleCalories());
             assertEquals(0, testLog.getProgressExcess());
             assertEquals(50, testLog.getProgressBar());
             assertEquals(700, testLog.getCalorieNet());
         }
         @Nested
         @DisplayName("Simple tests")
         class DailyLogSimple {

             @BeforeEach
             void setup() {
                 testLog.init(currDate, edibleList, 1400, 600, 200);
             }

             @Test
             @DisplayName("Edible List should be replaceable")
             void testEdibleList() {
                 ArrayList<Edible> newList = new ArrayList<Edible>();
                 try {
                     newList.add( new EdibleLog(
                             new Edible()
                                     .initDetails(7, "Rabbit", "desc", 40, Edible.Unit.tbsp)
                                     .initNutrition(400, 30, 20, 50)
                                     .initCategories(false, false, false, false, false)
                                     .initMetadata(false, null)
                     ).init(40, Edible.Unit.tbsp));
                     newList.add( new EdibleLog(
                             new Edible()
                                     .initDetails(6, "Carrots", "desc", 30, Edible.Unit.g)
                                     .initNutrition(300, 40, 50, 10)
                                     .initCategories(false, false, true, true, false)
                                     .initMetadata(false, null)
                     ).init(30, Edible.Unit.g));
                     newList.add( new EdibleLog(
                             new Edible()
                                     .initDetails(5, "Chicken", "desc", 20, Edible.Unit.tsp)
                                     .initNutrition(200, 25, 40, 35)
                                     .initCategories(false, false, false, false, true)
                                     .initMetadata(false, null)
                     ).init(20, Edible.Unit.tsp));
                     newList.add( new EdibleLog(
                             new Edible()
                                     .initDetails(7, "Rabbit", "desc", 40, Edible.Unit.tbsp)
                                     .initNutrition(400, 30, 20, 50)
                                     .initCategories(false, false, false, false, false)
                                     .initMetadata(false, null)
                     ).init(40, Edible.Unit.tbsp));
                 }
                 catch(Exception e) {
                     System.out.println(e);
                 }

                 testLog.setEdibleList(newList);
                 assertEquals(newList, testLog.getEdibleList());

             }

             @Test
             @DisplayName("Calorie goal can be set typical values")
             void testCalorieGoal() {
                testLog.setCalorieGoal(2000);
                assertEquals(2000, testLog.getCalorieGoal());
             }

             @Test
             @DisplayName("Exercise goal can be set typical values")
             void testExerciseGoal() {
                 testLog.setExerciseGoal(2000);
                 assertEquals(2000, testLog.getExerciseGoal());
             }

             @Test
             @DisplayName("Exercise actual can be set typical values")
             void testExerciseActual() {
                 testLog.setExerciseActual(200);
                 assertEquals(200, testLog.getExerciseActual());
             }

             @Test
             @DisplayName("We can add a new edible to the list")
             void testAddEdible() {
                 Edible newEdible = null;
                 int prevListSize = testLog.getEdibleList().size();
                 try {
                     newEdible = new Edible()
                             .initDetails(7, "Rabbit", "desc", 40.0, Edible.Unit.tbsp)
                             .initNutrition(400, 30, 20, 50)
                             .initCategories(false, false, false, false, false)
                             .initMetadata(false, null);
                 }
                 catch(Exception e) {
                     System.out.println(e);
                 }
                 testLog.addEdibleToLog(newEdible);
                 assertEquals(prevListSize + 1, testLog.getEdibleList().size());
             }
         }

         @Nested
         @DisplayName("Progress calculation tests")
         class DailyLogProgress {

             @BeforeEach
             void setup() {
                 testLog.init(currDate, edibleList, 1400, 600, 200);
             }

             @Test
             @DisplayName("Edible List should be replaceable")
             void testEdibleList() {
                 ArrayList<Edible> newList = new ArrayList<Edible>();
                 try {
                     newList.add( new EdibleLog(
                             new Edible()
                                     .initDetails(7, "Rabbit", "desc", 40, Edible.Unit.tbsp)
                                     .initNutrition(400, 30, 20, 50)
                                     .initCategories(false, false, false, false, false)
                                     .initMetadata(false, null)
                     ).init(40, Edible.Unit.tbsp));
                     newList.add( new EdibleLog(
                             new Edible()
                                     .initDetails(6, "Carrots", "desc", 30, Edible.Unit.g)
                                     .initNutrition(300, 40, 50, 10)
                                     .initCategories(false, false, true, true, false)
                                     .initMetadata(false, null)
                     ).init(30, Edible.Unit.g));
                     newList.add( new EdibleLog(
                             new Edible()
                                     .initDetails(5, "Chicken", "desc", 20, Edible.Unit.tsp)
                                     .initNutrition(200, 25, 40, 35)
                                     .initCategories(false, false, false, false, true)
                                     .initMetadata(false, null)
                     ).init(20, Edible.Unit.tsp));
                     newList.add( new EdibleLog(
                             new Edible()
                                     .initDetails(7, "Rabbit", "desc", 40, Edible.Unit.tbsp)
                                     .initNutrition(400, 30, 20, 50)
                                     .initCategories(false, false, false, false, false)
                                     .initMetadata(false, null)
                     ).init(40, Edible.Unit.tbsp));
                 }
                 catch(Exception e) {
                     System.out.println(e);
                 }

                 testLog.setEdibleList(newList);
                 assertEquals(newList, testLog.getEdibleList());

             }

             @Test
             @DisplayName("Setting Calorie goal typical values results in correct progress calculation")
             void testCalorieGoal() {
                 testLog.setCalorieGoal(2800);
                 assertEquals(2800, testLog.getCalorieGoal());
                 assertEquals(600, testLog.getExerciseGoal());
                 assertEquals(200, testLog.getExerciseActual());
                 assertEquals(900, testLog.getEdibleCalories());
                 assertEquals(0, testLog.getProgressExcess());
                 assertEquals(25, testLog.getProgressBar());
                 assertEquals(2100, testLog.getCalorieNet());
             }

             @Test
             @DisplayName("Setting Exercise goal typical values results in correct progress calculation")
             void testExerciseGoal() {
                 testLog.setExerciseGoal(2000);
                 assertEquals(1400, testLog.getCalorieGoal());
                 assertEquals(2000, testLog.getExerciseGoal());
                 assertEquals(200, testLog.getExerciseActual());
                 assertEquals(900, testLog.getEdibleCalories());
                 assertEquals(0, testLog.getProgressExcess());
                 assertEquals(50, testLog.getProgressBar());
                 assertEquals(700, testLog.getCalorieNet());
             }

             @Test
             @DisplayName("Setting Exercise actual typical values results in correct progress calculation")
             void testExerciseActual() {
                 testLog.setExerciseActual(550);
                 assertEquals(1400, testLog.getCalorieGoal());
                 assertEquals(600, testLog.getExerciseGoal());
                 assertEquals(550, testLog.getExerciseActual());
                 assertEquals(900, testLog.getEdibleCalories());
                 assertEquals(0, testLog.getProgressExcess());
                 assertEquals(25, testLog.getProgressBar());
                 assertEquals(1050, testLog.getCalorieNet());
             }

             @Test
             @DisplayName("Adding new edible to the list results in correct progress calculation")
             void testAddEdible() {
                 Edible newEdible = null;
                 try {
                     newEdible = new Edible()
                             .initDetails(7, "Rabbit", "desc", 40.0, Edible.Unit.tbsp)
                             .initNutrition(350, 30, 20, 50)
                             .initCategories(false, false, false, false, false)
                             .initMetadata(false, null);
                 }
                 catch(Exception e) {
                     System.out.println(e);
                 }
                 testLog.addEdibleToLog(newEdible);
                 assertEquals(1400, testLog.getCalorieGoal());
                 assertEquals(600, testLog.getExerciseGoal());
                 assertEquals(200, testLog.getExerciseActual());
                 assertEquals(1250, testLog.getEdibleCalories());
                 assertEquals(0, testLog.getProgressExcess());
                 assertEquals(75, testLog.getProgressBar());
                 assertEquals(350, testLog.getCalorieNet());
             }

             @Test
             @DisplayName("Removing edible from the list results in correct progress calculation")
             void testRemoveEdible() {
                 Edible newEdible = null;
                 int prevListSize = testLog.getEdibleList().size();
                 try {
                     newEdible = new Edible()
                             .initDetails(7, "Rabbit", "desc", 40.0, Edible.Unit.tbsp)
                             .initNutrition(350, 30, 20, 50)
                             .initCategories(false, false, false, false, false)
                             .initMetadata(false, null);
                 }
                 catch(Exception e) {
                     System.out.println(e);
                 }
                 testLog.addEdibleToLog(newEdible);
                 testLog.getEdibleList().remove(prevListSize);
                 testLog.updateProgress();
                 assertEquals(1400, testLog.getCalorieGoal());
                 assertEquals(600, testLog.getExerciseGoal());
                 assertEquals(200, testLog.getExerciseActual());
                 assertEquals(900, testLog.getEdibleCalories());
                 assertEquals(0, testLog.getProgressExcess());
                 assertEquals(50, testLog.getProgressBar());
                 assertEquals(700, testLog.getCalorieNet());
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
                                 .initMetadata(false, null)
                 ).init(40, Edible.Unit.tbsp));
                 edibleList.add(new EdibleLog(
                         new Edible()
                                 .initDetails(6, "Carrots", "desc", 30, Edible.Unit.g)
                                 .initNutrition(300, 40, 50, 10)
                                 .initCategories(false, false, true, true, false)
                                 .initMetadata(false, null)
                 ).init(30, Edible.Unit.g));
                 edibleList.add(new EdibleLog(
                         new Edible()
                                 .initDetails(5, "Chicken", "desc", 20, Edible.Unit.tsp)
                                 .initNutrition(200, 25, 40, 35)
                                 .initCategories(false, false, false, false, true)
                                 .initMetadata(false, null)
                 ).init(20, Edible.Unit.tsp));

             } catch (Exception e) {
                 System.out.println(e);
             }
         }

         @Nested
         @DisplayName("Edge case tests empty list")
         class DailyLogEdgeZeroList {
             private DailyLog testLog;
             private Calendar currDate;
             private ArrayList<Edible> edibleList;

             @BeforeEach
             void setup() {
                 testLog = new DailyLog();
                 currDate = Calendar.getInstance();
                 edibleList = new ArrayList<Edible>();
             }

             @Test
             @DisplayName("init should accept empty edible list")
             void testInitZeroList() {
                 try {
                     testLog.init(currDate, edibleList, 1400, 600, 0);
                 } catch (Exception e) {
                     System.out.println(e);
                 }
                 assertEquals(currDate.get(Calendar.DAY_OF_YEAR), testLog.getDate().get(Calendar.DAY_OF_YEAR));
                 assertEquals(currDate.get(Calendar.YEAR), testLog.getDate().get(Calendar.YEAR));
                 assertEquals(currDate.get(Calendar.MONTH), testLog.getDate().get(Calendar.MONTH));
                 assertEquals(edibleList, testLog.getEdibleList());
                 assertEquals(1400, testLog.getCalorieGoal());
                 assertEquals(600, testLog.getExerciseGoal());
                 assertEquals(0, testLog.getExerciseActual());
                 assertEquals(0, testLog.getEdibleCalories());
                 assertEquals(0, testLog.getProgressExcess());
                 assertEquals(0, testLog.getProgressBar());
                 assertEquals(1400, testLog.getCalorieNet());
             }

             @Test
             @DisplayName("init with empty edible list and positive exercise results in 0 progress")
             void testInitZeroListZeroExercise() {
                 try {
                     testLog.init(currDate, edibleList, 1400, 600, 200);
                 } catch (Exception e) {
                     System.out.println(e);
                 }
                 assertEquals(currDate.get(Calendar.DAY_OF_YEAR), testLog.getDate().get(Calendar.DAY_OF_YEAR));
                 assertEquals(currDate.get(Calendar.YEAR), testLog.getDate().get(Calendar.YEAR));
                 assertEquals(currDate.get(Calendar.MONTH), testLog.getDate().get(Calendar.MONTH));
                 assertEquals(edibleList, testLog.getEdibleList());
                 assertEquals(1400, testLog.getCalorieGoal());
                 assertEquals(600, testLog.getExerciseGoal());
                 assertEquals(200, testLog.getExerciseActual());
                 assertEquals(0, testLog.getEdibleCalories());
                 assertEquals(0, testLog.getProgressExcess());
                 assertEquals(0, testLog.getProgressBar());
                 assertEquals(1600, testLog.getCalorieNet());
             }

             @Test
             @DisplayName("init with empty edible list and one edible results in correct progress")
             void testInitZeroListAddOneEdible() {
                 ArrayList<Edible> edibleList = new ArrayList<Edible>();
                 Edible newEdible = null;

                 try {
                     testLog.init(currDate, edibleList, 1400, 600, 200);
                 } catch (Exception e) {
                     System.out.println(e);
                 }
                 try {
                     newEdible = new Edible()
                             .initDetails(7, "Rabbit", "desc", 40.0, Edible.Unit.tbsp)
                             .initNutrition(200, 30, 20, 50)
                             .initCategories(false, false, false, false, false)
                             .initMetadata(false, null);
                 }
                 catch(Exception e) {
                     System.out.println(e);
                 }
                 testLog.addEdibleToLog(newEdible);
                 assertEquals(currDate.get(Calendar.DAY_OF_YEAR), testLog.getDate().get(Calendar.DAY_OF_YEAR));
                 assertEquals(currDate.get(Calendar.YEAR), testLog.getDate().get(Calendar.YEAR));
                 assertEquals(currDate.get(Calendar.MONTH), testLog.getDate().get(Calendar.MONTH));
                 assertEquals(edibleList, testLog.getEdibleList());
                 assertEquals(1400, testLog.getCalorieGoal());
                 assertEquals(600, testLog.getExerciseGoal());
                 assertEquals(200, testLog.getExerciseActual());
                 assertEquals(200, testLog.getEdibleCalories());
                 assertEquals(0, testLog.getProgressExcess());
                 assertEquals(0, testLog.getProgressBar());
                 assertEquals(1400, testLog.getCalorieNet());
             }

             @Test
             @DisplayName("removing the last edible in list results in correct progress")
             void testRemoveLastEdible() {
                 ArrayList<Edible> edibleList = new ArrayList<Edible>();
                 Edible newEdible = null;

                 try {
                     testLog.init(currDate, edibleList, 1400, 600, 200);
                 } catch (Exception e) {
                     System.out.println(e);
                 }
                 try {
                     newEdible = new Edible()
                             .initDetails(7, "Rabbit", "desc", 40.0, Edible.Unit.tbsp)
                             .initNutrition(200, 30, 20, 50)
                             .initCategories(false, false, false, false, false)
                             .initMetadata(false, null);
                 }
                 catch(Exception e) {
                     System.out.println(e);
                 }
                 testLog.addEdibleToLog(newEdible);
                 testLog.getEdibleList().remove(0);
                 testLog.updateProgress();
                 assertEquals(currDate.get(Calendar.DAY_OF_YEAR), testLog.getDate().get(Calendar.DAY_OF_YEAR));
                 assertEquals(currDate.get(Calendar.YEAR), testLog.getDate().get(Calendar.YEAR));
                 assertEquals(currDate.get(Calendar.MONTH), testLog.getDate().get(Calendar.MONTH));
                 assertEquals(edibleList, testLog.getEdibleList());
                 assertEquals(1400, testLog.getCalorieGoal());
                 assertEquals(600, testLog.getExerciseGoal());
                 assertEquals(200, testLog.getExerciseActual());
                 assertEquals(0, testLog.getEdibleCalories());
                 assertEquals(0, testLog.getProgressExcess());
                 assertEquals(0, testLog.getProgressBar());
                 assertEquals(1600, testLog.getCalorieNet());
             }
         }

         @Nested
         @DisplayName("Edge case tests set zero")
         class DailyLogEdgeZeroGoals {
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
                                     .initMetadata(false, null)
                     ).init(40, Edible.Unit.tbsp));
                     edibleList.add(new EdibleLog(
                             new Edible()
                                     .initDetails(6, "Carrots", "desc", 30, Edible.Unit.g)
                                     .initNutrition(300, 40, 50, 10)
                                     .initCategories(false, false, true, true, false)
                                     .initMetadata(false, null)
                     ).init(30, Edible.Unit.g));
                     edibleList.add(new EdibleLog(
                             new Edible()
                                     .initDetails(5, "Chicken", "desc", 20, Edible.Unit.tsp)
                                     .initNutrition(200, 25, 40, 35)
                                     .initCategories(false, false, false, false, true)
                                     .initMetadata(false, null)
                     ).init(20, Edible.Unit.tsp));

                 } catch (Exception e) {
                     System.out.println(e);
                 }
             }

             @Test
             @DisplayName("we can set calorie goal to zero and get correct progress")
             void testInitCalorieGoalZero() {
                 Calendar newDate = Calendar.getInstance();
                 newDate.set(2021, 12, 1);
                 try {
                     testLog.init(newDate, edibleList, 0, 600, 200);
                 }
                 catch(Exception e) {
                     System.out.println(e);
                 }
                 assertEquals(newDate.get(Calendar.DAY_OF_YEAR),testLog.getDate().get(Calendar.DAY_OF_YEAR));
                 assertEquals(newDate.get(Calendar.YEAR),testLog.getDate().get(Calendar.YEAR));
                 assertEquals(newDate.get(Calendar.MONTH),testLog.getDate().get(Calendar.MONTH));
                 assertEquals(0, testLog.getCalorieGoal());
                 assertEquals(600, testLog.getExerciseGoal());
                 assertEquals(200, testLog.getExerciseActual());
                 assertEquals(900, testLog.getEdibleCalories());
                 assertEquals(100, testLog.getProgressExcess());
                 assertEquals(100, testLog.getProgressBar());
                 assertEquals(-700, testLog.getCalorieNet());
             }

             @Test
             @DisplayName("we can set exercise goal to zero and get correct progress")
             void testInitExerciseGoalZero() {
                 Calendar newDate = Calendar.getInstance();
                 newDate.set(2021, 12, 1);
                 try {
                     testLog.init(newDate, edibleList, 1400, 0, 200);
                 }
                 catch(Exception e) {
                     System.out.println(e);
                 }
                 assertEquals(newDate.get(Calendar.DAY_OF_YEAR),testLog.getDate().get(Calendar.DAY_OF_YEAR));
                 assertEquals(newDate.get(Calendar.YEAR),testLog.getDate().get(Calendar.YEAR));
                 assertEquals(newDate.get(Calendar.MONTH),testLog.getDate().get(Calendar.MONTH));
                 assertEquals(1400, testLog.getCalorieGoal());
                 assertEquals(0, testLog.getExerciseGoal());
                 assertEquals(200, testLog.getExerciseActual());
                 assertEquals(900, testLog.getEdibleCalories());
                 assertEquals(0, testLog.getProgressExcess());
                 assertEquals(50, testLog.getProgressBar());
                 assertEquals(700, testLog.getCalorieNet());
             }

             @Test
             @DisplayName("we can set exercise goal to zero and get correct progress")
             void testInitExerciseActualZero() {
                 Calendar newDate = Calendar.getInstance();
                 newDate.set(2021, 12, 1);
                 try {
                     testLog.init(newDate, edibleList, 1800, 600, 0);
                 }
                 catch(Exception e) {
                     System.out.println(e);
                 }
                 assertEquals(newDate.get(Calendar.DAY_OF_YEAR),testLog.getDate().get(Calendar.DAY_OF_YEAR));
                 assertEquals(newDate.get(Calendar.YEAR),testLog.getDate().get(Calendar.YEAR));
                 assertEquals(newDate.get(Calendar.MONTH),testLog.getDate().get(Calendar.MONTH));
                 assertEquals(1800, testLog.getCalorieGoal());
                 assertEquals(600, testLog.getExerciseGoal());
                 assertEquals(0, testLog.getExerciseActual());
                 assertEquals(900, testLog.getEdibleCalories());
                 assertEquals(0, testLog.getProgressExcess());
                 assertEquals(50, testLog.getProgressBar());
                 assertEquals(900, testLog.getCalorieNet());
             }

             @Test
             @DisplayName("we can set everything to zero and get correct progress")
             void testInitSetEverythingZero() {
                 ArrayList<Edible> edibleList = new ArrayList<Edible>();
                 Calendar newDate = Calendar.getInstance();
                 newDate.set(2021, 12, 1);
                 try {
                     testLog.init(newDate, edibleList, 0, 0, 0);
                 }
                 catch(Exception e) {
                     System.out.println(e);
                 }
                 assertEquals(newDate.get(Calendar.DAY_OF_YEAR),testLog.getDate().get(Calendar.DAY_OF_YEAR));
                 assertEquals(newDate.get(Calendar.YEAR),testLog.getDate().get(Calendar.YEAR));
                 assertEquals(newDate.get(Calendar.MONTH),testLog.getDate().get(Calendar.MONTH));
                 assertEquals(0, testLog.getCalorieGoal());
                 assertEquals(0, testLog.getExerciseGoal());
                 assertEquals(0, testLog.getExerciseActual());
                 assertEquals(0, testLog.getEdibleCalories());
                 assertEquals(100, testLog.getProgressExcess());
                 assertEquals(100, testLog.getProgressBar());
                 assertEquals(0, testLog.getCalorieNet());
             }

             private void setTestLog(){
                 try {
                     testLog.init(currDate, edibleList, 1400, 600, 200);
                 }
                 catch(Exception e) {
                     System.out.println(e);
                 }
             }

             @Test
             @DisplayName("setDate should take typical values")
             void testCalorieGoalZero() {
                 setTestLog();
                 testLog.setCalorieGoal(0);
                 assertEquals(currDate.get(Calendar.DAY_OF_YEAR),testLog.getDate().get(Calendar.DAY_OF_YEAR));
                 assertEquals(currDate.get(Calendar.YEAR),testLog.getDate().get(Calendar.YEAR));
                 assertEquals(currDate.get(Calendar.MONTH),testLog.getDate().get(Calendar.MONTH));
                 assertEquals(0, testLog.getCalorieGoal());
                 assertEquals(600, testLog.getExerciseGoal());
                 assertEquals(200, testLog.getExerciseActual());
                 assertEquals(900, testLog.getEdibleCalories());
                 assertEquals(100, testLog.getProgressExcess());
                 assertEquals(100, testLog.getProgressBar());
                 assertEquals(-700, testLog.getCalorieNet());
             }

             @Test
             @DisplayName("setDate should take typical values")
             void testExerciseGoalZero() {
                 setTestLog();
                 testLog.setExerciseGoal(0);
                 assertEquals(currDate.get(Calendar.DAY_OF_YEAR),testLog.getDate().get(Calendar.DAY_OF_YEAR));
                 assertEquals(currDate.get(Calendar.YEAR),testLog.getDate().get(Calendar.YEAR));
                 assertEquals(currDate.get(Calendar.MONTH),testLog.getDate().get(Calendar.MONTH));
                 assertEquals(1400, testLog.getCalorieGoal());
                 assertEquals(0, testLog.getExerciseGoal());
                 assertEquals(200, testLog.getExerciseActual());
                 assertEquals(900, testLog.getEdibleCalories());
                 assertEquals(0, testLog.getProgressExcess());
                 assertEquals(50, testLog.getProgressBar());
                 assertEquals(700, testLog.getCalorieNet());
             }

             @Test
             @DisplayName("setDate should take typical values")
             void testExerciseActualZero() {
                 setTestLog();
                 testLog.setExerciseActual(0);
                 assertEquals(currDate.get(Calendar.DAY_OF_YEAR),testLog.getDate().get(Calendar.DAY_OF_YEAR));
                 assertEquals(currDate.get(Calendar.YEAR),testLog.getDate().get(Calendar.YEAR));
                 assertEquals(currDate.get(Calendar.MONTH),testLog.getDate().get(Calendar.MONTH));
                 assertEquals(1400, testLog.getCalorieGoal());
                 assertEquals(600, testLog.getExerciseGoal());
                 assertEquals(0, testLog.getExerciseActual());
                 assertEquals(900, testLog.getEdibleCalories());
                 assertEquals(0, testLog.getProgressExcess());
                 assertEquals(64, (int) testLog.getProgressBar());
                 assertEquals(500, testLog.getCalorieNet());

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
             }

             void seedEdibleList() {
                 try {
                     edibleList.add(new EdibleLog(
                             new Edible()
                                     .initDetails(7, "Rabbit", "desc", 40, Edible.Unit.tbsp)
                                     .initNutrition(400, 30, 20, 50)
                                     .initCategories(false, false, false, false, false)
                                     .initMetadata(false, null)
                     ).init(40, Edible.Unit.tbsp));
                     edibleList.add(new EdibleLog(
                             new Edible()
                                     .initDetails(6, "Carrots", "desc", 30, Edible.Unit.g)
                                     .initNutrition(300, 40, 50, 10)
                                     .initCategories(false, false, true, true, false)
                                     .initMetadata(false, null)
                     ).init(30, Edible.Unit.g));
                     edibleList.add(new EdibleLog(
                             new Edible()
                                     .initDetails(5, "Chicken", "desc", 20, Edible.Unit.tsp)
                                     .initNutrition(200, 25, 40, 35)
                                     .initCategories(false, false, false, false, true)
                                     .initMetadata(false, null)
                     ).init(20, Edible.Unit.tsp));

                 } catch (Exception e) {
                     System.out.println(e);
                 }
             }
         }

         @Test
         @DisplayName("setDate should take typical values")
         void testEdibleList() {

         }

         @Test
         @DisplayName("setDate should take typical values")
         void testCalorieGoal() {

         }

         @Test
         @DisplayName("setDate should take typical values")
         void testExerciseGoal() {

         }

         @Test
         @DisplayName("setDate should take typical values")
         void testExerciseActual() {

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
                                 .initMetadata(false, null)
                 ).init(40, Edible.Unit.tbsp));
                 edibleList.add(new EdibleLog(
                         new Edible()
                                 .initDetails(6, "Carrots", "desc", 30, Edible.Unit.g)
                                 .initNutrition(300, 40, 50, 10)
                                 .initCategories(false, false, true, true, false)
                                 .initMetadata(false, null)
                 ).init(30, Edible.Unit.g));
                 edibleList.add(new EdibleLog(
                         new Edible()
                                 .initDetails(5, "Chicken", "desc", 20, Edible.Unit.tsp)
                                 .initNutrition(200, 25, 40, 35)
                                 .initCategories(false, false, false, false, true)
                                 .initMetadata(false, null)
                 ).init(20, Edible.Unit.tsp));

             } catch (Exception e) {
                 System.out.println(e);
             }
         }

         @Test
         @DisplayName("object state at construction")
         void testDefaultValues() {
             assertEquals(currDate.get(Calendar.DAY_OF_YEAR), testLog.getDate().get(Calendar.DAY_OF_YEAR));
             assertEquals(currDate.get(Calendar.YEAR), testLog.getDate().get(Calendar.YEAR));
             assertEquals(currDate.get(Calendar.MONTH), testLog.getDate().get(Calendar.MONTH));
             assertNull(testLog.getEdibleList());
             assertEquals(-1, testLog.getCalorieGoal());
             assertEquals(-1, testLog.getExerciseGoal());
             assertEquals(0, testLog.getExerciseActual());
             assertEquals(0, testLog.getEdibleCalories());
             assertEquals(-1, testLog.getProgressExcess());
             assertEquals(-1, testLog.getProgressBar());
             assertEquals(-1, testLog.getCalorieNet());
         }

         @Test
         @DisplayName("init should take typical values")
         void testInit() {
             Calendar newDate = Calendar.getInstance();
             newDate.set(2021, 12, 1);
             try {
                 testLog.init(newDate, edibleList, 1400, 600, 200);
             } catch (Exception e) {
                 System.out.println(e);
             }
             assertEquals(newDate.get(Calendar.DAY_OF_YEAR), testLog.getDate().get(Calendar.DAY_OF_YEAR));
             assertEquals(newDate.get(Calendar.YEAR), testLog.getDate().get(Calendar.YEAR));
             assertEquals(newDate.get(Calendar.MONTH), testLog.getDate().get(Calendar.MONTH));
             assertEquals(edibleList, testLog.getEdibleList());
             assertEquals(1400, testLog.getCalorieGoal());
             assertEquals(600, testLog.getExerciseGoal());
             assertEquals(200, testLog.getExerciseActual());
             assertEquals(900, testLog.getEdibleCalories());
             assertEquals(0, testLog.getProgressExcess());
             assertEquals(50, testLog.getProgressBar());
             assertEquals(700, testLog.getCalorieNet());
         }

         @Test
         @DisplayName("setDate should take typical values")
         void testSetDate() {

         }

         @Test
         @DisplayName("setDate should take typical values")
         void testEdibleList() {

         }

         @Test
         @DisplayName("setDate should take typical values")
         void testCalorieGoal() {

         }

         @Test
         @DisplayName("setDate should take typical values")
         void testExerciseGoal() {

         }

         @Test
         @DisplayName("setDate should take typical values")
         void testExerciseActual() {

         }
     }
//     @Nested
//     @DisplayName("Complex tests")
//     class Test_Complex {
//         private DailyLog testLog;
//
//         @BeforeEach
//         void setup(){
//             testLog = new DailyLog();
//         }
//
//         @Test
//         void testLogOverriding() {
//             ArrayList<Edible> logs = new ArrayList<Edible>();
//             logs.add(new Drink());
//             logs.add(new Drink());
//
//             assertTrue(testLog.init(5, 100, 50, 1, logs));
//
//             logs = new ArrayList<Edible>();
//             logs.add(new Drink());
//             logs.add(new Drink());
//             logs.add(new Drink());
//
//             assertEquals(testLog.getFoodList().size(), 2);
//
//             assertTrue(testLog.setFoodList(logs));
//             for(int i = 0; i < testLog.getFoodList().size(); i++) {
//                 assertNotNull(testLog.getFoodList().get(i));
//             }
//
//             assertEquals(testLog.getFoodList().size(), 3);
//         }
//
//         @Test
//         void testSetAVarietyOfEdiblesLog() {
//             ArrayList<Edible> logs = new ArrayList<Edible>();
//             logs.add(new Drink());
//             logs.add(new Meal());
//             logs.add(new Food());
//
//             assertTrue(testLog.setFoodList(logs));
//             for(int i = 0; i < testLog.getFoodList().size(); i++) {
//                 assertNotNull(testLog.getFoodList().get(i));
//             }
//
//             assertEquals(testLog.getFoodList().size(), 3);
//         }
//
//         @Test
//         void testLongFoodLog() {
//             ArrayList<Edible> logs = new ArrayList<Edible>();
//             logs.add(new Meal());
//             logs.add(new Meal());
//             logs.add(new Meal());
//             logs.add(new Meal());
//             logs.add(new Meal());
//             logs.add(new Meal());
//             logs.add(new Meal());
//             logs.add(new Meal());
//             logs.add(new Meal());
//
//             assertTrue(testLog.setFoodList(logs));
//             for(int i = 0; i < testLog.getFoodList().size(); i++) {
//                 assertNotNull(testLog.getFoodList().get(i));
//             }
//
//             assertEquals(testLog.getFoodList().size(), 9);
//         }
//
//         @Test
//         void testSetExcerciseActual() {
//             assertTrue(testLog.setExcActual(500));
//             assertTrue(testLog.setExcActual(50));
//             assertTrue(testLog.setExcActual(900));
//         }
//
//         @Test
//         void testSetCalGoal() {
//             assertTrue(testLog.setCalGoal(500));
//             assertTrue(testLog.setCalGoal(50));
//             assertTrue(testLog.setCalGoal(900));
//         }
//
//         @Test
//         void testSetExcerciseGoal() {
//             assertTrue(testLog.setExcGoal(500));
//             assertTrue(testLog.setExcGoal(50));
//             assertTrue(testLog.setExcGoal(900));
//         }
//     }
//     @Nested
//     @DisplayName("Empty tests")
//     class Test_Empty {
//         private DailyLog testLog;
//
//         @BeforeEach
//         void setup() {
//             testLog = new DailyLog();
//         }
//
//         @Test
//         void testLogCreation() {
//             assertFalse(testLog.init(null, 0, 0, 0, null));
//             assertFalse(testLog.init(null, 0, 0, 0, new ArrayList<Edible>()));
//         }
//
//         @Test
//         void testSetFoodList() {
//             assertFalse(testLog.setFoodList(null));
//             assertFalse(testLog.setFoodList(new ArrayList<Edible>()));
//         }
//
//         @Test
//         void testNullInsideFoodList() {
//             ArrayList<Edible> newLog = new ArrayList<Edible>();
//             newLog.add(new Meal());
//             newLog.add(null);
//             newLog.add(new Meal());
//
//             assertFalse(testLog.setFoodList(newLog));
//         }
//
//         @Test
//         void testSetExcerciseActual() {
//             assertTrue(testLog.setExcActual(0));
//         }
//
//         @Test
//         void testSetCalGoal() {
//             assertTrue(testLog.setCalGoal(0));
//         }
//
//         @Test
//         void testSetExcerciseGoal() {
//             assertTrue(testLog.setExcGoal(0));
//         }
//
//     }
//     @Nested
//     @DisplayName("Edge case tests")
//     class Test_EdgeCases {
//         private DailyLog testLog;
//
//         @BeforeEach
//         void setup(){
//             testLog = new DailyLog();
//         }
//
//         @Test
//         void testBasicLogCreation() {
//             ArrayList<Edible> newLog = new ArrayList<Edible>();
//             newLog.add(new Food());
//
//             assertTrue(testLog.init(0, 0, 0, 0, newLog));
//             assertTrue(testLog.init(0, Constant.ENTRY_MAX_VALUE, Constant.ENTRY_MAX_VALUE, Constant.ENTRY_MAX_VALUE, newLog));
//         }
//
//         @Test
//         void testSetExcerciseActual() {
//             assertTrue(testLog.setExcActual(0));
//             assertTrue(testLog.setExcActual(Constant.ENTRY_MAX_VALUE));
//         }
//
//         @Test
//         void testSetCalGoal() {
//             assertTrue(testLog.setCalGoal(0));
//             assertTrue(testLog.setCalGoal(Constant.ENTRY_MAX_VALUE));
//         }
//
//         @Test
//         void testSetExcerciseGoal() {
//             assertTrue(testLog.setExcGoal(0));
//             assertTrue(testLog.setExcGoal(Constant.ENTRY_MAX_VALUE));
//         }
//     }
//
//     @Nested
//     @DisplayName("Invalid tests")
//     class Test_Invalid {
//         private DailyLog testLog;
//
//         @BeforeEach
//         void setup(){
//             testLog = new DailyLog();
//         }
//
//         @Test
//         void testBasicLogCreation() {
//             assertFalse(testLog.init(-1, 20, 20, 20, new ArrayList<Edible>()));
//             assertFalse(testLog.init(null, 20, 20, 20, new ArrayList<Edible>()));
//             assertFalse(testLog.init(20, -1, 20, 20, new ArrayList<Edible>()));
//             assertFalse(testLog.init(20, Constant.ENTRY_MAX_VALUE + 1, 20, 20, new ArrayList<Edible>()));
//             assertFalse(testLog.init(20, 20, -1, 20, new ArrayList<Edible>()));
//             assertFalse(testLog.init(20, 20, Constant.ENTRY_MAX_VALUE + 1, 20, new ArrayList<Edible>()));
//             assertFalse(testLog.init(20, 20, 5, -1, new ArrayList<Edible>()));
//             assertFalse(testLog.init(20, 20, 20, Constant.ENTRY_MAX_VALUE + 1, new ArrayList<Edible>()));
//             assertFalse(testLog.init(-1, Constant.ENTRY_MAX_VALUE + 1, Constant.ENTRY_MAX_VALUE + 1, Constant.ENTRY_MAX_VALUE + 1, null));
//             assertFalse(testLog.init(-1, -1, -1, -1, null));
//         }
//
//         @Test
//         void testSetExcerciseActual() {
//             assertFalse(testLog.setExcActual(-1));
//             assertFalse(testLog.setExcActual(Constant.ENTRY_MAX_VALUE + 1));
//         }
//
//         @Test
//         void testSetCalGoal() {
//             assertFalse(testLog.setCalGoal(-1));
//             assertFalse(testLog.setCalGoal(Constant.ENTRY_MAX_VALUE + 1));
//         }
//
//         @Test
//         void testSetExcerciseGoal() {
//             assertFalse(testLog.setExcGoal(-1));
//             assertFalse(testLog.setExcGoal(Constant.ENTRY_MAX_VALUE + 1));
//         }
//     }
 }
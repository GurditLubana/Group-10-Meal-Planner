 package comp3350.team10.business;

 import static org.junit.jupiter.api.Assertions.*;
 import org.junit.jupiter.api.DisplayName;
 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.Nested;
 import org.junit.jupiter.api.Test;

 import comp3350.team10.objects.DailyLog;
 import comp3350.team10.persistence.*;
 import comp3350.team10.business.MealDiaryOps;
 import comp3350.team10.objects.Edible;

 import java.time.temporal.ChronoUnit;
 import java.util.Calendar;
 import java.util.ArrayList;
 import java.util.NoSuchElementException;

 public class TestMealDiaryOps {

     @Nested
     @DisplayName("Simple Tests")
     class mealDiarySimple {
         private MealDiaryOps ops;
         private Calendar currDate;
         private Calendar testDate;

         @BeforeEach
         void setup() {
             ops = new MealDiaryOps();
             currDate = Calendar.getInstance();
             testDate = Calendar.getInstance();
         }

         @Test
         @DisplayName("object state at construction")
         void objectStateAtConstruction(){
             DailyLog currLog = ops.getCurrLog();

             assertEquals(currDate.get(Calendar.YEAR),currLog.getDate().get(Calendar.YEAR));
             assertEquals(currDate.get(Calendar.MONTH),currLog.getDate().get(Calendar.MONTH));
             assertEquals(currDate.get(Calendar.DATE),currLog.getDate().get(Calendar.DATE));

         }

         @Test
         @DisplayName("prevDate Should Decrement Date by 1 day")
         void prev(){
            ops.prevDate();
            testDate = ops.getCurrLog().getDate();
            assertEquals(currDate.get(Calendar.DAY_OF_YEAR) - 1, testDate.get(Calendar.DAY_OF_YEAR));
         }

         @Test
         @DisplayName("nextDate Should Increment Date by 1 day")
         void next(){
             ops.nextDate();
             testDate = ops.getCurrLog().getDate();
             assertEquals(currDate.get(Calendar.DAY_OF_YEAR) + 1, testDate.get(Calendar.DAY_OF_YEAR));
         }

         @Test
         @DisplayName("prevDate nextDate can return to a previous date")
         void prevnext(){
             ops.prevDate();
             ops.prevDate();
             ops.nextDate();
             ops.nextDate();
             testDate = ops.getCurrLog().getDate();
             assertEquals(currDate.get(Calendar.DAY_OF_YEAR), testDate.get(Calendar.DAY_OF_YEAR));
         }

         @Test
         @DisplayName("prevDate prevDate can return to a previous date")
         void nextprev(){
             ops.nextDate();
             ops.nextDate();
             ops.prevDate();
             ops.prevDate();
             testDate = ops.getCurrLog().getDate();
             assertEquals(currDate.get(Calendar.DAY_OF_YEAR), testDate.get(Calendar.DAY_OF_YEAR));
         }

         @Test
         @DisplayName("we should be able to set a date within 2 years")
         void anyDate(){
             Calendar newDate = Calendar.getInstance();
             newDate.set(2021, 12, 1);

             ops.setListDate(newDate);
             testDate = ops.getCurrLog().getDate();
             assertEquals(newDate.get(Calendar.YEAR),testDate.get(Calendar.YEAR));
             assertEquals(newDate.get(Calendar.MONTH),testDate.get(Calendar.MONTH));
             assertEquals(newDate.get(Calendar.DATE),testDate.get(Calendar.DATE));

             newDate.set(2023, 4, 1);
             ops.setListDate(newDate);
             testDate = ops.getCurrLog().getDate();
             assertEquals(newDate.get(Calendar.YEAR),testDate.get(Calendar.YEAR));
             assertEquals(newDate.get(Calendar.MONTH),testDate.get(Calendar.MONTH));
             assertEquals(newDate.get(Calendar.DATE),testDate.get(Calendar.DATE));
         }

         @Test
         @DisplayName("Switching dates results in updated food log")
         void newDateNewLog(){
             DailyLog currLog = ops.getCurrLog();
             DailyLog newLog = null;

             ops.prevDate();
             newLog = ops.getCurrLog();

             assertNotEquals(currLog,newLog);
         }

         @Test
         @DisplayName("We should be able to add a food item from the db to the DailyLog")
         void addFoodToLog(){
             DailyLog currLog = ops.getCurrLog();

             int prevLogSize = currLog.getEdibleList().size();
             ops.addByKey(2);
             assertEquals(prevLogSize+1,currLog.getEdibleList().size());
         }

         @Test
         @DisplayName("We should be able to commit modified Logs to persistent storage")
         void commitLogToDB(){
             DailyLog currLog = ops.getCurrLog();

             //TODO test logchangedupdatedb
         }

     }

     @Nested
     @DisplayName("Edge case Tests should pass")
     class mealDiaryEdge {
         private MealDiaryOps ops;
         private Calendar currDate;
         private Calendar testDate;

         @BeforeEach
         void setup() {
             ops = new MealDiaryOps();
             currDate = Calendar.getInstance();
             testDate = Calendar.getInstance();
         }

         @Test
         @DisplayName("we should be able to set a date within 2 years inclusive")
         void anyDate(){
             Calendar newDate = Calendar.getInstance();
             newDate.set(currDate.get(Calendar.YEAR) - 2, currDate.get(Calendar.MONTH), currDate.get(Calendar.DATE));

             ops.setListDate(newDate);
             testDate = ops.getCurrLog().getDate();
             assertEquals(newDate.get(Calendar.YEAR),testDate.get(Calendar.YEAR));
             assertEquals(newDate.get(Calendar.MONTH),testDate.get(Calendar.MONTH));
             assertEquals(newDate.get(Calendar.DATE),testDate.get(Calendar.DATE));

             newDate.set(currDate.get(Calendar.YEAR) + 2, currDate.get(Calendar.MONTH), currDate.get(Calendar.DATE));
             ops.setListDate(newDate);
             testDate = ops.getCurrLog().getDate();
             assertEquals(newDate.get(Calendar.YEAR),testDate.get(Calendar.YEAR));
             assertEquals(newDate.get(Calendar.MONTH),testDate.get(Calendar.MONTH));
             assertEquals(newDate.get(Calendar.DATE),testDate.get(Calendar.DATE));
         }

         @Test
         @DisplayName("We should be able to add the first food item in the db to the DailyLog")
         void addFoodToLog(){
             DailyLog currLog = ops.getCurrLog();

             int prevLogSize = currLog.getEdibleList().size();
             ops.addByKey(1);
             assertEquals(prevLogSize+1,currLog.getEdibleList().size());
         }
     }


     @Nested
     @DisplayName("Tests that should fail")
     class mealDiaryFail {
         private MealDiaryOps ops;
         private Calendar currDate;
         private Calendar testDate;

         @BeforeEach
         void setup() {
             ops = new MealDiaryOps();
             currDate = Calendar.getInstance();
             testDate = Calendar.getInstance();
         }

         @Test
         @DisplayName("any date request beyond 2 years of current date")
         void anyDate(){
             Calendar newDate = Calendar.getInstance();
             newDate.set(currDate.get(Calendar.YEAR) - 3, currDate.get(Calendar.MONTH), currDate.get(Calendar.DATE));
             assertThrows(IllegalArgumentException.class, () -> {
                 ops.setListDate(newDate);
             });

             newDate.set(currDate.get(Calendar.YEAR) + 3, currDate.get(Calendar.MONTH), currDate.get(Calendar.DATE));
             assertThrows(IllegalArgumentException.class, () -> {
                 ops.setListDate(newDate);
             });
         }

         @Test
         @DisplayName("requests for dbkeys that do not exist")
         void dbKey(){

             assertThrows(NoSuchElementException.class, () -> {
                 ops.addByKey(-5);
             });


             assertThrows(NoSuchElementException.class, () -> {
                 ops.addByKey(999999999);
             });
         }

     }
 }
//     @Nested
//     @DisplayName("Setting Goals")
//     class goals{
//         DataAccessStub db;
//         MealDiaryOps ops;
//         Calendar currDate;
//
//         @BeforeEach
//         void setup() {
//             SharedDB.start("test");
//             db =SharedDB.getSharedDB();
//             ops = new MealDiaryOps(db);
//             currDate = (Calendar) ops.getListDate().clone();
//         }
//
//         @Test
//         @DisplayName("Can set new calorie goal")
//         void canSetCal() {
//             ops.setCalorieGoal(500);
//             assertEquals(500, ops.getCalorieGoal());
//         }
//
//         @Test
//         @DisplayName("Can input new actual exercise calories")
//         void canSetExcAct() {
//             ops.setCalorieExercise(200);
//             assertEquals(200, ops.getCalorieExercise());
//         }
//
//         @Test
//         @DisplayName("Set new calorie goal causes progress update")
//         void setCalUpdates() {
//             Integer prevProgress = ops.getProgressBar();
//             Integer newProgress = 0;
//             Integer prevGoal = ops.getCalorieGoal();
//
//             if( prevProgress > 0 ) {
//                 if (prevProgress < 99) {
//                     ops.setCalorieGoal(prevGoal + 1000);
//                     newProgress = ops.getProgressBar();
//                 } else {
//                     prevProgress = ops.getProgressExcess();
//                     ops.setCalorieGoal(prevGoal + 1000);
//                     newProgress = ops.getProgressExcess();
//                 }
//                 assertTrue(prevProgress.intValue() > newProgress.intValue());
//             }
//             else {
//                 assertEquals(prevProgress.intValue(), newProgress.intValue());
//             }
//         }
//
//         @Test
//         @DisplayName("Set new actual exercise calories causes progress update")
//         void setExcActUpdates() {
//             Integer prevProgress = ops.getProgressBar();
//             Integer newProgress = 0;
//
//             if( prevProgress > 0 ) {
//                 if (prevProgress < 100) {
//                     ops.setCalorieExercise(1000);
//                     newProgress = ops.getProgressBar();
//                 } else {
//                     prevProgress = ops.getProgressExcess();
//                     ops.setCalorieExercise(1000);
//                     newProgress = ops.getProgressExcess();
//                 }
//                 assertNotEquals(prevProgress.intValue(), newProgress.intValue());
//             }
//             else {
//                 assertEquals(prevProgress.intValue(), newProgress.intValue());
//             }
//
//         }
//     }
//
//     @Nested
//     @DisplayName("Tests that should fail")
//     class testShouldfail{
//         DataAccessStub db;
//         MealDiaryOps ops;
//         Calendar currDate;
//
//         @BeforeEach
//         void setup() {
//             SharedDB.start("test");
//             db =SharedDB.getSharedDB();
//             ops = new MealDiaryOps(db);
//             currDate = (Calendar) ops.getListDate().clone();
//         }
//
//         @Test
//         @DisplayName("Dates that more than 2 years older than current date")
//         void testBadDate1(){
//             Calendar badDate = Calendar.getInstance();
//             badDate.set(Calendar.YEAR, badDate.get(Calendar.YEAR) -3);
//             ops.setListDate(badDate);
//             assertEquals(currDate, ops.getListDate());
//         }
//
//         @Test
//         @DisplayName("Date that is zero")
//         void testBadDate2(){
//             Calendar badDate = Calendar.getInstance();
//             badDate.set(Calendar.YEAR, 0);
//             ops.setListDate(badDate);
//             assertEquals(currDate, ops.getListDate());
//         }
//
//         @Test
//         @DisplayName("Calorie goal negative")
//         void canSetCal() {
//             int prevGoal = ops.getCalorieGoal();
//             ops.setCalorieGoal(-5);
//             assertEquals(prevGoal, ops.getCalorieGoal());
//         }
//
//         @Test
//         @DisplayName("Exercise actual negative")
//         void canSetExcAct() {
//             int prevExercise = ops.getCalorieExercise();
//             ops.setCalorieExercise(-5);
//             assertEquals(prevExercise, ops.getCalorieExercise());
//         }
//
//         @Test
//         @DisplayName("Calorie goal 99999")
//         void canSetCal1() {
//             int prevGoal = ops.getCalorieGoal();
//             ops.setCalorieGoal(-5);
//             assertEquals(prevGoal, ops.getCalorieGoal());
//         }
//
//         @Test
//         @DisplayName("Exercise actual 99999")
//         void canSetExcAct1() {
//             int prevExercise = ops.getCalorieExercise();
//             ops.setCalorieExercise(-5);
//             assertEquals(prevExercise, ops.getCalorieExercise());
//         }
//     }

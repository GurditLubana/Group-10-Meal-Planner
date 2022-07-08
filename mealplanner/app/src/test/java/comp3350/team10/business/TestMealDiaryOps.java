 package comp3350.team10.business;

 import static org.junit.jupiter.api.Assertions.*;

 import org.junit.jupiter.api.AfterAll;
 import org.junit.jupiter.api.AfterEach;
 import org.junit.jupiter.api.DisplayName;
 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.Nested;
 import org.junit.jupiter.api.Test;

 import comp3350.team10.application.Main;
 import comp3350.team10.objects.DailyLog;
 import java.util.Calendar;
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
             Main.startUp();
             ops = new MealDiaryOps();
             currDate = Calendar.getInstance();
             testDate = Calendar.getInstance();
         }

         @AfterEach
         void shutdown() {
             Main.shutDown();
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
             ops.addByKey(2, false);
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
             Main.startUp();
             ops = new MealDiaryOps();
             currDate = Calendar.getInstance();
             testDate = Calendar.getInstance();
         }

         @AfterEach
         void shutdown() {
             Main.shutDown();
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
             ops.addByKey(1, false);
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
             Main.startUp();
             ops = new MealDiaryOps();
             currDate = Calendar.getInstance();
             testDate = Calendar.getInstance();
         }

         @AfterEach
         void shutdown() {
             Main.shutDown();
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
                 ops.addByKey(-5, false);
             });


             assertThrows(NoSuchElementException.class, () -> {
                 ops.addByKey(999999999, false);
             });
         }

     }
 }

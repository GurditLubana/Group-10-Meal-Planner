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

public class DailyLogUnitTest {

    @Nested
    @DisplayName("Tests for Daily log object")
    class basicTests{

        DailyLog testLog = new DailyLog();
        ArrayList<Edible> foodlist = new ArrayList<>();
        Food food1 = new Food();
        Food food2 = new Food();
        Food food3 = new Food();
        Drink drink1 = new Drink();
        boolean shouldBeTrue;
        @BeforeEach
        void SetLog(){
            food1.init("pasta", 2, 450, FragmentType.diaryEntry, Unit.liter, 5, 4);
            food2.init("food2", 3, 100, FragmentType.recipe, Unit.ml, 15, 2);
            food3.init("food3", 4, 250, FragmentType.diaryAdd, Unit.g, 4, 1);
            drink1.init("drink1",5,300, FragmentType.diaryEntry,Unit.ml, 15, 3);

            foodlist.add(food1);
            foodlist.add(food2);
            foodlist.add(food3);
            foodlist.add(drink1);
            shouldBeTrue = testLog.init(13,250,7,55,foodlist);
        }

        @Test
        void objectSetup(){
            assertTrue(shouldBeTrue);
        }

        @Test
        void testListGetter(){
            assertTrue(testLog.getFoodList().equals(foodlist));
        }

        @Test
        void testExcActual(){
            assertTrue(testLog.getExcActual() >= Constant.ENTRY_MIN_VALUE);
            assertTrue(testLog.getExcActual() <= Constant.ENTRY_MAX_VALUE);
        }

        @Test
        void testExcGoal(){
            assertTrue(testLog.getExcGoal() >= Constant.ENTRY_MIN_VALUE);
            assertTrue(testLog.getExcGoal() <= Constant.ENTRY_MAX_VALUE);
        }

        @Test
        void testCalGoal(){
            assertTrue(testLog.getCalGoal() >= Constant.ENTRY_MIN_VALUE);
            assertTrue(testLog.getCalGoal() <= Constant.ENTRY_MAX_VALUE);
        }


    }

    @Nested
    @DisplayName("testing the Setters for DailyLog")
    class Testsetter{

        DailyLog testLog2 = new DailyLog();
        ArrayList<Edible> testList = new ArrayList<>();
        ArrayList<Edible> testList2 = new ArrayList<>();
        Food food1 = new Food();
        Food food2 = new Food();
        Food food3 = new Food();
        Drink drink1 = new Drink();
        Drink drink2 = new Drink();
        Food food4 = new Food();
        boolean shouldBeTrue;
        @BeforeEach
        void SetLog(){
            food1.init("pasta", 2, 450, FragmentType.diaryEntry, Unit.liter, 5, 4);
            food2.init("food2", 3, 100, FragmentType.recipe, Unit.ml, 15, 2);
            food3.init("food3", 4, 250, FragmentType.diaryAdd, Unit.g, 4, 1);

            testList.add(food1);
            testList.add(food2);
            testList.add(food3);
            shouldBeTrue = testLog2.init(13,250,100,55,testList);
        }

        @Test
        void testListSetter(){
            drink1.init("daaru",3 ,400, FragmentType.recipe,Unit.liter, 3, 12);
            drink2.init("sharbat", 4, 250, FragmentType.diaryEntry, Unit.ml, 4, 2);
            food4.init("Dosa", 2, 100, FragmentType.diaryAdd, Unit.serving, 3, 55);

            testList2.add(drink1);
            testList2.add(drink2);
            testList2.add(food4);

            assertTrue(testLog2.getFoodList().equals(testList));
            testLog2.setFoodList(testList2);
            assertTrue(testLog2.getFoodList().equals(testList2));
        }

        @Test
        void testExcActualSetter(){
            int currExcActual = testLog2.getExcActual();
            int newActual = 301;
            if(testLog2.setExcActual(newActual)) {
                assertTrue(testLog2.getExcActual() >= Constant.ENTRY_MIN_VALUE);
                assertTrue(testLog2.getExcActual() <= Constant.ENTRY_MAX_VALUE);
                assertTrue(testLog2.getExcActual() != currExcActual);
                assertTrue(testLog2.getExcActual() == newActual);
            }
        }

        @Test
        void testCalGoalSetter(){
            int currCalGoal = testLog2.getCalGoal();
            int newGoal = 200;
            if(testLog2.setCalGoal(newGoal)) {
                assertTrue(testLog2.getCalGoal() >= Constant.ENTRY_MIN_VALUE);
                assertTrue(testLog2.getCalGoal() <= Constant.ENTRY_MAX_VALUE);
                assertTrue(testLog2.getCalGoal() != currCalGoal);
                assertTrue(testLog2.getCalGoal() == newGoal);
            }
        }

        @Test
        void testExcGoalSetter(){
            int currExcGoal = testLog2.getExcGoal();
            int newGoal = 2000;
            if(testLog2.setExcGoal(newGoal)){
                assertTrue(testLog2.getExcGoal() >= Constant.ENTRY_MIN_VALUE);
                assertTrue(testLog2.getExcGoal() <= Constant.ENTRY_MAX_VALUE);
                assertTrue(testLog2.getExcGoal() != currExcGoal);
                assertTrue(testLog2.getExcGoal() == newGoal);
            }
        }
        
    }
}

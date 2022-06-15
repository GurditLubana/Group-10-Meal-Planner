package comp3350.team10.objects;

import comp3350.team10.objects.*;
import comp3350.team10.objects.ListItem.FragmentType;
import comp3350.team10.objects.Edible.Unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


//import static org.junit.Assert.*;
//import org.junit.Test;


public class DrinkUnitTest {

    Drink testDrink;

    @Nested
    @DisplayName("Testing Drink Object getters")
    class testDrink{

        @BeforeEach
        void SetDrinks(){
            testDrink = new Drink("Mohito", 2, 250, "Instructions", "ingredients", FragmentType.diaryEntry, Unit.liter, 4, 7);
        }

        @Test
        void testGetters(){
            assertTrue(testDrink.getName() == "Mohito");
            assertTrue(testDrink.getIconPath() == 2);
            assertTrue(testDrink.getCalories() == 250);
            assertTrue(testDrink.getInstructions() == "Instructions");
            assertTrue(testDrink.getIngredients() == "ingredients");
            assertTrue(testDrink.getFragmentType() == FragmentType.diaryEntry);
            assertTrue(testDrink.getBaseUnit() == Unit.liter);
            assertTrue(testDrink.getQuantity() == 4);
            assertTrue(testDrink.getDbkey() == 7);
        }
        @Test
        void DrinkIngNotNull(){
            assertTrue(testDrink.getIngredients() != null);
        }

        @Test
        void DrinkIngNotEmpty(){
            assertTrue(testDrink.getIngredients() != "");
        }

        @Test
        void testIconpath(){
            assertTrue(testDrink.getIconPath() >=0);
            assertTrue(testDrink.getIconPath() <= 9999);
        }

        @Test
        void testInstNotNull(){
            assertTrue(testDrink.getInstructions() != null);
        }

        @Test
        void testInstNotEmpty(){
            assertTrue(testDrink.getInstructions() != "");
        }

        @Test
        void testIngNotNull(){
            assertTrue(testDrink.getIngredients() != null);
        }

        @Test
        void testIngNotEmpty(){
            assertTrue(testDrink.getIngredients() != "");
        }

        @Test
        void TestFragType(){

        }
    }
}
/*
public class DrinkUnitTest {
	@Test
	public void test1() {
		String[] testInstruct = {"1","2","3"};
		String[] testIngredients = {"banana","apple","peach"};
		
		DrinkIngredient testDrinkIngredient0 = new DrinkIngredient("juice", 12.25, "3", true, false);
		DrinkIngredient testDrinkIngredient1 = new DrinkIngredient("apple", 12.5, "2", false, false);
		DrinkIngredient testDrinkIngredient2 = new DrinkIngredient("cake", 5.43, "1", true, true);
		
		DrinkIngredient testDrinkIngredient4 = new DrinkIngredient("apple", 12.5, "2", false, false);
		DrinkIngredient testDrinkIngredient5 = new DrinkIngredient("cake", 5.43, "1", true, true);
		
		DrinkIngredient[] testDrinkIngredientList = new DrinkIngredient[3];
		testDrinkIngredientList[0] = testDrinkIngredient0;
		testDrinkIngredientList[1] = testDrinkIngredient1;
		testDrinkIngredientList[2] = testDrinkIngredient2;
		
		DrinkIngredient[] testDrinkIngredientList1 = new DrinkIngredient[2];
		testDrinkIngredientList1[0] = testDrinkIngredient4;
		testDrinkIngredientList1[1] = testDrinkIngredient5;
		
		
		Drink testDrink0 = new Drink("water", 1, testInstruct,testDrinkIngredientList, FragmentType.diaryEntry, Unit.cups, 2, 5);
		
		
//		assertEquals("water", testDrink0.getName());
//		assertEquals(1, testDrink0.getIconPath());
//		assertEquals(0, testDrink0.getCalories());
		
		DrinkIngredient[] getDrinkIngredientList = testDrink0.getIngredients();
		
		for (int i = 0; i < getDrinkIngredientList.length; i++) {
			assertEquals(testDrinkIngredientList[i].getName(), getDrinkIngredientList[i].getName());
			assertEquals(testDrinkIngredientList[i].isAlcoholic(), getDrinkIngredientList[i].isAlcoholic());
		}
		
		testDrink0.changeIngredients(testDrinkIngredientList1);
		
		getDrinkIngredientList = testDrink0.getIngredients();
		
		for (int i = 0; i < getDrinkIngredientList.length; i++) {
			assertEquals(testDrinkIngredientList1[i].getName(), getDrinkIngredientList[i].getName());
			assertEquals(testDrinkIngredientList1[i].isAlcoholic(), getDrinkIngredientList[i].isAlcoholic());
		}
		
		
//		Drink testDrink1 = new Drink("water", 1, 564,testInstruct,testDrinkIngredientList, FragmentType.diaryEntry, Unit.cups, 2, 5);
		
	}
	

}*/


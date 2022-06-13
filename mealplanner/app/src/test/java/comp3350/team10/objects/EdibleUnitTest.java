package comp3350.team10.objects;
// Edible is a abstract class, test it with child class Drink
import comp3350.team10.objects.*;
import comp3350.team10.objects.ListItem.FragmentType;
import comp3350.team10.objects.Edible.Unit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


//import static org.junit.Assert.*;
//import org.junit.Test;

public class EdibleUnitTest {
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
		
		
		Edible testEdible0 = new Drink("water", 1, testInstruct,testDrinkIngredientList, FragmentType.diaryEntry, Unit.cups, 2, 5);
		
		//test orignal get
		assertEquals("water", testEdible0.getName());
		assertEquals(1, testEdible0.getIconPath());
		assertEquals(250, testEdible0.getCalories());
		testEdible0.modifyCalories(500);
		assertEquals(750, testEdible0.getCalories());
		testEdible0.modifyCalories(1);
		assertEquals(751, testEdible0.getCalories());
		assertEquals(FragmentType.diaryEntry, testEdible0.getFragmentType());
		assertEquals(Unit.cups, testEdible0.getBaseUnit());
		assertEquals(2,	testEdible0.getQuantity());
		assertEquals(5, testEdible0.getDbkey());
		//test orignal get
		
		//test set
		testEdible0.setFragmentType(FragmentType.diaryAdd);
		assertEquals(FragmentType.diaryAdd, testEdible0.getFragmentType());
		
		testEdible0.setCalories(123);
		assertEquals(123, testEdible0.getCalories());
		
		testEdible0.setBaseUnit(Unit.g);
		assertEquals(Unit.g, testEdible0.getBaseUnit());
		
		testEdible0.setQuantity(3);
		assertEquals(3,	testEdible0.getQuantity());
		
		testEdible0.setDbkey(7);
		assertEquals(7,	testEdible0.getDbkey());
		
		testEdible0.setName("hot");
		assertEquals("hot", testEdible0.getName());
		
		testEdible0.setIconPath(2);
		assertEquals(2, testEdible0.getIconPath());
		//test set
		
		
	
		Edible testEdible1 = new Drink("water", 1, 564,testInstruct,testDrinkIngredientList, FragmentType.diaryEntry, Unit.cups, 2, 5);
    	testEdible1.modifyCalories(-100);
    	assertEquals(464, testEdible1.getCalories());
    	testEdible1.modifyCalories(200);
    	assertEquals(664, testEdible1.getCalories());
		
	}
	

}



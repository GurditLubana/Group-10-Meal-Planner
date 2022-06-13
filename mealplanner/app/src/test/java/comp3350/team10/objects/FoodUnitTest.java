package comp3350.team10.objects;
//macro is also test in this class
import comp3350.team10.objects.*;
import comp3350.team10.objects.ListItem.FragmentType;
import comp3350.team10.objects.Edible.Unit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


//import static org.junit.Assert.*;
//import org.junit.Test;

public class FoodUnitTest {
	@Test
	public void test1() {
		String[] testInstruct = {"1","2","3"};
		String[] testIngredients = {"banana","apple","peach"};
		
		Food testFood = new Food("pasta", 2, 450, FragmentType.diaryEntry, Unit.liter, 5, 4);
		assertEquals(450, testFood.getCalories());
		//test macro
		Macros testMacros = testFood.getMacros();
		assertTrue(testMacros.getCarbs() <= 15);
		assertTrue(testMacros.getCarbs() >= 1);
		
		assertTrue(testMacros.getFat() <= 15);
		assertTrue(testMacros.getFat() >= 1);
		
		assertTrue(testMacros.getProtein() <= 15);
		assertTrue(testMacros.getProtein() >= 1);
		//test macro
		
		Food testFood1 = new Food("pasta", 2);
		
		assertTrue(testFood1.getCalories() <= 300);
		assertTrue(testFood1.getCalories() >= 25);
	}
	

}


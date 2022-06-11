package comp3350.team10.objects;

import comp3350.team10.objects.*;
import comp3350.team10.objects.ListItem.FragmentType;
import comp3350.team10.objects.ListItem.Unit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


//import static org.junit.Assert.*;
//import org.junit.Test;

public class DrinkIngredientUnitTest {
	@Test
	public void test1() {
		String[] testInstruct = {"1","2","3"};
		String[] testIngredients = {"banana","apple","peach"};
		
		DrinkIngredient testDrinkIngredient = new DrinkIngredient("juice", 12.25, "3", true, false);
		assertEquals("juice", testDrinkIngredient.getName());
		assertFalse(testDrinkIngredient.isAlcoholic());
		assertTrue(testDrinkIngredient.isReplacement()); 
		
	}
	

}

